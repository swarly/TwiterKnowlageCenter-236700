package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ac.il.technion.twc.api.TwitterKnowledgeCenter;

public class LogicTests
{
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();

	@Before
	public void clean()
	{
		$.cleanPersistentData();
	}

	@After
	public void cleanAfter()
	{
		$.cleanPersistentData();
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullImportTest() throws Exception
	{
		final String[] lines = null;
		$.importData(lines);
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyHistogramTest() throws Exception
	{
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		final String[] lines = null;
		$.importData(lines);
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalRetweetDateTest1() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalRetweetToItself() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet2" };
		$.importData(lines);
		$.setupIndex();
	}

	@Ignore
	@Test
	public void illegalSameRetweetTwice1() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:00, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	// expected like this
	// @Test(expected = IllegalArgumentException.class)
	@Ignore
	@Test
	public void illegalSameRetweetTwice2() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalSameRetweetTwice3() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet1, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalDate2() throws Exception
	{
		String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();
		lines = new String[] { "05/03/2014, tweet3, tweet2" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test
	public void illegalDate4() throws Exception
	{
		String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();
		lines = new String[] { "05/03/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();

	}

	@Test
	public void illegalDate5() throws Exception
	{
		String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();
		lines = new String[] { "06/03/2014 13:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	// this should raise error , because retweets must be LATER than the
	// original tweet.
	@Test(expected = IllegalArgumentException.class)
	public void sameDateTest() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test
	public void dateTest1() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:01, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		assertEquals("1000", $.getLifetimeOfTweets("tweet1"));
	}

}
