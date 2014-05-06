package ac.il.technion.twc;

import ac.il.technion.twc.tweet.ITweet;

public class MutableDailyTweetData extends AbstractDailyTweetData
{
	public void addTweet(ITweet tweet)
	{
		this.totalTweets++;
		if (!tweet.isOriginal())
			this.retweeted++;
	}
}
