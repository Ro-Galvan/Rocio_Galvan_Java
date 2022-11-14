package com.tunes.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorecatalog.model.Artist;
import com.tunes.musicstorecatalog.repository.ArtistRepository;
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
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtistRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private Artist inputArtist;
    private Artist inputArtist2;
    private Artist outputArtist;
    private Artist outputArtist2;
    private List<Artist> allArtists = new ArrayList<>();
    private String allArtistsJson;

    @Before
    public void setUp() throws Exception {
        inputArtist = new Artist("Adele","@BritishAdele","@Adele");
        inputArtist2 = new Artist("Drake","@champagnepapi","@Drake");
        allArtists.add(inputArtist);
        allArtists.add(inputArtist2);
        allArtistsJson = mapper.writeValueAsString(allArtists);

        outputArtist = new Artist(1,"Adele","@BritishAdele","@Adele");
        outputArtist2 = new Artist(2,"Drake","@champagnepapi","@Drake");
        allArtists = new ArrayList<>(Arrays.asList(
                outputArtist,
                outputArtist2
        ));
    }
    //    Create/POST an Artist
    @Test
    public void shouldCreateNewArtistOnPostRequest() throws Exception {
        String inputArtistJson = mapper.writeValueAsString(inputArtist);
        String outputArtistJson = mapper.writeValueAsString(outputArtist);

        doReturn(outputArtist).when(repository).save(inputArtist);

        // Act
        mockMvc.perform(post("/artist")
                        .content(inputArtistJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistJson));
    }

    //    Get artist by Id
    @Test
    public void shouldReturnArtistById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputArtist2);

        doReturn(Optional.of(outputArtist2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/artist/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All artists
    @Test
    public void shouldReturnAllArtistsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allArtists);

        doReturn(allArtists).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/artist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //     Update artist
    @Test
    public void shouldUpdateArtistByIdAndReturn204StatusCode() throws Exception {
        inputArtist.setArtistId(1);
        inputArtist.setInstagram("@Adele");
        String inputJson = mapper.writeValueAsString(inputArtist);

        doReturn(Optional.of(outputArtist)).when(repository).findById(1);

        mockMvc.perform(
                        put("/artist")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }

    //     Delete artist
    @Test
    public void shouldDeleteArtistByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/artist/2")).andExpect(status().isNoContent());
    }
}