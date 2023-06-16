package com.choujiang;

public class BlindBoxProduct {
    private String productName;
    private Double rate;//中奖比重

    public BlindBoxProduct() {
    }

    public BlindBoxProduct(String productName, Double rate) {
        this.productName = productName;
        this.rate = rate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
