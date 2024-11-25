package com.demo.numbers.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.numbers.dto.NumberRequest;
import com.demo.numbers.dto.NumberResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NumberService {

	public List<NumberResponse> findPairs(NumberRequest numberRequest) {
		return numberRequest.getNumbers().stream()
				.collect(Collectors.collectingAndThen(
						Collectors.toCollection(HashSet::new),
						set -> numberRequest.getNumbers().stream()
								.filter(num -> {
									int complement = numberRequest.getX() - num;
									boolean isPairFound = set.contains(complement);
									set.add(num);
									return isPairFound;
								})
								.map(num -> new NumberResponse(num, numberRequest.getX() - num))
								.collect(Collectors.toList())
				));
	}
}
