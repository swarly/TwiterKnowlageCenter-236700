package ac.il.technion.twc.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ac.il.technion.twc.api.ITweet;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class GraphTweetLifeTimeProccesor implements TweetLifeTimeProccesor
{
	private final Multimap<String, ITweet> map;
	private final Map<String, ITweet> idToTweet;

	public GraphTweetLifeTimeProccesor()
	{
		super();
		map = LinkedListMultimap.create();
		idToTweet = new HashMap<String, ITweet>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.TweetLifeTimeProccesor#addTweet(ac.il.technion.twc .Tweet)
	 */
	@Override
	public void addTweet(ITweet tweet)
	{
		idToTweet.put(tweet.getId(), tweet);
		if (!tweet.isOriginal())
			map.put(tweet.getOriginalTweetID(), tweet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.TweetLifeTimeProccesor#getTweetLifeTime(ac.il.technion .twc.Tweet)
	 */
	@Override
	public long getTweetLifeTime(String tweetid)
	{
		if (!map.containsKey(tweetid))
			return 0;
		return getLatestRetweetedTime(idToTweet.get(tweetid)) - idToTweet.get(tweetid).getTweetedDate().getTime();
	}

	private long getLatestRetweetedTime(ITweet tweet)
	{
		if (!map.containsKey(tweet.getId()))
			return tweet.getTweetedDate().getTime();
		final Collection<ITweet> childs = map.get(tweet.getId());

		final ITweet max = Collections.max(childs, new Comparator<ITweet>()
				{

			@Override
			public int compare(ITweet o1, ITweet o2)
			{
				return (int) (getLatestRetweetedTime(o1) - getLatestRetweetedTime(o2));
			}
				});
		return getLatestRetweetedTime(max);

	}

	@Override
	public void addAll(Collection<ITweet> values)
	{
		for (final ITweet tweet : values)
			addTweet(tweet);

	}
}
