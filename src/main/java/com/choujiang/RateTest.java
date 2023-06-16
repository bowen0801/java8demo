package com.choujiang;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.*;

public class RateTest {
    public static void main(String[] args) {
        int productACount = 0;
        int productBCount = 0;
        int productCCount = 0;
        int productDCount = 0;
        int productECount = 0;

        int totalCount = 1000000;
        for (int k = 0 ; k < totalCount;k++) {
            BlindBoxProduct blindBoxProduct = selectBlindBox();
            String productName = blindBoxProduct.getProductName();
            //System.out.println(productName);
            if (productName.equals("商品A")) {
                productACount ++;
            } else if(productName.equals("商品B")){
                productBCount ++;
            } else if(productName.equals("商品C")){
                productCCount ++;
            } else if(productName.equals("商品D")){
                productDCount ++;
            } else if(productName.equals("商品E")){
                productECount ++;
            } else {
                System.out.println("error---");
            }
        }
        //结果
        System.out.println("盲盒抽取总次数："+totalCount);
        System.out.println("productA 设置中奖概率比重：10  实际抽中概率："+productACount*1.0/totalCount*100);
        System.out.println("productB 设置中奖概率比重：20  实际抽中概率："+productBCount*1.0/totalCount*100);
        System.out.println("productC 设置中奖概率比重：30  实际抽中概率："+productCCount*1.0/totalCount*100);
        System.out.println("productD 设置中奖概率比重：20  实际抽中概率："+productDCount*1.0/totalCount*100);
        System.out.println("productE 设置中奖概率比重：20  实际抽中概率："+productECount*1.0/totalCount*100);

        /*BlindBoxProduct blindBoxProduct = selectBlindBox();*/

    }

    public static BlindBoxProduct selectBlindBox(){
        List<BlindBoxProduct> blindBoxList = Lists.newArrayList();
        //商品A 中奖概率 10%
        BlindBoxProduct productA = new BlindBoxProduct("商品A",new Double("10"));
        //商品B 中奖概率 30%
        BlindBoxProduct productB = new BlindBoxProduct("商品B",new Double("20"));
        //商品C 中奖概率 50%
        BlindBoxProduct productC = new BlindBoxProduct("商品C",new Double("30"));
        //商品D 中奖概率 1%
        BlindBoxProduct productD = new BlindBoxProduct("商品D",new Double("20"));
        //商品E 中奖概率 9%
        BlindBoxProduct productE = new BlindBoxProduct("商品E",new Double("20"));
        blindBoxList.add(productA);
        blindBoxList.add(productB);
        blindBoxList.add(productC);
        blindBoxList.add(productD);
        blindBoxList.add(productE);

        //BigDecimal totalRate = productA.getRate().add(productB.getRate()).add(productC.getRate()).add(productD.getRate()).add(productE.getRate());
        //System.out.println(totalRate.longValue());
        Map<Integer,BlindBoxProduct> blindBoxProdutMap = new LinkedHashMap<>();
        Integer totalRate = new Integer("0");
        for (BlindBoxProduct blindBoxProduct : blindBoxList) {
            totalRate = totalRate + blindBoxProduct.getRate().intValue();
            //System.out.println(totalRate);
            blindBoxProdutMap.put(totalRate.intValue(),blindBoxProduct);
        }
        Integer randomNum = (int)(Math.random() * totalRate.intValue());
        //System.out.println("randomNum:"+randomNum);
        Set<Integer> keySet = blindBoxProdutMap.keySet();
        BlindBoxProduct blindBox = null;
        for (Integer blindBoxKey : keySet) {
            if(randomNum < blindBoxKey){
                blindBox = blindBoxProdutMap.get(blindBoxKey);
                break;
            }
        }
        return blindBox;
    }
}
