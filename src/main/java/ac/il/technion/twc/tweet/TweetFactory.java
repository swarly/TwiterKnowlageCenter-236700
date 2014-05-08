package ac.il.technion.twc.tweet;

import java.util.Date;

import org.json.JSONObject;

public class TweetFactory
{
	public static ITweet getTweetFromLine(String line)
	{
		return new RawTweet(line);
	}

	public static ITweet getTweetPersistable(String id, Date originalDate,
			boolean isOriginal, String originalTweet, long lifeTime)
	{
		return new StoreAbleTweet(id, originalDate, isOriginal, originalTweet,
				lifeTime);
	}

	public static ITweet getTweetFromJSON(JSONObject jsonObject)
	{
		return new StoreAbleTweet(jsonObject.getString(ITweet.idName),
				new Date(jsonObject.getLong(ITweet.timeName)), jsonObject
						.optString(ITweet.originalName).isEmpty(), jsonObject
						.optString(ITweet.originalName).isEmpty() ? null
						: jsonObject.optString(ITweet.originalName),
						jsonObject.getLong(ITweet.liftimeName));
	}

	public static ITweet getTweetPersistable(ITweet tweet, long tweetLifeTime)
	{
		return new StoreAbleTweet(tweet, tweetLifeTime);
	}
}