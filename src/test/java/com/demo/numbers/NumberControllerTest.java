package com.demo.numbers;

import java.util.List;

import com.demo.numbers.dto.NumberRequest;
import com.demo.numbers.dto.NumberResponse;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NumberControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testProcessNumbers_Success() {
		NumberRequest numberRequest = new NumberRequest();
		numberRequest.setNumbers(List.of(2, 12, 41, 1028, 16, 15, 17, 150));
		numberRequest.setX(43);

		ResponseEntity<List<NumberResponse>> response = restTemplate.exchange(
				createURLWithPort("/numbers"),
				HttpMethod.POST,
				new HttpEntity<>(numberRequest),
				new ParameterizedTypeReference<>() {}
		);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<NumberResponse> numberResponses = response.getBody();
		assertThat(numberResponses).isNotNull();
		assertThat(numberResponses.size()).isEqualTo(2);
		NumberResponse numberResponse = new NumberResponse(2, 41);
		assertThat(numberResponses.contains(numberResponse)).isTrue();
	}

	@Test
	public void testProcessNumbers_ListOfInputIsEmpty_fail() {
		NumberRequest numberRequest = new NumberRequest();
		numberRequest.setNumbers(null);
		numberRequest.setX(43);

		ResponseEntity<List<NumberResponse>> response = restTemplate.exchange(
				createURLWithPort("/numbers"),
				HttpMethod.POST,
				new HttpEntity<>(numberRequest),
				new ParameterizedTypeReference<>() {}
		);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		List<NumberResponse> numberResponses = response.getBody();
		assertThat(numberResponses).isNotNull();
		assertThat(numberResponses.size()).isEqualTo(0);
	}

	@Test
	public void testProcessNumbers_SizeOfListOfInputIs1_fail() {
		NumberRequest numberRequest = new NumberRequest();
		numberRequest.setNumbers(List.of(1));
		numberRequest.setX(43);

		ResponseEntity<List<NumberResponse>> response = restTemplate.exchange(
				createURLWithPort("/numbers"),
				HttpMethod.POST,
				new HttpEntity<>(numberRequest),
				new ParameterizedTypeReference<>() {}
		);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		List<NumberResponse> numberResponses = response.getBody();
		assertThat(numberResponses).isNotNull();
		assertThat(numberResponses.size()).isEqualTo(0);
	}

	@Test
	public void testProcessNumbers_xIsNull_fail() {
		NumberRequest numberRequest = new NumberRequest();
		numberRequest.setNumbers(List.of(2, 12, 41, 1028, 16, 15, 17, 150));
		numberRequest.setX(null);

		ResponseEntity<List<NumberResponse>> response = restTemplate.exchange(
				createURLWithPort("/numbers"),
				HttpMethod.POST,
				new HttpEntity<>(numberRequest),
				new ParameterizedTypeReference<>() {}
		);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		List<NumberResponse> numberResponses = response.getBody();
		assertThat(numberResponses).isNotNull();
		assertThat(numberResponses.size()).isEqualTo(0);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
