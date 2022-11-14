package com.tunes.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunes.musicstorecatalog.model.Label;
import com.tunes.musicstorecatalog.repository.LabelRepository;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabelRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private Label inputLabel;
    private Label inputLabel2;
    private Label outputLabel;
    private Label outputLabel2;
    private List<Label> allLabels = new ArrayList<>();
    private String allLabelsJson;

    @Before
    public void setUp() throws Exception {
        inputLabel = new Label("Columbia Records","www.Columbia-Records.com");
        inputLabel2 = new Label("OVO Sound","www.OVO-Sound.com");
        allLabels.add(inputLabel);
        allLabels.add(inputLabel2);
        allLabelsJson = mapper.writeValueAsString(allLabels);

        outputLabel = new Label(1,"Columbia Records","www.Columbia-Records.com");
        outputLabel2 = new Label(2,"OVO Sound","www.OVO-Sound.com");
        allLabels = new ArrayList<>(Arrays.asList(
                outputLabel,
                outputLabel2
        ));
    }

    //    Create/POST a Label
    @Test
    public void shouldCreateNewLabelOnPostRequest() throws Exception {
        String inputLabelJson = mapper.writeValueAsString(inputLabel);
        String outputLabelJson = mapper.writeValueAsString(outputLabel);

        doReturn(outputLabel).when(repository).save(inputLabel);

        // Act
        mockMvc.perform(post("/label")
                        .content(inputLabelJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelJson));
    }

    //    Get label by Id
    @Test
    public void shouldReturnLabelById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputLabel2);

        doReturn(Optional.of(outputLabel2)).when(repository).findById(2);

        mockMvc.perform(
                        get("/label/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

    //     Get All labels
    @Test
    public void shouldReturnAllLabelsOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allLabels);

        doReturn(allLabels).
                when // conditional
                (repository).findAll(); //method we want to test

        mockMvc.perform(
                        get("/label"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //     Update label
    @Test
    public void shouldUpdateLabelByIdAndReturn204StatusCode() throws Exception {
        inputLabel.setLabelId(1);
        inputLabel.setWebsite("www.Columbia-Records-Studio.com");
        String inputJson = mapper.writeValueAsString(inputLabel);

        doReturn(Optional.of(outputLabel)).when(repository).findById(1);

        mockMvc.perform(
                        put("/label")
                                .content(inputJson) //the string jsonbody
                                .contentType(MediaType.APPLICATION_JSON)) //header needed for every HTTP request
                .andExpect(status().isNoContent());  //can do isOk if you want to see json body

    }

    //     Delete label
    @Test
    public void shouldDeleteLabelByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/label/2")).andExpect(status().isNoContent());
    }
}