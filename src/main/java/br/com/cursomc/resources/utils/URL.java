package br.com.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	private URL() {
		throw new IllegalStateException();
	}

	public static String decodeParam(String string) {
		try {
			return URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static List<Integer> decodeIntToList(String string) {

		return Arrays.asList(string.split(",")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}
