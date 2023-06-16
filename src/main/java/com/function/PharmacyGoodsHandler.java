package com.function;

public class PharmacyGoodsHandler {

    public int process(String methodName, Supplier<List<GoodsUpdateVo>> dbSupplier, Consumer<List<GoodsUpdateVo>> cacheProcessor, Long inventoryUpdateType, String changedBy) {
        return process(methodName, dbSupplier, cacheProcessor, s -> {
        }, inventoryUpdateType, changedBy);
    }

}
