package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.results.NewSmsResult;
import org.example.biomedbacktdd.dto.viewmodels.NewSmsViewModel;
import org.example.biomedbacktdd.entities.sms.SmsHandler;
import org.example.biomedbacktdd.repositories.interfaces.sms.ISmsHandlerRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SmsHandlerServiceTest {

    private ISmsHandlerRepository repository;
    private SmsHandlerService smsHandlerService;
    private MockedStatic<DozerMapper> mockedDozerMapper;


    @BeforeEach
    public void setUp() {
        repository = mock(ISmsHandlerRepository.class);
        PagedResourcesAssembler<NewSmsViewModel> assembler = mock(PagedResourcesAssembler.class);
        mockedDozerMapper = mockStatic(DozerMapper.class);

        smsHandlerService = new SmsHandlerService(repository, assembler);
    }

    @AfterEach
    public void tearDown() {
        // Fecha o mock estático do DozerMapper
        mockedDozerMapper.close();
    }

    @Test
    void testFindAll_Exception() {
        // Dado
        Pageable pageable = mock(Pageable.class);
        when(repository.findAll(pageable)).thenThrow(new RuntimeException("Database error"));

        // Quando
        PagedModel<EntityModel<NewSmsViewModel>> result = smsHandlerService.findAll(pageable);

        // Então
        assertNull(result);
    }


    @Test
    void testFindById_NotFound() {
        // Dado
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Quando
        NewSmsViewModel result = smsHandlerService.findById(id);

        // Então
        assertNull(result);
    }


    @Test
    void testCreate_NullCommand() {
        // Quando
        NewSmsResult result = smsHandlerService.create(null);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_Success() {
        // Dado
        Integer smsCode = 1;
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());

        SmsHandler smsHandler = new SmsHandler();
        smsHandler.setSmsCode(smsCode);

        when(repository.findById(smsCode)).thenReturn(Optional.of(smsHandler));
        when(repository.save(any(SmsHandler.class))).thenReturn(smsHandler);
        when(DozerMapper.parseObject(any(SmsHandler.class), eq(NewSmsResult.class))).thenReturn(new NewSmsResult());

        // Quando
        NewSmsResult result = smsHandlerService.update(smsCode, returnDate);

        // Então
        assertNotNull(result);

        verify(repository, times(1)).findById(smsCode);
        verify(repository, times(1)).save(any(SmsHandler.class));
    }

    @Test
    void testUpdate_NotFound() {
        // Dado
        Integer smsCode = 1;
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());

        when(repository.findById(smsCode)).thenReturn(Optional.empty());

        // Quando
        NewSmsResult result = smsHandlerService.update(smsCode, returnDate);

        // Então
        assertNull(result);
    }

    @Test
    void testUpdate_NullParameters() {
        // Quando
        NewSmsResult result = smsHandlerService.update(null, null);

        // Então
        assertNull(result);
    }



    @Test
    void testVerifySmsCode_Expired() {
        // Dado
        Integer smsCode = 1;
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());
        String cpfDep = "12345678900";

        SmsHandler smsHandler = new SmsHandler();
        smsHandler.setSmsCode(smsCode);
        smsHandler.setCpfDep(cpfDep);
        smsHandler.setSendDate(new Timestamp(System.currentTimeMillis() - 700000)); // 7000 segundos atrás

        when(repository.findById(smsCode)).thenReturn(Optional.of(smsHandler));

        // Quando
        boolean result = smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);

        // Então
        assertTrue(result);
    }

    @Test
    void testVerifySmsCode_InvalidCpfDep() {
        // Dado
        Integer smsCode = 1;
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());
        String cpfDep = "12345678900";

        SmsHandler smsHandler = new SmsHandler();
        smsHandler.setSmsCode(smsCode);
        smsHandler.setCpfDep("09876543211"); // CPF diferente
        smsHandler.setSendDate(new Timestamp(System.currentTimeMillis()));

        when(repository.findById(smsCode)).thenReturn(Optional.of(smsHandler));

        // Quando
        boolean result = smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);

        // Então
        assertFalse(result);
    }

    @Test
    void testVerifySmsCode_NotFound() {
        // Dado
        Integer smsCode = 1;
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());
        String cpfDep = "12345678900";

        when(repository.findById(smsCode)).thenReturn(Optional.empty());

        // Quando
        boolean result = smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);

        // Então
        assertFalse(result);
    }

    @Test
    void testVerifySmsCode_NullParameters() {
        // Quando
        boolean result = smsHandlerService.verifySmsCode(null, null, null);

        // Então
        assertFalse(result);
    }

    @Test
    void testDelete_Success() {
        // Dado
        Integer id = 1;

        SmsHandler smsHandler = new SmsHandler();
        smsHandler.setSmsCode(id);

        when(repository.findById(id)).thenReturn(Optional.of(smsHandler));
        doNothing().when(repository).delete(smsHandler);

        // Quando
        Integer result = smsHandlerService.delete(id);

        // Então
        assertNotNull(result);
        assertEquals(id, result);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(smsHandler);
    }

    @Test
    void testDelete_NotFound() {
        // Dado
        Integer id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Quando
        Integer result = smsHandlerService.delete(id);

        // Então
        assertNull(result);
    }

    @Test
    void testDeleteByCpfDep_Exception() {
        // Dado
        String cpfDep = "12345678900";

        doThrow(new RuntimeException("Database error")).when(repository).deleteByCpfDep(cpfDep);

        // Quando
        String result = smsHandlerService.deleteByCpfDep(cpfDep);

        // Então
        assertNull(result);
    }
}
