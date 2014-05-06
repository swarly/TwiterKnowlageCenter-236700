package ac.il.technion.twc;

import java.util.Date;

public interface Tweet
{

	public abstract String toString();

	public abstract String getId();

	public abstract Date getOriginalDate();

	public abstract boolean isOriginal();

	public abstract String getOriginalTweet();

	public abstract int getTweetedDay();

}