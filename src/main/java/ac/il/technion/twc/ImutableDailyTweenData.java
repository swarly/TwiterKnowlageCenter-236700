package ac.il.technion.twc;

public class ImutableDailyTweenData extends AbstractDailyTweetData
{
	public ImutableDailyTweenData(int totalTweets, int retweeted)
	{
		super();
		if (totalTweets < retweeted)
			throw new RuntimeException(
					"Total tweets is less then retweeted, this is a paradox");
		this.totalTweets = totalTweets;
		this.retweeted = retweeted;
	}
}
