package com.bowen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		
	        String pharmacyIds = "-1,100012,122204";
	        List<Long> pharmacyIdList = Arrays.asList(pharmacyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
	        pharmacyIdList.forEach(ph-> System.out.println(ph));
	}

}
