package org.example.biomedbacktdd.controller.mixed;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.DependentWebDataDTO;
import org.example.biomedbacktdd.controllers.mixed.MixedController;
import org.example.biomedbacktdd.handlers.mixed.MixedHandler;
import org.example.biomedbacktdd.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(MixedController.class)
class MixedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MixedHandler handler;

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
    void testFindByIdWithSecurity() throws Exception {
        // Given
        String cpfRes = "98765432109";
        String contato1Res = "98765432";

        // Simular o retorno do método que busca o Dependente
        DependentDTO dependentDTO = new DependentDTO();
        dependentDTO.setKey(cpfRes);
        // Preencha os outros campos necessários se eles forem obrigatórios

        when(handler.handleFindByIdWithSecurity(cpfRes, contato1Res)).thenReturn(dependentDTO);

        // When & Then
        mockMvc.perform(get("/api/mixed/commonuser/params?cpfDep={cpfRes}&emergPhone={emergPhone}", cpfRes, contato1Res)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Alvo: 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "leandro", roles = {"COMMON_USER"})
    void testFindWebDataByIdWithSecurity() throws Exception {
        // Given
        String cpfDep = "12345";
        String emergPhone = "999999999";
        DependentWebDataDTO webDataDTO = new DependentWebDataDTO();

        // Mocking the repository calls
        when(handler.handleFindWebDataByIdWithSecurity(cpfDep, emergPhone)).thenReturn(webDataDTO);

        // When & Then
        mockMvc.perform(get("/api/mixed/commonuser/webdata/params?cpfDep={cpfDep}&emergPhone={emergPhone}", cpfDep, emergPhone)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpfDep").value(cpfDep))
                .andExpect(jsonPath("$.emergPhone").value(emergPhone));
    }
}
