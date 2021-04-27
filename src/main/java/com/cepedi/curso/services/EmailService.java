package com.cepedi.curso.services;

import com.cepedi.curso.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
  
  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);
}
