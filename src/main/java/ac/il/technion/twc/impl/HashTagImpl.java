package ac.il.technion.twc.impl;

public class HashTagImpl implements IHashTag
{

	private final String hashTag;

	public HashTagImpl(String hashtag)
	{
		this.hashTag = hashtag;
	}

	@Override
	public String getName()
	{
		return hashTag;
	}

}
