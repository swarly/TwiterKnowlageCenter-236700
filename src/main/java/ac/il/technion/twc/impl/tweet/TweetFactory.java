package ac.il.technion.twc.impl.tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import ac.il.technion.twc.impl.HashTagImpl;
import ac.il.technion.twc.impl.IHashTag;

import com.google.common.collect.Lists;

public class TweetFactory
{

	public static ITweet newTweetFromLine(String line) throws IllegalArgumentException
	{
		return new LineStringRawTweet(line);
	}

	public static ITweet newTweetPersistable(String id, Date originalDate, boolean isOriginal, String originalTweet,
			long lifeTime)
	{
		return new StringLineCompleteTweet(id, originalDate, isOriginal, originalTweet, lifeTime);
	}

	/**
	 * use for creating tweet from the Json dataHandler
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static ITweet newStringLineTweetFromJSON(JSONObject jsonObject)
	{
		return new StringLineCompleteTweet(jsonObject.getString(ITweet.idName), new Date(
				jsonObject.getLong(ITweet.timeName)), jsonObject.optString(ITweet.originalName).isEmpty(), jsonObject
				.optString(ITweet.originalName).isEmpty() ? null : jsonObject.optString(ITweet.originalName),
				jsonObject.getLong(ITweet.liftimeName));
	}

	public static ITweet newTweetPersistable(ITweet tweet, long twittLifeTime)
	{

		return new StringLineCompleteTweet(tweet, twittLifeTime);
	}

	public static ITweet newCompareDummy(Date date)
	{
		return new StringLineCompleteTweet("", date, false, "", 0);
	}

	/* Adds all hashtags from 'text' parameter to hashTagsCollection parameter */
	private static void parseHashTags(Collection<IHashTag> hashTags, String text)
	{
		final String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
		final Pattern pattern = Pattern.compile(patternStr);
		final Matcher matcher = pattern.matcher(text);
		String result = "";
		// Search for Hashtags
		while (matcher.find())
		{
			result = matcher.group();
			result = result.replace(" ", "");
			final String search = result.replace("#", "");
			final IHashTag currHashTag = new HashTagImpl(search);
			hashTags.add(currHashTag);
		}

	}

	/**
	 * use for importing tweet from Tweeter Json format
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 */
	public static ITweet importTweetFromJSON(JSONObject jsonObject)
	{
		final String dateString = jsonObject.getString("created_at");

		final SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		final SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("EEE");
		final Date formattedDate = null;
		final Date parsedDate;
		try
		{
			final Date testDate1 = targetFormat.parse("04/07/2013 13:00:00");
			// final Date test2 = simpleDateFormat2.parse("Wed May 15 10:08:07 2013");

			// final Date test3 = simpleDateFormat3.parse("Wed");
			// parsedDate = simpleDateFormat.parse(dateString);
			// formattedDate = targetFormat.parse(parsedDate.toString());

			final String tmptext = jsonObject.getString("text");

			final Collection<IHashTag> hashTags = Lists.newArrayList();
			parseHashTags(hashTags, tmptext);
			final String id = jsonObject.getString("id_str");
			String original_id;
			if (jsonObject.isNull("in_reply_to_status_id_str"))
				original_id = null;
			else
				original_id = jsonObject.getString("in_reply_to_status_id_str");

			// TODO: replace test1 with formattedDate
			return new JsonRawTweet(id, testDate1, original_id, hashTags);

		} catch (final ParseException e)
		{
			throw new IllegalArgumentException("date format is illegal");
		}
	}
}
