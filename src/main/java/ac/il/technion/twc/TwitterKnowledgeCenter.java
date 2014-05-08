package ac.il.technion.twc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ac.il.technion.twc.tweet.ITweet;
import ac.il.technion.twc.tweet.StoreAbleTweet;
import ac.il.technion.twc.tweet.TweetFactory;

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
	private final TweetLifeTimeProccesor lifeTimeProccesor;

	private Map<String, ITweet> finalTweets;
	private final IDataHandler dataHandler = new DataHandlerByJSON();

	public TwitterKnowledgeCenter()
	{
		super();
		// implementation for NON persistent
		finalTweets = new HashMap<String, ITweet>();
		finalTweets = new HashMap<String, ITweet>();
		weekHistogram = new ArrayList<DailyTweetData>();
		lifeTimeProccesor = new GraphTweetLifeTimeProccesor();
		for (int i = 0; i < 8; i++)
			weekHistogram.add(new DailyTweetData());
	}

	public void importData(String[] lines) throws Exception
	{
		// load from DB
		if (lines == null)
			throw new IllegalArgumentException("input cannot be null");
		finalTweets.putAll(dataHandler.loadFromFromData());
		// no previous data available on disc.

		final List<ITweet> tweets = new LinkedList<ITweet>();
		for (final ITweet storeAbleTweet : finalTweets.values())
			lifeTimeProccesor.addTweet(storeAbleTweet);

		for (final String line : lines)
		{
			final ITweet tweet = TweetFactory.getTweetFromLine(line);
			weekHistogram.get(tweet.getTweetedDay()).addTweet(tweet);
			lifeTimeProccesor.addTweet(tweet);
			tweets.add(tweet);
		}
		for (final ITweet tweet : tweets)
			finalTweets.put(
					tweet.getId(),
					TweetFactory.getTweetPersistable(tweet,
							lifeTimeProccesor.getTweetLifeTime(tweet.getId())));
		for (final ITweet tweet : tweets)
			if (tweet.getOriginalTweetID() != null
			&& !finalTweets.containsKey(tweet.getOriginalTweetID()))
				throw new IllegalArgumentException(
						"twitt reference does not exist");

		// save to DB
		dataHandler.saveToData(finalTweets, weekHistogram);
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
		// load from DB
		if (finalTweets.isEmpty())
			finalTweets.putAll(dataHandler.loadFromFromData());
		weekHistogram.clear();
		weekHistogram.addAll(dataHandler.getHistogramFromFile());
		if (finalTweets == null)
			throw new UnsupportedOperationException("Not implemented");

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
		return String.valueOf(((StoreAbleTweet) finalTweets.get(tweetId))
				.getLifeTime());
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

	/**
	 * Cleans up all persistent data from the system; this method will be called
	 * before every test, to ensure that all tests are independent.
	 */
	public void cleanPersistentData()
	{
		dataHandler.clearData();
	}

	// TODO remove this method. only for tests.
	public Map<String, ITweet> getFinalTweets()
	{
		return finalTweets;
	}

}