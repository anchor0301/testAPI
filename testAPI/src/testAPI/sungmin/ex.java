package testAPI.sungmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ex {
	public static void main(String[] args) throws IOException, ParseException {
		String key = "WugO7Pgnqoa7fJuWbJ4nMaaIh%2Bvw2l%2F%2FaGF7MGgIyBl4vRTVhumNtnrqkL%2BJDxh94rXo%2BR8DgPREJu8h6AVefQ%3D%3D";

		String urlAddress = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=2&perPage=4000&serviceKey=" + key;

		String result = "";
		
		try{
			URL url = new URL(urlAddress);
			
			BufferedReader bf;

			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
			JSONArray jsonArr = (JSONArray)jsonObject.get("data");
			
			if (jsonArr.size() > 0){
			    for(int j=0; j<jsonArr.size(); j++){
			        JSONObject jsonObj = (JSONObject)jsonArr.get(j);
			        if((StringUtils.contains((String)(jsonObj.get("소관기관명")), "경기도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "강원도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "충청남도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "충청북도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "경상북도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "전라남도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "전라북도")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "대구광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "인천광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "울산광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "광주광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "대전광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "부산광역시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "서울특별시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "세종특별자치시")) == false
			        		&& (StringUtils.contains((String)(jsonObj.get("소관기관명")), "제주특별자치도")) == false
			        		) {
			        	
			        	System.out.println("『부서명 : " + jsonObj.get("부서명") + "\n 서비스명 : " + jsonObj.get("서비스명") + "\n 소관기관명 : " + jsonObj.get("소관기관명") + "』\n");
			        }
			    }
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
