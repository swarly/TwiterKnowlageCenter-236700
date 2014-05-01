package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DailyTweetDataTest
{

	@Test
	public void testDailyTweetData()
	{
		final AbstractDailyTweetData $ = new MutableDailyTweetData();
		assertEquals(0, $.getRetweeted());
		assertEquals(0, $.getTotalTweets());
	}

	@Test
	public void testAddRetweeted()
	{
		final MutableDailyTweetData $ = new MutableDailyTweetData();
		$.addTweet(new Tweet("06/04/2014 11:00:00, 40624256"));
		assertEquals(1, $.getTotalTweets());
		$.addTweet(new Tweet("06/04/2014 11:05:00, 40624257, 40624256"));
		$.addTweet(new Tweet("06/04/2014 11:07:00, 40624256, 40624257"));
		assertEquals(2, $.getRetweeted());
		assertEquals(3, $.getTotalTweets());
	}

	@Test
	public void testAddOriginalTweet()
	{
		final MutableDailyTweetData $ = new MutableDailyTweetData();
		assertEquals(0, $.getTotalTweets());
		$.addTweet(new Tweet("06/04/2014 11:00:00, 40624256"));
		$.addTweet(new Tweet("06/04/2014 11:01:00, 40624257,40624256"));
		assertEquals(1, $.getRetweeted());
		assertEquals(2, $.getTotalTweets());
	}

	@Test
	public void testImutableDailyData()
	{
		final AbstractDailyTweetData $ = new ImutableDailyTweenData(4, 3);
		assertEquals($.getRetweeted(), 3);
		assertEquals($.getTotalTweets(), 4);

	}

	@Test(expected = RuntimeException.class)
	public void testInconsistentValues()
	{
		new ImutableDailyTweenData(2, 3);
	}
}
