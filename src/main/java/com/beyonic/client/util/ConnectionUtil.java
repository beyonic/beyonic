package com.beyonic.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.beyonic.client.constants.Constants;
import com.beyonic.client.exception.APIConnectionException;
import com.beyonic.client.exception.AuthenticationException;
import com.beyonic.client.exception.InvalidRequestException;
import com.google.gson.Gson;

public abstract class ConnectionUtil {

	public static final String CHARSET = "UTF-8";

	private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

	protected enum RequestMethod {
		GET, POST, PUT, DELETE
	}


	private static HttpsURLConnection createConnection(
			String url, RequestOptions options) throws IOException {

		HttpsURLConnection con = (HttpsURLConnection)new URL(url).openConnection();
		con.setConnectTimeout(30 * 1000);
		con.setReadTimeout(80 * 1000);
		con.setUseCaches(false);
		for (Map.Entry<String, String> header : options.getHeaders().entrySet()) {
			con.setRequestProperty(header.getKey(), header.getValue());
		}

		return con;
	}


	private static String formatURL(String url, String query) {
		if (query == null || query.isEmpty()) {
			return url;
		} else {
			// In some cases, URL can already contain a question mark (eg, upcoming invoice lines)
			String separator = url.contains("?") ? "&" : "?";
			return String.format("%s%s%s", url, separator, query);
		}
	}

	private static java.net.HttpURLConnection createGetConnection(
			String url, String query, RequestOptions options) throws IOException, APIConnectionException {
		String getURL = formatURL(url, query);
		HttpsURLConnection con = createConnection(url, options);
		con.setRequestMethod("GET");

		return con;
	}

	private static java.net.HttpURLConnection createPostConnection(
			String url, String query, RequestOptions options) throws IOException, APIConnectionException {
		java.net.HttpURLConnection conn = createConnection(url, options);

		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", String.format(
				"application/x-www-form-urlencoded;charset=%s", CHARSET));

		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;
	}

	private static java.net.HttpURLConnection createDeleteConnection(
			String url, String query, RequestOptions options) throws IOException, APIConnectionException {
		String deleteUrl = formatURL(url, query);
		java.net.HttpURLConnection conn = createConnection(
				deleteUrl, options);
		conn.setRequestMethod("DELETE");

		return conn;
	}

	private static String createQuery(Map<String, String> params)
			throws UnsupportedEncodingException, InvalidRequestException {
		StringBuilder queryStringBuffer = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (queryStringBuffer.length() > 0) {
				queryStringBuffer.append("&");
			}
			queryStringBuffer.append(urlEncodePair(entry.getKey(),
					entry.getValue()));
		}
		return queryStringBuffer.toString();
	}

	// represents Errors returned as JSON
	private static class ErrorContainer {
		private Error error;
	}

	private static class Error {
		@SuppressWarnings("unused")
		String type;

		String message;

		String code;

		String param;
	}

	private static String getResponseBody(InputStream responseStream)
			throws IOException {
		//\A is the beginning of
		// the stream boundary
		String rBody = new Scanner(responseStream, CHARSET)
												.useDelimiter("\\A")
												.next(); //

		responseStream.close();
		return rBody;
	}

	private static String makeURLConnectionRequest(RequestMethod method, String url, String query,
			RequestOptions options) throws APIConnectionException, InvalidRequestException, AuthenticationException {
		java.net.HttpURLConnection conn = null;
		try {
			switch (method) {
			case GET:
				conn = createGetConnection(url, query, options);
				break;
			case POST:
				conn = createPostConnection(url, query, options);
				break;
			case DELETE:
				conn = createDeleteConnection(url, query, options);
				break;
			default:
				throw new IllegalArgumentException("Unrecognized HTTP method");
			}
			// trigger the request
			int rCode = conn.getResponseCode();
			String rBody;
			rBody = getResponseBody(conn.getInputStream());
			if (rCode < 200 && rCode > 300) {
				handleAPIError(rBody, rCode);
			} 
			
			return rBody;
		} catch (IOException e) {
			throw new APIConnectionException("IOException during API request."
									+ "Please check your internet connection and try again.");
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected static String request(RequestMethod method, String url, RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException	{
		if (options == null) {
			options = RequestOptions.getDefault();
		}
		String originalDNSCacheTTL = null;
		Boolean allowedToSetTTL = true;

		try {
			originalDNSCacheTTL = java.security.Security
					.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
			// disable DNS cache
			java.security.Security
					.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
		} catch (SecurityException se) {
			allowedToSetTTL = false;
		}

		try {
			return _request(method, url, options);
		} finally {
			if (allowedToSetTTL) {
				if (originalDNSCacheTTL == null) {
					// value unspecified by implementation
					// DNS_CACHE_TTL_PROPERTY_NAME of -1 = cache forever
					java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "-1");
				} else {
					java.security.Security.setProperty(
							DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
				}
			}
		}
	}

	protected static String _request(RequestMethod method, String url,	RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException {
		String apiKey = options.getHeaders().get(Constants.API_KEY);
		if (apiKey == null || apiKey.trim().isEmpty()) {
			throw new AuthenticationException(
					"No API key provided. (HINT: set your API key using 'Client.apiKey = <API-KEY>'. "
							+ "You can generate API keys from the Beyonic web interface. ");
		}

		String query;

		try {
			query = createQuery(options.getParams());
		} catch (UnsupportedEncodingException e) {
			throw new InvalidRequestException("Unable to encode parameters to "	+ CHARSET+"."+ e.getMessage());
		}

		String response;
		try {
			// HTTPSURLConnection verifies SSL cert by default
			response = makeURLConnectionRequest(method, url, query, options);
		} catch (ClassCastException ce) {
				throw ce;
		}
		
		return response;
	}

	private static void handleAPIError(String rBody, int rCode)
			throws InvalidRequestException, AuthenticationException, APIConnectionException {
		Error error = new Gson().fromJson(rBody,
				ErrorContainer.class).error;
		switch (rCode) {
		case 400:
			throw new InvalidRequestException(error.message);
		case 404:
			throw new InvalidRequestException(error.message);
		case 401:
			throw new AuthenticationException(error.message);
		default:
			throw new APIConnectionException(error.message);
		}
	}

	private static String urlEncode(String str) throws UnsupportedEncodingException {
		// Preserve original behavior that passing null for an object id will lead
		// to us actually making a request to /v1/foo/null
		if (str == null) {
			return null;
		}
		else {
			return URLEncoder.encode(str, CHARSET);
		}
	}

	private static String urlEncodePair(String k, String v)
			throws UnsupportedEncodingException {
		return String.format("%s=%s", urlEncode(k), urlEncode(v));
	}
}
