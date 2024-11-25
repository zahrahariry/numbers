package com.demo.numbers.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class NumberRequest {

	@NotNull(message = "Number request cannot be null")
	@Size(min = 2, message = "At least two numbers are required")
	private List<Integer> numbers;

	@NotNull(message = "X value cannot be null")
	private Integer x;
}
