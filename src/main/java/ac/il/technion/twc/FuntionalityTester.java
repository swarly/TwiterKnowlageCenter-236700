package ac.il.technion.twc;

import java.util.Arrays;
import java.util.Iterator;

import ac.il.technion.twc.api.DailyTweetData;
import ac.il.technion.twc.api.TWCApi;
import ac.il.technion.twc.api.TWCFactory;
import ac.il.technion.twc.api.TwitterKnowledgeCenter;

/**
 * This class is meant to act as a wrapper to test your functionality. You should implement all its methods and not
 * change any of their signatures. You can also implement an argumentless constructor if you wish, and any number of new
 * public methods
 *
 * @author Gal Lalouche
 */
public class FuntionalityTester
{
	private TWCApi $;

	public FuntionalityTester()
	{
		super();
		$ = TWCFactory.newTWCApi();
	}

	/**
	 * Loads the data from an array of lines
	 *
	 * @param lines
	 *            An array of lines, each line formatted as <time (dd/MM/yyyy HH:mm:ss)>,<tweet id>[,original tweet]
	 * @throws Exception
	 *             If for any reason, handling the data failed
	 */
	public void importData(String[] lines) throws Exception
	{
		$ = TWCFactory.fromStringLines(Arrays.asList(lines));
	}

	/**
	 * Loads the data from an array of JSON lines
	 *
	 * @param lines
	 *            An array of lines, each line is a JSON string
	 * @throws Exception
	 *             If for any reason, handling the data failed
	 */
	public void importDataJson(String[] lines) throws Exception
	{
		$ = TWCFactory.fromStringLines(Arrays.asList(lines));
	}

	/**
	 * Loads the index, allowing for queries on the data that was imported using
	 * {@link TwitterKnowledgeCenter#importData(String[])}. setupIndex will be called before any queries can be run on
	 * the system
	 *
	 * @throws Exception
	 *             If for any reason, loading the index failed
	 */
	public void setupIndex() throws Exception
	{

	}

	/**
	 * Gets the lifetime of the tweet, in milliseconds. You may assume we will ask about the lifetime of a retweet, but
	 * only about the lifetime of an original tweet.
	 *
	 * @param tweetId
	 *            The tweet's identifier
	 * @return A string, counting the number of milliseconds between the tweet's publication and its last retweet
	 *         (recursive)
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String getLifetimeOfTweets(String tweetId) throws Exception
	{
		return String.valueOf($.getQueryRunner().getLifetimeOfTweets(tweetId));
	}

	/**
	 * Gets the weekly histogram of all tweet and retweet data
	 *
	 * @return An array of strings, each string in the format of
	 *         ("<number of tweets (including retweets), number of retweets only>"), for example:
	 *         ["100, 10","250,20",...,"587,0"]. The 0th index of the array is Sunday.
	 */
	public String[] getDailyHistogram()
	{
		final String[] histogram = new String[7];
		final Iterator<Integer> iteratorTotal = $.getHistogram().getHistogram().iterator();
		final Iterator<Integer> iteratorRet = $.getHistogram().getRetweetedHistogram().iterator();
		for (int i = 0; i < histogram.length; i++)
			histogram[i] = String.valueOf(iteratorTotal.next()) + "," + iteratorRet.next();
		return histogram;
	}

	/**
	 * Gets the number of (recursive) retweets made to all original tweets made that contain a specific hashtag
	 *
	 * @param hashtag
	 *            The hashtag to check
	 * @return A string, in the format of a number, contain the number of retweets
	 */
	public String getHashtagPopularity(String hashtag)
	{
		return String.valueOf($.getQueryRunner().getHashtagPopularity(hashtag));
	}

	/**
	 * Gets the weekly histogram of all tweet data
	 *
	 * @param t1
	 *            A date string in the format of <b>dd/MM/yyyy HH:mm:ss</b>; all tweets counted in the histogram should
	 *            have been published <b>after<\b> t1.
	 * @param t2
	 *            A date string in the format of <b>dd/MM/yyyy HH:mm:ss</b>; all tweets counted in the histogram should
	 *            have been published <b>before<\b> t2.
	 * @return An array of strings, each string in the format of
	 *         ("<number of tweets (including retweets), number of retweets only>"), for example:
	 *         ["100, 10","250,20",...,"587,0"]. The 0th index of the array is Sunday.
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String[] getTemporalHistogram(String t1, String t2) throws Exception
	{
		int i = 0;
		final String[] days = new String[7];
		for (final DailyTweetData day : $.getHistogram().getTemporalHistogram(t1, t2))
			days[i++] = day.toString();
		return days;
	}

	/**
	 * Cleans up all persistent data from the system; this method will be called before every test, to ensure that all
	 * tests are independent.
	 */
	public void cleanPersistentData()
	{
		$.clear();
	}
}