package com.tunes.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorerecommendations.model.LabelRecommendation;
import com.tunes.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabelRecommendationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private LabelRecommendation inputLabelRecommendation;
    private LabelRecommendation inputLabelRecommendation2;
    private LabelRecommendation outputLabelRecommendation;
    private LabelRecommendation outputLabelRecommendation2;

    private List<LabelRecommendation> labelRecommendationList = new ArrayList<>();
    private String labelRecommendationListJson;

    @Before
    public void setUp() throws Exception {
        inputLabelRecommendation = new LabelRecommendation(1,1,true);
        inputLabelRecommendation2 = new LabelRecommendation(2,3,false);
        labelRecommendationList.add(inputLabelRecommendation);
        labelRecommendationList.add(inputLabelRecommendation2);
        labelRecommendationListJson = mapper.writeValueAsString(labelRecommendationList);

        //        outputs + id to mimic postman
        outputLabelRecommendation = new LabelRecommendation(1,1,1,true);
        outputLabelRecommendation2 = new LabelRecommendation(2,2,3,false);

        labelRecommendationList = new ArrayList<>(Arrays.asList(
                outputLabelRecommendation,
                outputLabelRecommendation2
        ));
    }

    //    Create/POST an Label Recommendation
    @Test
    public void shouldCreateNewLabelRecommendationOnPostRequest() throws Exception {
        String inputLabelRecommendationJson = mapper.writeValueAsString(inputLabelRecommendation);
        String outputLabelRecommendationJson = mapper.writeValueAsString(outputLabelRecommendation);

        doReturn(outputLabelRecommendation).when(repository).save(inputLabelRecommendation);

        // Act
        mockMvc.perform(post("/label-recommendation")
                        .content(inputLabelRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelRecommendationJson));
    }

    //    Get label recommendation by id
    @Test
    public void shouldReturnLabelById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputLabelRecommendation2);

        doReturn(Optional.of(outputLabelRecommendation2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/label-recommendation/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All label recommendations
    @Test
    public void shouldReturnAllLabelRecommendationsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(labelRecommendationList);

        doReturn(labelRecommendationList).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/label-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }


    //     Update label recommendation
    //    if you don't have a default constructor it will not work!
    @Test
    public void shouldUpdateLabelRecommendationByIdAndReturn204StatusCode() throws Exception {
        inputLabelRecommendation.setLabelId(1);
        inputLabelRecommendation.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputLabelRecommendation);

        doReturn(Optional.of(outputLabelRecommendation)).when(repository).findById(1);

        mockMvc.perform(
                        put("/label-recommendation")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
//                looks like this:[Content-Type:"application/json;charset=UTF-8", Content-Length:"106"]
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }


    //     Delete label recommendation
    @Test
    public void shouldDeleteLabelRecommendationByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/label-recommendation/2")).andExpect(status().isNoContent());
    }


}