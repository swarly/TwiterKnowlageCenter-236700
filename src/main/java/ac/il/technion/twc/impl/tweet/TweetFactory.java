package ac.il.technion.twc.impl.tweet;

import java.util.Date;

import org.json.JSONObject;

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
	public static ITweet newTweetFromJSON(JSONObject jsonObject)
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

	public static ITweet newCompareAbleDummy(Date date)
	{
		return new StringLineCompleteTweet("", date, false, "", 0);
	}

	/**
	 * use for importing tweet from Tweeter Json format
	 *
	 * @param jsonObject
	 * @return
	 */
	public static ITweet importTweetFromJSON(JSONObject jsonObject)
	{

		return new JsonRawTweet(jsonObject);
	}
}
