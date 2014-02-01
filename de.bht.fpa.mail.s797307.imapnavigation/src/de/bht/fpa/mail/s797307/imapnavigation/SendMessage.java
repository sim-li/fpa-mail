package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.bht.fpa.mail.s000000.common.mail.model.Account;

public final class SendMessage {
 
	public static void send(Account account, String recipient, String subject, String text) {
		Properties props = new Properties();
		System.out.println(account.getHost());
		System.out.println(account.getUsername());
		System.out.println(account.getPassword());
		props.put("mail.smtp.host", account.getHost());
		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.ssl.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			msg.setSubject(subject);
			msg.setText(text);
			System.out.println(recipient + ">" + subject  + ">" + text);
			Transport transport = session.getTransport("smtp");
            System.out.println("Connecting...");
			transport.connect(account.getHost(), account.getUsername(), account.getPassword());
			System.out.println("Setting transport sendmessage");
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Closing");
            transport.close();
            System.out.println("Sending...");
			Transport.send(msg);
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
//			System.out.println("Hey you know I got some prob here.");
		}
		
	}
}
