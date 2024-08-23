package com.scope.mainproject.service;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.scope.mainproject.model.User;
import com.scope.mainproject.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;



@Service
public class UserService {
		@Autowired
		private UserRepository repo;
		
		public void insert(User us) {
			repo.save(us);
		}
		@Autowired
		private JavaMailSender sender;
		
		public void register(User user,String siteUrl) throws UnsupportedEncodingException, MessagingException {
			String randomCode=RandomString.make(64);
			user.setVerificationcode(randomCode);
			user.setEnabled(false);
			repo.save(user);
			sendVerificationEmail(user,siteUrl);
		}
		public void sendVerificationEmail(User user,String siteUrl) throws MessagingException,UnsupportedEncodingException {
				String toaddr=user.getEmail();
				String fromaddr="sandrasajusl2019@gmail.com";
				String senderName="SCOPE INDIA";
				String subject="Verify Registration";
				String message="Dear [[name]] please click here to verify your account <br> <h3><a href=\"[[URL]]\" target = \"_blank\">VERIFY</a></h3>";
				MimeMessage msg=sender.createMimeMessage();
				MimeMessageHelper messageHelper=new MimeMessageHelper(msg);
				
				messageHelper.setFrom(fromaddr,senderName);
				messageHelper.setTo(toaddr);
				messageHelper.setSubject(subject);
				message=message.replace("[[name]]",user.getFirstname());
				String url=siteUrl+"/verify?code="+user.getVerificationcode();
				message=message.replace("[[URL]]",url);
				messageHelper.setText(message,true);
				sender.send(msg);
				
				
		}
public boolean verify(String verificationcode) {
	User user=repo.findByVerificationcode(verificationcode);
	if(user==null||user.isEnabled()) {
			return false;
	}else {
		user.setVerificationcode(null);
		user.setEnabled(true);
		repo.save(user);
		return true;
		
	}
	}
  
	
	public void sendEmail(String toAddress,String otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(toAddress);
		message.setSubject("Otp For Verification");
		message.setText("Your  verification code is "+ otp);
		sender.send(message);
	
	
	
}
//public User findByEmail(String email) {
	
}

