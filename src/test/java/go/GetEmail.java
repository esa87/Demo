package go;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class GetEmail {

	static String IMAP_AUTH_EMAIL;
	static String IMAP_AUTH_PWD;
	String IMAP_Server = "localhost";
	String IMAP_Port = "3143";
	static Message mes[];

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public GetEmail() {
		Properties properties = new Properties();
		properties.put("mail.debug", "false");
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.ssl.enable", "false");
		properties.put("mail.imap.port", IMAP_Port);

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
			}
		};
		Session session = Session.getDefaultInstance(properties, auth);
		session.setDebug(false);
		try {
			Store store = session.getStore("imap");

			// Подключение к почтовому серверу
			store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

			// Папка входящих сообщений
			Folder inbox = store.getFolder("INBOX");

			// Открываем папку в режиме только для чтения
			inbox.open(Folder.READ_ONLY);

			System.out.println("Количество сообщений : " + String.valueOf(inbox.getMessageCount()));
			if (inbox.getMessageCount() == 0)
				return;
			// Последнее сообщение; первое сообщение под номером 1
			mes = inbox.getMessages();

		} catch (NoSuchProviderException e) {
			System.err.println(e.getMessage());
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
		}
	}

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		}
		if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			return getTextFromMimeMultipart(mimeMultipart);
		}
		return "";
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		for (int i = 0; i < mimeMultipart.getCount(); i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
			}
			result += parseBodyPart(bodyPart);
		}
		return result;
	}

	private static String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
		if (bodyPart.isMimeType("text/html")) {
			return "\n" + org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).text();
		}
		if (bodyPart.getContent() instanceof MimeMultipart) {
			return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
		}

		return "";
	}

	public static Boolean checkMessage(String tor_id, String subjectMail, Date sendDate, String email)
			throws MessagingException, IOException, InterruptedException {
		if (!email.equals(""))
			try {
				IMAP_AUTH_EMAIL = email;
				IMAP_AUTH_PWD = email;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		else {
			IMAP_AUTH_EMAIL = "test@test.ru";
			IMAP_AUTH_PWD = "test@test.ru";
		}

		Boolean checkTorId = false;
		Integer steps = 0;
		String bodyMessage;
		while ((!checkTorId) && steps < 20) {
			new GetEmail();
			for (Message message : mes) {
				if (sendDate.getTime() < message.getReceivedDate().getTime()) {

					bodyMessage = getTextFromMessage(message);
					if (bodyMessage.toString().contains(tor_id)
							&& message.getSubject().toString().contains(subjectMail)) {
						checkTorId = true;
					}
				}

			}
			Thread.sleep(3000);
			steps++;
		}
		return checkTorId;
	}

	public static String getBodyMessage(String tor_id, String subjectMail, Date sendDate, String email)
			throws MessagingException, IOException, InterruptedException {
		if (!email.equals(""))
			try {
				IMAP_AUTH_EMAIL = email;
				IMAP_AUTH_PWD = email;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		else {
			IMAP_AUTH_EMAIL = "test@test.ru";
			IMAP_AUTH_PWD = "test@test.ru";
		}

		Boolean checkTorId = false;
		Integer steps = 0;
		String bodyMessage = "";
		while ((!checkTorId) && steps < 20) {
			new GetEmail();
			for (Message message : mes) {
				if (sendDate.getTime() < message.getReceivedDate().getTime()) {

					bodyMessage = getTextFromMessage(message);
					if (bodyMessage.toString().contains(tor_id)
							&& message.getSubject().toString().contains(subjectMail)) {
						checkTorId = true;
					}
				}

			}
			Thread.sleep(3000);
			steps++;
		}
		return bodyMessage;
	}

	public static void sendEmail() {
		String hostname = "qmail.test.ru";
		final String username = "user@qmail.test.ru";
		final String password = "123456";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", hostname);
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.localhost", hostname);

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("user@qmail.test.ru"));
			InternetAddress[] address = { new InternetAddress("test@test.ru") };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Jakarta Mail APIs Test");
			msg.addHeader("x-cloudmta-class", "standard");
			msg.addHeader("x-cloudmta-tags", "demo, example");
			msg.setText("Test Message Content");

			Transport.send(msg);

			System.out.println("Message Sent.");
		} catch (MessagingException ex) {
			throw new RuntimeException(ex);
		}
	}

}
