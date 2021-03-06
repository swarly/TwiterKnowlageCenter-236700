package ac.il.technion.twc.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import ac.il.technion.twc.impl.DailyTweetDataImpl;

/**
 * @author Arik
 *
 */
public interface TWCApi
{
	/**
	 * @return Ihistogram object to handke histogram queries
	 */
	public abstract IHistogram getHistogram();

	/**
	 * @return QuerryRunner object to handle item queries
	 */
	public abstract QueryRunner getQueryRunner();

	public interface IHistogram
	{
		/**
		 * @return string array size 7 represent all the data as strings
		 */
		public String[] getHistogramAsString();

		/**
		 * @return colleaction of ints represent the tweeted histogram
		 */
		public Collection<Integer> getHistogram();

		/**
		 * @param from
		 *            string in format dd/MM/yyyy HH:mm:ss that represent from (closed including the time specified)
		 * @param to
		 *            string in format dd/MM/yyyy HH:mm:ss that represent to (closed including the time specified)
		 * @return weekly histogram of the data betweet from and to
		 */
		public Collection<DailyTweetDataImpl> getTemporalHistogram(String from, String to);

		/**
		 * @param from
		 *            date object from when (closed including the time specified)
		 * @param to
		 *            date object represent end date ( closed including the time specified)
		 * @return weekly histogram of the data betweet from and to
		 */
		public Collection<DailyTweetDataImpl> getTemporalHistogram(Date from, Date to);

		/**
		 * @return collection of integers representing
		 */
		public Collection<Integer> getRetweetedHistogram();
	}

	public interface Loader
	{

		/**
		 * load data in file to TWC
		 *
		 * @param file
		 *            file object to load
		 * @throws IOException
		 */
		public abstract TWCApi fromFile(File file) throws IOException;

		/**
		 * load data as String
		 *
		 * @param string
		 *            string with the data - 1 json for line
		 */
		public TWCApi fromString(String string);

		/**
		 * string string with the data - 1 json for line
		 *
		 * @param lines
		 *            -collection of lines - one json for line
		 */
		public TWCApi fromStringLines(Collection<String> lines);

		/**
		 * @return
		 * @throws IOException
		 *             if error opeing persistent state
		 */
		TWCApi fromPersistence() throws IOException;
	}

	public interface QueryRunner
	{
		/**
		 * @param hashtag
		 * @return number of retweets to original tweet that contain the hash tag
		 */
		public int getHashtagPopularity(IHashTag hashtag);

		/**
		 *
		 * @param hashtag
		 * @return number of retweets to original tweet that contain the hash tag
		 */
		public int getHashtagPopularity(String hashtag);

		/**
		 * return the time between the tweet and it last retweet
		 *
		 * @param id
		 *            - id of the tweet
		 * @return
		 */
		public long getLifetimeOfTweets(String id);
	}

	public abstract void clear();
}
