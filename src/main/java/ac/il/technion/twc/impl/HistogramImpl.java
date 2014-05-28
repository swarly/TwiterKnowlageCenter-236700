package ac.il.technion.twc.impl;

import java.util.Collection;
import java.util.Date;

import ac.il.technion.twc.api.TWCApi.IHistogram;
import ac.il.technion.twc.impl.tweet.ITweet;

import com.google.common.collect.SortedMultiset;

public class HistogramImpl implements IHistogram
{

	private final SortedMultiset<ITweet> sortedMultiSet;

	public HistogramImpl(SortedMultiset<ITweet> sortedMultiset)
	{
		this.sortedMultiSet = sortedMultiset;
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
