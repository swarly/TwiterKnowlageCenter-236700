package ac.il.technion.twc.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ac.il.technion.twc.impl.DailyTweetDataImpl;

public interface IDataHandler
{
	public void saveToData(Collection<ITweet> tweets, List<DailyTweetDataImpl> histogram) throws IOException;

	/*
	 * Returns: The map that was saved to disc. null if no map exists.
	 */
	/**
	 * load data from persistence state
	 *
	 * @return self for chaining
	 *
	 * @throws IOException
	 */
	public IDataHandler load();

	/**
	 * @return map of tweet id to Itweet object
	 */
	public Map<String, ITweet> getTweets();

	/**
	 * @return List of daily tweedata as read from persistence;
	 */
	public List<DailyTweetDataImpl> getHistogramFromFile();

	public void clearData();
}
