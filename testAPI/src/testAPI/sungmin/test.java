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
		System.out.println("경상남도 계절 관광 추천 프로그램입니다. \n");
		System.out.print(" 1. 봄 \n 2.여름\n 3.가을\n 4.겨울 \n\n여행가고싶은 계절을 입력해주세요 : ");
		
		int Season = sc.nextInt();
		if (Season == 1) {
			System.out.println("봄, 여긴 어떤가요?");

		} else if (Season == 2) {
			System.out.println("여름, 쉬원한 바다로!.");

		} else if (Season == 3) {
			System.out.println("가을, 단풍 여행.");

		} else if (Season == 4) {
			System.out.println("겨울, 따듯한 겨울.");

		}

		AllSeason(Season);
		sc.close();

	}

	public static void AllSeason(int Season) throws IOException {
		/* Service Key */
		String ServiceKey = "WugO7Pgnqoa7fJuWbJ4nMaaIh%2Bvw2l%2F%2FaGF7MGgIyBl4vRTVhumNtnrqkL%2BJDxh94rXo%2BR8DgPREJu8h6AVefQ%3D%3D";

		/* 페이지 번호 */
		String PageNum = "1";

		/* 한 페이지 결과 수 */
		String numOfRows = "45";

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/6480000/gyeongnamtourseason/gyeongnamtourseasonlist"); /* URL */

		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ServiceKey); /* Service Key */

		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(PageNum, "UTF-8")); /* 페이지번호 */

		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */

		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* JSON방식으로 호출 시 파라미터 resultType=json 입력 */

		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());  /* 응답 결과 */
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
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsonStr);

			// obj를 우선 jsonMain에 담음
			JSONObject jsonMain = (JSONObject) obj;

			// jsonObject에서 jsonArray를 get함
			JSONObject jsonGN = (JSONObject) jsonMain.get("gyeongnamtourseasonlist");

			JSONObject jsonBody = (JSONObject) jsonGN.get("body");

			JSONObject jsonItems = (JSONObject) jsonBody.get("items");

			JSONArray jsonArr = (JSONArray) jsonItems.get("item");
			int j = 0;
			if (jsonArr.size() > 0) {
				for (int i = 0; i < jsonArr.size(); i++) {

					JSONObject jsonObj = (JSONObject) jsonArr.get(i);
					String Ss = (String) jsonObj.get("category_name2");

					if (Season == 1 && Ss.equals("봄")) {
						System.out.print((++j) + "  -  ");
						System.out.println((String) jsonObj.get("data_title"));
					} else if (Season == 2 && Ss.equals("여름")) {

						System.out.print((++j) + "  -  ");
						System.out.println((String) jsonObj.get("data_title"));
					} else if (Season == 3 && Ss.equals("가을")) {

						System.out.print((++j) + "  -  ");
						System.out.println((String) jsonObj.get("data_title"));
					} else if (Season == 4 && Ss.equals("겨울")) {

						System.out.print((++j) + "  -  ");
						System.out.println((String) jsonObj.get("data_title"));
					} else {
					}
//					System.out.print((String) jsonObj.get("category_name2") + "   ");\
				}
			}
			j = 0;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
