package ac.il.technion.twc;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ac.il.technion.twc.api.TwitterKnowledgeCenter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class FilesTest
{

	private static final String SMALL_TEST_FILE = "small_sample.json";
	private static final String LARGE_TEST_FILE = "large_sample.json";
	private File smallFile;
	private File largeFile;
	private String[] linesSmall;
	private String[] linesLarge;
	private TwitterKnowledgeCenter $;

	@Before
	public void initFiles() throws IOException
	{
		$ = new TwitterKnowledgeCenter();
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

	@After
	public void closeAll()
	{
		$.cleanPersistentData();
	}

	@Ignore
	@Test
	public void test1()
	{
		$.importData(linesSmall);
	}

	// TODO remove before submisssion
	@Test
	public void testlarge()
	{
		final long start = System.currentTimeMillis();
		$.importData(linesLarge);
		final long duration = System.currentTimeMillis() - start;
		final Date d = new Date(duration);
		// d.toString();
	}
}
