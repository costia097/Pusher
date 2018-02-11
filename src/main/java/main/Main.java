package main;

import main.config.DbConfig;
import main.config.MailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DbConfig.class, MailConfig.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


