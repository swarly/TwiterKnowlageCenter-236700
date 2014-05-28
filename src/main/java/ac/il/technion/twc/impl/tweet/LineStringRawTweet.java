package ac.il.technion.twc.impl.tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.json.JSONObject;

import ac.il.technion.twc.impl.IHashTag;

public class LineStringRawTweet extends AbstractTweet implements IRawTweet
{

	// TODO check if tweet can be declared twice
	LineStringRawTweet(String line) throws IllegalArgumentException
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

	@Override
	public String getText()
	{
		throw new UnsupportedOperationException(
				"You are tring to make an action that is not supportted for this type Tweet");
	}

	@Override
	public Collection<IHashTag> getHashTags()
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
		// TODO Auto-generated method stub
		return TweetType.TypeStringLine;
	}

}