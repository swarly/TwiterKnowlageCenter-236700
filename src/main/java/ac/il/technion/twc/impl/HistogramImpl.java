package ac.il.technion.twc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
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

	}

	public void addAll(Collection<ITweet> tweets)
	{

	}

	public HistogramImpl()
	{
		sortedMultiset = TreeMultiset.create();
	}

	@Override
	public String[] getHistogramAsString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Integer> getHistogram()
	{
		final Integer[] tweets = new Integer[8];
		for (final ITweet tweet : sortedMultiset)
			tweets[tweet.getTweetedDay()]++;
		return Arrays.asList(tweets);
	}

	@Override
	public Collection<Integer> getTemporalHistogram(Date from, Date to)
	{
		final ITweet lower = TweetFactory.newCompareDummy(from);
		final ITweet upper = TweetFactory.newCompareDummy(to);
		final Integer[] tweets = new Integer[8];
		for (final ITweet tweet : sortedMultiset.subMultiset(lower, BoundType.CLOSED, upper, BoundType.CLOSED))
			tweets[tweet.getTweetedDay()]++;
		return Arrays.asList(tweets);
	}

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

}
