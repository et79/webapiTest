package teamlabTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getResult(String urlString) {
		String result = "";
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = in.readLine()) != null) {
				result += tmp;
			}
			in.close();
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void readJson() throws IOException {
		URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:9784043636037");
		try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {

			JsonObject obj = rdr.readObject();
			JsonArray results = obj.getJsonArray("items");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				System.out.print(result.getJsonObject("volumeInfo").getString("title"));
				System.out.println("-----------");
			}
		}
	}
}
