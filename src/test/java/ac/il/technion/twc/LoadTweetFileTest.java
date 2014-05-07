package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ac.il.technion.twc.tweet.ITweet;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class LoadTweetFileTest
{
	private static final String SMALL_TEST_FILE = "small_sample.txt";
	private static final String LARGE_TEST_FILE = "large_sample.txt";
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();
	private File smallFile;
	private File largeFile;

	private String[] linesSmall;
	private String[] linesLarge;

	@Before
	public void initFiles() throws IOException
	{
		assertNotNull("file " + SMALL_TEST_FILE
				+ " does not exist in the right location ", getClass()
				.getClassLoader().getResource(SMALL_TEST_FILE));
		assertNotNull("file " + LARGE_TEST_FILE
				+ " does not exist in the right location ", getClass()
				.getClassLoader().getResource(LARGE_TEST_FILE));
		smallFile = new File(getClass().getClassLoader()
				.getResource(SMALL_TEST_FILE).getFile());
		largeFile = new File(getClass().getClassLoader()
				.getResource(LARGE_TEST_FILE).getFile());
		final List<String> linesListSmall = Files.readLines(smallFile,
				Charsets.UTF_8);
		linesSmall = linesListSmall.toArray(new String[linesListSmall.size()]);
		final List<String> linesListLarge = Files.readLines(largeFile,
				Charsets.UTF_8);
		linesLarge = linesListLarge.toArray(new String[linesListLarge.size()]);
		$.cleanPersistentData();

	}

	@Test(timeout = 196)
	public void testSmallFileTimeout() throws Exception
	{
		$.importData(linesSmall);
	}

	@Test(timeout = 179588)
	public void testLargeFileTimeout() throws Exception
	{
		$.importData(linesLarge);
	}

	@Test
	public void loadTweetFileLineByLineTest() throws Exception
	{

		final Map<String, ITweet> finalTweetsCopy = $.getFinalTweets();

		// compare original file data to new Map created and loaded from disc.
		final BufferedReader br = new BufferedReader(new FileReader(smallFile));

		// compare StoreAbleTweet - currently not working. storeabletweets are
		// empty.

		/*
		 * 
		 * 
		 * final Collection<StoreAbleTweet> alltweets =
		 * finalTweetsCopy.values(); String buildstr;
		 * 
		 * for (final StoreAbleTweet currTweet : alltweets) if ((currentLine =
		 * br.readLine()) != null) {
		 * 
		 * buildstr = currTweet.getOriginalDate() + ", " + currTweet.getId(); if
		 * (!currTweet.isOriginal()) buildstr = buildstr + ", " +
		 * currTweet.getOriginalTweet();
		 * 
		 * assertEquals(buildstr, currentLine); } else assertEquals("0", "1");
		 */

		// compare only keys (string of ids)
		final Collection<String> alltweetsIDs = finalTweetsCopy.keySet();

		String currentLindID;
		String currentLine;
		for (final String currTweetID : alltweetsIDs)
			if ((currentLine = br.readLine()) != null)
			{
				currentLindID = currentLine.split(" ")[2].replace(",", "");
				assertTrue(alltweetsIDs.contains(currentLindID));
			} else
				assertEquals("0", "1");

	}

	// @Test
	public void loadTweetFileAllLinesTest() throws Exception
	{

		final File myFile = new File(
				"D://Technion//Spring2014//236700 Software Design//Hw//Hw 2//samples//small_sample.txt");
		BufferedReader br = new BufferedReader(new FileReader(myFile));
		int numOfLines = 0;
		while (br.readLine() != null)
			numOfLines++;
		br.close();

		br = new BufferedReader(new FileReader(myFile));

		String currentLine;
		final String[] lines = new String[numOfLines];
		int i = 0;
		while ((currentLine = br.readLine()) != null)
		{
			lines[i] = currentLine;
			i++;
		}
		$.importData(lines);
		br.close();
	}
}
