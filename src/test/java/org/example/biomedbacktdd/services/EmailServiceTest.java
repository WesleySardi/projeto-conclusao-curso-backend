package org.example.biomedbacktdd.services;

import com.sendgrid.SendGrid;
import org.example.biomedbacktdd.dto.commands.NewEmailCommand;
import org.example.biomedbacktdd.entities.email.EmailHandler;
import org.example.biomedbacktdd.repositories.interfaces.email.IEmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PagedResourcesAssembler;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private IEmailRepository repository;

    @Mock
    private SendGrid sendGrid;

    @Mock
    private PagedResourcesAssembler assembler;

    @InjectMocks
    private EmailService emailService;

    private NewEmailCommand emailCommand;
    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        emailCommand = new NewEmailCommand();
        emailCommand.setEmailUser("test@example.com");
        emailCommand.setCpfDep("12345678900");

        emailHandler = new EmailHandler();
        emailHandler.setEmailCode(123456);
        emailHandler.setEmailUser("test@example.com");
        emailHandler.setSendDate(new Timestamp(System.currentTimeMillis()));
        emailHandler.setReturnDate(new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));
    }


    @Test
    void testDeleteByEmail_Success() {
        when(repository.existsByEmailUser("test@example.com")).thenReturn(true);

        String result = emailService.deleteByEmail("test@example.com");

        assertEquals("test@example.com", result);
        verify(repository, times(1)).deleteByEmailUser("test@example.com");
    }


    @Test
    void testSendEmailWithSendGrid_Success() {
        // Simula o envio de e-mail usando mocks do SendGrid.
        var emailContent = "Seu código de verificação: 123456";
        assertDoesNotThrow(() -> emailService.sendEmailWithSendGrid("test@example.com", emailContent));
    }

    @Test
    void testVerifyEmailCode_Valid() {
        when(repository.findByEmailUserAndEmailCode("test@example.com", 123456))
                .thenReturn(Optional.of(emailHandler));
        when(repository.existsByEmailUser("test@example.com")).thenReturn(true);

        boolean result = emailService.verifyEmailCode("test@example.com", 123456);

        assertTrue(result);
        verify(repository, times(1)).deleteByEmailUser("test@example.com");
    }

}
