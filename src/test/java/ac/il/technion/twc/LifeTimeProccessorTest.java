package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LifeTimeProccessorTest
{

	@Test
	public void testAddTweet()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	@Test
	public void testsingleTweet()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	// testing retweet should be 0
	@Test
	public void TwoTweetTestRetweet()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,234"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,123,234"));
		assertEquals(proccesor.getTweetLifeTime("123"), 0);
	}

	// testing retweet should be 0
	@Test
	public void TwoTweetTestOriginal()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,234"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,123,234"));
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
	}

	// original have 1
	@Test
	public void ThreeTweetsTestOriginal()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:02,321,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 2000);

	}

	@Test
	public void ThreeTweetTestChainTweeting()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:02,321,234"));
		assertEquals(proccesor.getTweetLifeTime("123"), 2000);
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}

	@Test
	public void FourTweetTestChainTweetingLongerPath()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:03,321,234"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:02,664,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 3000);
		assertEquals(proccesor.getTweetLifeTime("234"), 2000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}

	@Test
	public void FourTweetTestChainTweetingShorterPath()
	{
		final GraphTweetLifeTimeProccesor proccesor = new GraphTweetLifeTimeProccesor();
		assertNotNull(proccesor);
		proccesor.addTweet(new Tweet("01/01/2014 00:00:00,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:01,234,123"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:02,321,234"));
		proccesor.addTweet(new Tweet("01/01/2014 00:00:03,664,123"));
		assertEquals(proccesor.getTweetLifeTime("123"), 3000);
		assertEquals(proccesor.getTweetLifeTime("234"), 1000);
		assertEquals(proccesor.getTweetLifeTime("321"), 0);
	}
}
