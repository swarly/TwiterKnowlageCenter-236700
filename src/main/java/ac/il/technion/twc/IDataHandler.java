package ac.il.technion.twc;

import java.util.Map;

public interface IDataHandler
{
	public void saveToData(Map<String, Tweet> myMap);

	/*
	 * Returns: The map that was saved to disc. null if no map exists.
	 */
	public Map<String, Tweet> loadFromData();

	public void clearData();
}
