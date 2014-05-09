package ac.il.technion.twc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ac.il.technion.twc.tweet.ITweet;
import ac.il.technion.twc.tweet.TweetFactory;

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
		final List<String> linesListSmall = Files.readLines(smallFile, Charsets.UTF_8);
		linesSmall = linesListSmall.toArray(new String[linesListSmall.size()]);
		final List<String> linesListLarge = Files.readLines(largeFile, Charsets.UTF_8);
		linesLarge = linesListLarge.toArray(new String[linesListLarge.size()]);

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
		clearData(); // clear data at beginning of the test.
		final Map<String, ITweet> tweetsMapFromFile = new HashMap<String, ITweet>();

		final BufferedReader br = new BufferedReader(new FileReader(smallFile));
		String currentLine;
		String[] lines;
		while ((currentLine = br.readLine()) != null)
		{
			lines = new String[] { currentLine };
			$.importData(lines);
			final ITweet tweet = TweetFactory.getTweetFromLine(currentLine);
			tweetsMapFromFile.put(tweet.getId(), tweet);
		}
		br.close();

		final Map<String, ITweet> finalTweetsCopy = $.getFinalTweets();

		// compare maps
		compareMaps(finalTweetsCopy, tweetsMapFromFile);
	}

	@Test
	public void loadTweetFileAllLinesTest() throws Exception
	{
		clearData(); // clear data at beginning of the test.
		final Map<String, ITweet> tweetsMapFromFile = new HashMap<String, ITweet>();
		BufferedReader br = new BufferedReader(new FileReader(smallFile));
		int numOfLines = 0;
		while (br.readLine() != null)
			numOfLines++;
		br.close();

		br = new BufferedReader(new FileReader(smallFile));

		String currentLine;
		final String[] lines = new String[numOfLines];
		int i = 0;
		while ((currentLine = br.readLine()) != null)
		{
			lines[i] = currentLine;
			i++;
			final ITweet tweet = TweetFactory.getTweetFromLine(currentLine);
			tweetsMapFromFile.put(tweet.getId(), tweet);
		}
		$.importData(lines);
		br.close();

		final Map<String, ITweet> finalTweetsCopy = $.getFinalTweets();

		// compare maps
		compareMaps(finalTweetsCopy, tweetsMapFromFile);
	}

	private void compareMaps(Map<String, ITweet> finalTweetsCopy, Map<String, ITweet> tweetsMapFromFile)
	{
		for (final ITweet curr : finalTweetsCopy.values())
		{
			final ITweet tweet = tweetsMapFromFile.get(curr.getId());
			assertTrue(tweet.getId().equals(curr.getId()));
			assertTrue(tweet.getOriginalDate().getTime() == curr.getOriginalDate().getTime());
			if (tweet.getOriginalTweetID() == null)
				assertNull(curr.getOriginalTweetID());
			else
				assertTrue(tweet.getOriginalTweetID().equals(curr.getOriginalTweetID()));
			assertTrue(tweet.isOriginal() == curr.isOriginal());
			tweetsMapFromFile.remove(curr.getId()); // each key is defined once.
		}
	}

}
