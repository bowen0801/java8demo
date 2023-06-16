package com.function2;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProductHandler {

    public int updateQty(List<Long> skuIdList, Supplier<List<GoodsVo>> supplier, Consumer<List<GoodsVo>> refreshCacheConsumer){
        List<GoodsVo> goodsVos = supplier.get();
        if (goodsVos == null || goodsVos.size() <=0 ) {
            return 0;
        }
        //add log
        refreshCacheConsumer.accept(goodsVos);
        //addlog
        return goodsVos.size();
    }
}
