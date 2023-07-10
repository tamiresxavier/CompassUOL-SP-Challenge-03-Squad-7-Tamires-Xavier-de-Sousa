package br.com.compassuol.pb.challenge.notification.components;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.notification.dtos.EmailDto;

@Component
public class EmailProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendEmail(EmailDto emailDto) {
        amqpTemplate.convertAndSend("email-exchange", "email-key", emailDto);
    }
}
