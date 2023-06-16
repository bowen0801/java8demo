package com.function;


import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class PharmacyGoodsHandler {

    public static void main(String[] args) {
        PharmacyGoodsHandler handler = new PharmacyGoodsHandler();
        handler.process("updateQt",()->{
            //更新db数据库
            GoodsUpdateVo vo = new GoodsUpdateVo();
            vo.setSkuId(1L);
            vo.setQty(10L);
            GoodsUpdateVo vo2 = new GoodsUpdateVo();
            vo2.setSkuId(2L);
            vo2.setQty(10L);
            List<GoodsUpdateVo> goodsList = Lists.newArrayList();
            goodsList.add(vo);
            goodsList.add(vo2);
            return goodsList;
        },(successList)->{
            handler.updateStock(successList);
        },"a");
    }

    public int process(String methodName, Supplier<List<GoodsUpdateVo>> dbSupplier, Consumer<List<GoodsUpdateVo>> cacheProcessor, String changedBy) {
        return deal(methodName, dbSupplier, cacheProcessor, s -> {}, changedBy);
    }

    private int deal(String methodName, Supplier<List<GoodsUpdateVo>> dbSupplier, Consumer<List<GoodsUpdateVo>> cacheProcessor,
                       Consumer<List<GoodsUpdateVo>> afterProcessor,String changedBy) {
        long begin = System.currentTimeMillis();

        //数据库更新成功的商品列表
        List<GoodsUpdateVo> goodsList = dbSupplier.get();

        if (CollectionUtils.isEmpty(goodsList)) {
            return 0;
        }
        //记录更新日志
        //this.operateLogHandler.addOperateLog(goodsList, methodName, BisConstants.OPERATE_TYPE_UPDATE, changedBy);
        //记录库存操作日志
        //this.stockLogHandler.addInventoryLogs(goodsList, inventoryUpdateType);
        //执行缓存操作
        cacheProcessor.accept(goodsList);
        //执行完成后处理后操作
        afterProcessor.accept(goodsList);
        //logger.info("BisGoodsServiceImpl.{} 方法执行时间 ：{}ms,开始时间：{}", methodName, (System.currentTimeMillis() - begin), begin);
        return goodsList.size();
    }
    private void updateStock(List<GoodsUpdateVo> goodsLists) {
        System.out.println("--updateStock start--");
        for (GoodsUpdateVo vo : goodsLists) {
            System.out.println("skuId:"+ vo.getSkuId()+ " qty:"+vo.getQty());
        }
        System.out.println("--updateStock end--");

    }

}
