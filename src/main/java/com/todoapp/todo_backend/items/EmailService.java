package com.todoapp.todo_backend.items;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	
	//@Autowired not required
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@KafkaListener(topics = "todo-added", groupId = "my-consumer-group")
	public void sendAddEmail(String message) {
		
        String to = "u@yopmail.com";
        String subject = "New To-Do Item Added";
        String body =  message;

        //Email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        sendEmail(mailMessage, to);
    }
	
	
	@KafkaListener(topics = "todo-modified", groupId = "my-consumer-group")
	public void sendModifyEmail(String message) {
		
        String to = "u@yopmail.com";
        String subject = "New To-Do Item Added";
        String body =  message;

        //Email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        sendEmail(mailMessage,to);

       
    }
	
	private void sendEmail(SimpleMailMessage mailMessage, String to) {
		 // mail sender
        try {
            mailSender.send(mailMessage);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
	}
	
		
	

}
