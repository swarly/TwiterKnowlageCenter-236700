package ac.il.technion.twc;

import java.util.Map;

public interface IDataHandler
{
	public void saveToData(Map<String, StoreAbleTweet> myMap);

	/*
	 * Returns: The map that was saved to disc. null if no map exists.
	 */
	public Map<String, StoreAbleTweet> loadFromData();

	public void clearData();
}
