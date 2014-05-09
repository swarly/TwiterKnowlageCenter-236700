package ac.il.technion.twc.tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RawTweet extends AbstractTweet
{

	// TODO check if tweet can be declared twice
	RawTweet(String line) throws IllegalArgumentException
	{
		super();
		final String[] args = line.split(",");
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		try
		{
			this.originalDate = dateFormat.parse(args[0]);
		} catch (final ParseException e)
		{
			throw new IllegalArgumentException("date format is illegal");
		}
		this.id = args[1].replace(" ", "");
		if (args.length == 3)
			originalTweetID = args[2].replace(" ", "");
		this.isOriginal = args.length != 3;
		if (originalTweetID != null && originalTweetID.equals(this.id))
			throw new IllegalArgumentException("original twitt id cann not be same as self");
	}

	@Override
	public long getLifeTime()
	{
		return 0;
	}
}
