package util;

import com.google.gson.Gson;

public class JSONUtil {

	private static Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	static  public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	static public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}

}
