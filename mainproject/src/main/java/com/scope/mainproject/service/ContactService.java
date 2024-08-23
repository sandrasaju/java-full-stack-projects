package com.scope.mainproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.scope.mainproject.model.Contact;
import com.scope.mainproject.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository con;
	@Autowired
	private JavaMailSender sender;

	public void add(Contact user) {
		 con.save(user);
		 sendMail(user);
	}
	public void sendMail(Contact se) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo("sandrasajusl2019@gmail.com");
		message.setSubject(se.getSubject());
		message.setText(se.getMessage());
		sender.send(message);
	}
	}

