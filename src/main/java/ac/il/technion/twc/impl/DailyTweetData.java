package ac.il.technion.twc.impl;

import org.json.JSONObject;

import ac.il.technion.twc.impl.tweet.ITweet;

public class DailyTweetData
{
	protected int totalTweets;
	protected int retweeted;
	protected static String totalTweetsName = "totalTweets";
	protected static String retweetedName = "retweeted";

	public DailyTweetData()
	{
		this.retweeted = 0;
		this.totalTweets = 0;
	}

	public DailyTweetData(int totalTweets, int retweeted)
	{
		super();
		if (totalTweets < retweeted)
			throw new RuntimeException("Total tweets is less then retweeted, this is a paradox");
		this.totalTweets = totalTweets;
		this.retweeted = retweeted;
	}

	public DailyTweetData(JSONObject object)
	{

		super();
		this.totalTweets = object.getInt(totalTweetsName);
		this.retweeted = object.getInt(retweetedName);
		if (this.totalTweets < this.retweeted)
			throw new RuntimeException("Total tweets is less then retweeted, this is a paradox");

	}

	public int getTotalTweets()
	{
		return totalTweets;
	}

	public int getRetweeted()
	{
		return retweeted;
	}

	public JSONObject toJson()
	{
		final JSONObject object = new JSONObject();
		object.put("totalTweets", totalTweets);
		object.put("retweeted", retweeted);
		return object;
	}

	public void addTweet(ITweet tweet)
	{
		this.totalTweets++;
		if (!tweet.isOriginal())
			this.retweeted++;
	}

}
