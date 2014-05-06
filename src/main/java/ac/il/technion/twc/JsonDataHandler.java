package ac.il.technion.twc;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDataHandler implements IDataHandler
{

	@Override
	public void saveToData(Map<String, Tweet> myMap)
	{
		final JSONObject jsonObject = new JSONObject();
		final JSONArray array = new JSONArray();
		jsonObject.put("dfg", array);

	}

	@Override
	public Map<String, Tweet> loadFromData()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearData()
	{
		// TODO Auto-generated method stub

	}

}
