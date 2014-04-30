package ac.il.technion.twc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is meant to act as a wrapper to test your functionality. You
 * should implement all its methods and not change any of their signatures. You
 * can also implement an argumentless constructor if you wish.
 *
 * @author Gal Lalouche
 */
public class TwitterKnowledgeCenter
{
	/**
	 * Loads the data from an array of lines
	 *
	 * @param lines
	 *            An array of lines, each line formatted as <time (dd/MM/yyyy
	 *            HH:mm:ss)>,<tweet id>[,original tweet]
	 * @throws Exception
	 *             If for any reason, handling the data failed
	 */

	private final List<DailyTweetData> weekHistogram;

	public TwitterKnowledgeCenter()
	{
		super();
		// implementation for NON persistent
		weekHistogram = new ArrayList<DailyTweetData>();
		for (int i = 0; i < 8; i++)
			weekHistogram.add(new DailyTweetData());
	}

	public void importData(String[] lines) throws Exception
	{
		final List<Tweet> tweets = new LinkedList<Tweet>();
		for (final String line : lines)
		{
			final Tweet tweet = new Tweet(line);
			tweets.add(tweet);
			weekHistogram.get(tweet.getTweetedDay()).addTweet(tweet);
		}
	}

	/**
	 * Loads the index, allowing for queries on the data that was imported using
	 * {@link TwitterKnowledgeCenter#importData(String[])}. setupIndex will be
	 * called before any queries can be run on the system
	 *
	 * @throws Exception
	 *             If for any reason, loading the index failed
	 */
	public void setupIndex() throws Exception
	{
		// throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gets the lifetime of the tweet, in milliseconds.
	 *
	 * @param tweetId
	 *            The tweet's identifier
	 * @return A string, counting the number of milliseconds between the tweet's
	 *         publication and its last retweet
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String getLifetimeOfTweets(String tweetId) throws Exception
	{
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Gets the weekly histogram of all tweet data
	 *
	 * @return An array of strings, each string in the format of
	 *         ("<number of tweets (including retweets), number of retweets only>"
	 *         ), for example: ["100, 10","250,20",...,"587,0"]. The 0th index
	 *         of the array is Sunday.
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String[] getDailyHistogram()
	{
		final String[] histogram = new String[7];
		for (int i = 1; i < weekHistogram.size(); i++)
			histogram[i - 1] = weekHistogram.get(i).getTotalTweets() + ","
					+ weekHistogram.get(i).getRetweeted();
		return histogram;
	}
}