package com.example.monthandmath.controller;

import com.example.monthandmath.model.Month;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTest {

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
    public void shouldReturnAllMonthNumAndNameInAYear() throws Exception {
        mockMvc.perform(get("/month"))
                .andDo(print())
                .andExpect(status().isOk())


                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(jsonPath("$[0].number").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty());
    }

    @Test
    public void shouldReturnCorrespondingMonthNumAndNameWhenANumIsCalled() throws Exception {
//        output needed--no input for a get by {id/monthNumber}
        Month outputMonth = new Month();
        outputMonth.setNumber(7);
        outputMonth.setName("July");
        String outputMonthJson = mapper.writeValueAsString(outputMonth);

        mockMvc.perform(get("/month/{monthNumber}", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputMonthJson));
    }

    @Test
    public void shouldReturnARandomMonthObject() throws Exception {
        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk())

//   not in an array anymore so "$[0].number/name not needed
                .andExpect(jsonPath("$.number").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void shouldReturn422ErrorWhenNumIsNotBetween1Or12() throws Exception {
        mockMvc.perform(
                        get("/month/{monthNumber}", 33)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}

