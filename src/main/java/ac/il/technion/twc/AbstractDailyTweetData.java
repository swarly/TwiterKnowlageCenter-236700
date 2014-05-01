package ac.il.technion.twc;

public abstract class AbstractDailyTweetData
{
	protected int totalTweets;
	protected int retweeted;

	public AbstractDailyTweetData()
	{

		super();
		totalTweets = 0;
		retweeted = 0;

	}

	public int getTotalTweets()
	{
		return totalTweets;
	}

	public int getRetweeted()
	{
		return retweeted;
	}
}
