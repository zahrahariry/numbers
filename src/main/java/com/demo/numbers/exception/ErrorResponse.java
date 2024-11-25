package com.demo.numbers.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

	private int status;
	private String message;
	private LocalDateTime timestamp;
}
