package main.jms.consumers;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.jms.annotation.JmsListener;
        import org.springframework.stereotype.Component;

@Component
public class TestConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConsumer.class);

    @JmsListener(destination = "sample.queue")
    public void receiveQueueFirst(String text) {
        LOGGER.info("Received first" + text);
    }

    @JmsListener(destination = "sample.queue")
    public void receiveQueueSecond(String text) {
        LOGGER.info("Received second" + text);
    }

    @JmsListener(destination = "sample.queue")
    public void receiveThird(String text) {
        LOGGER.info("Received third" + text);
    }
}
