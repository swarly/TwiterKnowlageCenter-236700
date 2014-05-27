package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

import ac.il.technion.twc.impl.IHashTag;

public class JsonRawTweet extends AbstractTweet implements IRawTweet
{

	JsonRawTweet(JSONObject jsonObject)
	{
		id = jsonObject.getString("id");
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
	public int compareTo(ITweet arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TweetType getType()
	{
		// TODO Auto-generated method stub
		return TweetType.TypeJson;
	}

}
