package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.Objects.Steps.Instruction;
import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;
import com.gabrielw.recipeasy.Objects.Steps.InstructionContainer;

@Component
public class InstructionDAO implements DAO<InstructionComposite> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public InstructionComposite get(String key) {
        String query = "SELECT e.key, e.instructions, e.step_number, e.is_child, ARRAY_AGG(instruction_children.child_id) " +
                    "FROM instructions e " +
                    "LEFT JOIN instruction_children ON e.key = instruction_children.parent_id " +
                    "WHERE e.key = ? " +
                    "GROUP BY e.key ";
        List<InstructionComposite> instructions = jdbcTemplate.query(query, new String[]{key}, (rs, rowNum) -> {
            if (rs.getString("array_agg") != null) {
                InstructionContainer c = new InstructionContainer(rs.getString("key"), rs.getInt("step_number"), rs.getString("instructions"));
                java.sql.Array sqlArray = rs.getArray("array_agg");
                String[] childArray = (String[]) sqlArray.getArray();
                for (String child : childArray) {
                    c.add(get(child));
                }
                return c;
            }
            return new Instruction(rs.getString("key"), rs.getInt("step_number"), rs.getString("instructions"));
        });
        if (instructions.isEmpty()) {
            return null;
        }
        return instructions.get(0);
    }

    @Override
    public List<InstructionComposite> getAll() {
        String query = "SELECT e.key, e.instructions, e.step_number, e.is_child, ARRAY_AGG(instruction_children.child_id) " +
                    "FROM instructions e " +
                    "LEFT JOIN instruction_children ON e.key = instruction_children.parent_id " +
                    "WHERE e.is_child = false " +
                    "GROUP BY e.key ";
        List<InstructionComposite> instructions = jdbcTemplate.query(query, (rs, rowNum) -> {
            if (rs.getString("array_agg") != null) {
                InstructionContainer c = new InstructionContainer(rs.getString("key"), rs.getInt("step_number"), rs.getString("instructions"));
                java.sql.Array sqlArray = rs.getArray("array_agg");
                String[] childArray = (String[]) sqlArray.getArray();
                for (String child : childArray) {
                    c.add(get(child));
                }
                return c;
            }
            return new Instruction(rs.getString("key"), rs.getInt("step_number"), rs.getString("instructions"));
        });
        if (instructions.isEmpty()) {
            return null;
        }
        return instructions;
    }

    private boolean check_child(String key) {
        String sql = "SELECT child_id FROM instruction_children WHERE child_id = ?";
        List<String> parents = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return rs.getString("child_id");
        }, key);
        return !parents.isEmpty();
    }

    @Override
    public void add(InstructionComposite t) {
        boolean is_child = check_child(t.getKey());
        if (t.getValues() != null) {
            for (InstructionComposite child : t.getValues()) {
                add(child, true);
            }
        }
        upsert(t, is_child);
    }

    public void add(InstructionComposite t, boolean is_child) {
        if (t.getValues() != null) {
            for (InstructionComposite child : t.getValues()) {
                add(child, true);
            }
        }
        upsert(t, is_child);
    }

    private void upsert(InstructionComposite t, boolean is_child) {
        String upsert = "INSERT INTO instructions (key, step_number, instructions, is_child) VALUES (?, ?, ?, ?)" +
        "ON CONFLICT (key) DO UPDATE SET step_number = ?, instructions = ?, is_child = ?;";
        jdbcTemplate.update(upsert,t.getKey(), t.getStepNumber(), t.getDescription(), is_child, t.getStepNumber(), t.getDescription(), is_child);
        String deleteRelations = "DELETE FROM instruction_children WHERE parent_id = ?;";
        jdbcTemplate.update(deleteRelations, t.getKey());
        String addRelations = "INSERT INTO instruction_children (parent_id, child_id) VALUES (?, ?);";
        if (t.getValues() != null) {
            for (InstructionComposite child : t.getValues()) {
                jdbcTemplate.update(addRelations, t.getKey(), child.getKey());
            }
        }
    }


    @Override
    public void update(InstructionComposite t) {
        boolean is_child = check_child(t.getKey());
        if (t.getValues() != null) {
            for (InstructionComposite child : t.getValues()) {
                update(child, true);
            }
        }
        upsert(t, is_child);
    }

    public void update(InstructionComposite t, boolean is_child) {
        if (t.getValues() != null) {
            for (InstructionComposite child : t.getValues()) {
                update(child, true);
            }
        }
        upsert(t, is_child);
    }

    @Override
    public int delete(String key) {
        InstructionComposite t = get(key);
        int i = 0;
        i += del(t.getKey(), i);
        for (InstructionComposite child : t.getValues()) {
            i += delete(child, i);
        }
        return i;
    }

    public int delete(InstructionComposite t, int i) {
        i += del(t.getKey(), i);
        for (InstructionComposite child : t.getValues()) {
            i += delete(child, i);
        }
        return i;
    }

    private int del(String key, int i) {
        String delete = "DELETE FROM instructions WHERE key = ?;";
        i += jdbcTemplate.update(delete, key);
        String deleteRelations = "DELETE FROM instruction_children WHERE parent_id = ?;";
        i += jdbcTemplate.update(deleteRelations, key);
        return i;
    }
}