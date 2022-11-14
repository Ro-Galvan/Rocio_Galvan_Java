package com.tunes.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorerecommendations.model.TrackRecommendation;
import com.tunes.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrackRecommendationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private TrackRecommendation inputTrackRecommendation;
    private TrackRecommendation inputTrackRecommendation2;
    private TrackRecommendation outputTrackRecommendation;
    private TrackRecommendation outputTrackRecommendation2;

    private List<TrackRecommendation> trackRecommendationList = new ArrayList<>();
    private String trackRecommendationListJson;

    @Before
    public void setUp() throws Exception {
        inputTrackRecommendation = new TrackRecommendation(1,1,true);
        inputTrackRecommendation2 = new TrackRecommendation(2,3,false);
        trackRecommendationList.add(inputTrackRecommendation);
        trackRecommendationList.add(inputTrackRecommendation2);
        trackRecommendationListJson = mapper.writeValueAsString(trackRecommendationList);

        //        outputs + id to mimic postman
        outputTrackRecommendation = new TrackRecommendation(1,1,1,true);
        outputTrackRecommendation2 = new TrackRecommendation(2,2,3,false);

        trackRecommendationList = new ArrayList<>(Arrays.asList(
                outputTrackRecommendation,
                outputTrackRecommendation2
        ));
    }

    //    Create/POST an Track Recommendation
    @Test
    public void shouldCreateNewTrackRecommendationOnPostRequest() throws Exception {
        String inputTrackRecommendationJson = mapper.writeValueAsString(inputTrackRecommendation);
        String outputTrackRecommendationJson = mapper.writeValueAsString(outputTrackRecommendation);

        doReturn(outputTrackRecommendation).when(repository).save(inputTrackRecommendation);

        // Act
        mockMvc.perform(post("/track-recommendation")
                        .content(inputTrackRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackRecommendationJson));
    }

    //    Get track recommendation by id
    @Test
    public void shouldReturnTrackById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTrackRecommendation2);

        doReturn(Optional.of(outputTrackRecommendation2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/track-recommendation/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All track recommendations
    @Test
    public void shouldReturnAllTrackRecommendationsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(trackRecommendationList);

        doReturn(trackRecommendationList).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/track-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }


    //     Update track recommendation
    //    if you don't have a default constructor it will not work!
    @Test
    public void shouldUpdateTrackRecommendationByIdAndReturn204StatusCode() throws Exception {
        inputTrackRecommendation.setTrackId(1);
        inputTrackRecommendation.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputTrackRecommendation);

        doReturn(Optional.of(outputTrackRecommendation)).when(repository).findById(1);

        mockMvc.perform(
                        put("/track-recommendation")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
//                looks like this:[Content-Type:"application/json;charset=UTF-8", Content-Length:"106"]
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }


    //     Delete track recommendation
    @Test
    public void shouldDeleteTrackRecommendationByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/track-recommendation/2")).andExpect(status().isNoContent());
    }

}