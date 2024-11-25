package com.demo.numbers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class NumberResponse {

	private Integer firstNumber;

	private Integer secondNumber;
}
