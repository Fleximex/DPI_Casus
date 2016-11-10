/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Maarten
 */
public class MessageReceiverGateway
{
    private final Connection connection; // connection to the JMS server
    private final Session session; // JMS session for creating producers, consumers and messages
    private final Properties props = new Properties();
    private final MessageConsumer messageConsumer;

    public MessageReceiverGateway(String consumer) throws JMSException, NamingException
    {
        // connect to JMS
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        props.put(("queue." + consumer), consumer);

        // init connection
        Context jndiContext = new InitialContext(props);
        ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // connect to the receiver channel and register as a listener on it
        Destination receiverDestination = (Destination) jndiContext.lookup(consumer);
        messageConsumer = session.createConsumer(receiverDestination);
    }

    public void setReceivedMessageListener(MessageListener ml) throws JMSException
    {
        messageConsumer.setMessageListener(ml);
    }

    public void openConnection() throws NamingException, JMSException
    {
        connection.start();
    }
    
    public void closeConnection() throws JMSException
    {
        messageConsumer.close();
        connection.close();
    }
}
