package pl.student.wk.utilis;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class CatpchaVerify {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6LdSo04UAAAAAEAGRM_U0JhWOZ2pxq8dEOTAhYG-";

	public static boolean verify(String gRecaptchaResponse) throws IOException {

		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("Response Code from captcha : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();


			// parse JSON response and return 'success' value
			JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();

			return jsonObject.getBoolean("success");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
}