package org.example.biomedbacktdd.handler.encoding;

import org.example.biomedbacktdd.DTO.results.DecryptedMessageResult;
import org.example.biomedbacktdd.DTO.results.EncryptedMessageResult;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.encoding.EncodingHandler;
import org.example.biomedbacktdd.services.interfaces.encoding.IEncodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EncodingHandlerTest {

    @Mock
    private IEncodingService encodingService;

    @InjectMocks
    private EncodingHandler encodingHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleEncryptUrl_Success() {
        String url = "https://example.com";

        EncryptedMessageResult result = new EncryptedMessageResult("encodedUrl", "Mensagem codificada com sucesso.");

        when(encodingService.encryptUrl(url)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleEncryptUrl(url);

        StatusResponseViewModel body = response.getBody();
        assertNotNull(body);
        assertEquals("Sucesso", body.getInfoMessage());
        assertEquals("Mensagem codificada com sucesso.", body.getStatusMessage());
        assertEquals(HttpStatus.OK.value(), body.getStatus());
        assertTrue(body.getIsOk());
    }

    @Test
    void testHandleEncryptUrl_Failure() {
        String url = "https://example.com";

        when(encodingService.encryptUrl(url)).thenReturn(null);

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleEncryptUrl(url);

        StatusResponseViewModel body = response.getBody();

        assertNotNull(body);
        assertEquals("Erro", body.getInfoMessage());
        assertEquals("Erro ao codificar a mensagem.", body.getStatusMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
        assertFalse(body.getIsOk());
    }

    @Test
    void testHandleEncryptUrl_Exception() {
        String url = "https://example.com";

        when(encodingService.encryptUrl(url)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleEncryptUrl(url);

        StatusResponseViewModel body = response.getBody();
        assertNotNull(body);
        assertEquals("Um erro inesperado aconteceu.", body.getInfoMessage());
        assertEquals("Unexpected error", body.getStatusMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.getStatus());
        assertFalse(body.getIsOk());
    }

    @Test
    void testHandleDecryptUrl_Success() {
        String url = "encodedUrl";

        DecryptedMessageResult result = new DecryptedMessageResult("https://example.com", "Mensagem decodificada com sucesso.");

        when(encodingService.decryptUrl(url)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleDecryptUrl(url);

        StatusResponseViewModel body = response.getBody();
        assertNotNull(body);
        assertEquals("Sucesso", body.getInfoMessage());
        assertEquals("Mensagem decodificada com sucesso.", body.getStatusMessage());
        assertEquals(HttpStatus.OK.value(), body.getStatus());
        assertTrue(body.getIsOk());
    }

    @Test
    void testHandleDecryptUrl_Failure() {
        String url = "encodedUrl";

        when(encodingService.decryptUrl(url)).thenReturn(null);

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleDecryptUrl(url);

        StatusResponseViewModel body = response.getBody();
        assertNotNull(body);
        assertEquals("Erro", body.getInfoMessage());
        assertEquals("Erro ao decodificar a mensagem.", body.getStatusMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
        assertFalse(body.getIsOk());
    }

    @Test
    void testHandleDecryptUrl_Exception() {
        String url = "encodedUrl";

        when(encodingService.decryptUrl(url)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<StatusResponseViewModel> response = encodingHandler.handleDecryptUrl(url);

        StatusResponseViewModel body = response.getBody();
        assertNotNull(body);
        assertEquals("Um erro inesperado aconteceu.", body.getInfoMessage());
        assertEquals("Unexpected error", body.getStatusMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.getStatus());
        assertFalse(body.getIsOk());
    }
}
