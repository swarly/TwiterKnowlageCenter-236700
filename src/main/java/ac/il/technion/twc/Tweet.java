package ac.il.technion.twc;

import java.util.Date;

public class Tweet
{
	private String id;
	private Date originalDate;
	private boolean isOriginal;

	public Tweet(String id, Date originalDate)
	{
		super();
		this.id = id;
		this.originalDate = originalDate;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getOriginalDate()
	{
		return originalDate;
	}

	public void setOriginalDate(Date originalDate)
	{
		this.originalDate = originalDate;
	}

	public boolean isOriginal()
	{
		return isOriginal;
	}

	public void setOriginal(boolean isOriginal)
	{
		this.isOriginal = isOriginal;
	}

}
