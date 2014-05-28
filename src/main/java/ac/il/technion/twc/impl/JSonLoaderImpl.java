package ac.il.technion.twc.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import ac.il.technion.twc.api.TWCApi;
import ac.il.technion.twc.api.TWCApi.Loader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class JSonLoaderImpl implements Loader
{

	@Override
	public TWCApi fromFile(File file) throws IOException
	{
		return fromStringLines(Files.readLines(file, Charsets.UTF_8));
	}

	@Override
	public TWCApi fromString(String string)
	{
		return fromStringLines(Lists.newArrayList(string.split("\n")));
	}

	@Override
	public TWCApi fromStringLines(Collection<String> lines)
	{
		return new TWCImpl(lines);
	}

	@Override
	public TWCApi fromPersistence()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
