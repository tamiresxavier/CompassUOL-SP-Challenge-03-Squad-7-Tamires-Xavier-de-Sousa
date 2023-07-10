package br.com.compassuol.pb.challenge.notification.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.notification.dtos.EmailDto;
import br.com.compassuol.pb.challenge.notification.services.EmailService;

@Component
public class EmailListener {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void processEmail(EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }
}
