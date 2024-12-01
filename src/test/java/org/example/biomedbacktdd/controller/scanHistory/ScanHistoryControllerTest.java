package org.example.biomedbacktdd.controller.scanHistory;

import org.example.biomedbacktdd.controllers.scanhistory.ScanHistoryController;
import org.example.biomedbacktdd.dto.commands.ScanHistoryCommand;
import org.example.biomedbacktdd.dto.viewmodels.ScanHistoryViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.scanHistory.ScanHistoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScanHistoryControllerTest {

    @Mock
    private ScanHistoryHandler handler;

    @InjectMocks
    private ScanHistoryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para o método POST /api/scanHistory

    @Test
    @DisplayName("POST /api/scanHistory - Sucesso (200)")
    void testCreateScanHistory_Success() {
        ScanHistoryCommand command = new ScanHistoryCommand();

        StatusResponseViewModel<ScanHistoryCommand> successResponse = new StatusResponseViewModel<>();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Scan history created successfully.");
        successResponse.setInfoMessage("Detailed info message.");
        successResponse.setIsOk(true);
        successResponse.setContentResponse(command);

        when(handler.handleCreate(any(ScanHistoryCommand.class)))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> response = controller.createScanHistory(command);

        StatusResponseViewModel<ScanHistoryCommand> responseBody = response.getBody();

        assertNotNull(response, "A resposta não deve ser nula.");
        assertNotNull(responseBody, "O corpo da resposta não deve ser nulo.");
        assertEquals(200, responseBody.getStatus(), "O status deve ser 200.");
        assertEquals("Scan history created successfully.", responseBody.getStatusMessage(), "A mensagem de status deve estar correta.");
        assertEquals("Detailed info message.", responseBody.getInfoMessage(), "A mensagem de informação deve estar correta.");
        assertTrue(responseBody.getIsOk(), "O campo isOk deve ser true.");
        assertEquals(command, responseBody.getContentResponse(), "O conteúdo da resposta deve ser o comando enviado.");

        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/scanHistory - Requisição Inválida (400)")
    void testCreateScanHistory_BadRequest() {
        ScanHistoryCommand command = new ScanHistoryCommand();

        when(handler.handleCreate(any(ScanHistoryCommand.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> response = controller.createScanHistory(command);

        StatusResponseViewModel<ScanHistoryCommand> responseBody = response.getBody();
        assertNotNull(response, "A resposta não deve ser nula.");
        assertNull(responseBody, "O corpo da resposta deve ser nulo para Bad Request.");

        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/scanHistory - Erro Interno (500)")
    void testCreateScanHistory_InternalServerError() {
        ScanHistoryCommand command = new ScanHistoryCommand();

        when(handler.handleCreate(any(ScanHistoryCommand.class)))
                .thenReturn(ResponseEntity.status(500).build());

        ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> response = controller.createScanHistory(command);

        StatusResponseViewModel<ScanHistoryCommand> responseBody = response.getBody();
        assertNotNull(response, "A resposta não deve ser nula.");
        assertNull(responseBody, "O corpo da resposta deve ser nulo para Erro Interno.");

        verify(handler, times(1)).handleCreate(command);
    }


    @Test
    @DisplayName("GET /api/scanHistory/dependente/{depCpf} - Sucesso (200)")
    void testGetScansByDepCpf_Success() {
        String depCpf = "12345678900";

        ScanHistoryViewModel scan1 = new ScanHistoryViewModel();

        ScanHistoryViewModel scan2 = new ScanHistoryViewModel();

        List<ScanHistoryViewModel> scanList = List.of(scan1, scan2);

        StatusResponseViewModel<List<ScanHistoryViewModel>> successResponse = new StatusResponseViewModel<>();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Operation completed successfully.");
        successResponse.setInfoMessage("Detailed info message.");
        successResponse.setIsOk(true);
        successResponse.setContentResponse(scanList);

        when(handler.handleFindByDepCpf(depCpf))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = controller.getScansByDepCpf(depCpf);

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();

        assertNotNull(response, "A resposta não deve ser nula.");
        assertNotNull(responseBody, "O corpo da resposta não deve ser nulo.");
        assertEquals(200, responseBody.getStatus(), "O status deve ser 200.");
        assertEquals("Operation completed successfully.", responseBody.getStatusMessage(), "A mensagem de status deve estar correta.");
        assertEquals("Detailed info message.", responseBody.getInfoMessage(), "A mensagem de informação deve estar correta.");
        assertTrue(responseBody.getIsOk(), "O campo isOk deve ser true.");
        assertNotNull(responseBody.getContentResponse(), "O conteúdo da resposta não deve ser nulo.");
        assertEquals(2, responseBody.getContentResponse().size(), "A lista de scans deve conter 2 elementos.");
        assertEquals(scan1, responseBody.getContentResponse().get(0), "O primeiro scan deve estar correto.");
        assertEquals(scan2, responseBody.getContentResponse().get(1), "O segundo scan deve estar correto.");

        verify(handler, times(1)).handleFindByDepCpf(depCpf);
    }

    @Test
    @DisplayName("GET /api/scanHistory/dependente/{depCpf} - Sem Conteúdo (204)")
    void testGetScansByDepCpf_NoContent() {
        String depCpf = "00000000000";

        when(handler.handleFindByDepCpf(depCpf))
                .thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = controller.getScansByDepCpf(depCpf);

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();
        assertNotNull(response, "A resposta não deve ser nula.");
        assertNull(responseBody, "O corpo da resposta deve ser nulo para Sem Conteúdo.");

        verify(handler, times(1)).handleFindByDepCpf(depCpf);
    }

    @Test
    @DisplayName("GET /api/scanHistory/dependente/{depCpf} - Não Encontrado (404)")
    void testGetScansByDepCpf_NotFound() {
        String depCpf = "99999999999";

        when(handler.handleFindByDepCpf(depCpf))
                .thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = controller.getScansByDepCpf(depCpf);

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();

        assertNotNull(response, "A resposta não deve ser nula.");
        assertNull(responseBody, "O corpo da resposta deve ser nulo para Não Encontrado.");

        verify(handler, times(1)).handleFindByDepCpf(depCpf);
    }
}
