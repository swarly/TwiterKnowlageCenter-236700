package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

public class StringLineCompleteTweet extends AbstractTweet implements ICompleteTweet
{
	/**
	 *
	 */
	private final long lifeTime;

	StringLineCompleteTweet(ITweet tweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = tweet.getId();
		this.originalDate = tweet.getOriginalDate();
		this.isOriginal = tweet.isOriginal();
		this.originalTweetID = tweet.getOriginalTweetID();
	}

	public StringLineCompleteTweet(String id, Date originalDate, boolean isOriginal, String originalTweet, long lifeTime)
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
	public Collection<String> getHashTags()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
