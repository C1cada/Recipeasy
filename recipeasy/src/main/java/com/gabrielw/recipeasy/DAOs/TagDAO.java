/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.Objects.Tag;


@Component
public class TagDAO implements DAO<Tag>{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Tag get(String key) {
        String query = "SELECT t.key, t.name " +
                    "FROM tags t " +
                    "WHERE t.key = ? ";
        List<Tag> tags = jdbcTemplate.query(query, (rs, rowNum) -> {
            return new Tag(rs.getString("key"), rs.getString("name"));
        }, key);
        if (tags.isEmpty()) {
            return null;
        }
        return tags.get(0);
    }

    @Override
    public List<Tag> getAll() {
        String query = "SELECT t.key, t.name" +
                    "FROM tags t ";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            return new Tag(rs.getString("key"), rs.getString("name"));
        });
    }

    private void upsert(Tag t){
        String query = "INSERT INTO tags (key, name) VALUES (?, ?) ON CONFLICT (key) DO UPDATE SET name = ?";
        jdbcTemplate.update(query, t.getKey(), t.getName(), t.getName());
    }

    @Override
    public void add(Tag t) {
        upsert(t);
    }

    @Override
    public void update(Tag t) {
        upsert(t);
    }

    @Override
    public int delete(String key) {
        String query = "DELETE FROM tags WHERE key = ?";
        return jdbcTemplate.update(query, key);
    }
}
