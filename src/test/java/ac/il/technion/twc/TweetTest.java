package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

public class TweetTest
{

	@Test
	public void testGetOriginalDate()
	{
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final Date date = new Date(1000 * (System.currentTimeMillis() / 1000));
		final ITweet tweet = TweetFactory.newTweetFromLine(format.format(date) + ", 123");
		final Calendar now = Calendar.getInstance();
		now.setTime(date);
		final Calendar recievedDate = Calendar.getInstance();
		recievedDate.setTime(tweet.getOriginalDate());

		assertEquals(now.get(Calendar.YEAR), recievedDate.get(Calendar.YEAR));
		assertEquals(now.get(Calendar.MONTH), recievedDate.get(Calendar.MONTH));
		assertEquals(now.get(Calendar.DAY_OF_WEEK), recievedDate.get(Calendar.DAY_OF_WEEK));
		assertEquals(now.get(Calendar.HOUR), recievedDate.get(Calendar.HOUR));
		assertEquals(now.get(Calendar.MINUTE), recievedDate.get(Calendar.MINUTE));
		assertEquals(now.get(Calendar.SECOND), recievedDate.get(Calendar.SECOND));
	}

	@Test
	public void testGetOriginalTweet()
	{
		final ITweet tweet = TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123,234");
		assertEquals(tweet.getOriginalTweetID(), "234");
	}

	@Test
	public void testGetTweetedDay()
	{
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		final ITweet tweet = TweetFactory.newTweetFromLine(format.format(calendar.getTime()) + ", 123");
		assertEquals(tweet.getTweetedDay(), 1);
	}

	@Test
	public void testToJsonBasic()
	{
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		final ITweet tweet = TweetFactory.newTweetFromLine(format.format(calendar.getTime()) + ", 123");
		assertTrue(tweet.toJson().toString().contains("\"isOriginal\":true"));

	}

	@Test
	public void testToJsonTestOriginalNotChanged()
	{
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		final ITweet tweet = TweetFactory.newTweetFromLine(format.format(calendar.getTime()) + ", 123");
		final ITweet restoredTwitt = TweetFactory.newStringLineTweetFromJSON(tweet.toJson());
		assertTrue(restoredTwitt.isOriginal());
		assertTrue(!restoredTwitt.toJson().toString().contains(ITweet.originalName));

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testHashTagsRawTweet()
	{
		final ITweet tweet = TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123,234");
		tweet.getHashTags();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testgetTextRawTweet()
	{
		final ITweet tweet = TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123,234");
		tweet.getText();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testHashTagsCompleteTweet()
	{
		final ITweet tweet = TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123,234");
		TweetFactory.newPersistableStringLineTweet(tweet, 0).getHashTags();

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testgetTextCompleteTweet()
	{
		final ITweet tweet = TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123,234");
		TweetFactory.newPersistableStringLineTweet(tweet, 0).getText();
	}

}