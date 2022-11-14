package com.tunes.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorecatalog.model.Track;
import com.tunes.musicstorecatalog.repository.TrackRepository;
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
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrackRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private Track inputTrack;
    private Track inputTrack2;
    private Track outputTrack;
    private Track outputTrack2;
    private List<Track> allTracks = new ArrayList<>();
    private String allTracksJson;

    @Before
    public void setUp() throws Exception {
        inputTrack = new Track(1, "Rolling in the Deep", 3);
        inputTrack2 = new Track(2, "Passionfruit", 5);
        allTracks.add(inputTrack);
        allTracks.add(inputTrack2);
        allTracksJson = mapper.writeValueAsString(allTracks);

        outputTrack = new Track(1, 1, "Rolling in the Deep", 3);
        outputTrack2 = new Track(2, 2, "Passionfruit", 5);
        allTracks = new ArrayList<>(Arrays.asList(
                outputTrack,
                outputTrack2
        ));
    }
        //    Create/POST an Track
        @Test
        public void shouldCreateNewTrackOnPostRequest() throws Exception {
            String inputTrackJson = mapper.writeValueAsString(inputTrack);
            String outputTrackJson = mapper.writeValueAsString(outputTrack);

            doReturn(outputTrack).when(repository).save(inputTrack);

            // Act
            mockMvc.perform(post("/track")
                            .content(inputTrackJson)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputTrackJson));
        }

        //    Get track by Id
        @Test
        public void shouldReturnTrackById() throws Exception {
            String outputJson = mapper.writeValueAsString(outputTrack2);

            doReturn(Optional.of(outputTrack2)).when(repository).findById(2);

            mockMvc.perform(
                            get("/track/2"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect((content().json(outputJson)));
        }

        //     Get All tracks
        @Test
        public void shouldReturnAllTracksOnGetRequest() throws Exception {
            String outputJson = mapper.writeValueAsString(allTracks);

            doReturn(allTracks).
                    when // conditional
                    (repository).findAll(); //method we want to test

            mockMvc.perform(
                            get("/track"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect((content().json(outputJson)));
        }
        //     Update track
        @Test
        public void shouldUpdateTrackByIdAndReturn204StatusCode() throws Exception {
            inputTrack.setTrackId(1);
            inputTrack.setRunTime(4);
            String inputJson = mapper.writeValueAsString(inputTrack);

            doReturn(Optional.of(outputTrack)).when(repository).findById(1);

            mockMvc.perform(
                            put("/track")
                                    .content(inputJson) //the string jsonbody
                                    .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
                    .andExpect(status().isNoContent());  //can do isOk if you want to see json body

        }

        //     Delete track
        @Test
        public void shouldDeleteTrackByIdAndReturn204StatusCode () throws Exception {
            mockMvc.perform(delete("/track/2")).andExpect(status().isNoContent());
        }

}