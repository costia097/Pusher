package main.services;

import main.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final SenderResolver senderResolver;
    private final JavaMailSenderImpl javaMailSender;
    private final UserService userService;

    @Autowired
    public EmailService(SenderResolver senderResolver, @Qualifier("javaMailSenderImpl") JavaMailSenderImpl javaMailSender, UserService userService) {
        this.senderResolver = senderResolver;
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public void sendEmailToPerson(String to, String subject, String text) {
        try {
            senderResolver.resolveSender(javaMailSender, 0);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("From");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.info("Exception while send email {}", e);
        }
    }

    public String createEmailContentForConfirmRegistration(String name, String email) {
        Optional<User> user = checkOrGetUser(email);
        if (user.isPresent()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hello, ").append("dear ").append(name).append("Link:  ").append("http://localhost:8080/person/confirm/").append(user.get().getUuid());
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public String createEmailContentForForgotPassword(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello, ").append("dear ").append(name).append("Link:  ").append("http://localhost:4200/updatePassword");
        return stringBuilder.toString();
    }

    private Optional<User> checkOrGetUser(String email) {
        User user = userService.findByEmail(email);
        return Optional.of(user);
    }
}
