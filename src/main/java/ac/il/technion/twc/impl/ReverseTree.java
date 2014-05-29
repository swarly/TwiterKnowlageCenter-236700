package ac.il.technion.twc.impl;

import java.util.Map;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class ReverseTree<K>
{

	private final Map<K, K> fatherByChild;
	private final Multimap<K, K> childsByFather;

	public ReverseTree()
	{
		super();
		childsByFather = LinkedListMultimap.create();
		fatherByChild = Maps.newHashMap();
	}

	/**
	 * insert father child relationship such as 1->2
	 *
	 * @param father
	 * @param child
	 */
	public void put(K father, K child)
	{

		K origianlFather = father;
		while (fatherByChild.containsKey(origianlFather))
			origianlFather = fatherByChild.get(origianlFather);
		childsByFather.get(origianlFather).add(child);
		fatherByChild.put(child, origianlFather);

		K currentFather = father;
		while (fatherByChild.containsKey(currentFather))
		{
			final K tmp = fatherByChild.get(origianlFather);
			fatherByChild.put(currentFather, origianlFather);
			currentFather = tmp;
		}
	}

	public K get(K key)
	{
		return fatherByChild.get(key);
	}

	public int size(K key)
	{
		if (childsByFather.containsKey(key))
		{
			int size = childsByFather.get(key).size();
			for (final K item : childsByFather.get(key))
				size += size(item);
			return size;
		}

		return 0;
	}

	public void optimize()
	{
		for (final K child : fatherByChild.keySet())
		{
			if (!fatherByChild.containsKey(fatherByChild.get(child)))
				continue;
			K origianlFather = fatherByChild.get(child);
			while (fatherByChild.containsKey(origianlFather))
				origianlFather = fatherByChild.get(origianlFather);
			fatherByChild.put(child, origianlFather);
		}
	}
}
