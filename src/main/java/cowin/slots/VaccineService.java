package cowin.slots;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class VaccineService {

	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


	public String isAvailable(String pincode, String date) {


		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+pincode+"&date="+ LocalDate.parse(date, formatter).format(formatter2);

		HttpGet request = new HttpGet(url);

		request.addHeader("authority", "cdn-api.co-vin.in");
		request.addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"");
		request.addHeader("accept", "application/json, text/plain, */*");
		request.addHeader("sec-ch-ua-mobile", "?0");
		request.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
		request.addHeader("origin", "https://www.cowin.gov.in");
		request.addHeader("sec-fetch-mode", "cors");
		request.addHeader("sec-fetch-dest", "empty");
		request.addHeader("referer", "https://www.cowin.gov.in/");
		request.addHeader("accept-language", "en-GB,en-US;q=0.9,en;q=0.8");
		request.addHeader("if-none-match", "W/\"755-kSDAGwS0dhuJu/VuZ3UJpZ2STnc\"");

		try (CloseableHttpResponse response = httpClient.execute(request)) {

			// Get HttpResponse Status
			System.out.println("response="+response.getStatusLine().toString());

			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();

			if (entity != null) {
				// return it as a String
				String result = EntityUtils.toString(entity);
				System.out.println(result);
				Root root = objectMapper.readValue(result, Root.class);
				List<Center> centers = root.centers;

				if(centers !=  null) {


					/*
					 * boolean isAvailable = false;
					 * 
					 * for(int i = 0; i < root.centers.size(); i++){ List<Session> sessions =
					 * root.centers.get(i).getSessions(); System.out.println("session= "+
					 * sessions.toString()); for(int j = 0; j < sessions.size(); j++){ Session
					 * session = sessions.get(j); if(session.min_age_limit == 45 &&
					 * session.available_capacity > 0){ isAvailable = true; } } }
					 */

					return stripReposneToHtml(centers);
				}
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void notifyResponse() throws IOException{
		Runtime.getRuntime().exec(String.format("terminal-notifier -message Vaccine_Available"));

	}

	public String stripReposneToHtml(List<Center> centers) {

		StringBuilder resp = new StringBuilder();
		resp.append("<table border=\"1\">\n"

				+ "		<th>Center id</th>\n"
				+ "		<th>Name</th>\n"
				+ "		<th>Address</th>\n"
				+ "		<th>State</th>\n"
				+ "		<th>District</th>\n"
				+ "		<th>Block</th>\n"
				+ "		<th>Pincode</th>\n"
				+ "		<th>Fee Type</th>\n"
				+ "		<th>Date</th>\n"
				+ "		<th>Age Limit</th>\n"
				+ "		<th>available Dose1</th>\n"
				+ "		<th>available Dose2</th>\n"
				+ "		<th>Vaccine</th>");

		centers.stream()
		.forEach(center -> {
			resp.append("<tr>\n"
					+ "<td>"+ center.center_id+"</td>\n "
					+ "<td>"+ center.name+"</td>\n "
					+ "<td>"+ center.address+"</td>\n "
					+ "<td>"+ center.state_name+"</td>\n "
					+ "<td>"+ center.district_name+"</td>\n "
					+ "<td>"+ center.block_name+"</td>\n "
					+ "<td>"+ center.pincode+"</td>\n "
					+ "<td>"+ center.fee_type+"</td>\n ");
			center.getSessions()
			.stream().forEach(session -> {
				resp.append( "<td>"+session.date+"</td>\n ");
				resp.append( "<td>"+session.min_age_limit+"</td>\n ");
				resp.append( "<td>"+session.available_capacity_dose1+"</td>\n ");
				resp.append( "<td>"+session.available_capacity_dose2+"</td>\n ");
				resp.append( "<td>"+session.vaccine+"</td>\n ");
			} );
			resp.append( "</tr>");
		});

		return resp.toString();
	}
}
