package ac.il.technion.twc.tweet;

import java.util.Date;

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

	public static ITweet getTweetPersistable(ITweet tweet, long tweetLifeTime)
	{
		return new StoreAbleTweet(tweet, tweetLifeTime);
	}
}
