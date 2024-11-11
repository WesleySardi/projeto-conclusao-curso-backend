package org.example.biomedbacktdd.controller.mixed;

import org.example.biomedbacktdd.controllers.mixed.MixedController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@WebMvcTest(MixedController.class)
class MixedControllerTest {

    /*@Autowired
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
    }*/
}
