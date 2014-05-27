package ac.il.technion.twc.api;

import java.util.Collection;

public class TWCApi
{
	public interface IHistogram
	{
		public String[] getHistogramAsString();

		public Collection<Integer> getHistogram();
	}

	public interface Importer
	{
	}

	public interface Query
	{

	}
}
