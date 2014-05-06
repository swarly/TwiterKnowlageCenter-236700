package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ac.il.technion.twc.tweet.TweetFactory;

public class LifeTimeProccessorTest
{

	private final TweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();

	@Test
	public void testTweetNotExist()
	{
		assertNotNull(proccesor);
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	@Test
	public void testAddTweet()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	@Test
	public void testsingleTweet()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	// testing retweet should be 0
	@Test
	public void TwoTweetTestRetweet()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,234"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,123,234"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	// testing retweet should be 0
	@Test
	public void TwoTweetTestOriginal()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,234"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,123,234"));
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
	}

	// original have 1
	@Test
	public void ThreeTweetsTestOriginal()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:02,321,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 2000);

	}

	@Test
	public void ThreeTweetTestChainTweeting()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:02,321,234"));
		assertEquals(proccesor.getTweetLifeTime("123"), 2000);
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}

	@Test
	public void FourTweetTestChainTweetingLongerPath()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:03,321,234"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:02,664,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 3000);
		assertEquals(proccesor.getTweetLifeTime("234"), 2000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}

	@Test
	public void FourTweetTestChainTweetingShorterPath()
	{
		assertNotNull(proccesor);
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:00,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:02,321,234"));
		proccesor.addTweet(TweetFactory
				.getTweetFromLine("01/01/2014 00:00:03,664,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 3000);
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}
}
