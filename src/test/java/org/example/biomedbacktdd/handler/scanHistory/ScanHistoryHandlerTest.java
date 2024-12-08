package org.example.biomedbacktdd.handler.scanHistory;

import org.example.biomedbacktdd.dto.commands.ScanHistoryCommand;
import org.example.biomedbacktdd.dto.viewmodels.ScanHistoryViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.scanhistory.ScanHistory;
import org.example.biomedbacktdd.handlers.scanHistory.ScanHistoryHandler;
import org.example.biomedbacktdd.repositories.interfaces.scanHistory.IScanHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ScanHistoryHandlerTest {

    @Mock
    private IScanHistoryRepository scanHistoryRepository;

    @InjectMocks
    private ScanHistoryHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para o método handleCreate

    @Test
    @DisplayName("handleCreate - Sucesso (201)")
    void testHandleCreate_Success() {
        // Arrange
        ScanHistoryCommand command = new ScanHistoryCommand();
        command.setScanName("Scan de Segurança");
        command.setScanEmail("user@example.com");
        command.setScanPhone("123456789");
        command.setDepCpf("12345678900");
        command.setLatitude(-23.550520);
        command.setLongitude(-46.633308);

        ScanHistory savedScanHistory = new ScanHistory();
        savedScanHistory.setScanId(1L);
        savedScanHistory.setScanName(command.getScanName());
        savedScanHistory.setScanEmail(command.getScanEmail());
        savedScanHistory.setScanPhone(command.getScanPhone());
        savedScanHistory.setDepCpf(command.getDepCpf());
        savedScanHistory.setLatitude(command.getLatitude());
        savedScanHistory.setLongitude(command.getLongitude());
        savedScanHistory.setScanDateTime(OffsetDateTime.now());

        when(scanHistoryRepository.save(any(ScanHistory.class))).thenReturn(savedScanHistory);

        // Act
        ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "O status da resposta deve ser 201 CREATED");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<ScanHistoryCommand> responseBody = response.getBody();
        assertEquals(201, responseBody.getStatus(), "O código de status no corpo deve ser 201");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Histórico de scan criado com sucesso.", responseBody.getInfoMessage(), "O infoMessage deve estar correto");
        assertEquals("Success", responseBody.getStatusMessage(), "O statusMessage deve estar correto");
        assertNotNull(responseBody.getContentResponse(), "O conteúdo da resposta não deve ser nulo");
        assertEquals(command.getScanName(), responseBody.getContentResponse().getScanName(), "O nome do scan deve estar correto");
        assertEquals(command.getScanEmail(), responseBody.getContentResponse().getScanEmail(), "O email do scan deve estar correto");
        assertEquals(command.getScanPhone(), responseBody.getContentResponse().getScanPhone(), "O telefone do scan deve estar correto");
        assertEquals(command.getDepCpf(), responseBody.getContentResponse().getDepCpf(), "O CPF do dependente deve estar correto");
        assertEquals(command.getLatitude(), responseBody.getContentResponse().getLatitude(), "A latitude deve estar correta");
        assertEquals(command.getLongitude(), responseBody.getContentResponse().getLongitude(), "A longitude deve estar correta");

        verify(scanHistoryRepository, times(1)).save(any(ScanHistory.class));
    }

    @Test
    @DisplayName("handleCreate - Erro Interno do Servidor (500)")
    void testHandleCreate_InternalServerError() {
        // Arrange
        ScanHistoryCommand command = new ScanHistoryCommand();
        command.setScanName("Scan de Segurança");
        command.setScanEmail("user@example.com");
        command.setScanPhone("123456789");
        command.setDepCpf("12345678900");
        command.setLatitude(-23.550520);
        command.setLongitude(-46.633308);

        String exceptionMessage = "Erro ao salvar no banco de dados.";
        when(scanHistoryRepository.save(any(ScanHistory.class))).thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 INTERNAL_SERVER_ERROR");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<ScanHistoryCommand> responseBody = response.getBody();
        assertEquals(500, responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao criar histórico de scan: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage deve conter a mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage deve estar correto");
        assertNull(responseBody.getContentResponse(), "O conteúdo da resposta deve ser nulo em caso de erro");

        verify(scanHistoryRepository, times(1)).save(any(ScanHistory.class));
    }

    // Testes para o método handleFindByDepCpf

    @Test
    @DisplayName("handleFindByDepCpf - Sucesso (200)")
    void testHandleFindByDepCpf_Success() {
        // Arrange
        String depCpf = "12345678900";

        ScanHistory scan1 = new ScanHistory();
        scan1.setScanId(1L);
        scan1.setScanName("Scan de Entrada");
        scan1.setScanEmail("user1@example.com");
        scan1.setScanPhone("111111111");
        scan1.setDepCpf(depCpf);
        scan1.setLatitude(-23.550520);
        scan1.setLongitude(-46.633308);
        scan1.setScanDateTime(OffsetDateTime.now());

        ScanHistory scan2 = new ScanHistory();
        scan2.setScanId(2L);
        scan2.setScanName("Scan de Saída");
        scan2.setScanEmail("user2@example.com");
        scan2.setScanPhone("222222222");
        scan2.setDepCpf(depCpf);
        scan2.setLatitude(-23.551520);
        scan2.setLongitude(-46.634308);
        scan2.setScanDateTime(OffsetDateTime.now());

        List<ScanHistory> scans = Arrays.asList(scan1, scan2);

        when(scanHistoryRepository.findByDepCpf(depCpf)).thenReturn(scans);

        // Act
        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = handler.handleFindByDepCpf(depCpf);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();
        assertEquals(200, responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Histórico de scans recuperado com sucesso.", responseBody.getInfoMessage(), "O infoMessage deve estar correto");
        assertEquals("Success", responseBody.getStatusMessage(), "O statusMessage deve estar correto");
        assertNotNull(responseBody.getContentResponse(), "O conteúdo da resposta não deve ser nulo");
        assertEquals(2, responseBody.getContentResponse().size(), "A lista de scans deve conter 2 elementos");

        ScanHistoryViewModel viewModel1 = responseBody.getContentResponse().get(0);
        assertEquals(scan1.getScanName(), viewModel1.getScanName(), "O nome do scan1 deve estar correto");
        assertEquals(scan1.getScanEmail(), viewModel1.getScanEmail(), "O email do scan1 deve estar correto");
        assertEquals(scan1.getScanPhone(), viewModel1.getScanPhone(), "O telefone do scan1 deve estar correto");
        assertEquals(scan1.getDepCpf(), viewModel1.getDepCpf(), "O CPF do scan1 deve estar correto");
        assertEquals(scan1.getLatitude(), viewModel1.getLatitude(), "A latitude do scan1 deve estar correta");
        assertEquals(scan1.getLongitude(), viewModel1.getLongitude(), "A longitude do scan1 deve estar correta");
        assertEquals(scan1.getScanDateTime(), viewModel1.getScanDateTime(), "A data e hora do scan1 devem estar corretas");

        ScanHistoryViewModel viewModel2 = responseBody.getContentResponse().get(1);
        assertEquals(scan2.getScanName(), viewModel2.getScanName(), "O nome do scan2 deve estar correto");
        assertEquals(scan2.getScanEmail(), viewModel2.getScanEmail(), "O email do scan2 deve estar correto");
        assertEquals(scan2.getScanPhone(), viewModel2.getScanPhone(), "O telefone do scan2 deve estar correto");
        assertEquals(scan2.getDepCpf(), viewModel2.getDepCpf(), "O CPF do scan2 deve estar correto");
        assertEquals(scan2.getLatitude(), viewModel2.getLatitude(), "A latitude do scan2 deve estar correta");
        assertEquals(scan2.getLongitude(), viewModel2.getLongitude(), "A longitude do scan2 deve estar correta");
        assertEquals(scan2.getScanDateTime(), viewModel2.getScanDateTime(), "A data e hora do scan2 devem estar corretas");

        verify(scanHistoryRepository, times(1)).findByDepCpf(depCpf);
    }

    @Test
    @DisplayName("handleFindByDepCpf - Sem Conteúdo (204)")
    void testHandleFindByDepCpf_NoContent() {
        // Arrange
        String depCpf = "00000000000";

        when(scanHistoryRepository.findByDepCpf(depCpf)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = handler.handleFindByDepCpf(depCpf);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "O status da resposta deve ser 204 NO CONTENT");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();
        assertEquals(204, responseBody.getStatus(), "O código de status no corpo deve ser 204");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Nenhum histórico de scan encontrado para o CPF informado.", responseBody.getInfoMessage(), "O infoMessage deve estar correto");
        assertEquals("No Content", responseBody.getStatusMessage(), "O statusMessage deve estar correto");
        assertNotNull(responseBody.getContentResponse(), "O conteúdo da resposta não deve ser nulo");
        assertTrue(responseBody.getContentResponse().isEmpty(), "A lista de scans deve estar vazia");

        verify(scanHistoryRepository, times(1)).findByDepCpf(depCpf);
    }

    @Test
    @DisplayName("handleFindByDepCpf - Erro Interno do Servidor (500)")
    void testHandleFindByDepCpf_InternalServerError() {
        // Arrange
        String depCpf = "99999999999";
        String exceptionMessage = "Erro ao consultar o banco de dados.";
        when(scanHistoryRepository.findByDepCpf(anyString())).thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> response = handler.handleFindByDepCpf(depCpf);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 INTERNAL_SERVER_ERROR");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<ScanHistoryViewModel>> responseBody = response.getBody();
        assertEquals(500, responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao buscar histórico de scans: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage deve conter a mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage deve estar correto");
        assertNull(responseBody.getContentResponse(), "O conteúdo da resposta deve ser nulo em caso de erro");

        verify(scanHistoryRepository, times(1)).findByDepCpf(depCpf);
    }
}
