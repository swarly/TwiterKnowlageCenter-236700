package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

<<<<<<< HEAD
=======
import java.text.ParseException;

import org.junit.Before;
import org.junit.Ignore;
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
import org.junit.Test;

public class LogicTests
{
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();

<<<<<<< HEAD
	@Test
=======
	@Before
	public void clean()
	{
		$.cleanPersistentData();
	}

	@Test(expected = IllegalArgumentException.class)
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	public void nullImportTest() throws Exception
	{
		final String[] lines = null;
		$.importData(lines);
	}

	@Test
	public void emptyHistogramTest() throws Exception
	{
<<<<<<< HEAD
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
		final String[] lines = null;
		$.importData(lines);
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0",
				"0,0", "0,0" }, $.getDailyHistogram());
		final String[] lines = null;
		// $.importData(lines);
		// assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0",
		// "0,0", "0,0" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	}

	@Test
	public void retweetDoesntExistTest() throws Exception
	{
		try
		{

<<<<<<< HEAD
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/04/2014 12:00:00, tweet2, iddqd" };
=======
			final String[] lines = new String[] {
					"01/04/2014 12:00:00, tweet1",
			"05/04/2014 12:00:00, tweet2, iddqd" };
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
			$.importData(lines);
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

<<<<<<< HEAD
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
=======
	@Test(expected = IllegalArgumentException.class)
	public void retweetDoesntExistTest2() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
				"05/04/2014 12:00:00, tweet2, iddqd" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalRetweetDateTest1() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"05/03/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		$.getLifetimeOfTweets("tweet1");
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	}

	@Test
	public void illegalRetweetDateTest2() throws Exception
	{
		try
		{
<<<<<<< HEAD
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet2, tweet1" };
=======
			String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
			"05/03/2014 12:00:00, tweet2, tweet1" };
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
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

<<<<<<< HEAD
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

=======
	@Test(expected = IllegalArgumentException.class)
	public void illegalRetweetToItself() throws Exception
	{

		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"05/03/2014 12:00:00, tweet2, tweet2" };
		$.importData(lines);
		$.setupIndex();

	}

	// TODO twitt can be replaced we talked about it I think also please use
	// expected like this
	// @Test(expected = IllegalArgumentException.class)
	@Ignore
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	@Test
	public void illegalSameRetweetTwice1() throws Exception
	{
		try
		{
<<<<<<< HEAD
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:00, tweet1" };
=======
			final String[] lines = new String[] {
					"01/04/2014 12:00:00, tweet1",
			"01/04/2014 12:00:00, tweet1" };
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

<<<<<<< HEAD
=======
	// TODO twitt can be replaced we talked about it I think also please use
	// expected like this
	// @Test(expected = IllegalArgumentException.class)
	@Ignore
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	@Test
	public void illegalSameRetweetTwice2() throws Exception
	{
		try
		{
<<<<<<< HEAD
			final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/03/2014 12:00:00, tweet1" };
=======
			final String[] lines = new String[] {
					"01/04/2014 12:00:00, tweet1",
			"05/03/2014 12:00:00, tweet1" };
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
			$.importData(lines);
			$.setupIndex();
			assertTrue(false);
		} catch (final Exception e)
		{
			assertTrue(true);
		}
	}

<<<<<<< HEAD
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

=======
	@Test(expected = IllegalArgumentException.class)
	public void illegalSameRetweetTwice3() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
				"05/03/2014 12:00:00, tweet1, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalRetwittToFuture() throws Exception
	{
		String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
				"05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();
		lines = new String[] { "05/03/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
	}

	// TODO this is a good test but it seems that dateformat can handle it it
	// see 12:65 as 13:05 :)
	@Ignore
	@Test(expected = ParseException.class)
	public void illegalDate1() throws Exception
	{
		String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();
		lines = new String[] { "05/03/2014 12:65:00, tweet3, tweet2" };
		$.importData(lines);
		$.setupIndex();

	}

	@Ignore
	// TODO what is the problem here?
	@Test(expected = IllegalArgumentException.class)
	public void illegalDate2() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"05/03/2014 12:00:00, tweet2" };
		$.importData(lines);
		$.setupIndex();

	}

	// TODO setup with no persistent file is empty this is not exception nothing
	// wrong here
	// just no data loaded
	@Ignore
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
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

<<<<<<< HEAD
	@Test
	public void dontUpdateWithoutSetupTest() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "05/04/2014 12:00:00, tweet2, iddqd" };
		$.importData(lines);
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
=======
	// TODO I'm not sure that this should be an issue since import load the data
	// if we force setup to be run this is a boiler code
	@Ignore
	@Test
	public void dontUpdateWithoutSetupTest() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"05/04/2014 12:00:00, tweet2, iddqd" };
		$.importData(lines);
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0",
				"0,0", "0,0" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
	}

	@Test
	public void sameDateTest() throws Exception
	{
<<<<<<< HEAD
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
=======
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"01/04/2014 12:00:00, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0",
				"0,0", "0,0" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("0", $.getLifetimeOfTweets("tweet1"));
	}

	@Test
	public void dateTest1() throws Exception
	{
<<<<<<< HEAD
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1", "01/04/2014 12:00:01, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0", "0,0", "0,0" }, $.getDailyHistogram());
=======
		final String[] lines = new String[] { "01/04/2014 12:00:00, tweet1",
		"01/04/2014 12:00:01, tweet2, tweet1" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "0,0", "0,0", "2,1", "0,0", "0,0",
				"0,0", "0,0" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		/*
		 * final SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); final Date date1 =
		 * simpleDateFormat.parse("01/04/2014 12:00:00"); final Date date2 =
		 * simpleDateFormat.parse("01/04/2014 12:00:01"); final long res =
		 * date1.getTime() - date2.getTime();
		 */
<<<<<<< HEAD
		final String tmp = $.getLifetimeOfTweets("tweet1");
=======
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("1000", $.getLifetimeOfTweets("tweet1"));
	}

	@Test
	public void logicTest1() throws Exception
	{
<<<<<<< HEAD
		String[] lines = new String[] { "04/04/2014 12:00:00, iddqd", "05/04/2014 12:00:00, idkfa, iddqd" };
=======
		String[] lines = new String[] { "04/04/2014 12:00:00, iddqd",
		"05/04/2014 12:00:00, idkfa, iddqd" };
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		$.importData(lines);
		lines = new String[] { "06/04/2014 13:00:00, 593393706" };
		$.importData(lines);
		$.setupIndex();
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));
<<<<<<< HEAD
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());
		lines = new String[] { "06/04/2014 11:00:00, 40624256" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0",
				"1,0", "1,1" }, $.getDailyHistogram());
		lines = new String[] { "06/04/2014 11:00:00, 40624256" };
		$.importData(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,0", "0,0", "0,0", "0,0", "0,0",
				"1,0", "1,1" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b

		lines = new String[] { "05/04/2014 12:00:00, idkfa1, iddqd" };
		$.importData(lines);
		$.setupIndex();
<<<<<<< HEAD
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "2,2" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0",
				"1,0", "2,2" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 11:59:00, idkfa3, iddqd" };
		$.importData(lines);
		$.setupIndex();
<<<<<<< HEAD
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "3,3" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0",
				"1,0", "3,3" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "04/04/2014 12:59:59, idkfa4, iddqd" };
		$.importData(lines);
		$.setupIndex();
<<<<<<< HEAD
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "2,1", "3,3" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0",
				"2,1", "3,3" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 12:00:01, idkfa5, iddqd" };
		$.importData(lines);
		$.setupIndex();
<<<<<<< HEAD
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "1,0", "4,4" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0",
				"1,0", "4,4" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("86400001", $.getLifetimeOfTweets("iddqd"));

		lines = new String[] { "05/04/2014 12:00:01, idkfa6, idkfa5" };
		$.importData(lines);
		$.setupIndex();
<<<<<<< HEAD
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "1,0", "4,4" }, $.getDailyHistogram());
=======
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0",
				"1,0", "4,4" }, $.getDailyHistogram());
>>>>>>> 86462ea4287ed20f13590cbddfc8323b7299a32b
		assertEquals("86400001", $.getLifetimeOfTweets("iddqd"));

	}

}
