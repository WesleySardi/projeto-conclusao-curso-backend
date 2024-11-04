package org.example.biomedbacktdd.controller.responsible;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.example.biomedbacktdd.controllers.responsible.ResponsibleController;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(ResponsibleController.class)
class ResponsibleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponsibleHandler handler;

    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindAll() throws Exception {
        // Given
        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        responsibleDTO.setNomeRes("Test Responsible");
        List<ResponsibleDTO> responsibles = List.of(responsibleDTO);
        List<EntityModel<ResponsibleDTO>> entityModels = responsibles.stream()
                .map(responsible -> EntityModel.of(responsible))
                .toList();

        PagedModel<EntityModel<ResponsibleDTO>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(1, 0, responsibles.size()));

        when(handler.handleFindAll(any(Pageable.class))).thenReturn(pagedModel);

        // When & Then
        mockMvc.perform(get("/api/responsible/commonuser/findAll?page=0&size=12&direction=asc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.responsibleDTOes[0].nomeRes").value("Test Responsible"));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindResponsibleByName() throws Exception {
        // Given
        String nomeRes = "Test Responsible";
        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        responsibleDTO.setNomeRes(nomeRes);
        List<ResponsibleDTO> responsibles = List.of(responsibleDTO);
        List<EntityModel<ResponsibleDTO>> entityModels = responsibles.stream()
                .map(responsible -> EntityModel.of(responsible))
                .toList();

        PagedModel<EntityModel<ResponsibleDTO>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(1, 0, responsibles.size()));

        when(handler.handleFindResponsiblesByName(anyString(), any(Pageable.class))).thenReturn(pagedModel);

        // When & Then
        mockMvc.perform(get("/api/responsible/commonuser/findResponsibleByName/{nomeRes}?page=0&size=12&direction=asc", nomeRes)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.responsibleDTOes[0].nomeRes").value(nomeRes));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindResponsibleCpfAndName() throws Exception {
        // Given
        String emailRes = "test@example.com";
        String senhaRes = "password";
        Object[] response = new Object[]{"Test Responsible", "123456789"};

        when(handler.handleFindResponsiblesCpfAndName(emailRes, senhaRes)).thenReturn(Collections.singletonList(response));

        // When & Then
        mockMvc.perform(get("/api/responsible/commonuser/findResponsibleCpfAndName/params?emailRes={emailRes}&senhaRes={senhaRes}", emailRes, senhaRes)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0][0]").value("Test Responsible"))
                .andExpect(jsonPath("$[0][1]").value("123456789"));
    }
}
