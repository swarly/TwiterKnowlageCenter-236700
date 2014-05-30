package ac.il.technion.twc.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import ac.il.technion.twc.api.TWCApi.QueryRunner;
import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetType;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.QueryRunner#getHashtagPopularity(ac.il.technion.twc.impl.IHashTag)
	 */
	@Override
	public int getHashtagPopularity(IHashTag hashtag)
	{
		if (!tweetsByHash.containsKey(hashtag))
			return 0;
		int popularity = 0;
		for (final ITweet tweet : tweetsByHash.get(hashtag))
			try
			{
				popularity += reverseTree.size(tweet);
			} catch (final UnsupportedOperationException e)
			{// if tweet is old version it is not supported and will no be counted
			}
		return popularity;
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
		updateTree();
	}

	/**
	 * @param tweet
	 */
	private void updateTree()
	{
		reverseTree.clear();
		for (final ITweet tweet : tweetById.values())
		{
			if (!tweet.isOriginal())
				reverseTree.put(tweetById.get(tweet.getOriginalTweetID()), tweet);
			if (tweet.getType() == TweetType.TypeJson)
				for (final IHashTag hashTag : tweet.getHashTags())
					tweetsByHash.put(hashTag, tweet);
		}
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
			tweetById.put(tweet.getId(), tweet);
		updateTree();
	}

	@Override
	public int getHashtagPopularity(String hashtag)
	{
		return getHashtagPopularity(new HashTagImpl(hashtag));
	}

}
