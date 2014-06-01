package ac.il.technion.twc.api;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

/**
 * Tweet representation
 * 
 * @author Arik
 *
 */
public interface ITweet extends Comparable<ITweet>
{

	static String idName = "id";
	static String timeName = "time";
	static String originalName = "original";
	static String liftimeName = "lifetime";
	static final String TWEET_TYPE = "tweetType";
	static final String TEXTName = "text";

	/**
	 * @return id of the tweet as string
	 */
	public abstract String getId();

	/**
	 * @return the date the tweet qas tweeted
	 */
	public abstract Date getTweetedDate();

	/**
	 * @return false if retweet true if original
	 */
	public abstract boolean isOriginal();

	/**
	 * @return the id of the original tweet
	 */
	public abstract String getOriginalTweetID();

	/**
	 * @return the day of the week the tweet was made UTC time
	 */
	public abstract int getTweetedDay();

	/**
	 * @return lifetime of tweet if available 0 default
	 */
	public abstract long getLifeTime();

	/**
	 * @return create json from the tweet
	 */
	public abstract JSONObject toJson();

	/**
	 * @return collection of IHashTag
	 */
	public Collection<IHashTag> getHashTags();

	/**
	 * @return the text of the tweet
	 */
	public String getText();

	/**
	 * @return the type of the tweet
	 */
	public TweetType getType();
}