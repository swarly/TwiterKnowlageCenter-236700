package ac.il.technion.twc;

public class DailyTweetData
{
	private int totalTweets;
	private int retweeted;
	private final boolean mutable;

	// add exception if totalTweets < retweeted
	public DailyTweetData(int totalTweets, int retweeted)
	{
		super();
		if (totalTweets < retweeted)
			throw new RuntimeException(
					"Total tweets is less then retweeted, this is a paradox");
		this.totalTweets = totalTweets;
		this.retweeted = retweeted;
		this.mutable = false;
	}

	public DailyTweetData()
	{

		super();
		mutable = true;
		totalTweets = 0;
		retweeted = 0;

	}

	public void addTweet(Tweet tweet)
	{
		if (mutable)
		{
			totalTweets++;
			if (!tweet.isOriginal())
				retweeted++;
		}
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
