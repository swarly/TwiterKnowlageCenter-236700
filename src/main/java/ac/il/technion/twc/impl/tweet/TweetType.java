package ac.il.technion.twc.impl.tweet;

import org.json.JSONObject;

public enum TweetType
{
	TypeStringLine, TypeJson;
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
