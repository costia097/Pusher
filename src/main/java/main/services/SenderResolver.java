package main.services;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SenderResolver {

    private static Map<String, String> senders = new HashMap<>();

    @PostConstruct
    public void init() {
        senders.put("104kteam@gmail.com", "knedliks");
        senders.put("asdjjdadalvlf@gmail.com", "asdjjdadalvlf123");
    }

    public void resolveSender(JavaMailSenderImpl javaMailSender, Integer position) {
        List<Map.Entry<String, String>> listOfDb = new ArrayList<>(senders.entrySet());
        Map.Entry<String, String> targetEntry = listOfDb.get(position);
        javaMailSender.setUsername(targetEntry.getKey());
        javaMailSender.setPassword(targetEntry.getValue());
    }
}
