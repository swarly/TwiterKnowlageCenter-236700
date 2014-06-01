package ac.il.technion.twc.impl;

import java.util.Collection;

import ac.il.technion.twc.api.ITweet;

public interface TweetLifeTimeProccesor
{

	public abstract void addTweet(ITweet tweet);

	public abstract long getTweetLifeTime(String tweetid);

	public abstract void addAll(Collection<ITweet> values);

}