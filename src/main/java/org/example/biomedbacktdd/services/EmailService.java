package org.example.biomedbacktdd.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.biomedbacktdd.DTO.commands.NewEmailCommand;
import org.example.biomedbacktdd.DTO.results.NewEmailResult;
import org.example.biomedbacktdd.DTO.viewmodels.NewEmailViewModel;
import org.example.biomedbacktdd.controllers.email.EmailController;
import org.example.biomedbacktdd.entities.email.EmailHandler;
import org.example.biomedbacktdd.exceptions.RequiredObjectIsNullException;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.email.IEmailRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.example.biomedbacktdd.services.interfaces.email.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmailService implements IEmailService {

    private final Logger logger = Logger.getLogger(EmailService.class.getName());
    private final IEmailRepository repository;
    private final PagedResourcesAssembler<NewEmailViewModel> assembler;

    @Autowired
    public EmailService(IEmailRepository repository,
                        PagedResourcesAssembler<NewEmailViewModel> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public NewEmailResult create(NewEmailCommand emailVO) {
        NewEmailResult response = null;

        try {
            if (emailVO == null) throw new RequiredObjectIsNullException();

            logger.info("Creating an email entry and sending email!");

            int emailCode = generateRandomCode();

            String emailContent = "Your verification code is: " + emailCode;
            sendEmailWithSendGrid(emailVO.getEmailUser(), emailContent);

            Timestamp sendDate = new Timestamp(System.currentTimeMillis());

            Timestamp returnDate = new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));

            EmailHandler emailHandler = DozerMapper.parseObject(emailVO, EmailHandler.class);
            emailHandler.setEmailCode(emailCode);
            emailHandler.setSendDate(sendDate);
            emailHandler.setReturnDate(returnDate);
            emailHandler.setEmailUser(emailHandler.getEmailUser());
            emailHandler.setCpfDep(emailHandler.getCpfDep());

            emailHandler = repository.save(emailHandler);

            NewEmailResult vo = DozerMapper.parseObject(emailHandler, NewEmailResult.class);
            vo.add(linkTo(methodOn(EmailController.class).findById(emailHandler.getEmailCode())).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public String sendQrCodeWithSendGrid(String toEmail) {
        String response = null;

        try {
            Email from = new Email("wesleysardi.random@gmail.com");
            String subject = "Seu código de verificação!";
            Email to = new Email(toEmail);

            String htmlContent = """
                        <!DOCTYPE html>
                       <html lang="pt-BR">
                       
                       <head>
                           <meta charset="UTF-8">
                           <meta name="viewport" content="width=device-width, initial-scale=1.0">
                           <title>Email com QR Code</title>
                           <style>
                               body {
                                   font-family: Arial, sans-serif;
                                   background-color: #f4f4f4;
                                   margin: 0;
                                   padding: 0;
                                   text-align: center;
                               }
                       
                               .container {
                                   max-width: 600px;
                                   margin: 0 auto;
                                   padding: 20px;
                                   background-color: #ffffff;
                                   border-radius: 8px;
                                   box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                                   text-align: center;
                                   justify-content: center;
                                   align-items: center;
                               }
                       
                               .title {
                                   font-size: 24px;
                                   color: #333333;
                                   margin-bottom: 20px;
                                   max-width: 600px;
                                   width: 100%;
                                   text-align: center;
                                   justify-content: center;
                                   align-items: center;
                               }
                       
                               .qr-code {
                                   width: 100%;
                                   /* Ajusta a largura para ocupar todo o espaço disponível */
                                   height: auto;
                                   /* Ajusta a altura automaticamente */
                               }
                           </style>
                       </head>
                       
                       <body>
                           <div class="container">
                               <h1 class="title">Baixe o aplicativo lendo o QR Code abaixo!</h1>
                               <img src="https://i.imgur.com/6fOfcTT.png" alt="QR Code para baixar o aplicativo" class="qr-code">
                           </div>
                       </body>
                       
                       </html>
                    """;

            Content emailContent = new Content("text/html", htmlContent);
            Mail mail = new Mail(from, subject, to, emailContent);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response responseApi = sg.api(request);
                response = "Email sent. Status Code: " + responseApi.getStatusCode();
            } catch (IOException ex) {
                response = "Error sending email: " + ex.getMessage();
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    private void sendEmailWithSendGrid(String toEmail, String content) {
        Email from = new Email("wesleysardi.random@gmail.com");
        String subject = "Seu código de verificação!";
        Email to = new Email(toEmail);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info("Email sent. Status Code: " + response.getStatusCode());
        } catch (IOException ex) {
            logger.severe("Error sending email: " + ex.getMessage());
        }
    }

    private int generateRandomCode() {
        return (int) (Math.random() * 900000) + 100000;
    }

    public PagedModel<EntityModel<NewEmailViewModel>> findAll(Pageable pageable) {
        PagedModel<EntityModel<NewEmailViewModel>> response = null;

        try {
            logger.info("Finding all emails!");

            var emailPage = repository.findAll(pageable);

            var emailVosPage = emailPage.map(p -> {
                NewEmailViewModel vo = DozerMapper.parseObject(p, NewEmailViewModel.class);
                vo.add(linkTo(methodOn(EmailController.class).findById(vo.getKey())).withSelfRel());
                return vo;
            });

            Link link = linkTo(methodOn(EmailController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString())).withSelfRel();

            response = assembler.toModel(emailVosPage, link);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewEmailViewModel findById(Integer id) {
        NewEmailViewModel response = null;

        try {
            logger.info("Finding an email!");

            EmailHandler entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            NewEmailViewModel vo = DozerMapper.parseObject(entity, NewEmailViewModel.class);

            vo.add(linkTo(methodOn(EmailController.class).findById(id)).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public String deleteByEmail(String emailUser) {
        String response = null;

        try {
            logger.info("Deleting all Email entries for email: " + emailUser);
            boolean exists = repository.existsByEmailUser(emailUser);
            if (!exists) {
                logger.info("No records found for this email: " + emailUser);
                throw new ResourceNotFoundException("No records found for this email: " + emailUser);
            }
            repository.deleteByEmailUser(emailUser);

            response = emailUser;
        } catch (Exception e) {
            response = null;
        }

        return response;
    }

    public boolean verifyEmailCode(String email, int code) {
        boolean response = false;

        try {
            logger.info("Verifying an email code for: " + email);

            EmailHandler entity = repository.findByEmailUserAndEmailCode(email, code)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found for this email and code!"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (entity.getReturnDate() != null && currentTime.before(entity.getReturnDate())) {
                var deletedEmail = deleteByEmail(entity.getEmailUser());
                if (deletedEmail != null) {
                    response = true;
                } else {
                    response = false;
                }
            } else {
                response = false;
            }
        } catch (Exception e) {
            return false;
        }

        return response;
    }
}
