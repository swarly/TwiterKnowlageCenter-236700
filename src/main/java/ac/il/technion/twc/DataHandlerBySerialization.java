package ac.il.technion.twc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ac.il.technion.twc.tweet.ITweet;

public class DataHandlerBySerialization implements IDataHandler
{
	private final File myFile = new File("Resource/myMap.ser");

	@Override
	public void saveToData(Map<String, ITweet> myMap)
	{
		try
		{
			clearData(); // delete previous data saved on disc.

			// create parent directories, if doesn't exists.
			myFile.getParentFile().mkdirs();

			// TODO remove this foreach loop.. only for debugging.
			Date tmp;
			for (final ITweet currTweet : myMap.values())
			{
				tmp = currTweet.getOriginalDate();
				final int i = 5;
			}

			final FileOutputStream fileOut = new FileOutputStream(myFile);
			// TODO: replace with new FileOutputStream(file, true) for
			// appending = true;

			final ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// TODO: maybe replace upper line with: new ObjectOutputStream
			// (fileOutPutStream); ?

			out.writeObject(myMap);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + myFile);
		} catch (final IOException i)
		{
			i.printStackTrace();
		}
	}

	/*
	 * Returns: The map that was saved to disc. null if map doesn't exists.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ITweet> loadFromData()
	{
		Map<String, ITweet> myMap = new HashMap<String, ITweet>();
		try
		{
			final FileInputStream fileIn = new FileInputStream(myFile);
			final ObjectInputStream in = new ObjectInputStream(fileIn);
			myMap = (Map<String, ITweet>) in.readObject();

			if (myMap != null)
			{
				// TODO remove this foreach loop.. only for debugging.
				Date tmp;
				for (final ITweet currTweet : myMap.values())
				{
					tmp = currTweet.getOriginalDate();
					final int i = 5;
				}

			}

			myMap = (Map<String, ITweet>) in.readObject();
			in.close();
			fileIn.close();
		} catch (final IOException i)
		{
			i.printStackTrace();
			return null;
		} catch (final ClassNotFoundException c)
		{
			System.out.println(myFile + "not found"); // TODO: do we want to
			// return
			// null? or throw
			// exception?
			// or print message or
			// what?
			c.printStackTrace();
			return null;
		}
		System.out.println("Deserialized myMap...");
		return myMap;
	}

	@Override
	public void clearData()
	{
		if (myFile.exists())
			myFile.delete();
	}
}
