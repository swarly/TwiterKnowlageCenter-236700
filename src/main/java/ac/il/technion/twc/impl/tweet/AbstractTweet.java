package ac.il.technion.twc.impl.tweet;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONObject;

import ac.il.technion.twc.api.IHashTag;
import ac.il.technion.twc.api.ITweet;
import ac.il.technion.twc.api.TweetType;

public abstract class AbstractTweet implements Comparable<ITweet>
{

	protected String id;
	protected Date originalDate;
	protected boolean isOriginal;
	protected TweetType tweetType;
	Collection<IHashTag> hashTags;

	protected abstract long getLifeTime();

	@Override
	public int compareTo(ITweet o)
	{
		return originalDate.compareTo(o.getTweetedDate());
	}

	protected String originalTweetID;

	AbstractTweet()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#toString()
	 */
	@Override
	public String toString()
	{
		return "Tweet [id=" + id + ", originalDate=" + originalDate + ", originalTweet=" + originalTweetID + "]";
	}

	public JSONObject toJsonObject()
	{
		final JSONObject object = new JSONObject();
		object.put(ITweet.idName, getId());
		object.put("isOriginal", isOriginal());
		object.put(ITweet.timeName, getTweetedDate().getTime());
		object.put(ITweet.originalName, getOriginalTweetID());
		object.put(ITweet.liftimeName, getLifeTime());
		object.put(ITweet.TWEET_TYPE, getType().ordinal());

		return object;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#getId()
	 */
	public String getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#getOriginalDate()
	 */
	public Date getTweetedDate()
	{
		return originalDate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#isOriginal()
	 */
	public boolean isOriginal()
	{
		return isOriginal;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#getOriginalTweet()
	 */
	public String getOriginalTweetID()
	{
		return originalTweetID;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.Tweet#getTweetedDay()
	 */
	public int getTweetedDay()
	{
		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(originalDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public TweetType getType()
	{
		return this.tweetType;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final AbstractTweet other = (AbstractTweet) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}