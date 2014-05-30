package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ac.il.technion.twc.impl.DailyTweetData;
import ac.il.technion.twc.impl.HistogramImpl;
import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

public class HistogramTest
{
	private HistogramImpl $;
	private ITweet tweet;

	@Before
	public void init()
	{
		$ = new HistogramImpl();
		tweet = Mockito.mock(ITweet.class);
		Mockito.when(tweet.getId()).thenReturn("1");
	}

	@Test
	public void testAddTweet()
	{
		$.addTweet(tweet);
		assertEquals(1, $.size());
	}

	@Test
	public void testAddAll()
	{
		final ITweet iTweet = Mockito.mock(ITweet.class);
		Mockito.when(iTweet.getId()).thenReturn("2");
		$.addAll(Arrays.asList(iTweet, tweet));
		assertEquals(2, $.size());
	}

	@Test
	public void testGetEmptyHistogramAsString()
	{
		assertArrayEquals(new String[] { "0", "0", "0", "0", "0", "0", "0" }, $.getHistogramAsString());
	}

	@Test
	public void testGetOneHistogramAsString()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:00,123"));
		assertArrayEquals(new String[] { "0", "0", "0", "0", "1", "0", "0" }, $.getHistogramAsString());
	}

	@Test
	public void testGetHistogramAsString()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:00,123"));
		$.addTweet(TweetFactory.newTweetFromLine("28/05/2014 00:00:00,23"));
		$.addTweet(TweetFactory.newTweetFromLine("27/05/2014 00:00:00,13"));
		assertArrayEquals(new String[] { "0", "0", "1", "1", "1", "0", "0" }, $.getHistogramAsString());
	}

	@Test
	public void testGetEmptyTemporalHistogramDateDate()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:02,123"));
		assertEquals(Arrays.asList(new DailyTweetData(), new DailyTweetData(), new DailyTweetData(),
				new DailyTweetData(), new DailyTweetData(), new DailyTweetData(), new DailyTweetData()),
				new ArrayList<DailyTweetData>($.getTemporalHistogram("29/5/2014 00:00:00", "29/5/2014 00:00:01")));
	}

	@Test
	public void testGetOneTemporalHistogramDateDate()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:02,123"));
		assertEquals(Arrays.asList(new DailyTweetData(), new DailyTweetData(), new DailyTweetData(),
				new DailyTweetData(), new DailyTweetData(1, 0), new DailyTweetData(), new DailyTweetData()),
				new ArrayList<DailyTweetData>($.getTemporalHistogram("29/5/2014 00:00:00", "29/5/2014 00:00:03")));
	}

	@Test
	public void testGetOneClosedEdgeTemporalHistogramDateDate()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:02,123"));
		assertEquals(Arrays.asList(new DailyTweetData(), new DailyTweetData(), new DailyTweetData(),
				new DailyTweetData(), new DailyTweetData(1, 0), new DailyTweetData(), new DailyTweetData()),
				new ArrayList<DailyTweetData>($.getTemporalHistogram("29/5/2014 00:00:00", "29/5/2014 00:00:02")));
	}

	@Test
	public void testGetOneOfTwoTemporalHistogramDateDate()
	{
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:02,123"));
		$.addTweet(TweetFactory.newTweetFromLine("29/05/2014 00:00:05,1234"));
		assertEquals(Arrays.asList(new DailyTweetData(), new DailyTweetData(), new DailyTweetData(),
				new DailyTweetData(), new DailyTweetData(1, 0), new DailyTweetData(), new DailyTweetData()),
				new ArrayList<DailyTweetData>($.getTemporalHistogram("29/5/2014 00:00:00", "29/5/2014 00:00:02")));
	}

}
