package ac.il.technion.twc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import ac.il.technion.twc.tweet.ITweet;
import ac.il.technion.twc.tweet.TweetFactory;

public class DataHandlerByJSON implements IDataHandler
{
	private static final String HISTOGRAM = "histogram";
	private static final String TWEETS = "tweets";
	private final File myFile = new File("src/main/resources/myMap.json");
	private FileWriter myFileWriter;

	private String fileContent = "";

	public DataHandlerByJSON()
	{
		if (myFile.exists())
			try
			{
				fileContent = readFile(myFile);
			} catch (final IOException e)
			{
				e.printStackTrace();
			}
	}

	private String readFile(File file) throws IOException
	{
		final BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		final StringBuilder stringBuilder = new StringBuilder();
		final String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null)
		{
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();
		return stringBuilder.toString();
	}

	@Override
	public Map<String, ITweet> loadFromFromData() throws IOException
	{
		if (!myFile.exists())
			return new HashMap<>();

		final Map<String, ITweet> myMap = new HashMap<String, ITweet>();
		final JSONObject jsonObject = new JSONObject(fileContent);
		final JSONArray tweetArray = jsonObject.getJSONArray(TWEETS);
		for (int i = 0; i < tweetArray.length(); i++)
		{
			final ITweet tweet = TweetFactory.getTweetFromJSON(tweetArray
					.getJSONObject(i));
			myMap.put(tweet.getId(), tweet);
		}
		return myMap;
	}

	@Override
	public void clearData()
	{
		if (myFile.exists())
			myFile.delete();

	}

	@Override
	public List<DailyTweetData> getHistogramFromFile()
	{
		final List<DailyTweetData> histogram = new ArrayList<DailyTweetData>();
		final JSONObject jsonObject = new JSONObject(fileContent);
		final JSONArray tweetArray = jsonObject.getJSONArray(HISTOGRAM);
		for (int i = 0; i < tweetArray.length(); i++)
			histogram.add(new DailyTweetData(tweetArray.getJSONObject(i)));
		return histogram;
	}

	@Override
	public void saveToData(Map<String, ITweet> myMap,
			List<DailyTweetData> histogram) throws IOException
	{
		try
		{
			clearData();
			myFile.getParentFile().mkdirs();
			myFileWriter = new FileWriter(myFile.getPath());

		} catch (final IOException e)
		{
			e.printStackTrace();
		}
		final JSONObject result = new JSONObject();
		final JSONArray jsonHistogram = new JSONArray();
		for (final DailyTweetData dailyTweetData : histogram)
			jsonHistogram.put(dailyTweetData.toJson());
		result.put(HISTOGRAM, jsonHistogram);
		final JSONArray jsonTweets = new JSONArray();
		for (final ITweet currTweet : myMap.values())
			jsonTweets.put(currTweet.toJson());
		result.put(TWEETS, jsonTweets);
		myFileWriter.write(result.toString());
		myFileWriter.flush();
		myFileWriter.close();
		fileContent = result.toString();
	}
}
