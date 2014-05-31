package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import ac.il.technion.twc.impl.HashTagImpl;
import ac.il.technion.twc.impl.IHashTag;

import com.google.common.collect.Lists;

public class JsonCompleteTweet extends AbstractTweet implements ICompleteTweet
{

	@Override
	public TweetType getType()
	{
		return TweetType.TypeJson;
	}

	private static final String HASH_TAGS = "hashTags";
	private long lifetime;
	private String text;

	public JsonCompleteTweet(JSONObject jsonObject)
	{

		this.id = jsonObject.getString(ITweet.idName);
		this.isOriginal = jsonObject.optString(ITweet.originalName).isEmpty();
		this.originalDate = new Date(jsonObject.getLong(ITweet.timeName));
		this.lifetime = jsonObject.getLong(ITweet.liftimeName);
		this.originalTweetID = jsonObject.optString(ITweet.originalName).isEmpty() ? null : jsonObject
				.optString(ITweet.originalName);
		this.text = jsonObject.getString(ITweet.TEXTName);
		final JSONArray tagsArray = jsonObject.getJSONArray(HASH_TAGS);
		this.hashTags = Lists.newArrayList();
		for (int i = 0; i < tagsArray.length(); i++)
			hashTags.add(new HashTagImpl(tagsArray.getJSONObject(i).getString("name")));
	}

	public JsonCompleteTweet(ITweet tweet, long lifeTime)
	{
		this.id = tweet.getId();
		this.hashTags = Lists.newArrayList(tweet.getHashTags());
		this.isOriginal = tweet.isOriginal();
		this.originalDate = tweet.getOriginalDate();
		this.originalTweetID = tweet.getOriginalTweetID();
		this.lifetime = lifeTime;
		this.text = tweet.getText();

	}

	public JsonCompleteTweet()
	{

		this.tweetType = TweetType.TypeJson;

	}

	@Override
	public long getLifeTime()
	{
		return lifetime;
	}

	@Override
	public JSONObject toJson()
	{
		final JSONObject object = super.toJsonObject();
		object.put(ITweet.TEXTName, getText());
		object.put(ITweet.liftimeName, lifetime);
		object.put(HASH_TAGS, getHashTags());
		return object;
	}

	@Override
	public Collection<IHashTag> getHashTags()
	{
		return Collections.unmodifiableCollection(hashTags);
	}

	@Override
	public String getText()
	{
		return text;
	}

}
