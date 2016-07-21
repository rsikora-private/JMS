package pl.sikorasoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by robertsikora on 18.07.2016.
 */

@Component
public class JmsProducer {
    private static final String EVERY_FIVE_SEC = "*/1 * * * * *";
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name="queue.test")
    private Destination destination;
    @Resource(name="queue.test.response")
    private Destination responseDestination;

    @Scheduled(cron = EVERY_FIVE_SEC)
    public void tick(){
        System.out.println("tick()");
        jmsTemplate.send(destination, session -> {
            final TextMessage textMessage = session.createTextMessage("hej");
            textMessage.setBooleanProperty("active", true);
            textMessage.setJMSReplyTo(responseDestination);
            return textMessage;
        });
    }
}
