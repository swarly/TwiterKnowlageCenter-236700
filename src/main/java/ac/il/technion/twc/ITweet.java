package ac.il.technion.twc;

import java.util.Date;

public interface ITweet
{

	@Override
	public abstract String toString();

	public abstract String getId();

	public abstract Date getOriginalDate();

	public abstract boolean isOriginal();

	public abstract String getOriginalTweetID();

	public abstract int getTweetedDay();

	public abstract long getLifeTime();

}