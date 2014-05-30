package ac.il.technion.twc.impl;

public class HashTagImpl implements IHashTag
{
	private final String hashTag;

	public HashTagImpl(String hashTag)
	{
		this.hashTag = hashTag;
	}

	@Override
	public String getName()
	{
		return hashTag;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashTag == null) ? 0 : hashTag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final HashTagImpl other = (HashTagImpl) obj;
		if (hashTag == null)
		{
			if (other.hashTag != null)
				return false;
		} else if (!hashTag.equals(other.hashTag))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return hashTag;
	}

}
