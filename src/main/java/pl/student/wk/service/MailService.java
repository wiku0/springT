package pl.student.wk.service;

import java.util.Properties;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import pl.student.wk.domain.User;

@Component
public class MailService {

	public MailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setProtocol("smtp");
		mailSender.setUsername("projectspringv1");
		mailSender.setPassword("springspring!");
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}

	public void send(User user) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("projectspringv1@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Activation link");
		message.setText("Link aktywacyjny: http://localhost:8080/wk/ActivationLink?user=" + user.getLogin() + "&token="
				+ user.getEmailToken());
		try {
			mailSender().send(message);
			System.out.println("Mail sent to: " + user.getEmail());
		} catch (Exception e) {
			System.out.println("B³ad podczas wysy³ania maila: " + e);
		}
	}
}
