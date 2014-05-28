package ac.il.technion.twc.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import ac.il.technion.twc.impl.JSonLoaderImpl;

public class TWCFactory
{

	public static TWCApi fromFile(File file) throws IOException
	{
		return new JSonLoaderImpl().fromFile(file);
	}

	public static TWCApi fromString(String string)
	{
		return new JSonLoaderImpl().fromString(string);
	}

	public static TWCApi fromStringLines(Collection<String> lines)
	{
		return new JSonLoaderImpl().fromStringLines(lines);
	}

	public static TWCApi fromPersistence()
	{
		return new JSonLoaderImpl().fromPersistence();
	}
}
