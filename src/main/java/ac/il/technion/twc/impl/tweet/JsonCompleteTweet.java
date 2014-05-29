package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

import ac.il.technion.twc.impl.IHashTag;

public class JsonCompleteTweet extends AbstractTweet implements ICompleteTweet
{

	public JsonCompleteTweet()
	{

		this.tweetType = TweetType.TypeJson;

	}

	@Override
	public String getId()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getOriginalDate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOriginal()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOriginalTweetID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTweetedDay()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLifeTime()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONObject toJson()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IHashTag> getHashTags()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(ITweet o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TweetType getType()
	{
		// TODO Auto-generated method stub
		return tweetType;
	}

}
