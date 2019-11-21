package com.bruno.resources.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Long> decodeLongList(String s) {
		return Arrays.asList(s.split(",")).stream().map(x -> Long.parseLong(s)).collect(Collectors.toList());

	}
}
