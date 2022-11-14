package com.tunes.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorerecommendations.model.ArtistRecommendation;
import com.tunes.musicstorerecommendations.repository.ArtistRecommendationRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtistRecommendationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private ArtistRecommendation inputArtistRecommendation;
    private ArtistRecommendation inputArtistRecommendation2;
    private ArtistRecommendation outputArtistRecommendation;
    private ArtistRecommendation outputArtistRecommendation2;

    private List<ArtistRecommendation> artistRecommendationList = new ArrayList<>();
    private String artistRecommendationListJson;

    @Before
    public void setUp() throws Exception {
        inputArtistRecommendation = new ArtistRecommendation(1,1,true);
        inputArtistRecommendation2 = new ArtistRecommendation(2,3,false);
        artistRecommendationList.add(inputArtistRecommendation);
        artistRecommendationList.add(inputArtistRecommendation2);
        artistRecommendationListJson = mapper.writeValueAsString(artistRecommendationList);

        //        outputs + id to mimic postman
        outputArtistRecommendation = new ArtistRecommendation(1,1,1,true);
        outputArtistRecommendation2 = new ArtistRecommendation(2,2,3,false);

        artistRecommendationList = new ArrayList<>(Arrays.asList(
                outputArtistRecommendation,
                outputArtistRecommendation2
        ));
    }

    //    Create/POST an Artist Recommendation
    @Test
    public void shouldCreateNewArtistRecommendationOnPostRequest() throws Exception {
        String inputArtistRecommendationJson = mapper.writeValueAsString(inputArtistRecommendation);
        String outputArtistRecommendationJson = mapper.writeValueAsString(outputArtistRecommendation);

        doReturn(outputArtistRecommendation).when(repository).save(inputArtistRecommendation);

        // Act
        mockMvc.perform(post("/artist-recommendation")
                        .content(inputArtistRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistRecommendationJson));
    }

    //    Get artist recommendation by id
    @Test
    public void shouldReturnArtistById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputArtistRecommendation2);

        doReturn(Optional.of(outputArtistRecommendation2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/artist-recommendation/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All artist recommendations
    @Test
    public void shouldReturnAllArtistRecommendationsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(artistRecommendationList);

        doReturn(artistRecommendationList).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/artist-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }


    //     Update artist recommendation
    //    if you don't have a default constructor it will not work!
    @Test
    public void shouldUpdateArtistRecommendationByIdAndReturn204StatusCode() throws Exception {
        inputArtistRecommendation.setArtistId(1);
        inputArtistRecommendation.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputArtistRecommendation);

        doReturn(Optional.of(outputArtistRecommendation)).when(repository).findById(1);

        mockMvc.perform(
                        put("/artist-recommendation")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
//                looks like this:[Content-Type:"application/json;charset=UTF-8", Content-Length:"106"]
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }
}