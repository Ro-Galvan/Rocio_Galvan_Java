package com.tunes.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorerecommendations.model.AlbumRecommendation;
import com.tunes.musicstorerecommendations.repository.AlbumRecommendationRepository;
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
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlbumRecommendationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private AlbumRecommendation inputAlbumRecommendation;
    private AlbumRecommendation inputAlbumRecommendation2;
    private AlbumRecommendation outputAlbumRecommendation;
    private AlbumRecommendation outputAlbumRecommendation2;

    private List<AlbumRecommendation> albumRecommendationList = new ArrayList<>();
    private String albumRecommendationListJson;

    @Before
    public void setUp() throws Exception {
        inputAlbumRecommendation = new AlbumRecommendation(1,1,true);
        inputAlbumRecommendation2 = new AlbumRecommendation(2,3,false);
        albumRecommendationList.add(inputAlbumRecommendation);
        albumRecommendationList.add(inputAlbumRecommendation2);
        albumRecommendationListJson = mapper.writeValueAsString(albumRecommendationList);

        //        outputs + id to mimic postman
        outputAlbumRecommendation = new AlbumRecommendation(1,1,1,true);
        outputAlbumRecommendation2 = new AlbumRecommendation(2,2,3,false);

        albumRecommendationList = new ArrayList<>(Arrays.asList(
                outputAlbumRecommendation,
                outputAlbumRecommendation2
        ));
    }

    //    Create/POST an Album Recommendation
    @Test
    public void shouldCreateNewAlbumRecommendationOnPostRequest() throws Exception {
        String inputAlbumRecommendationJson = mapper.writeValueAsString(inputAlbumRecommendation);
        String outputAlbumRecommendationJson = mapper.writeValueAsString(outputAlbumRecommendation);

        doReturn(outputAlbumRecommendation).when(repository).save(inputAlbumRecommendation);

        // Act
        mockMvc.perform(post("/album-recommendation")
                        .content(inputAlbumRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumRecommendationJson));
    }

    //    Get album recommendation by id
    @Test
    public void shouldReturnAlbumById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAlbumRecommendation2);

        doReturn(Optional.of(outputAlbumRecommendation2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/album-recommendation/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All album recommendations
    @Test
    public void shouldReturnAllAlbumRecommendationsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(albumRecommendationList);

        doReturn(albumRecommendationList).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/album-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }


    //     Update album recommendation
    //    if you don't have a default constructor it will not work!
    @Test
    public void shouldUpdateAlbumRecommendationByIdAndReturn204StatusCode() throws Exception {
        inputAlbumRecommendation.setAlbumId(1);
        inputAlbumRecommendation.setLiked(false);
        String inputJson = mapper.writeValueAsString(inputAlbumRecommendation);

        doReturn(Optional.of(outputAlbumRecommendation)).when(repository).findById(1);

        mockMvc.perform(
                        put("/album-recommendation")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
//                looks like this:[Content-Type:"application/json;charset=UTF-8", Content-Length:"106"]
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }


    //     Delete album recommendation
    @Test
    public void shouldDeleteAlbumRecommendationByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/album-recommendation/2")).andExpect(status().isNoContent());
    }

}