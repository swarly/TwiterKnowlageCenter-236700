package ac.il.technion.twc;

import java.io.File;
import java.util.Map;

public class DataHandlerByJSON implements IDataHandler
{
	private final File myFile = new File("src/main/resources/myMap.ser");

	@Override
	public void saveToData(Map<String, StoreAbleTweet> myMap)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, StoreAbleTweet> loadFromData()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearData()
	{
		if (myFile.exists())
			myFile.delete();

	}

}
