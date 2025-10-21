package br.com.renata.messages;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailMessage {

	private static final String ACCOUNT = "desenvolvedorarenatanunes@gmail.com";
	private static final String PASSWORD = "hgix kvqg rkul mzkt"; 
	private static final String SMTP = "smtp.gmail.com";
	private static final Integer PORT = 587;
	
	public static void sendMessage(final String to, final String subject, final String body) throws Exception {
		
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setUsername(ACCOUNT);
		senderImpl.setPassword(PASSWORD);
		senderImpl.setHost(SMTP);
		senderImpl.setPort(PORT);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.transport.protocol", "smtp");
		senderImpl.setJavaMailProperties(properties);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setFrom(ACCOUNT); 
				helper.setTo(to); 
				helper.setSubject(subject); 
				helper.setText(body);
			}
		};
		
		senderImpl.send(messagePreparator);
	}
}