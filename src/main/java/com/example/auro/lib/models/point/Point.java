package com.example.auro.lib.models.point;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Point {
	
	@NonNull
	@Getter
	private final Integer row;
	
	@NonNull
	@Getter
	private final Integer column;

}
