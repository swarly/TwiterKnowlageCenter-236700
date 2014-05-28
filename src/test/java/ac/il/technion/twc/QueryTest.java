package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import ac.il.technion.twc.impl.IHashTag;
import ac.il.technion.twc.impl.QueryRunnerImpl;
import ac.il.technion.twc.impl.tweet.ITweet;

import com.google.common.collect.Lists;

public class QueryTest
{

	@Test
	public void testGetAllTweets()
	{

	}

	@Test
	public void testGetHashtagPopularity()
	{
		final IHashTag tag = Mockito.mock(IHashTag.class);
		Mockito.when(tag.getName()).thenReturn("tag");
		final ITweet iTweetRoot = Mockito.mock(ITweet.class);
		Mockito.when(iTweetRoot.isOriginal()).thenReturn(true);
		Mockito.when(iTweetRoot.getId()).thenReturn("1");
		Mockito.when(iTweetRoot.getHashTags()).thenReturn(Lists.newArrayList(tag));
		final ITweet iTweetChild = Mockito.mock(ITweet.class);
		Mockito.when(iTweetChild.isOriginal()).thenReturn(false);
		Mockito.when(iTweetChild.getId()).thenReturn("2");
		Mockito.when(iTweetChild.getOriginalTweetID()).thenReturn("1");
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		runner.addTweet(iTweetRoot);
		runner.addTweet(iTweetChild);
		assertEquals(1, runner.getHashtagPopularity(tag));
	}

	@Test
	public void testGetLifetimeOfTweets()
	{
		final ITweet tweet = Mockito.mock(ITweet.class);
		Mockito.when(tweet.getId()).thenReturn("1");
		Mockito.when(tweet.getLifeTime()).thenReturn((long) 4000);
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		assertEquals(0, runner.getLifetimeOfTweets("1"));
		runner.addTweet(tweet);
		assertEquals(4000, runner.getLifetimeOfTweets("1"));
	}

	@Test
	public void testAddTweet()
	{
		final ITweet tweet = Mockito.mock(ITweet.class);
		Mockito.when(tweet.getId()).thenReturn("1");
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		runner.addTweet(tweet);
		Mockito.verify(tweet).getId();
		Mockito.verify(tweet).isOriginal();
		assertEquals(1, runner.getAllTweets().size());
	}

	@Test
	public void testAddAll()
	{
		final ITweet tweet = Mockito.mock(ITweet.class);
		Mockito.when(tweet.getId()).thenReturn("1");
		final QueryRunnerImpl runner = new QueryRunnerImpl();
		runner.addTweet(tweet);
		runner.addTweet(tweet);
		Mockito.verify(tweet, Mockito.times(2)).getId();
		Mockito.verify(tweet, Mockito.times(2)).isOriginal();
		assertEquals(1, runner.getAllTweets().size());
	}

}
