package com.cepedi.curso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
  private static final Logger LOG=LoggerFactory.getLogger(MockEmailService.class);

  @Override
  public void sendEmail(SimpleMailMessage msg) {
    LOG.info("Finge que enviou");
    LOG.info(msg.toString());
    LOG.info("Finge que concluimos o envio");
  }
}
