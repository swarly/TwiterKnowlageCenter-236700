package ac.il.technion.twc;

import java.util.Calendar;
import java.util.Date;

public abstract class Tweet
{

	protected String id;
	protected Date originalDate;
	protected boolean isOriginal;
	protected String originalTweetID;

	public Tweet()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "Tweet [id=" + id + ", originalDate=" + originalDate
				+ ", originalTweet=" + originalTweetID + "]";
	}

	public String getId()
	{
		return id;
	}

	public Date getOriginalDate()
	{
		return originalDate;
	}

	public boolean isOriginal()
	{
		return isOriginal;
	}

	public String getOriginalTweet()
	{
		return originalTweetID;
	}

	public int getTweetedDay()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}