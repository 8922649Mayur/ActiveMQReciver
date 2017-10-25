package Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;

public class MessageReciver {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "JCG_QUEUE";

	public static void main(String[] args) throws JMSException {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		ConsumeMessageClass task1 = new ConsumeMessageClass(url, subject);

		System.out.println("The time is : " + new Date());

		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 1, 1,
				TimeUnit.SECONDS);

		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

	}
}