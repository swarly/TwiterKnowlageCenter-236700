package ac.il.technion.twc.impl.tweet;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

public abstract class AbstractTweet implements Comparable<ITweet>
{

	protected String id;
	protected Date originalDate;
	protected boolean isOriginal;

	protected abstract long getLifeTime();

	@Override
	public int compareTo(ITweet o)
	{
		return originalDate.compareTo(o.getOriginalDate());
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
		object.put(ITweet.timeName, getOriginalDate().getTime());
		object.put(ITweet.originalName, getOriginalTweetID());
		object.put(ITweet.liftimeName, getLifeTime());
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
	public Date getOriginalDate()
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
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}