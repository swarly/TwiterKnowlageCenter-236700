package ac.il.technion.twc;

import java.util.Date;

import org.json.JSONObject;

public class StoreAbleTweet extends AbstractTweet
{
	/**
	 *
	 */
	private final long lifeTime;

	public StoreAbleTweet(ITweet tweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = tweet.getId();
		this.originalDate = tweet.getOriginalDate();
		this.isOriginal = tweet.isOriginal();
		this.originalTweetID = tweet.getOriginalTweetID();
	}

	public StoreAbleTweet(String id, Date originalDate, boolean isOriginal,
			String originalTweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = id;
		this.originalDate = originalDate;
		this.isOriginal = isOriginal;
		this.originalTweetID = originalTweet;
	}

	@Override
	public long getLifeTime()
	{
		return lifeTime;
	}

	public JSONObject getAsJSON()
	{
		final JSONObject object = new JSONObject();
		object.put("id", getId());
		object.put("isOriginal", isOriginal());
		object.put("originalTweetID", getOriginalTweetID());
		object.put("originalDate", getOriginalDate().getTime());
		object.put("lifeTime", getLifeTime());
		return object;
	}

}
