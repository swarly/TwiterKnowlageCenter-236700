package ac.il.technion.twc;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HelpMethods
{

	public static int calculateDayOfWeek(Date date)
	{
		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
