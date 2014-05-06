package ac.il.technion.twc;

public interface TweetLifeTimeProccesor
{

	public abstract void addTweet(ITweet tweet);

	public abstract long getTweetLifeTime(String tweetid);

}