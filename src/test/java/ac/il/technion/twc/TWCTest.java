package ac.il.technion.twc;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ac.il.technion.twc.api.TwitterKnowledgeCenter;

public class TWCTest
{

	@Test
	public void testTemporalHistogram() throws Exception
	{
		final String[] lines = new String[] { "01/04/2014 01:00:00, tweet1", "02/04/2014 01:00:00, tweet2",
				"03/04/2014 01:00:00, tweet3", "04/04/2014 01:00:00, tweet4" };
		final TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();
		$.importData(lines);
		assertArrayEquals(new String[] { "0", "0", "0", "1", "1", "0", "0" },
				$.getTemporalHistogram("01/04/2014 01:01:00", "03/04/2014 01:50:00"));
		assertArrayEquals(new String[] { "0", "0", "0", "1", "0", "0", "0" },
				$.getTemporalHistogram("01/04/2014 01:01:00", "02/04/2014 2:00:00"));
		assertArrayEquals(new String[] { "0", "0", "0", "1", "1", "1", "0" },
				$.getTemporalHistogram("01/04/2014 01:01:00", "04/04/2014 13:00:00"));
	}
}
