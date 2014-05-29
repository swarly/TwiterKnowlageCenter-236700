package ac.il.technion.twc.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class DataHandlerByJSON implements IDataHandler
{
	private static final String HISTOGRAM = "histogram";
	private static final String TWEETS = "tweets";
	private File myFile;

	private String fileContent = "";
	private HashMap<String, ITweet> myMap;

	public DataHandlerByJSON()
	{
		try
		{
			myFile = new File(getClass().getResource("myMap.json").toURI());
			if (!myFile.exists())
				myFile.createNewFile();
			fileContent = readFile(myFile);
		} catch (final IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}
	}

	private String readFile(File file) throws IOException
	{

		final List<String> lines = Files.readLines(file, Charsets.UTF_8);
		final StringBuilder builder = new StringBuilder();
		for (final String line : lines)
			builder.append(line + "\n");
		// final BufferedReader reader = new BufferedReader(new
		// FileReader(file));
		// String line = null;
		// final StringBuilder stringBuilder = new StringBuilder();
		// final String ls = System.getProperty("line.separator");
		//
		// while ((line = reader.readLine()) != null)
		// {
		// stringBuilder.append(line);
		// stringBuilder.append(ls);
		// }
		// reader.close();
		return builder.toString();
	}

	@Override
	public IDataHandler load()
	{
		myMap = Maps.newHashMap();
		if (!myFile.exists() || fileContent.isEmpty())
			return this;

		final JSONObject jsonObject = new JSONObject(fileContent);
		final JSONArray tweetArray = jsonObject.getJSONArray(TWEETS);
		for (int i = 0; i < tweetArray.length(); i++)
		{
			final ITweet tweet = TweetFactory.newStringLineTweetFromJSON(tweetArray.getJSONObject(i));
			myMap.put(tweet.getId(), tweet);
		}
		return this;
	}

	@Override
	public void clearData()
	{
		try
		{
			Files.write("", myFile, Charsets.UTF_8);
		} catch (final IOException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public List<DailyTweetData> getHistogramFromFile()
	{
		final List<DailyTweetData> histogram = new ArrayList<DailyTweetData>();
		if (fileContent.isEmpty())
			return getEmptyHistogram(histogram);
		final JSONObject jsonObject = new JSONObject(fileContent);
		final JSONArray tweetArray = jsonObject.getJSONArray(HISTOGRAM);
		for (int i = 0; i < tweetArray.length(); i++)
			histogram.add(new DailyTweetData(tweetArray.getJSONObject(i)));
		return histogram;
	}

	private List<DailyTweetData> getEmptyHistogram(List<DailyTweetData> histogram2)
	{
		for (int i = 0; i < 8; i++)
			histogram2.add(new DailyTweetData());
		return histogram2;
	}

	@Override
	public void saveToData(Collection<ITweet> tweets, List<DailyTweetData> histogram) throws IOException
	{
		clearData();
		myFile.getParentFile().mkdirs();

		final JSONObject result = new JSONObject();
		final JSONArray jsonHistogram = new JSONArray();
		if (histogram != null)
			for (final DailyTweetData dailyTweetData : histogram)
				jsonHistogram.put(dailyTweetData.toJson());
		result.put(HISTOGRAM, jsonHistogram);
		final JSONArray jsonTweets = new JSONArray();
		for (final ITweet currTweet : tweets)
			jsonTweets.put(currTweet.toJson());
		result.put(TWEETS, jsonTweets);
		Files.write(result.toString(), myFile, Charsets.UTF_8);
		fileContent = result.toString();
	}

	@Override
	public Map<String, ITweet> getTweets()
	{

		return myMap;
	}
}
