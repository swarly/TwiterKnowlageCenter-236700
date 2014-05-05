package ac.il.technion.twc;

import java.io.Serializable;
import java.util.Date;

public class StoreAbleTweet extends Tweet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long lifeTime;

	public StoreAbleTweet(Tweet tweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = tweet.getId();
		this.originalDate = tweet.getOriginalDate();
		this.isOriginal = tweet.isOriginal();
		this.originalTweet = tweet.getOriginalTweet();
	}

	public StoreAbleTweet(String id, Date originalDate, boolean isOriginal,
			String originalTweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = id;
		this.originalDate = originalDate;
		this.isOriginal = isOriginal;
		this.originalTweet = originalTweet;
	}

	public long getLifeTime()
	{
		return lifeTime;
	}

}
