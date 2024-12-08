package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.DependentNameViewModel;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.dependent.IDependentRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DependentServiceTest {

    private IDependentRepository repository;
    private PagedResourcesAssembler<NewDependentViewModel> assembler;
    private DependentService dependentService;
    private MockedStatic<DozerMapper> mockedDozerMapper;

    @BeforeEach
    void setUp() {
        repository = mock(IDependentRepository.class);
        assembler = mock(PagedResourcesAssembler.class);
        dependentService = new DependentService(repository, assembler);
        mockedDozerMapper = mockStatic(DozerMapper.class);
    }

    @AfterEach
    void tearDown() {
        mockedDozerMapper.close();
    }

    @Test
    void testFindAll_Success() {
        // Dado
        Pageable pageable = mock(Pageable.class);

        Dependent dependent1 = new Dependent();
        dependent1.setCpfDep("12345678900");

        Dependent dependent2 = new Dependent();
        dependent2.setCpfDep("09876543211");

        List<Dependent> dependents = Arrays.asList(dependent1, dependent2);
        Page<Dependent> dependentPage = new PageImpl<>(dependents);

        when(repository.findAll(pageable)).thenReturn(dependentPage);

        NewDependentViewModel viewModel1 = new NewDependentViewModel();
        viewModel1.setKey("12345678900");

        NewDependentViewModel viewModel2 = new NewDependentViewModel();
        viewModel2.setKey("09876543211");

        PagedModel<EntityModel<NewDependentViewModel>> pagedModel = PagedModel.of(
                Arrays.asList(EntityModel.of(viewModel1), EntityModel.of(viewModel2)),
                new PagedModel.PageMetadata(2, 0, 2));

        // Mock do mapeamento
        mockedDozerMapper.when(() -> DozerMapper.parseObject(any(Dependent.class), eq(NewDependentViewModel.class)))
                .thenReturn(viewModel1, viewModel2);

        when(assembler.toModel(any(), any(Link.class))).thenReturn(pagedModel);

        // Quando
        PagedModel<EntityModel<NewDependentViewModel>> result = dependentService.findAll(pageable);

        // Então
        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        verify(repository, times(1)).findAll(pageable);
        verify(assembler, times(1)).toModel(any(), any(Link.class));
    }

    @Test
    void testFindAll_Exception() {
        // Dado
        Pageable pageable = mock(Pageable.class);
        when(repository.findAll(pageable)).thenThrow(new RuntimeException("Database error"));

        // Quando
        PagedModel<EntityModel<NewDependentViewModel>> result = dependentService.findAll(pageable);

        // Então
        assertNull(result);
    }

    @Test
    void testFindById_Success() {
        // Dado
        String id = "12345678900";

        Dependent dependent = new Dependent();
        dependent.setCpfDep(id);

        when(repository.findById(id)).thenReturn(Optional.of(dependent));

        NewDependentViewModel viewModel = new NewDependentViewModel();
        viewModel.setKey(id);

        // Mock do mapeamento
        mockedDozerMapper.when(() -> DozerMapper.parseObject(any(Dependent.class), eq(NewDependentViewModel.class)))
                .thenReturn(viewModel);

        // Quando
        NewDependentViewModel result = dependentService.findById(id);

        // Então
        assertNotNull(result);
        assertEquals(id, result.getKey());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        // Dado
        String id = "12345678900";
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Quando
        NewDependentViewModel result = dependentService.findById(id);

        // Então
        assertNull(result);
    }

    @Test
    void testCreate_Success() {
        // Dado
        NewDependentCommand command = new NewDependentCommand();
        command.setKey("12345678900");
        command.setNomeDep("Teste");

        Dependent dependent = new Dependent();
        dependent.setCpfDep(command.getKey());
        dependent.setNomeDep(command.getNomeDep());

        NewDependentResult resultVo = new NewDependentResult();
        resultVo.setKey(command.getKey());
        resultVo.setNomeDep(command.getNomeDep());

        // Mock do mapeamento
        mockedDozerMapper.when(() -> DozerMapper.parseObject(any(NewDependentCommand.class), eq(Dependent.class)))
                .thenReturn(dependent);
        mockedDozerMapper.when(() -> DozerMapper.parseObject(any(Dependent.class), eq(NewDependentResult.class)))
                .thenReturn(resultVo);

        when(repository.save(any(Dependent.class))).thenReturn(dependent);

        // Quando
        NewDependentResult result = dependentService.create(command);

        // Então
        assertNotNull(result);
        assertEquals(command.getKey(), result.getKey());
        assertEquals(command.getNomeDep(), result.getNomeDep());

        verify(repository, times(1)).save(any(Dependent.class));
    }

    @Test
    void testCreate_NullCommand() {
        // Quando
        NewDependentResult result = dependentService.create(null);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_Success() {
        // Dado
        NewDependentCommand command = new NewDependentCommand();
        command.setKey("12345678900");
        command.setNomeDep("Teste Atualizado");

        Dependent existingDependent = new Dependent();
        existingDependent.setCpfDep(command.getKey());
        existingDependent.setNomeDep("Teste");

        when(repository.findById(command.getKey())).thenReturn(Optional.of(existingDependent));
        when(repository.save(any(Dependent.class))).thenReturn(existingDependent);

        NewDependentResult resultVo = new NewDependentResult();
        resultVo.setKey(command.getKey());
        resultVo.setNomeDep(command.getNomeDep());

        // Mock do mapeamento
        mockedDozerMapper.when(() -> DozerMapper.parseObject(any(Dependent.class), eq(NewDependentResult.class)))
                .thenReturn(resultVo);

        // Quando
        NewDependentResult result = dependentService.update(command);

        // Então
        assertNotNull(result);
        assertEquals(command.getKey(), result.getKey());
        assertEquals("Teste Atualizado", result.getNomeDep());

        verify(repository, times(1)).findById(command.getKey());
        verify(repository, times(1)).save(any(Dependent.class));
    }

    @Test
    void testUpdate_NotFound() {
        // Dado
        NewDependentCommand command = new NewDependentCommand();
        command.setKey("12345678900");

        when(repository.findById(command.getKey())).thenReturn(Optional.empty());

        // Quando
        NewDependentResult result = dependentService.update(command);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_NullCommand() {
        // Quando
        NewDependentResult result = dependentService.update(null);

        // Então
        assertNull(result);
    }

    @Test
    void testDelete_Success() {
        // Dado
        String id = "12345678900";

        Dependent dependent = new Dependent();
        dependent.setCpfDep(id);

        when(repository.findById(id)).thenReturn(Optional.of(dependent));
        doNothing().when(repository).delete(dependent);

        // Quando
        String result = dependentService.delete(id);

        // Então
        assertNotNull(result);
        assertEquals(id, result);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(dependent);
    }

    @Test
    void testDelete_NotFound() {
        // Dado
        String id = "12345678900";

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Quando
        String result = dependentService.delete(id);

        // Então
        assertNull(result);
    }

    @Test
    void testGetDependentNameByCpf_Success() {
        // Dado
        String cpfDep = "12345678900";
        String nomeDep = "Teste";

        when(repository.findDependentNameByCpf(cpfDep)).thenReturn(nomeDep);

        // Quando
        DependentNameViewModel result = dependentService.getDependentNameByCpf(cpfDep);

        // Então
        assertNotNull(result);
        assertEquals(nomeDep, result.getNomeDep());

        verify(repository, times(1)).findDependentNameByCpf(cpfDep);
    }

    @Test
    void testGetDependentNameByCpf_NotFound() {
        // Dado
        String cpfDep = "12345678900";

        when(repository.findDependentNameByCpf(cpfDep)).thenReturn(null);

        // Quando e Então
        assertThrows(ResourceNotFoundException.class, () -> {
            dependentService.getDependentNameByCpf(cpfDep);
        });

        verify(repository, times(1)).findDependentNameByCpf(cpfDep);
    }
}
