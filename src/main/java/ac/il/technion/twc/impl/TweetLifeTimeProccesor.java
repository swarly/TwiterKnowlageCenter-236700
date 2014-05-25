package ac.il.technion.twc.impl;

import ac.il.technion.twc.impl.tweet.ITweet;

public interface TweetLifeTimeProccesor
{

	public abstract void addTweet(ITweet tweet);

	public abstract long getTweetLifeTime(String tweetid);

}