package ac.il.technion.twc.api;

import org.json.JSONObject;

import ac.il.technion.twc.impl.tweet.TweetFactory;

public enum TweetType
{
	/**
	 * type String line - created from a a single string line
	 */
	TypeStringLine,
	/**
	 * type json type - created from Json line
	 */
	TypeJson;
	/**
	 * create presistable tweet with lifetime using the TweetFactory
	 * 
	 * @param tweet
	 *            - original tweet
	 * @param lifeTime
	 *            - lifetime to add
	 * @return new complete tweet
	 */
	public ITweet getpersistableTweet(ITweet tweet, long lifeTime)
	{
		switch (this)
		{
		case TypeStringLine:
			return TweetFactory.newPersistableStringLineTweet(tweet, lifeTime);
		case TypeJson:
			return TweetFactory.newPersistableJsonTweet(tweet, lifeTime);
		}
		return null;
	}

	public ITweet getTweetFromPersistent(JSONObject jsonObject)
	{
		switch (this)
		{
		case TypeStringLine:
			return TweetFactory.newStringLineTweetFromJSON(jsonObject);
		case TypeJson:
			return TweetFactory.newJsonTweetFrompersistentJson(jsonObject);
		}
		return null;
	}
}
