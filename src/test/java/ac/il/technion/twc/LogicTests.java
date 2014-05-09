package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LogicTests
{
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();

	@Test
	public void nullImportTest() throws Exception
	{
		final String[] lines = null;
		$.importData(lines);
	}

	@Test
	public void emptyHistogramTest() throws Exception
	{
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		final String[] lines = null;
		$.importData(lines);
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
	}

	@Test
	public void retweetDoesntExistTest() throws Exception
	{
		try
		{

			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/04/2014 12:00:00, tweet2, iddqd" };
			$.importData(lines);
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void retweetDoesntExistTest2() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/04/2014 12:00:00, tweet2, iddqd" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalRetweetDateTest1() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalRetweetDateTest2() throws Exception
	{
		try
		{
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet1" };
			$.importData(lines);
			$.setupIndex();
			lines = new String[] { "05/03/2014 12:00:00, tweet2, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalRetweetToItself() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet2" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

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

	@Test
	public void illegalSameRetweetTwice3() throws Exception
	{
		try
		{
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet1, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalSameRetweetTwice4() throws Exception
	{
		try
		{
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
			$.importData(lines);
			$.setupIndex();
			lines = new String[] { "05/03/2014 12:00:00, tweet2, tweet1" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalDate1() throws Exception
	{
		try
		{
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
			$.importData(lines);
			$.setupIndex();
			lines = new String[] { "05/03/2014 12:65:00, tweet3, tweet2" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void illegalDate2() throws Exception
	{
		try
		{
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2" };
			$.importData(lines);
			$.setupIndex();
			lines = new String[] { "05/03/2014, tweet3, tweet2" };
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void runSetupOfEmptyFileTest() throws Exception
	{
		try
		{
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}

	}

	@Test
	public void dontUpdateWithoutSetupTest() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/04/2014 12:00:00, tweet2, iddqd" };
		$.importData(lines);
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
	}

	@Test
	public void sameDateTest() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		assertEquals("0", $.getLifetimeOfTweets("tweet1"));
	}

	@Test
	public void dateTest1() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:01, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		/*
		 * final SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); final Date date1 =
		 * simpleDateFormat.parse("01/04/2014 12:00:00"); final Date date2 =
		 * simpleDateFormat.parse("01/04/2014 12:00:01"); final long res =
		 * date1.getTime() - date2.getTime();
		 */
		final String tmp = $.getLifetimeOfTweets("tweet1");
		assertEquals("1000", $.getLifetimeOfTweets("tweet1"));
	}

	@Test
	public void logicTest1() throws Exception
	{
		String[] lines = new String[] { "04/04/2014 12:00:00, iddqd", "05/04/2014 12:00:00, idkfa, iddqd" };
		$.importData(lines);
		lines = new String[] { "06/04/2014 13:00:00, 593393706" };
		$.importData(lines);
		$.setupIndex();
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());
		lines = new String[] { "06/04/2014 11:00:00, 40624256" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());

		lines = new String[] { "05/04/2014 12:00:00, idkfa1, iddqd" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "2,2" }, $.getDailyHistogram());
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 11:59:00, idkfa3, iddqd" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "3,3" }, $.getDailyHistogram());
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "04/04/2014 12:59:59, idkfa4, iddqd" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "2,1", "3,3" }, $.getDailyHistogram());
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 12:00:01, idkfa5, iddqd" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "1,0", "4,4" }, $.getDailyHistogram());
		assertEquals("86400001", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 12:00:01, idkfa6, idkfa5" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "1,0", "4,4" }, $.getDailyHistogram());
		assertEquals("86400001", $.getLifetimeOfTweets("iddqd"));

	}

}
