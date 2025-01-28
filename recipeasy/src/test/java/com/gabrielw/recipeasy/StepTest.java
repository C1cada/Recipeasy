// package com.gabrielw.recipeasy;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.gabrielw.recipeasy.Objects.Steps.Instruction;
// import com.gabrielw.recipeasy.Objects.Steps.InstructionContainer;

// @SpringBootTest(classes = {Instruction.class, InstructionContainer.class})
// class StepTest {

//     @Test
//     void testStep() {
//         Instruction step = new Instruction(1, "Test Step");
//         assertEquals(1, step.getStepNumber());
//         assertEquals("Test Step", step.getDescription());
//         assertEquals(".1: Test Step", step.toString());
//         assertEquals(1, step.getValues().size());
//         assertEquals(step, step.getValues().get(0));
//     }

//     @Test
//     void testStepList() {
//         Instruction step1 = new Instruction(1, "Test Step 1");
//         Instruction step2 = new Instruction(2, "Test Step 2");
//         InstructionContainer container = new InstructionContainer(1, "Steps");
//         container.add(step1);
//         container.add(step2);
//         assertEquals("Steps", container.getDescription());
//         assertEquals(1, container.getStepNumber());
//         assertEquals(2, container.getValues().size());
//         assertEquals(step1, container.getValues().get(0));
//         assertEquals(step2, container.getValues().get(1));
//     }

// }