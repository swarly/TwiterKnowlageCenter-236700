package ac.il.technion.twc;

import java.util.Collection;
import java.util.Comparator;

public class MathHelper
{
	public static <T extends Comparable<T>> T max(Collection<T> values)
	{
		T max = null;
		for (final T t : values)
			if (max == null || t.compareTo(max) > 0)
				max = t;
		return max;
	}

	public static <T> T max(Collection<T> values, Comparator<T> comparator)
	{
		T max = null;
		for (final T t : values)
			if (max == null || comparator.compare(max, t) > 0)
				max = t;

		return max;
	}
}
