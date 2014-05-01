package ac.il.technion.twc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tweet
{

	private final String id;
	private Date originalDate;
	private final boolean isOriginal;
	private String originalTweet;

	// TODO check if tweet can be declared twice
	public Tweet(String line)
	{
		super();
		final String[] args = line.split(",");
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");
		try
		{
			this.originalDate = dateFormat.parse(args[0]);
		} catch (final ParseException e)
		{
		}
		this.id = args[1].replace(" ", "");
		if (args.length == 3)
			originalTweet = args[2].replace(" ", "");
		this.isOriginal = args.length != 3;

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
		return originalTweet;
	}

	public int getTweetedDay()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
}
