package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ac.il.technion.twc.impl.TwitterKnowledgeCenter;

public class SampleTest
{
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();

	// TODO change this back to FucnctionalityTester

	@Before
	public void setup() throws Exception
	{
		$.cleanPersistentData();
	}

	@Ignore
	@Test
	public void testDate()
	{
		final SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		final Date formattedDate;
		final Date parsedDate;
		try
		{
			final Date test1 = targetFormat.parse("11/12/2014 08:07:22");
			final Date test2 = simpleDateFormat2.parse("Wed May 15 10:08:07 2013");
			test2.toString();
		} catch (final ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void sampleTest() throws Exception
	{
		String[] lines = new String[] { "04/04/2014 12:00:00, iddqd", "05/04/2014 12:00:00, idkfa, iddqd" };
		$.importData(lines);
		lines = new String[] { "{\"created_at\":\"Sun April 07 13:00:00 +0000 2013\",\"id\":593393706,\"id_str\":\"593393706\",\"text\":\"Software design is fun! #technion #tdd #yolo\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twittbot.net\\/\\\" rel=\\\"nofollow\\\"\\u003etwittbot.net\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":608956240,\"id_str\":\"608956240\",\"name\":\"Harapan.....\",\"screen_name\":\"Swag\",\"location\":\"You\\u2665\",\"url\":null,\"description\":\"Kamu tau arti cinta sebenarnya? Ga tau? Huftt..Ya, contohnya kecil aja, seperti Aku cinta Kamu\\u2665\",\"protected\":false,\"followers_count\":383,\"friends_count\":0,\"listed_count\":1,\"created_at\":\"Fri Jun 15 09:54:28 +0000 2012\",\"favourites_count\":0,\"utc_offset\":25200,\"time_zone\":\"Bangkok\",\"geo_enabled\":false,\"verified\":false,\"statuses_count\":13534,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_background_images\\/765054218\\/2ef17a3420c1a99bbd0f35b00c88c50c.jpeg\",\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_background_images\\/765054218\\/2ef17a3420c1a99bbd0f35b00c88c50c.jpeg\",\"profile_background_tile\":true,\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/3115283251\\/f936656c7391279551924d6e51704e63_normal.jpeg\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/3115283251\\/f936656c7391279551924d6e51704e63_normal.jpeg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/608956240\\/1358311582\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"symbols\":[],\"urls\":[],\"user_mentions\":[]},\"favorited\":false,\"retweeted\":false,\"filter_level\":\"medium\",\"lang\":\"id\"}" };
		$.importDataJson(lines);
		$.setupIndex();
		assertEquals("86400000", $.getLifetimeOfTweets("iddqd"));
		assertArrayEquals(new String[] { "1,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());

		lines = new String[] { "{\"created_at\":\"Sun May 19 10:08:08 +0000 2013\",\"id\":334611146080387074,\"id_str\":\"334611146080387074\",\"text\":\"RT @COMMUNITY_ksa: ::\\n\\u0648\\u0632\\u0627\\u0631\\u0629 \\u0627\\u0644\\u062a\\u0631\\u0628\\u064a\\u0629 \\u0648\\u0627\\u0644\\u062a\\u0639\\u0644\\u064a\\u0645 \\u062a\\u0639\\u062a\\u0645\\u062f \\u0628\\u062f\\u0621 \\u0625\\u062c\\u0627\\u0632\\u0629 \\u0645\\u0639\\u0644\\u0645\\u064a \\u0648\\u0645\\u0639\\u0644\\u0645\\u0627\\u062a \\u0631\\u064a\\u0627\\u0636 \\u0627\\u0644\\u0623\\u0637\\u0641\\u0627\\u0644 \\u0648\\u0627\\u0644\\u0645\\u0631\\u062d\\u0644\\u0629 \\u0627\\u0644\\u0627\\u0628\\u062a\\u062f\\u0627\\u0626\\u064a\\u0629 \\u064a\\u0648\\u0645 \\u0627\\u0644\\u0623\\u0631\\u0628\\u0639\\u0627 \\u0621 26\\/7\\/1434 \\u0647\\u0640 \\u0628\\u062f\\u0644\\u0627\\u2026\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/iphone\\\" rel=\\\"nofollow\\\"\\u003eTwitter for iPhone\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":614401374,\"id_str\":\"614401374\",\"name\":\"Abo saleh\",\"screen_name\":\"m_nbvcc\",\"location\":\"\",\"url\":null,\"description\":null,\"protected\":false,\"followers_count\":2225,\"friends_count\":667,\"listed_count\":1,\"created_at\":\"Thu Jun 21 13:36:38 +0000 2012\",\"favourites_count\":29,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"verified\":false,\"statuses_count\":14358,\"lang\":\"ar\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/2633823366\\/5208a1c14724690cd374102fe94c3931_normal.jpeg\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/2633823366\\/5208a1c14724690cd374102fe94c3931_normal.jpeg\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Wed May 15 09:42:49 +0000 2013\",\"id\":593393706,\"id_str\":\"593393706\",\"text\":\"::\\n\\u0648\\u0632\\u0627\\u0631\\u0629 \\u0627\\u0644\\u062a\\u0631\\u0628\\u064a\\u0629 \\u0648\\u0627\\u0644\\u062a\\u0639\\u0644\\u064a\\u0645 \\u062a\\u0639\\u062a\\u0645\\u062f \\u0628\\u062f\\u0621 \\u0625\\u062c\\u0627\\u0632\\u0629 \\u0645\\u0639\\u0644\\u0645\\u064a \\u0648\\u0645\\u0639\\u0644\\u0645\\u0627\\u062a \\u0631\\u064a\\u0627\\u0636 \\u0627\\u0644\\u0623\\u0637\\u0641\\u0627\\u0644 \\u0648\\u0627\\u0644\\u0645\\u0631\\u062d\\u0644\\u0629 \\u0627\\u0644\\u0627\\u0628\\u062a\\u062f\\u0627\\u0626\\u064a\\u0629 \\u064a\\u0648\\u0645 \\u0627\\u0644\\u0623\\u0631\\u0628\\u0639\\u0627 \\u0621 26\\/7\\/1434 \\u0647\\u0640 \\u0628\\u062f\\u0644\\u0627\\u064b  \\u0645\\u0646 17\\/8\\/1434 \\u0647\\u0640 .\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/iphone\\\" rel=\\\"nofollow\\\"\\u003eTwitter for iPhone\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":317337788,\"id_str\":\"317337788\",\"name\":\"\\u0623\\u062e\\u0628\\u0627\\u0631\\u0627\\u0644\\u0645\\u062c\\u062a\\u0645\\u0639 \\u0627\\u0644\\u0633\\u0639\\u0648\\u062f\\u064a\",\"screen_name\":\"COMMUNITY_ksa\",\"location\":\"\\u0627\\u0644\\u0645\\u0645\\u0644\\u0643\\u0629 \\u0627\\u0644\\u0639\\u0631\\u0628\\u064a\\u0629 \\u0627\\u0644\\u0633\\u0639\\u0648\\u062f\\u064a\\u0629\",\"url\":null,\"description\":\"\\u0633\\u062a\\u0642\\u0631\\u0623\\u0648\\u0646 \\u0647\\u0646\\u0627 \\u0643\\u0644 \\u0645\\u0627 \\u064a\\u0647\\u0645 \\u0627\\u0644\\u0645\\u062c\\u062a\\u0645\\u0639 \\u0627\\u0644\\u0633\\u0639\\u0648\\u062f\\u064a \\u060c \\u0645\\u0646 \\u062f\\u0627\\u062e\\u0644 \\u0627\\u0644\\u0645\\u0645\\u0644\\u0643\\u0629 \\u0648\\u0645\\u0646 \\u062e\\u0627\\u0631\\u062c\\u0647\\u0627\\n\\u0627\\u0644\\u0645\\u0646\\u062a\\u062f\\u0649\\nhttp:\\/\\/forum.wam-kw.com\\/\\n \\u0644\\u0645\\u0631\\u0627\\u0633\\u0644\\u062a\\u0646\\u0627 : Staidman2003@yahoo.com\",\"protected\":false,\"followers_count\":419296,\"friends_count\":20,\"listed_count\":1432,\"created_at\":\"Tue Jun 14 19:50:22 +0000 2011\",\"favourites_count\":86,\"utc_offset\":-18000,\"time_zone\":\"Quito\",\"geo_enabled\":false,\"verified\":false,\"statuses_count\":22035,\"lang\":\"ar\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/1900971989\\/image_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/1900971989\\/image_normal.jpg\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweet_count\":81,\"favorite_count\":8,\"entities\":{\"hashtags\":[],\"symbols\":[],\"urls\":[],\"user_mentions\":[]},\"favorited\":false,\"retweeted\":false,\"lang\":\"ar\"},\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"symbols\":[],\"urls\":[],\"user_mentions\":[{\"screen_name\":\"COMMUNITY_ksa\",\"name\":\"\\u0623\\u062e\\u0628\\u0627\\u0631\\u0627\\u0644\\u0645\\u062c\\u062a\\u0645\\u0639 \\u0627\\u0644\\u0633\\u0639\\u0648\\u062f\\u064a\",\"id\":317337788,\"id_str\":\"317337788\",\"indices\":[3,17]}]},\"favorited\":false,\"retweeted\":false,\"filter_level\":\"low\"}" };
		$.importDataJson(lines);
		$.setupIndex();
		assertArrayEquals(new String[] { "2,1", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" }, $.getDailyHistogram());
		assertArrayEquals(new String[] { "0,0", "0,0", "0,0", "0,0", "0,0", "1,0", "1,1" },
				$.getTemporalHistogram("04/04/2014 12:00:00", "05/04/2014 12:00:00"));
		assertEquals("1", $.getHashtagPopularity("yolo"));
		assertEquals("0", $.getHashtagPopularity("matam"));
	}
}