package ac.il.technion.twc.impl.tweet;

import java.util.Date;

import org.json.JSONObject;

public class TweetFactory
{

	public static ITweet getTweetFromLine(String line) throws IllegalArgumentException
	{
		return new RawTweet(line);
	}

	public static ITweet getTweetPersistable(String id, Date originalDate, boolean isOriginal, String originalTweet,
			long lifeTime)
	{
		return new StoreAbleTweet(id, originalDate, isOriginal, originalTweet, lifeTime);
	}

	public static ITweet getTweetFromJSON(JSONObject jsonObject)
	{
		return new StoreAbleTweet(jsonObject.getString(ITweet.idName), new Date(jsonObject.getLong(ITweet.timeName)),
				jsonObject.optString(ITweet.originalName).isEmpty(), jsonObject.optString(ITweet.originalName)
				.isEmpty() ? null : jsonObject.optString(ITweet.originalName),
				jsonObject.getLong(ITweet.liftimeName));
	}

	public static ITweet getTweetPersistable(ITweet tweet, long twittLifeTime)
	{

		return new StoreAbleTweet(tweet, twittLifeTime);
	}

	public static ITweet getCompareDummy(Date date)
	{
		return new StoreAbleTweet("", date, false, "", 0);
	}

	public static ITweet[] newArray(int i)
	{
		return new StoreAbleTweet[i];
	}
}
