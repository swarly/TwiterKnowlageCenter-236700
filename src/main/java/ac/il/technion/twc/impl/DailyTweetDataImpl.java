package ac.il.technion.twc.impl;

import org.json.JSONObject;

import ac.il.technion.twc.api.DailyTweetData;
import ac.il.technion.twc.api.ITweet;

/**
 * @author Arik
 *
 */
public class DailyTweetDataImpl implements DailyTweetData
{
	protected int totalTweets;
	protected int retweeted;
	protected static String totalTweetsName = "totalTweets";
	protected static String retweetedName = "retweeted";

	public DailyTweetDataImpl()
	{
		this.retweeted = 0;
		this.totalTweets = 0;
	}

	public DailyTweetDataImpl(int totalTweets, int retweeted)
	{
		super();
		if (totalTweets < retweeted)
			throw new RuntimeException("Total tweets is less then retweeted, this is a paradox");
		this.totalTweets = totalTweets;
		this.retweeted = retweeted;
	}

	public DailyTweetDataImpl(JSONObject object)
	{

		super();
		this.totalTweets = object.getInt(totalTweetsName);
		this.retweeted = object.getInt(retweetedName);
		if (this.totalTweets < this.retweeted)
			throw new RuntimeException("Total tweets is less then retweeted, this is a paradox");

	}

	/* (non-Javadoc)
	 * @see ac.il.technion.twc.api.DailyTweetData#getTotalTweets()
	 */
	@Override
	public int getTotalTweets()
	{
		return totalTweets;
	}

	/* (non-Javadoc)
	 * @see ac.il.technion.twc.api.DailyTweetData#getRetweeted()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see ac.il.technion.twc.api.DailyTweetData#addTweet(ac.il.technion.twc.api.ITweet)
	 */
	@Override
	public void addTweet(ITweet tweet)
	{
		this.totalTweets++;
		if (!tweet.isOriginal())
			this.retweeted++;
	}

	@Override
	public String toString()
	{
		return totalTweets + "," + retweeted;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + retweeted;
		result = prime * result + totalTweets;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DailyTweetDataImpl other = (DailyTweetDataImpl) obj;
		if (retweeted != other.retweeted)
			return false;
		if (totalTweets != other.totalTweets)
			return false;
		return true;
	}
}
