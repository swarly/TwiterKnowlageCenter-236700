package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

import ac.il.technion.twc.impl.IHashTag;

public class JsonRawTweet extends AbstractTweet implements IRawTweet
{

	private final String text;

	public JsonRawTweet(String id, Date originalDate, String originalTweet, Collection<IHashTag> hashTags, String text)
	{
		super();
		this.id = id;
		this.originalDate = originalDate;
		this.isOriginal = (originalTweet == null) ? true : false;
		this.originalTweetID = originalTweet;
		this.hashTags = hashTags;
		this.tweetType = TweetType.TypeJson;
		this.text = text;

	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public JSONObject toJsonObject()
	{
		final JSONObject object = super.toJsonObject();

		return object;
	}

	@Override
	public long getLifeTime()
	{
		return 0;
	}

	@Override
	public JSONObject toJson()
	{
		return null;
	}

	@Override
	public Collection<IHashTag> getHashTags()
	{
		return hashTags;
	}

	@Override
	public String getText()
	{
		return text;
	}

	@Override
	public TweetType getType()
	{
		return TweetType.TypeJson;
	}

}
