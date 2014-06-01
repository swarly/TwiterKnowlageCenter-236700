package ac.il.technion.twc.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import ac.il.technion.twc.api.IDataHandler;
import ac.il.technion.twc.api.ITweet;
import ac.il.technion.twc.api.TWCApi;
import ac.il.technion.twc.api.TWCApi.IHistogram;
import ac.il.technion.twc.api.TWCApi.QueryRunner;
import ac.il.technion.twc.impl.tweet.TweetFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TWCImpl implements TWCApi
{

	private final HistogramImpl histogramHandler;

	private final QueryRunnerImpl queryRunner;

	private final IDataHandler dataHandler;

	/**
	 * c'tor for importing data
	 *
	 * @param lines
	 */
	public TWCImpl(Collection<String> lines)
	{

		final TweetLifeTimeProccesor lifeTimeProccesor = new GraphTweetLifeTimeProccesor();

		histogramHandler = new HistogramImpl();
		queryRunner = new QueryRunnerImpl();
		dataHandler = new DataHandlerByJSON();
		dataHandler.load();
		final Map<String, ITweet> tmpTweets = Maps.newHashMap();
		tmpTweets.putAll(dataHandler.getTweets());
		lifeTimeProccesor.addAll(dataHandler.getTweets().values());
		updateTempTweets(lines, lifeTimeProccesor, tmpTweets);
		final List<ITweet> finalTweets = Lists.newArrayList();
		for (final ITweet tweet : tmpTweets.values())
		{
			if (!tweet.isOriginal()
					&& tmpTweets.containsKey(tweet.getOriginalTweetID())
					&& tmpTweets.get(tweet.getOriginalTweetID()).getTweetedDate().getTime() >= tweet.getTweetedDate()
							.getTime())
				throw new IllegalArgumentException("do you have a time machine because retweet is before twitt");
			finalTweets.add(tweet.getType().getpersistableTweet(tweet,
					lifeTimeProccesor.getTweetLifeTime(tweet.getId())));

		}
		queryRunner.addAll(finalTweets);
		histogramHandler.addAll(finalTweets);
		try
		{
			dataHandler.saveToData(queryRunner.getAllTweets(), null);
		} catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param lines
	 * @param lifeTimeProccesor
	 * @param tmpTweet
	 *            TODO
	 * @return
	 */
	private Map<String, ITweet> updateTempTweets(Collection<String> lines,
			final TweetLifeTimeProccesor lifeTimeProccesor, Map<String, ITweet> tmpTweets)
	{

		for (final String line : lines)
		{
			ITweet rawTweet = null;
			if (line.startsWith("{") && line.endsWith("}"))
				rawTweet = TweetFactory.importTweetFromJSON(new JSONObject(line));
			else
				rawTweet = TweetFactory.newTweetFromLine(line);
			if (!tmpTweets.containsKey(rawTweet.getId()))
			{
				lifeTimeProccesor.addTweet(rawTweet);
				tmpTweets.put(rawTweet.getId(), rawTweet);
			}

		}
		return tmpTweets;
	}

	public TWCImpl()
	{
		super();
		dataHandler = new DataHandlerByJSON();
		histogramHandler = new HistogramImpl();
		queryRunner = new QueryRunnerImpl();
		load();
	}

	/**
	 *
	 */
	private void load()
	{
		dataHandler.load();
		histogramHandler.addAll(dataHandler.getTweets().values());
		queryRunner.addAll(dataHandler.getTweets().values());
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

	@Override
	public void clear()
	{
		dataHandler.clearData();

	}

}
