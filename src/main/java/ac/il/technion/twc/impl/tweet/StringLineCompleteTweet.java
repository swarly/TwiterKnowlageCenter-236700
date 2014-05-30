package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

import ac.il.technion.twc.impl.IHashTag;

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
		this.tweetType = TweetType.TypeStringLine;

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
	public Collection<IHashTag> getHashTags()
	{
		throw new UnsupportedOperationException(
				"You are tring to make an action that is not supportted for this type Tweet");
	}

	@Override
	public String getText()
	{
		throw new UnsupportedOperationException(
				"You are tring to make an action that is not supportted for this type Tweet");
	}

	@Override
	public JSONObject toJson()
	{
		return super.toJsonObject();
	}

	@Override
	public TweetType getType()
	{
		return TweetType.TypeStringLine;
	}

}
