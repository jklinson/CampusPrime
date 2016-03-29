package utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.UsersObjects;

public class SendEmail {
	 public static void sendEmail(UsersObjects usersObjects)
	   {
		 	System.out.println("in send mail function");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
	
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Constants.FROM_MAIL,Constants.FROM_MAIL_PASSWORD);
					}
				});
	
			try {
	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Constants.FROM_MAIL));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(usersObjects.getEmail()));
				message.setSubject("Campus Prime");
				message.setText("Dear "+usersObjects.getName()+"," + 
						"\n\n To activate your account please click on below given link."
						+ "\n\n" + Constants.MAIL_LINK+usersObjects.getUserId());
	
				Transport.send(message);
	
				System.out.println("Done");
	
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
}
