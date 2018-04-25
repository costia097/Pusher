package main.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import main.annotations.CheckGood;
import main.aop.Proxy;
import main.aop.ProxyInterface;
import main.aop.Target;
import main.entities.Role;
import main.entities.User;
import main.jms.producers.TestProducer;
import main.repositories.UserRepository;
import main.services.SenderResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Proxy.newProxyInstance;

@RestController
@CheckGood("aaa")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    @Qualifier("javaMailSenderImpl")
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private SenderResolver senderResolver;
    @Autowired
    private TestProducer testProducer;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/check")
    @PreAuthorize("hasRole('ADMIN')")
    public void check() {
        LOGGER.info("Auth");
    }

    @GetMapping(value = "/getData")
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> getData() {
        return Arrays.asList("Hello", "From", "Back", "!!!");
    }

    @GetMapping(value = "/send/{position}")
    public boolean send(@PathVariable("position") String position) {
        try {
            senderResolver.resolveSender(javaMailSender, Integer.valueOf(position));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("From");
            mimeMessageHelper.setTo("adaw36909@gmail.com");
            mimeMessageHelper.setSubject("Subject");
            mimeMessageHelper.setText("Text");
            javaMailSender.send(mimeMessage);
            LOGGER.info("suc sended");
            return true;
        } catch (MessagingException e) {
            LOGGER.error("Error");
            return false;
        }
    }

    @GetMapping("/sendInMQ")
    public void inMq() {
        LOGGER.info("SEND");
        testProducer.send();
    }

    public static void main(String[] args) {
        Target target = new Target();
        final Object o = newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{ProxyInterface.class}, new Proxy());
    }
}
