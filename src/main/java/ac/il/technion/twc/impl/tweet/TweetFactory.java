package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import ac.il.technion.twc.impl.HashTagImpl;
import ac.il.technion.twc.impl.IHashTag;

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

	public static ITweet newTweetPersistable(ITweet tweet, long twittLifeTime)
	{

		return new StringLineCompleteTweet(tweet, twittLifeTime);
	}

	public static ITweet newCompareDummy(Date date)
	{
		return new StringLineCompleteTweet("", date, false, "", 0);
	}

	/* Adds all hashtags from 'text' parameter to hashTagsCollection parameter */
	private static void parseHashTags(Collection<IHashTag> hashTagsCollection, String text)
	{
		final String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
		final Pattern pattern = Pattern.compile(patternStr);
		final Matcher matcher = pattern.matcher(text);
		String result = "";
		// Search for Hashtags
		while (matcher.find())
		{
			result = matcher.group();
			result = result.replace(" ", "");
			final String search = result.replace("#", "");
			final IHashTag currHashTag = new HashTagImpl(search);
			hashTagsCollection.add(currHashTag);
		}

	}

	/**
	 * use for importing tweet from Tweeter Json format
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static ITweet importTweetFromJSON(JSONObject jsonObject)
	{
		final int l = 5;
		// SimpleDateFormat dateFormat = new SimpleDateFormat("ddd mmm dd hh:MM:ss +zzzz yyyy")
		final String tmp = jsonObject.getString(ITweet.created_at);
		final Date tmpDate = new Date("Wed May 15 10:08:07 +0000 2013");
		final String tmptext = new String(
				"RT @PublishersLunch: #plnws E Ink and Sony Launch Flexible Digital Paper On Plastic #eyalllll (For Oversized Displays) httplyHeBKSHC");
		final Collection<IHashTag> hashTagsCollection = Lists.newArrayList();
		parseHashTags(hashTagsCollection, tmptext);
		final int k = 6;

		return new JsonRawTweet(jsonObject.getString("id"), new Date(jsonObject.getString("created_at")),
				jsonObject.getString("in_reply_to_status_id"));

	}
}
