package ac.il.technion.twc;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class LoadTweetFileTest
{
	private static final String SMALL_TEST_FILE = "small_sample.txt";
	private static final String LARGE_TEST_FILE = "large_sample.txt";
	TwitterKnowledgeCenter $ = new TwitterKnowledgeCenter();
	private File smallFile;
	private File largeFile;

	private String[] linesSmall;
	private String[] linesLarge;

	@After
	public void clearData()
	{
		$.cleanPersistentData();
	}

	@Before
	public void initFiles() throws IOException
	{
		assertNotNull("file " + SMALL_TEST_FILE + " does not exist in the right location ", getClass().getClassLoader()
				.getResource(SMALL_TEST_FILE));
		assertNotNull("file " + LARGE_TEST_FILE + " does not exist in the right location ", getClass().getClassLoader()
				.getResource(LARGE_TEST_FILE));
		smallFile = new File(getClass().getClassLoader().getResource(SMALL_TEST_FILE).getFile());
		largeFile = new File(getClass().getClassLoader().getResource(LARGE_TEST_FILE).getFile());
		final List<String> linesListSmall = Files.readLines(smallFile, Charsets.UTF_8);
		linesSmall = linesListSmall.toArray(new String[linesListSmall.size()]);
		final List<String> linesListLarge = Files.readLines(largeFile, Charsets.UTF_8);
		linesLarge = linesListLarge.toArray(new String[linesListLarge.size()]);

	}

	@Test(timeout = 196)
	public void testSmallFileTimeout() throws Exception
	{
		$.importData(linesSmall);
	}

	@Test(timeout = 179588)
	public void testLargeFileTimeout() throws Exception
	{
		$.importData(linesLarge);
	}

}
