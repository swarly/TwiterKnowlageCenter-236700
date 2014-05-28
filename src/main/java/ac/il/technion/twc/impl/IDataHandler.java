package ac.il.technion.twc.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ac.il.technion.twc.impl.tweet.ITweet;

public interface IDataHandler
{
	public void saveToData(Collection<ITweet> tweets, List<DailyTweetData> histogram) throws IOException;

	/*
	 * Returns: The map that was saved to disc. null if no map exists.
	 */
	/**
	 * load data from persistence state
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException;

	/**
	 * @return map of tweet id to Itweet object
	 */
	public Map<String, ITweet> getTweets();

	/**
	 * @return List of daily tweedata as read from persistence;
	 */
	public List<DailyTweetData> getHistogramFromFile();

	public void clearData();
}
