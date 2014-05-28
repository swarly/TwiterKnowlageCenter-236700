package ac.il.technion.twc.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.json.JSONObject;

import ac.il.technion.twc.api.TWCApi;
import ac.il.technion.twc.impl.tweet.ITweet;
import ac.il.technion.twc.impl.tweet.TweetFactory;

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
		dataHandler = new DataHandlerByJSON();

		histogramHandler = new HistogramImpl();
		queryRunner = new QueryRunnerImpl();
		final Map<String, ITweet> tmpTweets = Maps.newLinkedHashMap();
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
		for (final ITweet tweet : tmpTweets.values())
		{
			if (!tweet.isOriginal()
					&& tmpTweets.containsKey(tweet.getOriginalTweetID())
					&& tmpTweets.get(tweet.getOriginalTweetID()).getOriginalDate().getTime() >= tweet.getOriginalDate()
					.getTime())
				throw new IllegalArgumentException("do you have a time machine because retweet is before twitt");
			queryRunner.addTweet(TweetFactory.newTweetPersistable(tweet,
					lifeTimeProccesor.getTweetLifeTime(tweet.getId())));
			histogramHandler.addTweet(tweet);
		}
		try
		{
			dataHandler.saveToData(queryRunner.getAllTweets(), null);
		} catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public TWCImpl() throws IOException
	{
		super();
		dataHandler = new DataHandlerByJSON();
		histogramHandler = new HistogramImpl();
		queryRunner = new QueryRunnerImpl();
		dataHandler.load();
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
