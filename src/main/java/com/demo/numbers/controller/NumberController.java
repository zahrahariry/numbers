package com.demo.numbers.controller;

import java.util.List;

import com.demo.numbers.dto.NumberRequest;
import com.demo.numbers.dto.NumberResponse;
import com.demo.numbers.exception.InvalidInputException;
import com.demo.numbers.service.NumberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/numbers")
@RequiredArgsConstructor
@Tag(name = "Numbers Controller", description = "numbers APIs")
public class NumberController {

	private final NumberService numberService;

	@PostMapping
	public ResponseEntity<List<NumberResponse>> processNumbers(@RequestBody @Valid NumberRequest numberRequest) {
		if (numberRequest == null || numberRequest.getNumbers() == null || numberRequest.getX() == null ||
				numberRequest.getNumbers().size() < 2) {
			throw new InvalidInputException("Number request or numbers list cannot be null");
		}
		return ResponseEntity.ok(numberService.findPairs(numberRequest));
	}

}
