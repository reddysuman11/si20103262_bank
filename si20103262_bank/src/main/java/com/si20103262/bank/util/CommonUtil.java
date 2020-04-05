package com.si20103262.bank.util;

import java.util.List;

public class CommonUtil {

	public static <T> boolean isListEmpty(List<T> t) {
		return (t == null || t.isEmpty());
	}
	
	public static <T> boolean isListNotEmpty(List<T> t) {
		return !isListEmpty(t);
	}
	
	public static <T> boolean isNull(T t) {
		return (t == null);
	}
	
	public static <T> boolean isNotNull(T t) {
		return !isNull(t);
	}
}
