/**
 * @project 1933988940
 * @author Christian Teguh
 * @created Jul 3, 2015 4:28:36 PM
 *
 */
package org.redjamboo.books.activemqinaction.ch3;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.redjamboo.books.activemqinaction.ch2.portfolio.Listener;

public class Consumer {

	private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;
    
    public Consumer(String brokerURL) throws JMSException {
    	factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }    
    
    public static void main(String[] args) throws JMSException {
    	if (args.length == 0) {
    		System.err.println("Please define connection URI!");
    		return;
    	}
    	
    	//define connection URI
    	Consumer consumer = new Consumer(args[0]);
    	
    	//extract topics from the rest of arguments
    	String[] topics = new String[args.length - 1];
    	System.arraycopy(args, 1, topics, 0, args.length - 1);
    	for (String stock : topics) {
    		Destination destination = consumer.getSession().createTopic("STOCKS." + stock);
    		MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
    		messageConsumer.setMessageListener(new Listener());
    	}
    }
	
	public Session getSession() {
		return session;
	}
}
