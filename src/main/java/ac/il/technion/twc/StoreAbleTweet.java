package ac.il.technion.twc;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

public class StoreAbleTweet extends AbstractTweet implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final long lifeTime;

	public StoreAbleTweet(Tweet tweet, long lifeTime)
	{
		super();
		this.lifeTime = lifeTime;
		this.id = tweet.getId();
		this.originalDate = tweet.getOriginalDate();
		this.isOriginal = tweet.isOriginal();
		this.originalTweetID = tweet.getOriginalTweet();
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

	public long getLifeTime()
	{
		return lifeTime;
	}

	public JSONObject getAsJson()
	{
		final JSONObject object = new JSONObject();
		object.put("id", getId());
		object.put("original", isOriginal());
		object.put("originalTweet", getOriginalTweet());
		object.put("date", getOriginalDate().getTime());
		return object;

	}

}
