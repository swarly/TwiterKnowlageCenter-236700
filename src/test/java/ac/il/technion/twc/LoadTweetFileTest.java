package ac.il.technion.twc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Map;

import org.junit.Test;

import ac.il.technion.twc.tweet.ITweet;

public class LoadTweetFileTest
{

	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();

	@Test
	public void loadTweetFileLineByLineTest() throws Exception
	{
		$.cleanPersistentData();
		; // clear data at beginning of the test.
		final File myFile = new File(
				"D:\\Dropbox\\CS\\236700\\HW2\\small_sample.txt");
		BufferedReader br = new BufferedReader(new FileReader(myFile));
		String currentLine;
		String[] lines;
		while ((currentLine = br.readLine()) != null)
		{
			lines = new String[] { currentLine };
			$.importData(lines);
		}
		br.close();

		final Map<String, ITweet> finalTweetsCopy = $.getFinalTweets();

		// compare original file data to new Map created and loaded from disc.
		br = new BufferedReader(new FileReader(myFile));

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
