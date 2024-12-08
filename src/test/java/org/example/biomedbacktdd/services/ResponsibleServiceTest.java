package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class ResponsibleServiceTest {

    private IResponsibleRepository repository;
    private PagedResourcesAssembler<NewResponsibleViewModel> assembler;
    private ResponsibleService responsibleService;

    @BeforeEach
    public void setUp() {
        repository = mock(IResponsibleRepository.class);
        assembler = mock(PagedResourcesAssembler.class);

        responsibleService = new ResponsibleService(repository, assembler);
    }

    @Test
    void testFindAll_Success() {
        // Dado
        Pageable pageable = mock(Pageable.class);

        Responsible responsible1 = new Responsible();
        responsible1.setCpfRes("12345678900");

        Responsible responsible2 = new Responsible();
        responsible2.setCpfRes("09876543211");

        List<Responsible> responsibles = Arrays.asList(responsible1, responsible2);
        Page<Responsible> responsiblePage = new PageImpl<>(responsibles);

        when(repository.findAll(pageable)).thenReturn(responsiblePage);

        // Mock do mapeamento
        NewResponsibleViewModel viewModel1 = new NewResponsibleViewModel();
        viewModel1.setKey("12345678900");

        NewResponsibleViewModel viewModel2 = new NewResponsibleViewModel();
        viewModel2.setKey("09876543211");

        // Simulando o assembler
        PagedModel<EntityModel<NewResponsibleViewModel>> pagedModel = PagedModel.of(
                Arrays.asList(EntityModel.of(viewModel1), EntityModel.of(viewModel2)),
                new PagedModel.PageMetadata(2, 0, 2));

        when(assembler.toModel(any(Page.class), any(Link.class))).thenReturn(pagedModel);

        // Quando
        PagedModel<EntityModel<NewResponsibleViewModel>> result = responsibleService.findAll(pageable);

        // Então
        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        verify(repository, times(1)).findAll(pageable);
        verify(assembler, times(1)).toModel(any(Page.class), any(Link.class));
    }

    @Test
    void testFindAll_Exception() {
        // Dado
        Pageable pageable = mock(Pageable.class);
        when(repository.findAll(pageable)).thenThrow(new RuntimeException("Database error"));

        // Quando
        PagedModel<EntityModel<NewResponsibleViewModel>> result = responsibleService.findAll(pageable);

        // Então
        assertNull(result);
    }

    @Test
    void testFindById_Success() {
        // Dado
        String id = "12345678900";

        Responsible responsible = new Responsible();
        responsible.setCpfRes(id);

        when(repository.findById(id)).thenReturn(Optional.of(responsible));

        // Mock do mapeamento
        NewResponsibleViewModel viewModel = new NewResponsibleViewModel();
        viewModel.setKey(id);

        // Quando
        NewResponsibleViewModel result = responsibleService.findById(id);

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
        NewResponsibleViewModel result = responsibleService.findById(id);

        // Então
        assertNull(result);
    }

    @Test
    void testCreate_Success() {
        // Dado
        NewResponsibleCommand command = new NewResponsibleCommand();
        command.setKey("12345678900");
        command.setNomeRes("Teste");

        Responsible responsible = new Responsible();
        responsible.setCpfRes(command.getKey());
        responsible.setNomeRes(command.getNomeRes());

        when(repository.save(any(Responsible.class))).thenReturn(responsible);

        // Quando
        NewResponsibleResult result = responsibleService.create(command);

        // Então
        assertNotNull(result);
        assertEquals(command.getKey(), result.getKey());
        assertEquals(command.getNomeRes(), result.getNomeRes());

        verify(repository, times(1)).save(any(Responsible.class));
    }

    @Test
    void testCreate_NullCommand() {
        // Quando
        NewResponsibleResult result = responsibleService.create(null);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_Success() {
        // Dado
        NewResponsibleCommand command = new NewResponsibleCommand();
        command.setKey("12345678900");
        command.setNomeRes("Teste Atualizado");

        Responsible existingResponsible = new Responsible();
        existingResponsible.setCpfRes(command.getKey());
        existingResponsible.setNomeRes("Teste");

        when(repository.findById(command.getKey())).thenReturn(Optional.of(existingResponsible));
        when(repository.save(any(Responsible.class))).thenReturn(existingResponsible);

        // Quando
        NewResponsibleResult result = responsibleService.update(command);

        // Então
        assertNotNull(result);
        assertEquals(command.getKey(), result.getKey());
        assertEquals("Teste Atualizado", result.getNomeRes());

        verify(repository, times(1)).findById(command.getKey());
        verify(repository, times(1)).save(any(Responsible.class));
    }

    @Test
    void testUpdate_NotFound() {
        // Dado
        NewResponsibleCommand command = new NewResponsibleCommand();
        command.setKey("12345678900");

        when(repository.findById(command.getKey())).thenReturn(Optional.empty());

        // Quando
        NewResponsibleResult result = responsibleService.update(command);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_NullCommand() {
        // Quando
        NewResponsibleResult result = responsibleService.update(null);

        // Então
        assertNull(result);
    }

    @Test
    void testFindByEmail_Success() {
        // Dado
        String email = "test@example.com";

        Responsible responsible = new Responsible();
        responsible.setEmailRes(email);

        when(repository.findResponsibleByEmail(email)).thenReturn(Optional.of(responsible));

        // Quando
        NewResponsibleViewModel result = responsibleService.findByEmail(email);

        // Então
        assertNotNull(result);
        assertEquals(email, result.getEmailRes());

        verify(repository, times(1)).findResponsibleByEmail(email);
    }

    @Test
    void testFindByEmail_NotFound() {
        // Dado
        String email = "test@example.com";

        when(repository.findResponsibleByEmail(email)).thenReturn(Optional.empty());

        // Quando
        NewResponsibleViewModel result = responsibleService.findByEmail(email);

        // Então
        assertNull(result);
    }

    @Test
    void testFindByTelefone_Success() {
        // Dado
        String telefone = "123456789";

        Responsible responsible = new Responsible();
        responsible.setContato1Res(telefone);

        when(repository.findResponsibleByTelefone(telefone)).thenReturn(Optional.of(responsible));

        // Quando
        NewResponsibleViewModel result = responsibleService.findByTelefone(telefone);

        // Então
        assertNotNull(result);
        assertEquals(telefone, result.getContato1Res());

        verify(repository, times(1)).findResponsibleByTelefone(telefone);
    }

    @Test
    void testFindByTelefone_NotFound() {
        // Dado
        String telefone = "123456789";

        when(repository.findResponsibleByTelefone(telefone)).thenReturn(Optional.empty());

        // Quando
        NewResponsibleViewModel result = responsibleService.findByTelefone(telefone);

        // Então
        assertNull(result);
    }
}
