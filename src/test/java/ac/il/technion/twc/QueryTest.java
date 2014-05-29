package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ac.il.technion.twc.impl.IHashTag;
import ac.il.technion.twc.impl.QueryRunnerImpl;
import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;
import ac.il.technion.twc.impl.tweet.TweetType;

import com.google.common.collect.Lists;

public class QueryTest
{
	QueryRunnerImpl $;
	private ITweet tweet;

	@Before
	public void init()
	{
		$ = new QueryRunnerImpl();
		tweet = Mockito.mock(ITweet.class);
		Mockito.when(tweet.getId()).thenReturn("1");
	}

	@Test
	public void testGetAllTweets()
	{

	}

	@Test
	public void testGetHashtagPopularity()
	{
		final IHashTag tag = Mockito.mock(IHashTag.class);
		Mockito.when(tag.getName()).thenReturn("tag");
		Mockito.when(tweet.isOriginal()).thenReturn(true);
		Mockito.when(tweet.getHashTags()).thenReturn(Lists.newArrayList(tag));
		Mockito.when(tweet.getType()).thenReturn(TweetType.TypeJson);
		final ITweet iTweetChild = Mockito.mock(ITweet.class);
		Mockito.when(iTweetChild.isOriginal()).thenReturn(false);
		Mockito.when(iTweetChild.getId()).thenReturn("2");
		Mockito.when(iTweetChild.getOriginalTweetID()).thenReturn("1");

		$.addTweet(tweet);
		$.addTweet(iTweetChild);
		assertEquals(1, $.getHashtagPopularity(tag));
	}

	@Test
	public void testGetLifetimeOfTweets()
	{
		Mockito.when(tweet.getLifeTime()).thenReturn((long) 4000);
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		assertEquals(0, runner.getLifetimeOfTweets("1"));
		runner.addTweet(tweet);
		assertEquals(4000, runner.getLifetimeOfTweets("1"));
	}

	@Test
	public void testGetLifetimeOfTweetsMixed()
	{
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		assertEquals(0, runner.getLifetimeOfTweets("123"));
		runner.addTweet(TweetFactory.newTweetFromLine("01/01/2014 00:00:00,123"));
		assertEquals(0, runner.getLifetimeOfTweets("123"));
	}

	@Test
	public void testAddTweet()
	{
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		runner.addTweet(tweet);
		Mockito.verify(tweet).getId();
		Mockito.verify(tweet).isOriginal();
		assertEquals(1, runner.getAllTweets().size());
	}

	@Test
	public void testAddAll()
	{
		$.addTweet(tweet);
		$.addTweet(tweet);
		Mockito.verify(tweet, Mockito.times(2)).getId();
		Mockito.verify(tweet, Mockito.times(2)).isOriginal();
		assertEquals(1, $.getAllTweets().size());
	}

}
