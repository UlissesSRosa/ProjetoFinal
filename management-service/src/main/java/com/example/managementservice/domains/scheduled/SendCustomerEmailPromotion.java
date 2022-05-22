//package com.example.managementservice.domains.scheduled;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Address;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.example.managementservice.domains.clients.UserClient;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class SendCustomerEmailPromotion {
//
//	UserClient userClient;
//	private final long SEGUNDO = 1000;
//    private final long MINUTO = SEGUNDO * 60;
//    private final long HORA = MINUTO * 60;
//
//	@Scheduled(initialDelay = 2000, fixedDelay = HORA)
//	public void sendmail() {
//		List<Map<String, String>> produtos = new ArrayList<>();
//		for(int i = 0; i < 5; i++) {
//			Map<String, String> produto = new HashMap<>();
//			produto.put("nome", "produto"+i);
//			produto.put("preco", "10"+i);
//			produto.put("image", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/280px-Red_Apple.jpg");
//			produtos.add(produto);
//		}
//
//		String htmlEmail = buildHtml(produtos);
//
//		List<String> emails = new ArrayList<>();
//		emails.add("matheus.l.rizzo@gmail.com");
//		emails.add("ulisses_mano@hotmail.com");
//		sendEmailClient(emails, htmlEmail);
//
//	}
//
//	private void sendEmailClient(List<String> emails, String htmlEmail) {
//		log.info("Begin SendCustomerEmailPromotion");
//		Properties props = new Properties();
//	    props.put("mail.smtp.host", "smtp.gmail.com");
//	    props.put("mail.smtp.socketFactory.port", "465");
//	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.port", "465");
//
//	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//           protected PasswordAuthentication getPasswordAuthentication()
//           {
//                 return new PasswordAuthentication("techstoreprojeto@gmail.com","projeto@2022");
//           }
//	    });
//
//	    session.setDebug(true);
//
//	    try {
//	    	Message message = new MimeMessage(session);
//		    message.setFrom(new InternetAddress("techstoreprojeto@gmail.com"));
//
//		    emails.forEach(email -> {
//		    	log.info("Email: " + email);
//		    	try {
//		    		Address[] toUserAddress = InternetAddress.parse(email);
//				    message.setRecipients(Message.RecipientType.TO, toUserAddress);
//				    message.setSubject("Enviando email com JavaMail");
//				    message.setContent(htmlEmail, "text/html");
//				    Transport.send(message);
//				    log.info("Email enviado com sucesso");
//				} catch (Exception e) {
//					log.error("Erro ao Enviar Email: " + e);
//				}
//		    });
//
//		    log.info("End SendCustomerEmailPromotion");
//
//		} catch (Exception e) {
//			log.error("Erro ao Enviar Email: " + e);
//		}
//	}
//
//	private String buildHtml(List<Map<String, String>> produtos) {
//		log.info("Montando HTML. Produtos em Promoção: " + produtos.size());
//
//		StringBuilder html = new StringBuilder();
//		html.append(montainicioHtml());
//
//		produtos.forEach(produto -> {
//			html.append(montaMeioHtml(produto));
//		});
//
//		html.append(montafimHtml());
//		log.info("Html Email: " + html.toString());
//		return html.toString();
//	}
//
//	private String montainicioHtml() {
//		StringBuilder htmlInicio = new StringBuilder();
//		htmlInicio.append("<!DOCTYPE html><html lang=\"pt-br\"><head><!-- Meta tags Obrigatórias --><meta charset=\"utf-8\" /> ");
//		htmlInicio.append("<meta name=\"viewport\"content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"/> ");
//		htmlInicio.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" ");
//		htmlInicio.append("integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\"/> ");
//		htmlInicio.append("<style> .flex-center {display: flex; justify-content: center;}</style></head> ");
//		htmlInicio.append("<body><div class=\"container\"><div class=\"row hidden-sm-up mt-2\"><h4 class=\"text-left\"><b><i>Caro cliente,</i></b></h4></div> ");
//		htmlInicio.append("<div class=\"row hidden-sm-up\"><p class=\"text-5xl\"><b><i>Aproveite as nossas ofertas de hoje:</i></b></p></div> ");
//		htmlInicio.append("<div class=\"row hidden-sm-up\"> ");
//		return htmlInicio.toString();
//	}
//
//	private Object montaMeioHtml(Map<String, String> produto) {
//		StringBuilder htmlMeio = new StringBuilder();
//		htmlMeio.append("<div class=\"col-md-4 mt-2\"><div class=\"card \" style=\"width: 18rem\"> ");
//		htmlMeio.append("<img class=\"card-img-top\" src=\"$URLIMAGEM\" alt=\"100rem\"/> <div class=\"card-body\"> ");
//		htmlMeio.append("<h5 class=\"card-title\">$NOMEPRODUTO</h5> <p class=\"card-text\">$PRECO</p> ");
//		htmlMeio.append("<a href=\"#\" class=\"btn btn-primary\">Visitar</a> </div></div></div> ");
//		String htmlMeioEdit = htmlMeio.toString();
//		htmlMeioEdit = htmlMeioEdit.replace("$URLIMAGEM", produto.get("image"));
//		htmlMeioEdit = htmlMeioEdit.replace("$NOMEPRODUTO", produto.get("nome"));
//		htmlMeioEdit = htmlMeioEdit.replace("$PRECO", produto.get("preco"));
//		return htmlMeioEdit;
//	}
//
//	private String montafimHtml() {
//		StringBuilder htmlFim = new StringBuilder();
//		htmlFim.append("</div><div class=\"row hidden-sm-up mt-3\"><p class=\"text-5xl\"> ");
//		htmlFim.append("<b><i>Atenciosamente,<br>Tech Store</i></b></p></div></div></div> ");
//		htmlFim.append("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" ");
//		htmlFim.append("integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" ");
//		htmlFim.append("crossorigin=\"anonymous\"></script> ");
//		htmlFim.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" ");
//		htmlFim.append("integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" ");
//		htmlFim.append("crossorigin=\"anonymous\"></script><script ");
//		htmlFim.append("src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" ");
//		htmlFim.append("integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" ");
//		htmlFim.append("crossorigin=\"anonymous\"></script></body></html> ");
//		return htmlFim.toString();
//	}
//
//}
