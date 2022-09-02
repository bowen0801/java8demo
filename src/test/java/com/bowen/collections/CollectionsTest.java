package com.bowen.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class CollectionsTest {
	public static void main(String[] args) {
		List<String> list1 = null;
		List<String> list2 = new ArrayList<String>();
		list2.add("a");
		
		System.out.println("list1 is empty:"+CollectionUtils.isEmpty(list1));
		System.out.println("list2 is empty:"+CollectionUtils.isEmpty(list2));
	}

}
