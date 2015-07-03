/**
 * @project 1933988940
 * @author Christian Teguh
 * @created Jul 3, 2015 4:24:37 PM
 *
 */
package org.redjamboo.books.activemqinaction.ch2.portfolio;

import java.text.DecimalFormat;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class Listener implements MessageListener {

	public void onMessage(Message message) {
		try {
			MapMessage map = (MapMessage)message;
			String stock = map.getString("stock");
			double price = map.getDouble("price");
			double offer = map.getDouble("offer");
			boolean up = map.getBoolean("up");
			DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );
			System.out.println(stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
