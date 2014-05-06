package ac.il.technion.twc;

import java.io.IOException;
import java.util.Map;

import ac.il.technion.twc.tweet.ITweet;

public interface IDataHandler
{
	public void saveToData(Map<String, ITweet> myMap) throws IOException;

	/*
	 * Returns: The map that was saved to disc. null if no map exists.
	 */
	public Map<String, ITweet> loadFromData();

	public void clearData();
}
