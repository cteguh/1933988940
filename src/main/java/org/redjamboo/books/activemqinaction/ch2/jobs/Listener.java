/**
 * @project 1933988940
 * @author Christian Teguh
 * @created Jun 27, 2015 7:22:46 PM
 *
 */
package org.redjamboo.books.activemqinaction.ch2.jobs;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class Listener implements MessageListener {

private String job;
	
	public Listener(String job) {
		this.job = job;
	}

	public void onMessage(Message message) {
		try {
			//do something here
			System.out.println(job + " id:" + ((ObjectMessage)message).getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
