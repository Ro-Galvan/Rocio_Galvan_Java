package com.tunes.musicstorecatalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.tunes.musicstorecatalog.model.Album;
import com.tunes.musicstorecatalog.repository.AlbumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import from junit and runwith springrunner, class that allows u to run tests on spring application
@RunWith(SpringRunner.class)
//allows us to create instance of controller- then tell it which controller to make an instance of
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    //dependency injection is applied when it finds a MockMvc object that matches this type MockMvc and when test runs:
    @Autowired
    MockMvc mockMvc;
    //    it will find any object that matches MockMvc
//    when tests run it will set the variable mockMvc to the object MockMvc
//    you don't have to call any constructors by having the MockMvc
    @MockBean
    AlbumRepository repository;
    /*    "jackson mapper", will make it easier, in our tests, to turn things from a java object to JavaScript object notation object and back---from java to json and json to java */
//    ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    private Album inputAlbum;
    private Album inputAlbum2;
    private Album outputAlbum;
    private Album outputAlbum2;
    private List<Album> allAlbums = new ArrayList<>();
    private String allAlbumsJson;

    //    runs once before each tests
//    ***** had to add JsonProcessingException to get sllAlbumsJson to return correctly-- later changed to exception to catch all
    @Before
    public void setUp() throws Exception {
//        repository.deleteAll();
        inputAlbum = new Album("21",1, LocalDate.of(2011,12,01),1, new BigDecimal("19.99"));
        inputAlbum2 = new Album("More Life",2, LocalDate.of(2017,03,18),2, new BigDecimal("23.00"));
        allAlbums.add(inputAlbum);
        allAlbums.add(inputAlbum2);
        allAlbumsJson = mapper.writeValueAsString(allAlbums);

        //        outputs + id to mimic postman
        outputAlbum = new Album(1,"21",1, LocalDate.of(2011,12,01),1, new BigDecimal("19.99"));
        outputAlbum2 = new Album(2,"More Life",2, LocalDate.of(2017,03,18),2, new BigDecimal("23.00"));



        allAlbums = new ArrayList<>(Arrays.asList(
                outputAlbum,
                outputAlbum2
        ));
    }
    //    Create/POST an Album
//    if you don't have a default constructor it will not work!
    @Test
    public void shouldCreateNewAlbumOnPostRequest() throws Exception {
        String inputAlbumJson = mapper.writeValueAsString(inputAlbum);
        String outputAlbumJson = mapper.writeValueAsString(outputAlbum);

        doReturn(outputAlbum).when(repository).save(inputAlbum);

        // Act
        mockMvc.perform(post("/album")
                        .content(inputAlbumJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));
    }

    //    Get album by Id
    @Test
    public void shouldReturnAlbumById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAlbum2);

        doReturn(Optional.of(outputAlbum2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/album/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All albums
    @Test
    public void shouldReturnAllAlbumsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allAlbums);

        doReturn(allAlbums).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/album"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //     Update album
    //    if you don't have a default constructor it will not work!
    @Test
    public void shouldUpdateAlbumByIdAndReturn204StatusCode() throws Exception {
        inputAlbum.setAlbumId(1);
        inputAlbum.setTitle("21-The Album");
        String inputJson = mapper.writeValueAsString(inputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repository).findById(1);

        mockMvc.perform(
                        put("/album")
//
                                .content(inputJson) //the string jsonbody
                                // {"albumId":1,"title":"21-The Album","artistId":1,"releaseDate":"2011-12-01","labelId":1,"listPrice":19.99}
                                .contentType(MediaType.APPLICATION_JSON)) ////header needed for every HTTP request
//                looks like this:[Content-Type:"application/json;charset=UTF-8", Content-Length:"106"]
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }


    //     Delete album
    @Test
    public void shouldDeleteAlbumByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/album/3")).andExpect(status().isNoContent());
    }
}