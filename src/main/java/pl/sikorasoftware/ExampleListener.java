package pl.sikorasoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * Created by robertsikora on 19.07.2016.
 */
public class ExampleListener implements MessageListener {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void onMessage(final Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println(((TextMessage) message).getText());
                //do response
                final Destination responseDest = message.getJMSReplyTo();
                if(responseDest != null){
                    jmsTemplate.send(responseDest, session -> {
                        final Message responseEvent = session.createMessage();
                        responseEvent.setJMSCorrelationID(message.getJMSMessageID());
                        return responseEvent;
                    });
                }
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else if(message instanceof ObjectMessage){
            final ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                System.out.println(objectMessage.getObject());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
