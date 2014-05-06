package ac.il.technion.twc;

import ac.il.technion.twc.tweet.ITweet;

public interface TweetLifeTimeProccesor
{

	public abstract void addTweet(ITweet tweet);

	public abstract long getTweetLifeTime(String tweetid);

}