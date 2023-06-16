package com.bowen;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<Long> shopIds = Lists.newArrayList(1L,2L,3L,4L,5L);
        List<List<Long>> allSubShopList = getAllSubShopList(shopIds);
        System.out.println(allSubShopList);

    }

    private static List<List<Long>> getAllSubShopList(List<Long> shopIds) {
        List<List<Long>> allSubShopList = new ArrayList<>();
        List<Long> validShopList = Lists.newArrayList();
        validShopList.addAll(shopIds);
        for (int m = 0; m < validShopList.size(); m++) {
            List<List<Long>> subShopList = new ArrayList<List<Long>>();
            Long shopId = validShopList.get(m);
            List<Long> subList = new ArrayList<Long>();
            subList.add(shopId);
            subShopList.add(subList);
            for (int n = 0; n < validShopList.size(); n++) {
                if (n <= m) {
                    continue;
                }
                Long shopId1 = validShopList.get(n);
                if (subShopList.size() > 0) {
                    addSubList(subShopList, shopId1);
                }
            }
            allSubShopList.addAll(subShopList);
        }
        Collections.sort(allSubShopList, new Comparator<List<Long>>() {
            @Override
            public int compare(List<Long> sub1, List<Long> sub2) {
                return sub1.size() - sub2.size();
            }
        });
        return allSubShopList;
    }
    private static void addSubList(List<List<Long>> shopListBsse, Long shopId) {
        List<List<Long>> shopList = new ArrayList<List<Long>>();
        for (List<Long> subListBsse : shopListBsse) {
            List<Long> subList = new ArrayList<Long>();
            subList.addAll(subListBsse);
            subList.add(shopId);
            shopList.add(subList);
        }
        shopListBsse.addAll(shopList);
    }
}
