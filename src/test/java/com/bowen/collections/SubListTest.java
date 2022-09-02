package com.bowen.collections;

import java.util.ArrayList;
import java.util.List;

public class SubListTest {
	public static void main(String[] args) {
		List<String> a = new ArrayList<>();
		a.add("a");
		a.add("b");
		a.add("c");
		
		List<String> subList = a.subList(0, 2);
		for (String string : subList) {
			System.out.println(string);
		}
		
	}

}
