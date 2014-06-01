package ac.il.technion.twc.api;

/**
 * @author Arik represent tweet data for specific day
 */
public interface DailyTweetData
{

	/**
	 * @return total amount of tweets for this day
	 */
	public abstract int getTotalTweets();

	/**
	 * @return total amount of retweets for this day
	 */
	public abstract int getRetweeted();

	/**
	 * add tweet to day
	 * 
	 * @param tweet
	 *            tweet to add
	 */
	public abstract void addTweet(ITweet tweet);

}