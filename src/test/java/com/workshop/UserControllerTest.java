package com.workshop;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Value("${local.server.port}")
	protected int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private static final String GRANT_TYPE = "password";
	private static final String CLIENT_ID = "workshop2";
	private static final String CLIENT_SECRET = "secret";
	private static final String USERNAME = "user2";
	private static final String PASSWORD = "pas123";

	@Test
	public void testWithoutAuthenticate() throws Exception {
		ResponseEntity<String> response = testRestTemplate.getForEntity(String.format("http://localhost:%d", port).concat("/users"), String.class);
		assertEquals("Response Code should match expected code", HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}


	@Test
	public void testWithAuthAndContentTypeHeaders() throws Exception {
		String token = getAuthToken();
		HttpHeaders authHeader = new HttpHeaders();
		authHeader.set("Authorization", "Bearer " + token);
		ResponseEntity<String> response = testRestTemplate.exchange(String.format("http://localhost:%d", port).concat("/users"),
				HttpMethod.GET, new HttpEntity(null, authHeader), String.class);
		assertEquals("Response Code should match expected code", HttpStatus.OK, response.getStatusCode());
	}

	private String getAuthToken() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", GRANT_TYPE);
		body.add("username", USERNAME);
		body.add("password", PASSWORD);
		body.add("client_id", CLIENT_ID);
		body.add("client_secret", CLIENT_SECRET);

		ResponseEntity<String> response = testRestTemplate.exchange(
				String.format("http://localhost:%d", port).concat("/oauth/token"),
				HttpMethod.POST, new HttpEntity(body, headers), String.class);
		JsonNode json = objectMapper.readValue(response.getBody(), JsonNode.class);
		return json.get("access_token").asText();
	}




}
