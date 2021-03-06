package com.cepedi.curso.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class SmtpEmailService extends AbstractEmailService {
  @Autowired
  private MailSender mailSender;
  @Autowired
  private JavaMailSender javaMailSender;
  private static final Logger LOG=LoggerFactory.getLogger(MockEmailService.class);

  @Override
  public void sendEmail(SimpleMailMessage msg) {
      mailSender.send(msg);
  }

  public void sendHtmlEmail(MimeMessage msg){
    javaMailSender.send(msg);
  }

  
}
