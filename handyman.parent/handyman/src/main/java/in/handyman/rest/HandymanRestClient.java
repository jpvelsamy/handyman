package in.handyman.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.codec.binary.Base64;
import in.handyman.rest.SimpleHttpRequest.Method;

import com.google.gson.Gson;

public class HandymanRestClient implements RestClient {

	private static final String MIFOS_ENDPOINT = "mifos.endpoint";

	private final String baseURL;

	private final String userName;

	private final String password;

	private final String tenantId;

	private String authToken;

	private final String authType;

	static {
		if (System.getProperty(MIFOS_ENDPOINT) != null && System.getProperty(MIFOS_ENDPOINT).contains("localhost")) {
			// for localhost testing only
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, @SuppressWarnings("unused") SSLSession sslSession) {
					if (hostname.equals("localhost")) {
						return true;
					}
					return false;
				}
			});
		}
	}

	public HandymanRestClient() {

		baseURL = System.getProperty(MIFOS_ENDPOINT);
		userName = System.getProperty("mifos.user.id");
		password = System.getProperty("mifos.password");
		tenantId = System.getProperty("mifos.tenant.id");
		authType = "chimeraauth";
	}
	
	

	public HandymanRestClient(String baseURL, String authInfo) {
		super();
		String[] authInfoArr = authInfo.split(":");
		this.baseURL = baseURL;
		this.userName = authInfoArr[0];
		this.password = authInfoArr[1];
		this.authType = authInfoArr[2];
		this.tenantId = authInfoArr[3];
	}

	/*
	 * public HandymanRestClient(String baseURL, String userName, String password,
	 * String tenantId) { super(); this.baseURL = baseURL; this.userName = userName;
	 * this.password = password; this.tenantId = tenantId; authType = "chimeraauth";
	 * }
	 */

	public static final class Header {
		public static final String AUTHORIZATION = "Authorization";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String MIFOS_TENANT_ID = "Fineract-Platform-TenantId";
	}

	@Override
	public String post(String path, String payload) {
		String url = baseURL + path;
		try {

			SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.POST)
					.addHeader(Header.AUTHORIZATION, "Basic " + authToken)
					.addHeader(Header.CONTENT_TYPE, "application/json; charset=utf-8")
					.addHeader(Header.MIFOS_TENANT_ID, tenantId).withContent(payload).execute();

			String content = readContentAndClose(response.getContent());
			System.out.println("Post response");
			System.out.println(content);

			if (response.getStatus() != HttpURLConnection.HTTP_OK) {
				throw new IllegalStateException(content);
			}
			return content;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String get(String path) {
		String url = baseURL + path;
		try {
			SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.GET)
					.addHeader(Header.AUTHORIZATION, "Basic " + authToken)
					.addHeader(Header.MIFOS_TENANT_ID, tenantId)
					.execute();
			String content = readContentAndClose(response.getContent());
			if (response.getStatus() != HttpURLConnection.HTTP_OK) {
				throw new IllegalStateException(content);
			}
			return content;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void createAuthToken() {
		if (authType.equals("chimeraauth")) {
			String url = baseURL + "/authentication?username=" + userName + "&password=" + password;
			try {
				SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.POST)
						.addHeader(Header.MIFOS_TENANT_ID, tenantId)
						.addHeader(Header.CONTENT_TYPE, "application/json; charset=utf-8").execute();
				String content = readContentAndClose(response.getContent());
				AuthToken auth = new Gson().fromJson(content, AuthToken.class);
				authToken = auth.getBase64EncodedAuthenticationKey();

				System.out.println("Authentication Response");
				System.out.println(content);
				System.out.println(url);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (authType.equals("basicauth")) {
			String authString = userName + ":" + password;
			authToken = new String(Base64.encodeBase64(authString.getBytes()));
		}
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