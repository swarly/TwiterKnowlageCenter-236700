package ac.il.technion.twc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ac.il.technion.twc.api.TWCApi.IHistogram;
import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

import com.google.common.collect.BoundType;
import com.google.common.collect.Lists;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

public class HistogramImpl implements IHistogram
{

	private final SortedMultiset<ITweet> sortedMultiset;
	private final List<DailyTweetData> weekHistogram;

	public HistogramImpl()
	{
		sortedMultiset = TreeMultiset.create();
		weekHistogram = Lists.newArrayListWithCapacity(8);
		for (int i = 0; i < 8; i++)
			weekHistogram.add(new DailyTweetData());
	}

	public void addTweet(ITweet tweet)
	{
		sortedMultiset.add(tweet);
		weekHistogram.get(tweet.getTweetedDay()).addTweet(tweet);
	}

	public void addAll(Collection<ITweet> tweets)
	{
		sortedMultiset.addAll(tweets);
		for (final ITweet tweet : tweets)
			weekHistogram.get(tweet.getTweetedDay()).addTweet(tweet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getHistogramAsString()
	 */
	@Override
	public String[] getHistogramAsString()
	{
		final String[] stringHistogram = new String[7];
		int i = 0;
		for (final Integer day : getHistogram())
			stringHistogram[i++] = String.valueOf(day);
		return stringHistogram;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getHistogram()
	 */
	@Override
	public Collection<Integer> getHistogram()
	{
		final Collection<Integer> histogram = Lists.newLinkedList();
		for (int i = 1; i < 8; i++)
			histogram.add(weekHistogram.get(i).getTotalTweets());
		return histogram;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getTemporalHistogram(java.util.Date, java.util.Date)
	 */
	@Override
	public Collection<DailyTweetData> getTemporalHistogram(Date from, Date to)
	{
		final ITweet lower = TweetFactory.newCompareAbleDummy(from);
		final ITweet upper = TweetFactory.newCompareAbleDummy(to);
		final DailyTweetData[] tweets = new DailyTweetData[8];
		for (int i = 0; i < tweets.length; i++)
			tweets[i] = new DailyTweetData();
		for (final ITweet tweet : sortedMultiset.subMultiset(lower, BoundType.CLOSED, upper, BoundType.CLOSED))
			tweets[tweet.getTweetedDay()].addTweet(tweet);

		return Collections.unmodifiableCollection(Arrays.asList(tweets).subList(1, tweets.length));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getTemporalHistogram(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<DailyTweetData> getTemporalHistogram(String t1, String t2)
	{
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try
		{
			return getTemporalHistogram(dateFormat.parse(t1), dateFormat.parse(t2));
		} catch (final ParseException e)
		{
			e.printStackTrace();
		}
		return Lists.newArrayList();

	}

	public int size()
	{
		return sortedMultiset.size();
	}

	@Override
	public Collection<Integer> getRetweetedHistogram()
	{
		final Collection<Integer> histogram = Lists.newLinkedList();
		for (int i = 1; i < 8; i++)
			histogram.add(weekHistogram.get(i).getRetweeted());
		return histogram;
	}
}
