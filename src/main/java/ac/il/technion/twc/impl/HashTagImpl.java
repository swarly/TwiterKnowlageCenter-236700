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
		// TODO Auto-generated method stub
		return hashTag;
	}

}
