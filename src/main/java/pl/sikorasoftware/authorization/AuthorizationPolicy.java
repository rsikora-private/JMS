package pl.sikorasoftware.authorization;

import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.Message;
import org.apache.activemq.security.MessageAuthorizationPolicy;
import pl.sikorasoftware.SerializableObject;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by robertsikora on 23.07.2016.
 */
public class AuthorizationPolicy implements MessageAuthorizationPolicy {
    @Override
    public boolean isAllowedToConsume(ConnectionContext connectionContext, Message message) {
        try {
            return null != message.getProperty("active");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String ... args) {
        bigDecimal();

        SerializableObject serializable = test();
        System.out.println(serializable);
    }

    private static void bigDecimal(){
        new BigDecimal(11) {
            public void addOne() {
                System.out.println("add one");
            }

        }.addOne();
    }

    public static <T extends Serializable & Closeable> T test(){
        return (T) new SerializableObject("ala");
    }
}
