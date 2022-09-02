package com.bowen;

import org.apache.commons.lang3.StringUtils;

public class NotEmptyTest {
	public static void main(String[] args) {
		String b = "null";
		Long.parseLong(b);
		boolean notEmpty = StringUtils.isNotEmpty(b);
		System.out.println(notEmpty);
		boolean notBlank2 = StringUtils.isNotBlank(b);
		System.out.println(notBlank2);
	}

}
