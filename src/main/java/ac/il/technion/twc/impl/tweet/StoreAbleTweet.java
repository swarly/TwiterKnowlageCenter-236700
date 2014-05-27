package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import ac.il.technion.twc.impl.IHashTag;

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

	public StoreAbleTweet(String id, Date originalDate, boolean isOriginal, String originalTweet, long lifeTime)
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

	@Override
	public Collection<IHashTag> getHashTags()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
