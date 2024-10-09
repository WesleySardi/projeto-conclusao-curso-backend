package org.example.biomedbacktdd.controller.dependent;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.services.DependentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DependentController.class)
public class DependentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DependentService service;

    @Test
    public void testFindAll() throws Exception {
        // Mock response expected from service layer
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeDep"));

        // Creating instances of DependentDTO with mock data
        DependentDTO dependent1 = new DependentDTO();
        dependent1.setKey("12345678901");
        dependent1.setNomeDep("Dependent 1");
        dependent1.setIdadeDep(25);
        dependent1.setTipoSanguineo("O+");
        dependent1.setLaudo("Healthy");
        dependent1.setGeneroDep("Male");
        dependent1.setRgDep("12345678");
        dependent1.setCpfResDep("98765432109");
        dependent1.setPiTagIdDep(1001);
        dependent1.setCpfTerDep("11223344556");
        dependent1.setIdCirurgiaDep(1);
        dependent1.setIdScanDep(1);

        DependentDTO dependent2 = new DependentDTO();
        dependent2.setKey("10987654321");
        dependent2.setNomeDep("Dependent 2");
        dependent2.setIdadeDep(30);
        dependent2.setTipoSanguineo("A+");
        dependent2.setLaudo("Minor Allergies");
        dependent2.setGeneroDep("Female");
        dependent2.setRgDep("87654321");
        dependent2.setCpfResDep("99887766554");
        dependent2.setPiTagIdDep(1002);
        dependent2.setCpfTerDep("22334455667");
        dependent2.setIdCirurgiaDep(2);
        dependent2.setIdScanDep(2);

        List<DependentDTO> dependents = Arrays.asList(dependent1, dependent2);
        Page<DependentDTO> page = new PageImpl<>(dependents, pageable, dependents.size());

        // Convert the dependent DTOs to EntityModel
        List<EntityModel<DependentDTO>> dependentEntities = dependents.stream()
                .map(dependent -> EntityModel.of(dependent, Link.of("/dependents/" + dependent.getKey()).withSelfRel()))
                .collect(Collectors.toList());

        // Create PageMetadata for the PagedModel
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements()
        );

        // Create the PagedModel object with metadata and content
        PagedModel<EntityModel<DependentDTO>> pagedModel = PagedModel.of(dependentEntities, pageMetadata, Link.of("/commonuser/findAll").withSelfRel());

        // When the service is called, return the mocked response
        when(service.findAll(pageable)).thenReturn(pagedModel);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/commonuser/findAll")
                        .param("page", "0")
                        .param("size", "12")
                        .param("direction", "asc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Validate the status is OK (200)
                .andExpect(jsonPath("$.content[0].nomeDep").value("Dependent 1"))
                .andExpect(jsonPath("$.content[1].nomeDep").value("Dependent 2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
