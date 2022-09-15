package testAPI.sungmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		AllSeason();
		System.out.print("1.봄  2.여름  3.가을  4.겨울  \n여행가고싶은 계절을 입력해주세요");
		int Season = sc.nextInt();
		if (Season == 1) {
			System.out.println("봄 검색 결과입니다.");

		} else if (Season == 2) {
			System.out.println("여름 검색 결과입니다.");

		} else if (Season == 3) {
			System.out.println("가을 검색 결과입니다.");

		} else if (Season == 4) {
			System.out.println("겨울 검색 결과입니다.");

		} else {
			System.out.println("통합 검색 결과입니다.");

		}
		System.out.println("실행끝");
	}

	public static void AllSeason() throws IOException {

		String ServiceKey = "WugO7Pgnqoa7fJuWbJ4nMaaIh%2Bvw2l%2F%2FaGF7MGgIyBl4vRTVhumNtnrqkL%2BJDxh94rXo%2BR8DgPREJu8h6AVefQ%3D%3D";
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/6480000/gyeongnamtourseason/gyeongnamtourseasonlist"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ServiceKey); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* JSON방식으로 호출 시 파라미터 resultType=json 입력 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		try {
		String jsonStr = sb.toString();
		JSONParser parser =new JSONParser();
		Object obj;
		
			obj = parser.parse(jsonStr);
		
		

		// obj를 우선 JSONObject에 담음
		JSONObject  jsonMain= (JSONObject)obj;

		// jsonObject에서 jsonArray를 get함
		JSONArray jsonBody = (JSONArray) jsonMain.get("body");

	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());
	}
}
