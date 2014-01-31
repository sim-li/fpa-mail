package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class SendMessage {
 
	public static void send(String recipient, String subject, String text) {
//		String host = "smtp.googlemail.com";
//		String username = "bhtfpa@googlemail.com";
//		String password = "B-BgxkT_anr2bubbyTLM";
		String host = "smtp.mail.yahoo.com";
		String username = "funkjaymatada@ymail.com";
		String password = "sonysaqz";
		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.ssl.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props, null);
 
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("funkjaymatada@ymail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("simon@a-studios.org"));
			message.setSubject("Hola123");
			message.setText("Testing the west.");
			
			Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
//			throw new RuntimeException(e);
			System.out.println("Hey you know I got some prob here.");
		}
		
	}
}
