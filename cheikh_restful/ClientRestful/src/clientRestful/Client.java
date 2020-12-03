package clientRestful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class Client {

	public static void main(String[] args) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new
		HttpGet("http://10.153.39.45:8080/NotreBanque/msir/comptes/1");
		getRequest.addHeader("accept", "application/xml");
		try {
		HttpResponse response = httpClient.execute(getRequest);		
		if (response.getStatusLine().getStatusCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " +
		response.getStatusLine().getStatusCode());
		}
		BufferedReader br = new BufferedReader(new
		InputStreamReader((response.getEntity().getContent())));
		String output;
		while ((output = br.readLine()) != null) {
		System.out.println(output);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
