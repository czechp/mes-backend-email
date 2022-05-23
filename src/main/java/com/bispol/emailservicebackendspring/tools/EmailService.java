package com.bispol.emailservicebackendspring.tools;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service()
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final Logger logger;

    @Autowired()
    EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.logger = LoggerFactory.getLogger(EmailService.class);
    }

    public void sendEmails(String from, String subject, String content, List<String> emails) {
        emails
                .forEach(e -> {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
                    try {
                        mimeMessageHelper.setFrom(from);
                        mimeMessageHelper.setTo(e);
                        mimeMessageHelper.setSubject(subject);
                        mimeMessageHelper.setText(content, true);
                        new Thread(new EmailSendThread(mimeMessage, e))
                                .start();
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                });
    }

    class EmailSendThread implements Runnable {
        private final MimeMessage message;
        private final String address;

        EmailSendThread(MimeMessage message, String address) {
            this.message = message;
            this.address = address;

        }

        @SneakyThrows
        @Override
        public void run() {
            javaMailSender.send(message);
            logger.info("Email sent from: {}  to {}", message.getFrom(), address);
        }
    }
}
