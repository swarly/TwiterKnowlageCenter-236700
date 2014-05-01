package ac.il.technion.twc;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class GraphTweetLifeTimeProccesor implements TweetLifeTimeProccesor
{
	private final Multimap<String, Tweet> map;
	private final Map<String, Tweet> idToTweet;

	public GraphTweetLifeTimeProccesor()
	{
		super();
		map = LinkedListMultimap.create();
		idToTweet = new HashMap<String, Tweet>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ac.il.technion.twc.TweetLifeTimeProccesor#addTweet(ac.il.technion.twc
	 * .Tweet)
	 */
	@Override
	public void addTweet(Tweet tweet)
	{
		idToTweet.put(tweet.getId(), tweet);
		if (!tweet.isOriginal())
			map.put(tweet.getOriginalTweet(), tweet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ac.il.technion.twc.TweetLifeTimeProccesor#getTweetLifeTime(ac.il.technion
	 * .twc.Tweet)
	 */
	@Override
	public long getTweetLifeTime(String tweetid)
	{

		if (!map.containsKey(tweetid))
			return 0;
		return getLatestRetweetedTime(idToTweet.get(tweetid))
				- idToTweet.get(tweetid).getOriginalDate().getTime();
	}

	private long getLatestRetweetedTime(Tweet tweet)
	{
		if (!map.containsKey(tweet.getId()))
			return tweet.getOriginalDate().getTime();
		final Collection<Tweet> childs = map.get(tweet.getId());

		final Tweet max = Collections.max(childs, new Comparator<Tweet>()
		{

			@Override
			public int compare(Tweet o1, Tweet o2)
			{
				return (int) (getLatestRetweetedTime(o1) - getLatestRetweetedTime(o2));
			}
		});
		return getLatestRetweetedTime(max);

	}
}
