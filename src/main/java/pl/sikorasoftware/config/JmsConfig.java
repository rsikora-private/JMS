package pl.sikorasoftware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import pl.sikorasoftware.ExampleListener;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;

/**
 * Created by robertsikora on 19.07.2016.
 */

@Configuration
public class JmsConfig {
    @Resource(name="queue.test")
    private Destination destination;

    @Bean
    public ExampleListener listener(){
        return new ExampleListener();
    }

    @Bean
    public DefaultMessageListenerContainer jmsContainer(final ConnectionFactory connectionFactory,
                                                        final MessageListener messageListener){
        final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestination(destination);
        container.setMessageListener(messageListener);
        return container;
    }
}
