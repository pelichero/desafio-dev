package com.br.maplink.desafio_dev.autentication;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;  // JDK 1.8 only - older versions may need to use Apache Commons or similar.
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.maplink.desafio_dev.dto.search.Location;
import com.br.maplink.desafio_dev.vos.Address;

@Component
/**
 * 
 * @author felipe.pelichero
 *
 * 8 de jun de 2017
 */
public class RequestSigner {

	private static final String TRAVEL_MODE = "shortest";
	private static final String QUERY_TYPE = "address";
	private static final int LIMIT_RESPONSE = 1;
	private byte[] key;
	private String applicationCode;
	private String token;
  
	@Autowired
	public RequestSigner(@Value("${key}") String keyString, @Value("${applicationCode}") String applicationCode,
			@Value("${token}") String token) throws IOException {
		keyString = keyString.replace('-', '+');
		keyString = keyString.replace('_', '/');
		this.key = Base64.getDecoder().decode(keyString);
		this.applicationCode = applicationCode;
		this.token = token;
	}

	public URI addressSignWithToken(String path, Address address) {
		URI uri = UriComponentsBuilder.fromUriString(path).queryParam("q", address.getFullAddress())
				.queryParam("types", QUERY_TYPE).queryParam("limit", LIMIT_RESPONSE).queryParam("token", token).build()
				.encode().toUri();

		return uri;
	}
	  
	public URI routeSignWithToken(String path, List<Location> location) {
		URI uri = UriComponentsBuilder.fromUriString(path).queryParam("travel.mode", TRAVEL_MODE)
				.queryParam("result", "summary.duration,summary.distance,summary.tolls")
				.queryParam("waypoint.0.latlng", location.get(0).getGeoLocation())
				.queryParam("waypoint.1.latlng", location.get(1).getGeoLocation()).queryParam("token", token).build()
				.encode().toUri();

		return uri;
	}
  
	public String sign(String _url) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException,
			URISyntaxException, MalformedURLException {

		URL url = new URL(_url);

		String resource = url.getPath() + '?' + url.getQuery();
		SecretKeySpec sha1Key = new SecretKeySpec(key, "HmacSHA1");

		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(sha1Key);

		byte[] sigBytes = mac.doFinal(resource.getBytes());

		String signature = Base64.getEncoder().encodeToString(sigBytes);

		signature = signature.replace('+', '-');
		signature = signature.replace('/', '_');

		return url.getProtocol() + "://" + url.getHost() + resource + "&signature=" + signature + "&applicationCode="
				+ applicationCode;
	}

	public String generateSignature(String path, String query, String body)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, URISyntaxException {

		// Retrieve the proper URL components to sign
		String resource = path + '?' + query + body;

		// Get an HMAC-SHA1 signing key from the raw key bytes
		SecretKeySpec sha1Key = new SecretKeySpec(key, "HmacSHA1");

		// Get an HMAC-SHA1 Mac instance and initialize it with the HMAC-SHA1
		// key
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(sha1Key);

		// compute the binary signature for the request
		byte[] sigBytes = mac.doFinal(resource.getBytes());

		// base 64 encode the binary signature
		// Base64 is JDK 1.8 only - older versions may need to use Apache
		// Commons or similar.
		String signature = Base64.getEncoder().encodeToString(sigBytes);

		// convert the signature to 'web safe' base 64
		signature = signature.replace('+', '-');
		signature = signature.replace('/', '_');

		return signature;
	}
  
}  