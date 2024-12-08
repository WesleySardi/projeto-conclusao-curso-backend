package org.example.biomedbacktdd.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.example.biomedbacktdd.sms.codegenerator.CodeGenerator.gerarCodigoSMS;

@Service
public class MessageSms {
    @Value("${twillio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twillio.account.authtoken}")
    private String AUTH_TOKEN;

    private Integer codigoSMS;

    public Integer getCodigoSMS() {
        return codigoSMS;
    }

    public void setCodigoSMS() {
        this.codigoSMS = Integer.valueOf(gerarCodigoSMS(6));
    }

    public void sendSms(String phoneUser) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneUser),
                        new com.twilio.type.PhoneNumber("+15078586132"),
                        "Security code: " + codigoSMS)
                .create();

        System.out.println(message.getSid());
    }
}