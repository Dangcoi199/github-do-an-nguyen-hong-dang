package com.nguyenhongdang.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtil {
	@Autowired
	public JavaMailSender emailSender;

	public void SendMail(String toEmail , String content,String title) {
		MimeMessage message = emailSender.createMimeMessage();

		boolean multipart = true;
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
			message.setContent(content, "text/html;charset=UTF-8");

			helper.setTo(toEmail);

			helper.setSubject(title);

			this.emailSender.send(message);
		} catch (MessagingException e) {
			// TODO: handle exception
		}

		
	}
}
