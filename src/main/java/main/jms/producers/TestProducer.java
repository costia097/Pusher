package main.jms.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class TestProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void send() {
        for (int i = 0; i < 1000; i++) {
            jmsMessagingTemplate.convertAndSend(queue, "Message");
        }
    }
}
