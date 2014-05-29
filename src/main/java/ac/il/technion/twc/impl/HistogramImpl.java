package ac.il.technion.twc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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

	public void addTweet(ITweet tweet)
	{
		sortedMultiset.add(tweet);
	}

	public void addAll(Collection<ITweet> tweets)
	{
		sortedMultiset.addAll(tweets);
	}

	public HistogramImpl()
	{
		sortedMultiset = TreeMultiset.create();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getHistogramAsString()
	 */
	@Override
	public String[] getHistogramAsString()
	{
		final String[] stringHistogram = new String[8];
		int i = 0;
		for (final Integer day : getHistogram())
			stringHistogram[i++] = String.valueOf(day);
		return Arrays.copyOfRange(stringHistogram, 1, stringHistogram.length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getHistogram()
	 */
	@Override
	public Collection<Integer> getHistogram()
	{
		final Integer[] tweets = new Integer[8];
		Arrays.fill(tweets, 0);
		for (final ITweet tweet : sortedMultiset)
			tweets[tweet.getTweetedDay()]++;
		return Arrays.asList(tweets);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getTemporalHistogram(java.util.Date, java.util.Date)
	 */
	@Override
	public Collection<Integer> getTemporalHistogram(Date from, Date to)
	{
		final ITweet lower = TweetFactory.newCompareAbleDummy(from);
		final ITweet upper = TweetFactory.newCompareAbleDummy(to);
		final Integer[] tweets = new Integer[8];
		Arrays.fill(tweets, 0);
		for (final ITweet tweet : sortedMultiset.subMultiset(lower, BoundType.CLOSED, upper, BoundType.CLOSED))
			tweets[tweet.getTweetedDay()]++;
		return Collections.unmodifiableCollection(Arrays.asList(tweets).subList(1, tweets.length));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ac.il.technion.twc.api.TWCApi.IHistogram#getTemporalHistogram(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<Integer> getTemporalHistogram(String t1, String t2)
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
}
