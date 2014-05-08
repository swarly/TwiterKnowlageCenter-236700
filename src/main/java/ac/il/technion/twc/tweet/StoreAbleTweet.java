package ac.il.technion.twc.tweet;

import java.util.Date;

public class StoreAbleTweet extends AbstractTweet
{
	/**
	 *
	 */
	private final long lifeTime;

	StoreAbleTweet(ITweet tweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = tweet.getId();
		this.originalDate = tweet.getOriginalDate();
		this.isOriginal = tweet.isOriginal();
		this.originalTweetID = tweet.getOriginalTweetID();
	}

	public StoreAbleTweet(String id, Date originalDate, boolean isOriginal,
			String originalTweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = id;
		this.originalDate = originalDate;
		this.isOriginal = isOriginal;
		this.originalTweetID = originalTweet;
	}

	@Override
	public long getLifeTime()
	{
		return lifeTime;
	}

}
