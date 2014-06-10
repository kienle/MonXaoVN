package com.kienle.monxaovn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.kienle.monxaovn.entity.MonXao;

public class JsonUtil {
	
	// parse json string to MonXao object
	public static List<MonXao> parseJsonToEntity(String jsonString) {
		List<MonXao> monXaoEntities = new ArrayList<MonXao>();
		try {
			JSONObject monXaoObject = new JSONObject(jsonString);
			JSONArray monXaoArray = monXaoObject.getJSONArray("monxaos");
			for (int i = 0; i < monXaoArray.length(); i++) {
				JSONObject monXaoObj = monXaoArray.getJSONObject(i);
				MonXao entity = new MonXao();
				entity.setThumb(monXaoObj.getString(MonXao.THUMB));
				entity.setImage(monXaoObj.getString(MonXao.IMAGE));
				entity.setName(monXaoObj.getString(MonXao.NAME));
				monXaoEntities.add(entity);
			}
		} catch (JSONException e) {
			Log.d("KienLT", "[JsonUtil] parseJsonToEntity error = " + e.getMessage());
		}
		
		return monXaoEntities;
	}
	
	// get content (json string) from asset
	public static String getJsonContent(Context context) {
		String result = "";
		
		try {
		    BufferedReader reader = new BufferedReader(
		        new InputStreamReader(context.getAssets().open(Config.FILE_JSON_NAME)));

		    String inputLine;
			
			while ((inputLine = reader.readLine()) != null) {
//				System.out.println(inputLine);
				result += inputLine;
			}
			
			reader.close();
			
		} catch (IOException e) {
			Log.d("KienLT", "[JsonUtil] getJsonContent error = " + e.getMessage());
			result = "";
		}
		
		return result;
	}
	
}
