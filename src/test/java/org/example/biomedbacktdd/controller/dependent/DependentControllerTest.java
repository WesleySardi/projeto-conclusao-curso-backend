package org.example.biomedbacktdd.controller.dependent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
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

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(DependentController.class)
class DependentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DependentHandler handler;

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
        DependentDTO dependentDTO = new DependentDTO();
        dependentDTO.setNomeDep("Test");
        List<DependentDTO> dependents = List.of(dependentDTO);
        List<EntityModel<DependentDTO>> entityModels = dependents.stream()
                .map(dependent -> EntityModel.of(dependent))
                .toList();

        PagedModel<EntityModel<DependentDTO>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(1, 0, dependents.size()));

        when(handler.handleFindAll(any(Pageable.class))).thenReturn(pagedModel);

        // When & Then
        mockMvc.perform(get("/api/dependent/commonuser/findAll?page=0&size=12&direction=asc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.dependentDTOes[0].nomeDep").value("Test"));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindDependentsByName() throws Exception {
        // Given
        String nomeDep = "Test";
        DependentDTO dependentDTO = new DependentDTO();
        dependentDTO.setNomeDep(nomeDep);
        List<DependentDTO> dependents = List.of(dependentDTO);
        List<EntityModel<DependentDTO>> entityModels = dependents.stream()
                .map(dependent -> EntityModel.of(dependent))
                .toList();

        PagedModel<EntityModel<DependentDTO>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(1, 0, dependents.size()));

        when(handler.handleFindDependentsByName(anyString(), any(Pageable.class))).thenReturn(pagedModel);

        // When & Then
        mockMvc.perform(get("/api/dependent/commonuser/findDependentsByName/{nomeDep}?page=0&size=12&direction=asc", nomeDep)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.dependentDTOes[0].nomeDep").value(nomeDep));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindDependentsByCpfRes() throws Exception {
        // Given
        String cpfRes = "12345678901";
        DependentDTO dependentDTO = new DependentDTO();
        dependentDTO.setNomeDep("Test");
        List<DependentDTO> dependents = List.of(dependentDTO);
        List<EntityModel<DependentDTO>> entityModels = dependents.stream()
                .map(dependent -> EntityModel.of(dependent))
                .toList();

        PagedModel<EntityModel<DependentDTO>> pagedModel = PagedModel.of(entityModels,
                new PagedModel.PageMetadata(1, 0, dependents.size()));

        when(handler.handleFindDependentsByCpfRes(anyString(), any(Pageable.class))).thenReturn(pagedModel);

        // When & Then
        mockMvc.perform(get("/api/dependent/commonuser/findDependentsByCpfRes/{cpfRes}?page=0&size=12&direction=asc", cpfRes)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.dependentDTOes[0].nomeDep").value("Test"));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindById() throws Exception {
        // Given
        String id = "1";
        DependentDTO dependentDTO = new DependentDTO();
        dependentDTO.setNomeDep("Test");

        when(handler.handleFindById(id)).thenReturn(dependentDTO);

        // When & Then
        mockMvc.perform(get("/api/dependent/commonuser/findById/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomeDep").value("Test"));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testVerifyDependentsCpfAndEmergPhone() throws Exception {
        // Given
        String cpfDep = "12345678901";
        String emergPhone = "987654321";
        Map<String, String> response = Map.of("cpfDep", cpfDep, "emergPhone", emergPhone);

        when(handler.handleVerifyDependentsCpfAndEmergPhone(cpfDep, emergPhone)).thenReturn(response);

        // When & Then
        mockMvc.perform(get("/api/dependent/commonuser/verifyDependentsCPFandEmergPhone/params?cpfDep={cpfDep}&emergPhone={emergPhone}", cpfDep, emergPhone)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpfDep").value(cpfDep))
                .andExpect(jsonPath("$.emergPhone").value(emergPhone));
    }
}
