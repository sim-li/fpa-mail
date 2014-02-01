package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.bht.fpa.mail.s000000.common.mail.model.Account;

public final class SendMessage {

	public static void send(final Account account, String recipient,
			String subject, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", account.getHost());
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.ssl.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								account.getUsername(), account.getPassword());
					}
				});
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(account.getUsername()));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			msg.setSubject(subject);
			msg.setText(text);
			Transport transport = session.getTransport("smtp");
			System.out.println("Connecting...");
			transport.connect();
			System.out.println("Sending...");
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Closing");
			transport.close();
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
