package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ac.il.technion.twc.api.TwitterKnowledgeCenter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class LoadTweetFileTest
{
	private static final String SMALL_TEST_FILE = "small_sample.txt";
	private static final String LARGE_TEST_FILE = "large_sample.txt";
	private static final String RES_LARGE_TEST_FILE = "res_large.txt";
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();
	private File smallFile;
	private File largeFile;
	private File largeFileRes;

	private String[] linesSmall;
	private String[] linesLarge;
	private List<String> linesListLargeResults;

	@After
	public void clearData()
	{
		$.cleanPersistentData();
	}

	@Before
	public void initFiles() throws IOException
	{
		assertNotNull("file " + SMALL_TEST_FILE + " does not exist in the right location ", getClass().getClassLoader()
				.getResource(SMALL_TEST_FILE));
		assertNotNull("file " + LARGE_TEST_FILE + " does not exist in the right location ", getClass().getClassLoader()
				.getResource(LARGE_TEST_FILE));
		smallFile = new File(getClass().getClassLoader().getResource(SMALL_TEST_FILE).getFile());
		largeFile = new File(getClass().getClassLoader().getResource(LARGE_TEST_FILE).getFile());
		largeFileRes = new File(getClass().getClassLoader().getResource(RES_LARGE_TEST_FILE).getFile());
		final List<String> linesListSmall = Files.readLines(smallFile, Charsets.UTF_8);
		linesSmall = linesListSmall.toArray(new String[linesListSmall.size()]);
		final List<String> linesListLarge = Files.readLines(largeFile, Charsets.UTF_8);
		linesLarge = linesListLarge.toArray(new String[linesListLarge.size()]);
		linesListLargeResults = Files.readLines(largeFileRes, Charsets.UTF_8);
	}

	@Ignore
	@Test(timeout = 196)
	public void testSmallFileTimeout() throws Exception
	{
		$.importData(linesSmall);
	}

	@Ignore
	@Test(timeout = 179588)
	public void testLargeFileTimeout() throws Exception
	{
		$.importData(linesLarge);
	}

	@Ignore
	@Test
	public void testLargeFileHistogramLarge() throws Exception
	{
		$.importData(linesLarge);
		assertArrayEquals($.getDailyHistogram(),
				new String[] { "0,0", "0,0", "0,0", "89794,18467", "0,0", "0,0", "0,0" });
	}

	@Ignore
	@Test
	public void testLifeTimesLarge() throws Exception
	{
		$.importData(linesLarge);
		for (final String string : linesListLargeResults)
		{
			final String[] res = string.split(":");
			assertEquals(res[1].replace(" ", ""), $.getLifetimeOfTweets(res[0].replace(" ", "")));

		}
	}

}
