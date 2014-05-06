package ac.il.technion.twc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class DataHandlerByJSON implements IDataHandler
{
	private final File myFile = new File("src/main/resources/myMap.ser");
	private FileWriter myFileWriter;

	public DataHandlerByJSON()
	{

	}

	@Override
	public void saveToData(Map<String, ITweet> myMap) throws IOException
	{
		try
		{
			myFileWriter = new FileWriter(myFile.getPath());
		} catch (final IOException e)
		{
			e.printStackTrace();
		}
		clearData(); // delete previous data saved on disc.

		// create parent directories, if doesn't exists.
		myFile.getParentFile().mkdirs();

		// TODO remove this foreach loop.. only for debugging.
		final Date tmp;
		for (final ITweet currTweet : myMap.values())
			try
			{
				{
					final JSONObject currJSON = ((StoreAbleTweet) currTweet)
							.getAsJSON();

					currJSON.write(myFileWriter);
					myFileWriter.flush();

				}
			} catch (final JSONException e)
			{
				e.printStackTrace();
			}
		myFileWriter.close();
	}

	@Override
	public Map<String, ITweet> loadFromData()
	{
		final Map<String, ITweet> myMap = new HashMap<String, ITweet>();
		/*
		 * try { final JSONParser parser = new JSONParser(); final Object obj =
		 * parser.parse(new FileReader(myFile.getPath())); final JSONObject
		 * jsonObject = (JSONObject) obj;
		 * 
		 * } catch (final IOException i) { i.printStackTrace(); return null; }
		 * catch (final ClassNotFoundException c) { c.printStackTrace(); return
		 * null; }
		 */

		return myMap;
	}

	@Override
	public void clearData()
	{
		if (myFile.exists())
			myFile.delete();

	}

}
