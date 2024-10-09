package org.example.biomedbacktdd.service.dependent;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.repositories.DependentRepository;
import org.example.biomedbacktdd.services.DependentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DependentsServiceTest {

    @InjectMocks
    private DependentService service;

    @Mock
    private DependentRepository repository;

    @Mock
    private PagedResourcesAssembler<DependentDTO> assembler;

    @Test
    public void testFindAllDependents() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeDep"));

        // Criando dependentes com os atributos corretos
        Dependent dependent1 = new Dependent();
        dependent1.setCpfDep("11111111111");
        dependent1.setNomeDep("Dependent 1");

        Dependent dependent2 = new Dependent();
        dependent2.setCpfDep("22222222222");
        dependent2.setNomeDep("Dependent 2");

        List<Dependent> dependents = Arrays.asList(dependent1, dependent2);
        Page<Dependent> dependentPage = new PageImpl<>(dependents);

        // Mock repository response
        when(repository.findAll(pageable)).thenReturn(dependentPage);

        // Test the service method
        PagedModel<EntityModel<DependentDTO>> result = service.findAll(pageable);

        // Validate the result
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Dependent 1", result.getContent().iterator().next().getContent().getNomeDep());
    }
}

