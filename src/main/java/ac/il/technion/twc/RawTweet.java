package ac.il.technion.twc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RawTweet extends AbstractTweet
{

	// TODO check if tweet can be declared twice
	public RawTweet(String line)
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
			originalTweetID = args[2].replace(" ", "");
		this.isOriginal = args.length != 3;

	}
}
