package com.example.managementservice.domains.scheduled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.managementservice.domains.clients.UserClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendCustomerEmailPromotion {
	
	UserClient userClient;
	private final long SEGUNDO = 1000; 
    private final long MINUTO = SEGUNDO * 60; 
    private final long HORA = MINUTO * 60;
	
	@Scheduled(initialDelay = 2000, fixedDelay = HORA)
	public void sendmail() {
		List<Map<String, String>> produtos = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			Map<String, String> produto = new HashMap<>();
			produto.put("nome", "produto"+i);
			produto.put("desc", "descricao"+i);
			produto.put("image", "url"+i);
			produtos.add(produto);
		}
		
		String htmlEmail = buildHtml(produtos);
		
		List<String> emails = new ArrayList<>();
		emails.add("matheus.l.rizzo@gmail.com");
		emails.add("ulisses_mano@hotmail.com");
		sendEmailClient(emails, htmlEmail);
		
	}

	private void sendEmailClient(List<String> emails, String htmlEmail) {
		log.info("Begin SendCustomerEmailPromotion");
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication("techstoreprojeto@gmail.com","projeto@2022");
           }
	    });
	    
	    session.setDebug(true);
	    
	    try {
	    	Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("techstoreprojeto@gmail.com"));
		    
		    emails.forEach(email -> {
		    	log.info("Email: " + email);
		    	try {
		    		Address[] toUserAddress = InternetAddress.parse(email);
				    message.setRecipients(Message.RecipientType.TO, toUserAddress);
				    message.setSubject("Enviando email com JavaMail");
				    message.setContent(htmlEmail, "text/html");
				    Transport.send(message);
				    log.info("Email enviado com sucesso");
				} catch (Exception e) {
					log.error("Erro ao Enviar Email: " + e);
				}  
		    });
		    
		    log.info("End SendCustomerEmailPromotion");
		    
		} catch (Exception e) {
			log.error("Erro ao Enviar Email: " + e);
		}
	}
	
	private String buildHtml(List<Map<String, String>> produtos) {
		log.info("Montando HTML length produtos: " + produtos.size());
		produtos.forEach(produto -> {
			log.info("nome" + produto.get("nome"));
			log.info("desc" + produto.get("desc"));
			log.info("image" + produto.get("image"));
		});
		
		return "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /></head><body><b>Testeeeeeeeeeee</b></body></html>\r\n"
				+ "";
	}
	
}
