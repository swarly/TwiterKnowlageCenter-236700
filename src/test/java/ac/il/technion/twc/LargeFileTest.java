package ac.il.technion.twc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ac.il.technion.twc.api.TwitterKnowledgeCenter;

public class LargeFileTest
{
	TwitterKnowledgeCenter tkc;
	String lines[];

	@Before
	public void initMilionTweets()
	{
		tkc = new TwitterKnowledgeCenter();
		lines = new String[1000000];
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		for (int i = 0; i < 1000000; i++)
			lines[i] = dateFormat.format(new Date(i * 1000)) + ", " + i;
	}

	@Ignore
	@Test(timeout = 30 * 60 * 1000)
	public void test() throws Exception
	{
		tkc.importData(lines);

	}

	@After
	public void clean()
	{
		tkc.cleanPersistentData();
	}

	@Test
	public void testDate()
	{
		final SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		final SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("EEE");
		final Date formattedDate;
		final Date parsedDate;
		try
		{
			final Date test1 = targetFormat.parse("11/12/2014 08:07:22");
			final Date test2 = simpleDateFormat2.parse("Wed May 15 10:08:07 2013");
			test2.toString();
		} catch (final ParseException e)
		{
			e.printStackTrace();
		}

	}
}