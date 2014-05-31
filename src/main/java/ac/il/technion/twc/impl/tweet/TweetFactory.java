package ac.il.technion.twc.impl.tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import ac.il.technion.twc.impl.HashTagImpl;
import ac.il.technion.twc.impl.IHashTag;
import ac.il.technion.twc.twitter.Extractor;

import com.google.common.collect.Lists;

public class TweetFactory
{

	public static ITweet newTweetFromLine(String line) throws IllegalArgumentException
	{
		return new LineStringRawTweet(line);
	}

	public static ITweet newTweetPersistable(String id, Date originalDate, boolean isOriginal, String originalTweet,
			long lifeTime)
	{
		return new StringLineCompleteTweet(id, originalDate, isOriginal, originalTweet, lifeTime);
	}

	/**
	 * use for creating tweet from the Json dataHandler
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static ITweet newStringLineTweetFromJSON(JSONObject jsonObject)
	{
		return new StringLineCompleteTweet(jsonObject.getString(ITweet.idName), new Date(
				jsonObject.getLong(ITweet.timeName)), jsonObject.optString(ITweet.originalName).isEmpty(), jsonObject
				.optString(ITweet.originalName).isEmpty() ? null : jsonObject.optString(ITweet.originalName),
				jsonObject.getLong(ITweet.liftimeName));
	}

	public static ITweet newPersistableStringLineTweet(ITweet tweet, long twittLifeTime)
	{

		return new StringLineCompleteTweet(tweet, twittLifeTime);
	}

	public static ITweet newCompareAbleDummy(Date date)
	{
		return new StringLineCompleteTweet("", date, false, "", 0);
	}

	/* Adds all hashtags from 'text' parameter to hashTagsCollection parameter */
	private static void parseHashTags(Collection<IHashTag> hashTags, String text)
	{
		final Extractor extractor = new Extractor();

		final List<String> res = extractor.extractHashtags(text);

		for (final String element : res)
		{
			final IHashTag currHashTag = new HashTagImpl(element);
			hashTags.add(currHashTag);
		}

		/*
		 * final String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)"; final Pattern pattern =
		 * Pattern.compile(patternStr); final Matcher matcher = pattern.matcher(text); String result = ""; // Search for
		 * Hashtags while (matcher.find()) { result = matcher.group(); result = result.replace(" ", ""); final String
		 * search = result.replace("#", ""); final IHashTag currHashTag = new HashTagImpl(search);
		 * hashTags.add(currHashTag); }
		 */

	}

	/**
	 * use for importing tweet from Tweeter Json format
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 */
	public static ITweet importTweetFromJSON(JSONObject jsonObject)
	{
		final String dateString = jsonObject.getString("created_at");

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
		final Date parsedDate;
		try
		{
			parsedDate = simpleDateFormat.parse(dateString);
			final String text = jsonObject.getString("text");
			final Collection<IHashTag> hashTags = Lists.newArrayList();
			parseHashTags(hashTags, text);
			final String id = jsonObject.getString("id_str");
			String original_id;
			if (jsonObject.isNull("retweeted_status"))
				original_id = null;
			else
				original_id = String.valueOf(jsonObject.getJSONObject("retweeted_status").getInt("id"));

			return new JsonRawTweet(id, parsedDate, original_id, hashTags, text);

		} catch (final ParseException e)
		{
			throw new IllegalArgumentException("date format is illegal");
		}
	}

	public static ITweet newPersistableJsonTweet(ITweet tweet, long lifeTime)
	{
		return new JsonCompleteTweet(tweet, lifeTime);
	}

	public static ITweet newJsonTweetFrompersistentJson(JSONObject jsonObject)
	{
		return new JsonCompleteTweet(jsonObject);
	}
}
