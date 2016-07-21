package pl.sikorasoftware.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Created by robertsikora on 18.07.2016.
 */

@Configuration
@ConditionalOnClass(JmsTemplate.class)
public class DestinationConfiguration {
    @Bean(name="queue.test")
    public Destination queue(){
        return new ActiveMQQueue("queue.test");
    }

    @Bean(name="queue.test.response")
    public Destination queueResponse(){
        return new ActiveMQQueue("queue.test.response");
    }
}
