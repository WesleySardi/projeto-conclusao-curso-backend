package org.example.biomedbacktdd.controller.responsible;

import org.example.biomedbacktdd.controllers.responsible.ResponsibleController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@WebMvcTest(ResponsibleController.class)
class ResponsibleControllerTest {

    /*@Autowired
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
    }*/
}
