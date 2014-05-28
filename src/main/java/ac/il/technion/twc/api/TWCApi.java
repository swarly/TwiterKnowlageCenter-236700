package ac.il.technion.twc.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import ac.il.technion.twc.impl.IHashTag;

public interface TWCApi
{
	public abstract IHistogram getHistogram();

	public abstract QueryRunner getQueryRunner();

	public interface IHistogram
	{
		public String[] getHistogramAsString();

		public Collection<Integer> getHistogram();

		public Collection<Integer> getTemporalHistogram(String from, String to);

		public Collection<Integer> getTemporalHistogram(Date from, Date to);

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

		TWCApi fromPersistence();
	}

	public interface QueryRunner
	{
		/**
		 * return number of retweets to original tweet that contain the hash tag
		 *
		 * @param hashtag
		 * @return
		 */
		public int getHashtagPopularity(IHashTag hashtag);

		/**
		 * return the time between the tweet and it last retweet
		 *
		 * @param id
		 *            - id of the tweet
		 * @return
		 */
		public long getLifetimeOfTweets(String id);
	}
}
