package ac.il.technion.twc.api;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * This class is meant to act as a wrapper to test your functionality. You should implement all its methods and not
 * change any of their signatures. You can also implement an argumentless constructor if you wish.
 *
 * @author Gal Lalouche
 */
public class TwitterKnowledgeCenter
{
	private TWCApi api;

	public TwitterKnowledgeCenter()
	{
		super();

		api = TWCFactory.newTWCApi();
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
		if (lines == null)
			throw new IllegalArgumentException("lines array is null");
		api = TWCFactory.fromStringLines(Arrays.asList(lines));
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
	 * Gets the lifetime of the tweet, in milliseconds.
	 *
	 * @param tweetId
	 *            The tweet's identifier
	 * @return A string, counting the number of milliseconds between the tweet's publication and its last retweet
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String getLifetimeOfTweets(String tweetId) throws Exception
	{
		return String.valueOf(api.getQueryRunner().getLifetimeOfTweets(tweetId));
	}

	/**
	 * Gets the weekly histogram of all tweet data
	 *
	 * @return An array of strings, each string in the format of
	 *         ("<number of tweets (including retweets), number of retweets only>" ), for example:
	 *         ["100, 10","250,20",...,"587,0"]. The 0th index of the array is Sunday.
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String[] getDailyHistogram()
	{
		final String[] histogram = new String[7];
		final Iterator<Integer> iteratorTotal = api.getHistogram().getHistogram().iterator();
		final Iterator<Integer> iteratorRet = api.getHistogram().getRetweetedHistogram().iterator();
		for (int i = 0; i < histogram.length; i++)
			histogram[i] = String.valueOf(iteratorTotal.next()) + "," + iteratorRet.next();
		return histogram;
	}

	public void importDataJson(String json)
	{
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Loads the data from an array of JSON lines
	 *
	 * @param lines
	 *            An array of lines, each line is a JSON string
	 * @throws Exception
	 *             If for any reason, handling the data failed
	 */
	public void importDataJson(String[] lines)
	{
		for (final String line : lines)
		{
			final ITweet tweet = TweetFactory.importTweetFromJSON(new JSONObject(line));
			tweet.toString();
		}
	}

	public void importDataJson(File file) throws IOException
	{
		final List<String> lines = Files.readLines(file, Charsets.UTF_8);
		importDataJson(lines.toArray(new String[lines.size()]));
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
		throw new UnsupportedOperationException("Not implemented");
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
	 *         ("<number of tweets (including retweets), number of retweets only>" ), for example:
	 *         ["100, 10","250,20",...,"587,0"]. The 0th index of the array is Sunday.
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String[] getTemporalHistogram(String t1, String t2) throws ParseException
	{
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return getTemporalHistogram(dateFormat.parse(t1), dateFormat.parse(t2));

	}

	public String[] getTemporalHistogram(Date from, Date to)
	{
		// final ITweet lower = TweetFactory.newCompareAbleDummy(from);
		// final ITweet upper = TweetFactory.newCompareAbleDummy(to);
		// final int[] tweets = new int[8];
		// for (final ITweet tweet : sortedMultiset.subMultiset(lower, BoundType.CLOSED, upper, BoundType.CLOSED))
		// tweets[tweet.getTweetedDay()]++;
		// final String[] histogram = new String[7];
		// for (int i = 1; i < tweets.length; i++)
		// histogram[i - 1] = String.valueOf(tweets[i]);
		// return histogram;
		int i = 0;
		final String[] days = new String[7];
		for (final Integer day : api.getHistogram().getTemporalHistogram(from, to))
			days[i++] = String.valueOf(day);
		return days;
	}

	/**
	 * Cleans up all persistent data from the system; this method will be called before every test, to ensure that all
	 * tests are independent.
	 */
	public void cleanPersistentData()
	{
		// dataHandler.clearData();
	}

}