package ac.il.technion.twc.impl;

import java.util.Collection;
import java.util.Date;

import ac.il.technion.twc.api.TWCApi.IHistogram;
import ac.il.technion.twc.impl.tweet.ITweet;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTemporalHistogram(String t1, String t2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTemporalHistogramAsStrings(Date t1, Date t2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Integer> getTemporalHistogram(Date t1, Date t2)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
