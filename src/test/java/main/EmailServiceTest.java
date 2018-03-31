package main;

import main.services.EmailService;
import main.services.SenderResolver;
import main.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest {
    @Mock
    private  SenderResolver senderResolver;
    @Mock
    private JavaMailSenderImpl javaMailSender;
    @Mock
    private UserService userService;
    @Mock
    private MimeMessage mimeMessage;

    @Test
    public void testsendEmailToPerson() throws MessagingException {
        EmailService emailService = new EmailService(senderResolver, javaMailSender, userService);
        String to = "to";
        String subject = "subject";
        String text = "text";
        doNothing().when(senderResolver).resolveSender(any(),any());
        when(javaMailSender.createMimeMessage()).thenReturn(null);
        emailService.sendEmailToPerson(to, subject, text);
        verify(javaMailSender).send(mimeMessage);
    }
}
