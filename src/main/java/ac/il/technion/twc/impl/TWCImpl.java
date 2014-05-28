package ac.il.technion.twc.impl;

import java.util.Collection;

import org.json.JSONObject;

import ac.il.technion.twc.api.TWCApi;
import ac.il.technion.twc.impl.tweet.ITweet;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

public class TWCImpl implements TWCApi
{
	private final ReverseTree<ITweet> reverseTree;

	private final SortedMultiset<ITweet> sortedMultiset;

	private final IHistogram histogramHandler;

	private final QueryRunner queryRunner;

	/**
	 * c'tor for importing data
	 * 
	 * @param lines
	 */
	public TWCImpl(Collection<String> lines)
	{
		reverseTree = new ReverseTree<>();
		sortedMultiset = TreeMultiset.create();
		histogramHandler = new HistogramImpl(sortedMultiset);
		queryRunner = new QueryRunnerImpl();
		for (final String line : lines)
		{
			final JSONObject object = new JSONObject(line);
			object.toString();
		}
	}

	@Override
	public IHistogram getHistogram()
	{
		return histogramHandler;
	}

	@Override
	public QueryRunner getQueryRunner()
	{
		return queryRunner;
	}

}
