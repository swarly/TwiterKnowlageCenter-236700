package ac.il.technion.twc.impl.tweet;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

public interface ITweet extends Comparable<ITweet>
{

	static String idName = "id";
	static String timeName = "time";
	static String originalName = "original";
	static String liftimeName = "lifetime";

	@Override
	public abstract String toString();

	public abstract String getId();

	public abstract Date getOriginalDate();

	public abstract boolean isOriginal();

	public abstract String getOriginalTweetID();

	public abstract int getTweetedDay();

	public abstract long getLifeTime();

	public abstract JSONObject toJson();

	public Collection<String> getHashTags();
}