package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TweetTest
{

	@Test
	public void testGetOriginalDate()
	{
		final SimpleDateFormat format = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");
		final Date date = new Date(1000 * (System.currentTimeMillis() / 1000));
		final ITweet tweet = new RawTweet(format.format(date) + ", 123");
		final Calendar now = Calendar.getInstance();
		now.setTime(date);
		final Calendar recievedDate = Calendar.getInstance();
		recievedDate.setTime(tweet.getOriginalDate());

		assertEquals(now.get(Calendar.YEAR), recievedDate.get(Calendar.YEAR));
		assertEquals(now.get(Calendar.MONTH), recievedDate.get(Calendar.MONTH));
		assertEquals(now.get(Calendar.DAY_OF_WEEK),
				recievedDate.get(Calendar.DAY_OF_WEEK));
		assertEquals(now.get(Calendar.HOUR), recievedDate.get(Calendar.HOUR));
		assertEquals(now.get(Calendar.MINUTE),
				recievedDate.get(Calendar.MINUTE));
		assertEquals(now.get(Calendar.SECOND),
				recievedDate.get(Calendar.SECOND));
	}

	@Test
	public void testGetOriginalTweet()
	{
		final ITweet tweet = new RawTweet("01/01/2014 00:00:00,123,234");
		assertEquals(tweet.getOriginalTweetID(), "234");
	}

	@Test
	public void testGetTweetedDay()
	{
		final SimpleDateFormat format = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		final ITweet tweet = new RawTweet(format.format(calendar.getTime())
				+ ", 123");
		assertEquals(tweet.getTweetedDay(), 1);
	}

}
