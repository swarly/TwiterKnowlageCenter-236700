package ac.il.technion.twc;

public class MutableDailyTweetData extends AbstractDailyTweetData
{
	public void addTweet(ITweet tweet)
	{
		this.totalTweets++;
		if (!tweet.isOriginal())
			this.retweeted++;
	}
}
