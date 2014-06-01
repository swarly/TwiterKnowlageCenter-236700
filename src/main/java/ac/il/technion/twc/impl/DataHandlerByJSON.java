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

import ac.il.technion.twc.api.IDataHandler;
import ac.il.technion.twc.api.ITweet;
import ac.il.technion.twc.api.TweetType;

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

			final ITweet tweet = TweetType.values()[tweetArray.getJSONObject(i).getInt("tweetType")]
					.getTweetFromPersistent(tweetArray.getJSONObject(i));
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

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.impl.IDataHandler#getHistogramFromFile()
	 */
	@Override
	public List<DailyTweetDataImpl> getHistogramFromFile()
	{
		final List<DailyTweetDataImpl> histogram = new ArrayList<DailyTweetDataImpl>();
		if (fileContent.isEmpty())
			return getEmptyHistogram(histogram);
		final JSONObject jsonObject = new JSONObject(fileContent);
		final JSONArray tweetArray = jsonObject.getJSONArray(HISTOGRAM);
		for (int i = 0; i < tweetArray.length(); i++)
			histogram.add(new DailyTweetDataImpl(tweetArray.getJSONObject(i)));
		return histogram;
	}

	/**
	 * clear a given histogram
	 *
	 * @param histogram
	 *            histogram to clear
	 * @return
	 */
	private List<DailyTweetDataImpl> getEmptyHistogram(List<DailyTweetDataImpl> histogram2)
	{
		for (int i = 0; i < 8; i++)
			histogram2.add(new DailyTweetDataImpl());
		return histogram2;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.impl.IDataHandler#saveToData(java.util.Collection, java.util.List)
	 */
	@Override
	public void saveToData(Collection<ITweet> tweets, List<DailyTweetDataImpl> histogram) throws IOException
	{
		clearData();
		myFile.getParentFile().mkdirs();

		final JSONObject result = new JSONObject();
		final JSONArray jsonHistogram = new JSONArray();
		if (histogram != null)
			for (final DailyTweetDataImpl dailyTweetData : histogram)
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
