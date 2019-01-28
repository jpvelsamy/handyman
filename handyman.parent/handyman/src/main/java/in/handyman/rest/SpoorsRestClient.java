package in.handyman.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Date;

import in.handyman.rest.SimpleHttpRequest.Method;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SpoorsRestClient implements RestClient {

	private final String baseURL;

	private final String password;

	private final String authType;
	
	public SpoorsRestClient() {
		
		baseURL = "http://nd.spoors.in/effort5-2016/service/api/all?userName=lasalle";
		password = "​";		
		authType = "jwt";
	}
	

	public SpoorsRestClient(String baseURL, String password, String authInfo) {
		super();
		this.baseURL = baseURL;				
		this.password = password;
		this.authType = authInfo;
		
	}

	public static final class Header {
		public static final String CONTENT_TYPE = "Content-Type";		
	}

	@Override
	public String post(String path,String payload) {
		String url = baseURL+path;
		try {

						
			String jwt = SpoorsRestClient.getJWT(password);
			System.out.println("Using token for jwt auth="+jwt);
			SimpleHttpResponse response= new HttpRequestBuilder().withURL(url).withMethod(Method.POST)
					.addHeader(Header.CONTENT_TYPE, "application/json; charset=utf-8")
					.addHeader("jwt", jwt)
					.withContent(payload)
					.execute();
			String content = readContentAndClose(response.getContent());
			
			System.out.println("Received response"+content);	
			
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return null;
	}

	@Override
	public String get(String path) {
		String url = baseURL;
		try {
			String jwt = SpoorsRestClient.getJWT(password);
			SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.GET)
					.addHeader("jwt", jwt)
					.execute();
			String content = readContentAndClose(response.getContent());
			if (response.getStatus() != HttpURLConnection.HTTP_OK) {
				throw new IllegalStateException(content);
			}
			return content;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void createAuthToken() {
		 if (authType.equals("jwt")) {
			
			String url = baseURL;
			try {
				String jwt = SpoorsRestClient.getJWT(password);
				SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.POST)
						.addHeader(Header.CONTENT_TYPE, "application/json; charset=utf-8")
						.addHeader("jwt", jwt)
						.execute();
				String content = readContentAndClose(response.getContent());
				System.out.println(content);	
				
				
			}
			catch (IOException e) {
				throw new IllegalStateException(e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 public static String getJWT(String secret) throws Exception
	    {
		 
	       String jwt =  Jwts.builder()
	                .setExpiration(new Date(System.currentTimeMillis() + 360000))
	                .setIssuer("Lasalle")
	                .setSubject("Spoors Api")
	                .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
	                .compact();
	       return jwt;
	    
	  }
	
	private String readContentAndClose(InputStream content) throws IOException {
		InputStreamReader stream = new InputStreamReader(content, "UTF-8");
		BufferedReader reader = new BufferedReader(stream);
		String data = reader.readLine();
		stream.close();
		reader.close();
		return data;
	}
}