package ac.il.technion.twc.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.StringLineCompleteTweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.BoundType;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.io.Files;

/**
 * This class is meant to act as a wrapper to test your functionality. You should implement all its methods and not
 * change any of their signatures. You can also implement an argumentless constructor if you wish.
 *
 * @author Gal Lalouche
 */
public class TwitterKnowledgeCenter
{
	private final List<DailyTweetData> weekHistogram;
	private final TweetLifeTimeProccesor lifeTimeProccesor;

	private final Map<String, ITweet> finalTweets;
	private final IDataHandler dataHandler = new DataHandlerByJSON();

	private final SortedMultiset<ITweet> sortedMultiset;

	public TwitterKnowledgeCenter()
	{
		super();

		// implementation for NON persistent
		sortedMultiset = TreeMultiset.create();
		finalTweets = new HashMap<String, ITweet>();
		weekHistogram = new ArrayList<DailyTweetData>();
		lifeTimeProccesor = new GraphTweetLifeTimeProccesor();
		for (int i = 0; i < 8; i++)
			weekHistogram.add(new DailyTweetData());
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
			throw new IllegalArgumentException("input cannot be null");

		// load existing stored data, if there is any, and add to it all new tweets.
		dataHandler.load();
		finalTweets.putAll(dataHandler.getTweets());

		final Map<String, ITweet> tweets = new HashMap<String, ITweet>();
		tweets.putAll(finalTweets);
		// TODO: why do we need tweets for ? we can iterate over finalTweets.
		for (final ITweet storeAbleTweet : finalTweets.values())
			lifeTimeProccesor.addTweet(storeAbleTweet);

		for (final String line : lines)
		{
			final ITweet tweet = TweetFactory.newTweetFromLine(line);
			weekHistogram.get(tweet.getTweetedDay()).addTweet(tweet);
			if (!tweets.containsKey(tweet.getId()))
			{
				lifeTimeProccesor.addTweet(tweet);
				tweets.put(tweet.getId(), tweet);
			}
		}
		for (final ITweet tweet : tweets.values())
		{
			if (!tweet.isOriginal()
					&& tweets.containsKey(tweet.getOriginalTweetID())
					&& tweets.get(tweet.getOriginalTweetID()).getOriginalDate().getTime() >= tweet.getOriginalDate()
							.getTime())
				throw new IllegalArgumentException("do you have a time machine because retweet is before twitt");
			finalTweets.put(tweet.getId(),
					TweetFactory.newTweetPersistable(tweet, lifeTimeProccesor.getTweetLifeTime(tweet.getId())));
			sortedMultiset.add(tweet);
		}

		// save to database
		dataHandler.saveToData(finalTweets.values(), weekHistogram);
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
		// load existing stored data
		if (finalTweets.isEmpty())
		{
			dataHandler.load();
			final Map<String, ITweet> tweets = dataHandler.getTweets();
			finalTweets.putAll(tweets);
			sortedMultiset.addAll(tweets.values());
		}
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
	 * @return A string, counting the number of milliseconds between the tweet's publication and its last retweet
	 * @throws Exception
	 *             If it is not possible to complete the operation
	 */
	public String getLifetimeOfTweets(String tweetId) throws Exception
	{
		if (!finalTweets.containsKey(tweetId))
			throw new IllegalArgumentException("tweet does not exist");
		return String.valueOf(((StringLineCompleteTweet) finalTweets.get(tweetId)).getLifeTime());
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
		for (int i = 1; i < weekHistogram.size(); i++)
			histogram[i - 1] = weekHistogram.get(i).getTotalTweets() + "," + weekHistogram.get(i).getRetweeted();
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
		// final StringBuilder builder = new StringBuilder();
		// for (final String line : lines)
		// builder.append(line);
		// importDataJson(builder.toString());
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
		final ITweet lower = TweetFactory.newCompareDummy(from);
		final ITweet upper = TweetFactory.newCompareDummy(to);
		final int[] tweets = new int[8];
		for (final ITweet tweet : sortedMultiset.subMultiset(lower, BoundType.CLOSED, upper, BoundType.CLOSED))
			tweets[tweet.getTweetedDay()]++;
		final String[] histogram = new String[7];
		for (int i = 1; i < tweets.length; i++)
			histogram[i - 1] = String.valueOf(tweets[i]);
		return histogram;
	}

	/**
	 * Cleans up all persistent data from the system; this method will be called before every test, to ensure that all
	 * tests are independent.
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