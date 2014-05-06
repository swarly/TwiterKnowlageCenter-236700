package ac.il.technion.twc;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractTweet implements Tweet
{

	protected String id;
	protected Date originalDate;
	protected boolean isOriginal;
	protected String originalTweetID;

	public AbstractTweet()
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
		return "Tweet [id=" + id + ", originalDate=" + originalDate
				+ ", originalTweet=" + originalTweetID + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.Tweet#getId()
	 */
	@Override
	public String getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.Tweet#getOriginalDate()
	 */
	@Override
	public Date getOriginalDate()
	{
		return originalDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.Tweet#isOriginal()
	 */
	@Override
	public boolean isOriginal()
	{
		return isOriginal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.Tweet#getOriginalTweet()
	 */
	@Override
	public String getOriginalTweet()
	{
		return originalTweetID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.Tweet#getTweetedDay()
	 */
	@Override
	public int getTweetedDay()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}