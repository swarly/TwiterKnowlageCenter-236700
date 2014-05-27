package ac.il.technion.twc.impl.tweet;

import java.util.Date;

import org.json.JSONObject;

public class TweetFactory
{

	public static ITweet getTweetFromLine(String line) throws IllegalArgumentException
	{
		return new LineStringTweet(line);
	}

	public static ITweet getTweetPersistable(String id, Date originalDate, boolean isOriginal, String originalTweet,
			long lifeTime)
	{
		return new StringLineCompleteTweet(id, originalDate, isOriginal, originalTweet, lifeTime);
	}

	public static ITweet getTweetFromJSON(JSONObject jsonObject)
	{
		return new StringLineCompleteTweet(jsonObject.getString(ITweet.idName), new Date(jsonObject.getLong(ITweet.timeName)),
				jsonObject.optString(ITweet.originalName).isEmpty(), jsonObject.optString(ITweet.originalName)
						.isEmpty() ? null : jsonObject.optString(ITweet.originalName),
						jsonObject.getLong(ITweet.liftimeName));
	}

	public static ITweet getTweetPersistable(ITweet tweet, long twittLifeTime)
	{

		return new StringLineCompleteTweet(tweet, twittLifeTime);
	}

	public static ITweet getCompareDummy(Date date)
	{
		return new StringLineCompleteTweet("", date, false, "", 0);
	}

	public static ITweet[] newArray(int i)
	{
		return new StringLineCompleteTweet[i];
	}
}
