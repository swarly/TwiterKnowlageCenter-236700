package ac.il.technion.twc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LargeFileTest
{
	TwitterKnowledgeCenter tkc;
	String lines[];

	@Before
	public void initMilionTweets()
	{
		tkc = new TwitterKnowledgeCenter();
		lines = new String[1000000];
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");
		for (int i = 0; i < 1000000; i++)
			lines[i] = dateFormat.format(new Date(i * 1000)) + ", " + i;
	}

	@Test(timeout = 30 * 60 * 1000)
	public void test() throws Exception
	{
		tkc.importData(lines);
	}

}
