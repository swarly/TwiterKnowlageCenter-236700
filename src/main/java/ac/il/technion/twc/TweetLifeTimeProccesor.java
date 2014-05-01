package ac.il.technion.twc;

public interface TweetLifeTimeProccesor
{

	public abstract void addTweet(Tweet tweet);

	public abstract long getTweetLifeTime(String tweetid);

}