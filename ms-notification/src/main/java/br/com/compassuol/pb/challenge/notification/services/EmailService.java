package br.com.compassuol.pb.challenge.notification.services;

import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.notification.dtos.EmailDto;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailService {
    public void sendEmail(EmailDto emailDto) {
        // Lógica para enviar o email
        // Aqui você pode usar alguma biblioteca de envio de email, como JavaMail, Spring Mail, etc.
    	
    	Properties properties = new Properties();
    	properties.put("mail.smtp.host", "smtp.gmail.com");
    	properties.put("mail.smtp.port", "587");
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	
    	Session session = Session.getInstance(properties, new Authenticator() {
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication("challenge03week12@gmail.com", "challenge03");
    	    }
    	});
    	
    	try {
    	    Message message = new MimeMessage(session);
    	    message.setFrom(new InternetAddress("challenge03week12@gmail.com"));
    	    message.setRecipient(Message.RecipientType.TO, new InternetAddress("tamiresxavierdesousa@gmail.com"));
    	    message.setSubject("Assunto do email");
    	    message.setText("Conteúdo do email");

    	    // Você pode adicionar anexos, formatação de texto, etc. aqui se necessário

    	    // Envie o email
    	    Transport.send(message);

    	    System.out.println("Email enviado com sucesso!");
    	} catch (MessagingException e) {
    	    System.out.println("Erro ao enviar o email: " + e.getMessage());
    	}
    }
}