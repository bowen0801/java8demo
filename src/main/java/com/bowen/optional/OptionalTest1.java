package com.bowen.optional;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class OptionalTest1 {
    @Test
    public void test1(){
        Student s = null;//new Student();
        //s.setGender("woman");
        String aNull = Optional.ofNullable(s).map(u -> u.getGender()).orElse("null");//消除if else
        System.out.println(aNull);
    }

    @Test
    public void test2(){
        List<String> list = Lists.newArrayList("book","tom","boat");
        Optional.ofNullable(list).ifPresent(todoTask->{
            todoTask.forEach(item->{
                System.out.println("item:"+item);
            });
        });
    }

    @Test
    public void test3(){
        String str = "";
        String s = Optional.ofNullable(null).orElse(-1).toString();
        System.out.println(s);
    }
    @Test
    public void testListNull(){
        //1.判断List集合是否为空
        List<String> resultList = new ArrayList<>();
        List<String> list = Lists.newArrayList("book","tom","boat");//null;//new ArrayList<>();//Lists.newArrayList("book","tom","boat");
        Optional.ofNullable(list).ifPresent(taskList -> resultList.addAll(taskList));
        resultList.forEach(item ->{
            System.out.println("== :"+item);
        });

        Optional.ofNullable(list).ifPresent(taskList -> resultList.addAll(taskList));
        resultList.forEach(item ->{
            System.out.println("-- :"+item);
        });


        Optional.ofNullable(list).ifPresent(taskList->{
            taskList.forEach(item->{
                System.out.println("vali item:"+item);
            });
        });

        Optional.ofNullable(list).ifPresent(taskList->{
            for (String s : taskList) {
                System.out.println("for:"+ s);
            }
        });

        Optional.ofNullable(list).ifPresent(taskList -> taskList.forEach(item ->{
            System.out.println("for list:"+ item);
        }));

    }

    @Test
    public void testMapNull(){
        Map<Long,String> map = new HashMap<>();
        for (String value : map.values()) {
            System.out.println("====");
        }
        //map.put(1L,"a");
       /* Optional.ofNullable(map).ifPresent( tmp ->{
            Set<Long> longs = map.keySet();
            System.out.println("---");
            for (Long aLong : longs) {
                System.out.println("key:"+aLong + " value:"+map.get(aLong));
            }
        });*/
    }

    @Test
    public void test5(){
        Integer quantity = null;
        Integer newQuantity = Optional.ofNullable(quantity).orElse(1);
        System.out.println(newQuantity);

		/*
		1.使用Optional空判断，避免出现if条件
		  Optional.ofNullable(presentParam.getQuantity()).orElse(0L).longValue()

		for (PresentParam presentParam : presentList) {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setSkuId(presentParam.getSkuId());
			item.setQuantity(Optional.ofNullable(presentParam.getQuantity()).orElse(0L).longValue());
			item.setProductId(presentParam.getProductId());
			item.setPharmacyId(pharmacyId);
			item.setPromotionId(presentParam.getPromotionId());
			item.setPromotionType(new Long(PromotionTypeEnum.PRESENT.getCode()));// 标识增加购物车类型
			list.add(item);
		}*/
        Long u = 1L;
        long result = Optional.ofNullable(u).orElse(0L).longValue();
        System.out.println(result);
    }

}
