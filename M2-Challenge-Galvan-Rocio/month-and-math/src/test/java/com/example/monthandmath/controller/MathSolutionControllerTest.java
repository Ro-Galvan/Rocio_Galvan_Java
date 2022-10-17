package com.example.monthandmath.controller;

import com.example.monthandmath.model.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        // may need to set up some data in here
    }

//    code referenced from record-store activity in class
//            **********MockMVC tests for successful responses**********
    @Test
    public void shouldReturnAdditionSolutionOnPostRequest() throws Exception {
//    input
        MathSolution inputAddition = new MathSolution();
        inputAddition.setOperand1(10);
        inputAddition.setOperand2(5);
        String inputAdditionJson = mapper.writeValueAsString(inputAddition);

//        output
        MathSolution outputAddition = new MathSolution();
        outputAddition.setOperand1(10);
        outputAddition.setOperand2(5);
        outputAddition.setOperation("add");
        outputAddition.setAnswer(15);
        String outputAdditionJson = mapper.writeValueAsString(outputAddition);

        mockMvc.perform(post("/add")
                        .content(inputAdditionJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAdditionJson));
    }

    @Test
    public void shouldReturnSubtractionSolutionOnPostRequest() throws Exception {
//    input
        MathSolution inputSubtraction = new MathSolution();
        inputSubtraction.setOperand1(10);
        inputSubtraction.setOperand2(5);
        String inputSubtractionJson = mapper.writeValueAsString(inputSubtraction);

//        output
        MathSolution outputSubtraction = new MathSolution();
        outputSubtraction.setOperand1(10);
        outputSubtraction.setOperand2(5);
        outputSubtraction.setOperation("subtract");
        outputSubtraction.setAnswer(5);
        String outputSubtractionJson = mapper.writeValueAsString(outputSubtraction);

        mockMvc.perform(post("/subtract")
                        .content(inputSubtractionJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputSubtractionJson));
    }

    @Test
    public void shouldReturnMultiplicationSolutionOnPostRequest() throws Exception {
//    input
        MathSolution inputMultiplication = new MathSolution();
        inputMultiplication.setOperand1(3);
        inputMultiplication.setOperand2(3);
        String inputMultiplicationJson = mapper.writeValueAsString(inputMultiplication);

//        output
        MathSolution outputMultiplication = new MathSolution();
        outputMultiplication.setOperand1(3);
        outputMultiplication.setOperand2(3);
        outputMultiplication.setOperation("multiply");
        outputMultiplication.setAnswer(9);
        String outputMultiplicationJson = mapper.writeValueAsString(outputMultiplication);

        mockMvc.perform(post("/multiply")
                        .content(inputMultiplicationJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMultiplicationJson));
    }

    @Test
    public void shouldReturnDivisionSolutionOnPostRequest() throws Exception {
//    input
        MathSolution inputDivision = new MathSolution();
        inputDivision.setOperand1(50);
        inputDivision.setOperand2(10);
        String inputDivisionJson = mapper.writeValueAsString(inputDivision);

//        output
        MathSolution outputDivision = new MathSolution();
        outputDivision.setOperand1(50);
        outputDivision.setOperand2(10);
        outputDivision.setOperation("divide");
        outputDivision.setAnswer(5);
        String outputDivisionJson = mapper.writeValueAsString(outputDivision);

        mockMvc.perform(post("/divide")
                        .content(inputDivisionJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputDivisionJson));
    }

    //            **********MockMVC tests for invalid requests**********
//    TODO MockMVC test for invalid requests:


}