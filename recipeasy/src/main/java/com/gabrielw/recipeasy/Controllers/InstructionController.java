package com.gabrielw.recipeasy.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;
import com.gabrielw.recipeasy.Services.InstructionService;


@RestController
@RequestMapping("/api/v0/instructions")
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    @GetMapping
    public List<InstructionComposite> getAllInstructions() {
        return instructionService.getAll();
    }

    @GetMapping("/{key}")
    public ResponseEntity<InstructionComposite> getInstructionById(@PathVariable String key) {
        InstructionComposite instruction = instructionService.get(key);
        if (instruction != null) {
            return ResponseEntity.ok(instruction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public InstructionComposite addInstruction(@RequestBody InstructionComposite instruction) {
        System.err.println(instruction.getValues());
        return instructionService.add(instruction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructionComposite> updateInstruction(@RequestBody InstructionComposite instructionDetails) {
        InstructionComposite updatedInstruction = instructionService.update(instructionDetails);
        if (updatedInstruction != null) {
            return ResponseEntity.ok(updatedInstruction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstruction(@PathVariable String key) {
        int isDeleted = instructionService.delete(key);
        if (isDeleted > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}