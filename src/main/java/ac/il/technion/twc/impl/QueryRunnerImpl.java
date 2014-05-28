package ac.il.technion.twc.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import ac.il.technion.twc.api.TWCApi.QueryRunner;
import ac.il.technion.twc.impl.tweet.ITweet;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class QueryRunnerImpl implements QueryRunner
{
	private final ReverseTree<ITweet> reverseTree;
	private final Multimap<IHashTag, ITweet> tweetsByHash;
	private final Map<String, ITweet> tweetById;

	public QueryRunnerImpl()
	{
		super();
		tweetsByHash = HashMultimap.create();
		tweetById = Maps.newHashMap();
		reverseTree = new ReverseTree<>();

	}

	/**
	 * @return unmodifiable collection of the tweet in the system
	 */
	public Collection<ITweet> getAllTweets()
	{
		return Collections.unmodifiableCollection(tweetById.values());
	}

	@Override
	public int getHashtagPopularity(String hashtag)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.QueryRunner#getLifetimeOfTweets(java.lang.String)
	 */
	@Override
	public long getLifetimeOfTweets(String id)
	{

		if (tweetById.containsKey(id))
			return tweetById.get(id).getLifeTime();
		return 0;
	}

	/**
	 * add collection of tweets to query runner
	 * 
	 * @param tweet
	 *            to add
	 */
	public void addTweet(ITweet tweet)
	{
		tweetById.put(tweet.getId(), tweet);
		if (!tweet.isOriginal())
			reverseTree.put(tweetById.get(tweet.getOriginalTweetID()), tweet);
		for (final IHashTag hashTag : tweet.getHashTags())
			tweetsByHash.put(hashTag, tweet);
	}

	/**
	 * add collection of tweets to query runner
	 * 
	 * @param tweets
	 *            to add
	 */
	public void addAll(Collection<ITweet> tweets)
	{
		for (final ITweet tweet : tweets)
			addTweet(tweet);
	}

}
