package main;

import main.config.DbConfig;
import main.config.JmsConfig;
import main.config.MailConfig;
import main.config.MvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@Import({DbConfig.class, MailConfig.class, JmsConfig.class, MvcConfig.class})
@EnableJms
public class Pusher {
    public static void main(String[] args) {
        SpringApplication.run(Pusher.class, args);
    }
}


