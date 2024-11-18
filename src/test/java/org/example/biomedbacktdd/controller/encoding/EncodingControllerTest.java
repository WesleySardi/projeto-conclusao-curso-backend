package org.example.biomedbacktdd.controller.encoding;

import org.example.biomedbacktdd.dto.commands.DecryptMessageCommand;
import org.example.biomedbacktdd.dto.commands.EncryptMessageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.controllers.encoding.EncodingController;
import org.example.biomedbacktdd.handlers.encoding.EncodingHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EncodingControllerTest {

    @Mock
    private EncodingHandler encodingHandler;

    @InjectMocks
    private EncodingController encodingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEncryptUrl() {
        // Arrange
        EncryptMessageCommand encryptMessageCommand = new EncryptMessageCommand("testUrl");

        StatusResponseViewModel statusResponse = new StatusResponseViewModel(
                "Encrypted content",
                "Info message",
                "Encrypted successfully",
                HttpStatus.OK.value(),
                true
        );

        ResponseEntity<StatusResponseViewModel> responseEntity = new ResponseEntity<>(statusResponse, HttpStatus.OK);

        when(encodingHandler.handleEncryptUrl(encryptMessageCommand.getUrl())).thenReturn(responseEntity);

        // Act
        ResponseEntity<StatusResponseViewModel> response = encodingController.encryptUrl(encryptMessageCommand);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Encrypted content", response.getBody().getContentResponse());
        assertEquals("Info message", response.getBody().getInfoMessage());
        assertEquals("Encrypted successfully", response.getBody().getStatusMessage());
        assertEquals(200, response.getBody().getStatus());
        assertEquals(true, response.getBody().getIsOk());

        verify(encodingHandler, times(1)).handleEncryptUrl("testUrl");
    }

    @Test
    public void testDecryptUrl() {
        // Arrange
        DecryptMessageCommand decryptMessageCommand = new DecryptMessageCommand("testUrl");

        StatusResponseViewModel statusResponse = new StatusResponseViewModel(
                "Decrypted content",
                "Info message",
                "Decrypted successfully",
                HttpStatus.OK.value(),
                true
        );

        ResponseEntity<StatusResponseViewModel> responseEntity = new ResponseEntity<>(statusResponse, HttpStatus.OK);

        when(encodingHandler.handleDecryptUrl(decryptMessageCommand.getUrl())).thenReturn(responseEntity);

        // Act
        ResponseEntity<StatusResponseViewModel> response = encodingController.decryptUrl(decryptMessageCommand);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Decrypted content", response.getBody().getContentResponse());
        assertEquals("Info message", response.getBody().getInfoMessage());
        assertEquals("Decrypted successfully", response.getBody().getStatusMessage());
        assertEquals(200, response.getBody().getStatus());
        assertEquals(true, response.getBody().getIsOk());

        verify(encodingHandler, times(1)).handleDecryptUrl("testUrl");
    }
}
