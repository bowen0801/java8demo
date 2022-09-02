package com.bowen;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * java8 流API
 * @version v1.0.0
 * @since 2019年08月24日
 */
public class StreamTest {
    //limit使用
    public static <T> void show(String title,Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title + ":");
        for(int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.println(",");
            if (i < SIZE) System.out.println(firstElements.get(i));
            else System.out.println("...");
        }
        System.out.println();

        /*
         1.limit使用
           list长度过长，想截取前50个,使用limit例子
         public List<Long> getOverseasParentPharmacyByIdList(List<Long> pharmacyIdList) {
            if (CollectionUtils.isEmpty(pharmacyIdList)) {
                return new ArrayList<>();
            }
            return oPharmacyMapper.getOverseasParentPharmacyByIdList(pharmacyIdList.stream().limit(50).collect(Collectors.toList()));
        }*/
    }

    public static <T> void showMore(String title,Stream<T> stream) {
        List<T> firstElements = stream
                .collect(Collectors.toList());
        System.out.println(title + ":");
        for(int i = 0; i < firstElements.size(); i++) {
            System.out.println(firstElements.get(i));
        }
        System.out.println();
    }
    /**
     * 字数条件过滤
     * @throws IOException
     */
    @Test
    public void testCountWord() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split("\\PL+"));
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
    /**
     * 集合按条件过滤 排序
     */
    @Test
    public void testFilter1() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame",20));
        userList.add(new User(2L,"Jame",30));
        userList.add(new User(3L,"Jame",22));
        userList.add(new User(4L,"Jame",24));
        List<User> sortList = userList.stream().filter(user -> user.getAge() < 30 && user.getAge() > 20).sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        sortList.forEach(user->{
            System.out.println("id:"+user.getId()+" name:"+user.getName()+" "+user.getAge());
        });

        //Comparator
        List<User> userList1 = new ArrayList<User>();
        userList1.add(new User(1L,"Tom1",20));
        userList1.add(new User(2L,"Tom2",30));
        userList1.add(new User(3L,"Tom3",22));
        userList1.add(new User(4L,"Tom4",24));

        /*userList1.sort((h1,h2) -> h2.getAge() - h1.getAge());
        userList1.forEach(user -> System.out.println("id:"+user.getId()+" name:"+user.getName()+" "+user.getAge()));*/

        userList1.sort((h1,h2) -> {
            return h1.getAge() - h2.getAge();
        });
        userList1.forEach(user -> System.out.println("id:"+user.getId()+" name:"+user.getName()+" "+user.getAge()));


    }
    /**
     * List根据里边对象某个属性转为Map  toMap
     */
    @Test
    public void testMap1() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame1",20));
        userList.add(new User(2L,"Jame2",30));
        userList.add(new User(3L,"Jame3",22));
        userList.add(new User(4L,"Jame4",24));
        Map<Long, User> map = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        for(Long key : map.keySet()) {
            System.out.println("key:"+key+" value:"+map.get(key).getName());
        }
    }
    /**
     * List根据里边对象某个属性转为list  toLIst
     */
    @Test
    public void testList() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame1",20));
        userList.add(new User(2L,"Jame2",30));
        userList.add(new User(3L,"Jame3",22));
        userList.add(new User(4L,"Jame4",24));

        List<Long> userIdList = userList.stream().map(u->u.getId()).collect(Collectors.toList());
        for(Long uid : userIdList) {
            System.out.println("userId:"+uid);
        }
    }

    /**
     * List根据里边对象某个属性转为Map  toMap
     */
    @Test
    public void testMap2() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame1",20));
        userList.add(new User(2L,"Jame2",30));
        userList.add(new User(3L,"Jame3",22));
        userList.add(new User(4L,"Jame4",24));
        Map<Long, User> map = userList.stream().collect(Collectors.toMap(User::getId, user->user));
        for(Long key : map.keySet()) {
            System.out.println("key:"+key+" value:"+map.get(key).getName());
        }

        /*
           1. list转map
        List<MultipleSubmitCartItemVo> itemVoList = multipleOrderContext.getItemVoList();
        Map<Long, List<PresentParam>> presentParamMap = itemVoList.stream()
                .collect(Collectors.toMap(m -> m.getShopId(), m -> Optional.ofNullable(m.getPresentList()).orElse(new ArrayList<>())));*/
    }

    /**
     * List根据里边对象某个属性转为Map  toMap
     * filter 过滤
     */
    @Test
    public void testMap3() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame1",20));
        userList.add(new User(null,"Jame2",30));
        userList.add(new User(3L,"Jame3",22));
        userList.add(new User(4L,"Jame4",24));
        Map<Long, String> map = userList.stream().collect(Collectors.toMap(User::getId,User::getName));
        for(Long key : map.keySet()) {
            System.out.println("key:"+key+" value:"+map.get(key));
        }
        System.out.println("++++++++++++++++++++");
        Map<Long, String> map2 = userList.stream().filter(u->u.getId() != null).collect(Collectors.toMap(User::getId,User::getName));
        for(Long key : map2.keySet()) {
            System.out.println("key:"+key+" value:"+map.get(key));
        }


    }




    /**
     * 创建流
     * @throws IOException
     */
    @Test
    public void testStreamOf() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(content.split("\\PL+"));
        words.forEach(w->{
            System.out.println(w);
        });
        System.out.println("-------------------------");
        Stream<String> jim = Stream.of("Jim", "Tom", "Dome");
        jim.forEach(name->{
            System.out.println(name);
        });
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("alice.txt");
        String contents = new String(Files.readAllBytes(path),StandardCharsets.UTF_8);
        Stream<Object> silence = Stream.empty();
        show("silence",silence);

        Stream<String> echos = Stream.generate(() -> "ECHO");
        show("echos",echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integer",integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay",wordsAnotherWay);

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        show("lines",lines);
        //map
        List<String> words = Arrays.asList(new String[]{"aaaA", "bbb", "ccc"});
        Stream<String> lowerCaseWords = words.stream().map(String::toLowerCase);
        show("lowercasewords",lowerCaseWords);
        //map
        Stream<String> firstletters= words.stream().map(s -> s.substring(0, 1));
        show("firstletters",firstletters);
    }
    /**
     * 创建随机的流，元素数量限制100
     */
    @Test
    public void test2() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        showMore("randoms",randoms);
    }
    /**
     * 跳过元素 skip
     */
    public void test3() {

    }
    /**
     * 去重复
     */
    @Test
    public void test4() {
        Stream<String> distinct = Stream.of("a", "b", "c", "c", "d", "d").distinct();
        showMore("distinct",distinct);
    }
    /**
     * 排序
     */
    @Test
    public void test5() {
        Stream<String> words = Stream.of("a", "bb", "ccc", "cccc", "ddddd", "dddddd");
        Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
        showMore("longestFirst",longestFirst);
    }
    /**
     * 字符串处理成Long的集合
     */
    @Test
    public void test6() {
        String ids = "1,2,3,4,5,6";
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::valueOf).collect(Collectors.toList());
        idList.forEach(id->{
            System.out.println(id);
        });
    }

    /*private static User buildUserList() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"Tom",10));

        return list;
    }*/

    /**************************************功能示例********************************************/
    /**
     * 逗号分隔的字符串转为Long类型的List集合
     */
    @Test
    public void test7() {
        String pharmacyIds = "-1,100012,122204";
        List<Long> pharmacyIdList = Arrays.asList(pharmacyIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        pharmacyIdList.forEach(ph-> System.out.println(ph));
    }

    @Test
    public void testJoin() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame",20));
        userList.add(new User(2L,"Jame",30));
        userList.add(new User(3L,"Jame",22));
        userList.add(new User(4L,"Jame",24));
        String result = userList.stream().map(x -> x.getName()).collect(Collectors.joining(","));
        System.out.println(result);
    }
    @Test
    public void testNotNull() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L,"Jame",20));
        userList.add(new User(2L,"Jame",30));
        userList.add(new User(3L,"Jame",22));
        userList.add(new User(4L,"Jame",24));
        String result = userList.stream().map(x -> x.getName()).collect(Collectors.joining(","));
        System.out.println(result);
    }

    @Test
    public void test20(){

       /* JSONArray = new JSONArray();
        JSONObject j1 = new JSONObject();
        j1.put ("name", "kung");
        JSONObject j2 = new JSONObject();
        j2.put ("name", "soft");
        jsonArray.add(j1);
        jsonArray.add(j2);
        Stream<String> ss = jsonArray.stream().map (json->json.toString ());
        List<String> list = ss.collect (Collectors.toList ());
        System.out.println(list);*/
    }


    //List转 map
    @Test
    public void listToMap(){
        // 提交参数按店铺ID分组
        //Map<Long, MultipleSubmitCartItemVo> itemVoMap = itemVoList.stream().collect(Collectors.toMap(MultipleSubmitCartItemVo::getShopId, Function.identity(), (oldValue, newValue) -> newValue));
        //list to map
        //cartItemMap = shoppingCartService.queryByIds(cartList, userId).stream().collect(Collectors.toMap(ShoppingCartItem::getId, Function.identity()));
        List<User> list = new ArrayList<>();
        list.add(new User(1L,"张三",20));
        list.add(new User(1L,"李四",21));
        //Map<Long, User> collect = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (oldValue, newValue) -> newValue)); // 有合并函数指定合并规则，出现重复key后面覆盖前面的
        //Map<Long, DtDeliveryFeeRule> allFeeRuleMap = allFeeRuleList.stream().collect(Collectors.toMap(DtDeliveryFeeRule::getId, Function.identity(), (key1, key2) -> key2));
        Map<Long, User> collect = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (oldValue, newValue) -> oldValue)); // 有合并函数指定合并规则，出现重复key前面覆盖后面的
        //Map<Long, User> collect = list.stream().collect(Collectors.toMap(User::getId, Function.identity()));// 出现重复key会报错
        for (Long key : collect.keySet()) {
            User user = collect.get(key);
            System.out.println(user.getId()+" " + user.getName() +"  "+ user.getAge());
        }

        /*
        1. listToMap  lits转Map
        List<PharmacyTypesVo> pharmacyList = pharmacyService.queryPharmacyTypesList(pharmacyIdList, DowngradeSwitch.isDowngrade(DowngradeSwitch.Module.ORDER));
        Map<Long,Integer> pharmacyMap = pharmacyList.stream().collect(Collectors.toMap(PharmacyTypesVo::getId, PharmacyTypesVo::getPharmacyType));*/
    }


    @Test
    public void listToMap2(){
        //Map<Long, List<PresentParam>> presentParamMap = itemVoList.stream()
        //                .collect(Collectors.toMap(m -> m.getShopId(), m -> Optional.ofNullable(m.getPresentList()).orElse(new ArrayList<>())));
        /**
         * 1.list转map
         * 2.Optional orElse用法
         */
        //item.setQuantity(Optional.ofNullable(presentParam.getQuantity()).orElse(0L).longValue());

        /**
         * 1. stream 按条件过滤
         * 2.Optional orElse 用法
         */
        //List<MultipleSubmitCartItemVo> cartItemVos = itemVoList.stream().filter(i -> Optional.ofNullable(i.getVirtualGoods()).orElse(0) == 1).collect(Collectors.toList());

        List<String> nameList = Lists.newArrayList("bowen","Tom","hanmeimei");
        List<String> resultList = nameList.stream().filter(name -> name.equals("Tom1")).collect(Collectors.toList());
        System.out.println("size:"+resultList.size());
        for (String s : resultList) {
            System.out.println(s);
        }

        ArrayList<String> cards = Lists.newArrayList("ofenbuy", "hotrank");
        List<String> collect = cards.stream().filter(name -> !"ofenbuy".equals(name)).collect(Collectors.toList());
        collect.forEach(item ->System.out.println(item));


        //1.Entity List 按某个属性条件过滤后得到新List
        /*List<PharmacyInfoSelfVo> pharmacyInfoList = productRelatedBaseService.getPharmacyInfoListCache(pharmacyIds, 1);
        List<PharmacyInfoSelfVo> haiwaiPharmacy = pharmacyInfoList.stream().filter(pharmacy -> ShopCooperatorType.HAIWAI.getCode() == pharmacy.getCooperateType()).collect(Collectors.toList());*/

        /*
        filter里边添加return
        List<MedicineTaker> ninetyDaysMedicineTakers = medicineTakers.stream().filter(m -> {
            int days = 0;
            try {
                days = DateUtil.daysBetween(m.getCreatedAt(), new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return days <= 90;
        }).collect(Collectors.toList());*/

        /*List<PatientVo> patients = familyMemberApi.queryPatientList(userId);
        logger.info("用药人列表：{}",JsonUtils.object2Json(patients));
        List<PatientMember> patientMembers = patients.stream()
                .map(p -> {
                    PatientMember patientMember = new PatientMember(p.getId(), p.getName(), p.getGender(), p.getAge(), p.getIdCard());
                    patientMember.setIdenauthen(p.getIdenauthen());
                    return patientMember;
                })
                .collect(Collectors.toList());*/


        /**
         * 1.数据库查询出来list直接转Map,此处是否需要判断空指针？
         * 不用判断空指针，因为mybatis当返回集合类型时，即使查询不到数据也会返回空集合，不会返回null
         *                 mybatis查询单个对象时，如果查询不到会返回null
         */
        /*if (CollectionUtils.isNotEmpty(cartList)) {
            cartItemMap = shoppingCartService.queryByIds(cartList, userId).stream().collect(Collectors.toMap(ShoppingCartItem::getId, Function.identity()));
        }*/
        //List<PharmacyInfoSelfVo> pharmacyInfoList = productRelatedBaseService.getPharmacyInfoListCache(pharmacyIds, 1);
        //Map<Long, PharmacyInfoSelfVo> pharmacyInfoMap = pharmacyInfoList.stream().collect(Collectors.toMap(PharmacyInfoSelfVo::getId, Function.identity()));
        //List<PharmacyInfoSelfVo> pharmacyInfoList = productRelatedBaseService.getPharmacyInfoListCache(pharmacyIds, 1);
        //Map<Long, PharmacyInfoSelfVo> pharmacyInfoMap = pharmacyInfoList.stream().collect(Collectors.toMap(PharmacyInfoSelfVo::getId, Function.identity()))



        /**
         * 1.从list对象中取shopId,并去重，重新组成一个list
         */
        //List<Long> pharmacyIds = itemVoList.stream().map(MultipleSubmitCartItemVo::getShopId).distinct().collect(Collectors.toList());
        //List<Long> pharmacyIds = multipleOrderContext.getPharmacyIds().stream().distinct().collect(Collectors.toList());
    }


    @Test
    public void testListForEach(){
        /**
         * 1.list forEach循环
         * 2.list addAll
         * 3.if 省略写法
         */
        /*if (CollectionUtils.isNotEmpty(itemVoList)) {
            itemVoList.forEach(itemVo -> {
                if (itemVo.getCartItems() != null) cartList.addAll(itemVo.getCartItems());
                if (itemVo.diseaseIdList != null) diseaseIdList.addAll(itemVo.diseaseIdList);
            });
        }*/

        /*
         2.foreach方法内部调用外部方法setShippingMethods
        orderVo.getOrderShops().forEach(shopOrder -> setShippingMethods(multipleOrderContext, shopOrder));

        private void setShippingMethods(MultipleOrderContext mContext, MultipleShopOrderPrepareVo shopOrderVo) {
        Map<Long, Map<String, List<PharmacyDeliverFee>>> deliverFeeMap = mContext.getDeliverFeeMap();
        Map<Long, List<Long>> selfTakeSkuMap = mContext.getSelfTakeSkuMap();
        String appVersion = mContext.getAppVersion();
        Map<Long, PharmacyInfoSelfVo> pharmacyInfoMap = mContext.getPharmacyInfoMap();
        Calendar newDay = Calendar.getInstance();
        String startDate = DateTimeUtils.formatToDay(newDay.getTime());
        Long pharmacyId = shopOrderVo.getShopId();
        PharmacyInfoSelfVo pharmacyInfo = pharmacyInfoMap.get(pharmacyId);
        List<ShippingMethod> shippingMethods = Lists.newArrayList();

        Map<String, List<PharmacyDeliverFee>> subDeliverFeeMap = deliverFeeMap.get(pharmacyId);
        List<PharmacyDeliverFee> deliverFees = subDeliverFeeMap == null ? Lists.newArrayList() : subDeliverFeeMap.get(startDate);
        if (!ShopCooperatorType.getType(pharmacyInfo.getCooperateType()).isB2cShop() ) {
            shippingMethods = AppointmentUtils.getShippingMethods(deliverFees,
                    pharmacyInfo, appVersion, shopOrderVo.getAvailableTimeslots(), CollectionUtils.isNotEmpty(selfTakeSkuMap.get(pharmacyId)));
        }

        // 拼团只有预约配送
        if (mContext.isGroupBuying()) {
            shippingMethods.removeIf(shippingMethod -> shippingMethod.getCode() != ShippingMethodEnum.APPOINTMENT_SHIPPING.getCode());
        }
        shopOrderVo.setShippingMethods(shippingMethods);
    }
        */
    }



    @Test
    public void testMapForEach(){
        /*
        1. Map 遍历
        Map<Long, List<ShoppingCartItem>>
        multipleOrderContext.getCartItemShopMap().forEach((pharmacyId, cartItemLIst)->{
            Map<Long, Long> normalSku = Maps.newHashMap();
            cartItemLIst.forEach(cartItem->{
                if (cartItem.getAddOnItem() <= 0 && cartItem.getZeroBuyItem() <= 0 && cartItem.getPromotionType() <=0) {
                    normalSku.put(cartItem.getSkuId(), cartItem.getQuantity());
                }
            });
            multipleOrderContext.getNormalSkuMap().put(pharmacyId, normalSku);
        });*/

        /*@Override
        public Map<Long, List<PharmacyGoodsDetailSelfVo>> getValidGoodsDetailBatch(List<PharmacyGoodsQueryParam> paramList, boolean setDynamicPro, String cityName) {
            Map<Long, List<PharmacyGoodsDetailSelfVo>> detailSelfVos = Maps.newHashMap();
            if (paramList.isEmpty()) {
                return detailSelfVos;
            }
            Map<Long, List<PharmacyGoodsDetail>> queryMap = oProductService.getValidGoodsDetailBatch(paramList, setDynamicPro, cityName);
            queryMap.forEach((pharmacyId, detailList)->{
                List<PharmacyGoodsDetailSelfVo> result = new ArrayList<>();
                if(null != detailList && detailList.size() >0 ){
                    result = BeanMapper.mapList(detailList,PharmacyGoodsDetailSelfVo.class);
                }
                detailSelfVos.put(pharmacyId, result);
            });
            return detailSelfVos;
        }*/
    }

    @Test
    public void mapToList(){
        /**
         *  1. map 转list
         * Map<Long, PharmacyInfoSelfVo> pharmacyInfoMap = multipleOrderContext.getPharmacyInfoMap();
         * Map<Long, Long> refreshPharmacyPair = multipleOrderContext.getRefreshPharmacyPair();
         * List<PharmacyInfoSelfVo> pharmacyInfoList = Lists.newArrayList(pharmacyInfoMap.values());
         */

        /*
        1.Map forEach方法 oldId 是map的key  newId是map的value
        2. new ArrayList()可以直接赋值，不用一个个add

        Map<Long, Long> refreshPharmacyPair = multipleOrderContext.getRefreshPharmacyPair();
        if (!refreshPharmacyPair.isEmpty()) {
            Map<Long, PharmacyInfoSelfVo> newPharmacyMap = productRelatedBaseService.getPharmacyInfoListCache(new ArrayList<>(refreshPharmacyPair.values()), 1).stream().collect(Collectors.toMap(PharmacyInfoSelfVo::getId, Function.identity()));
            refreshPharmacyPair.forEach((oldId, newId) -> {
                pharmacyInfoMap.remove(oldId);
                pharmacyInfoMap.put(newId, newPharmacyMap.get(newId));
            });
        }*/
    }

    @Test
    public void testGroupingBy(){
        /*
        1.java8 groupingby 类数据库groupby分组
          此代码作用：店铺相同的分成一组
          Map<Long,DtDeliveryFeeRuleShop>
          100012: DtDeliveryFeeRuleShop1 DtDeliveryFeeRuleShop2
          100013: DtDeliveryFeeRuleShop3 DtDeliveryFeeRuleShop4
        取全部店铺关联*//*
        List<DtDeliveryFeeRuleShop> allShopRelateDtRuleList = this.queryDtFeeRuleShopList(shopIds, 0);
        *//*<店铺ID, 店铺-规则(1-n)>*//*
        Map<Long, List<DtDeliveryFeeRuleShop>> allShopRelateRuleMap = allShopRelateDtRuleList.stream().collect(Collectors.groupingBy(DtDeliveryFeeRuleShop::getShopId));*/
    }

    @Test
    public void testCopy(){

    }

    /*
    1.拷贝
    private List<FeeRuleCalVo> convert2CalVos(List<?> ruleList) {
        if(CollectionUtils.isEmpty(ruleList)) {
            return Collections.emptyList();
        }
        List<FeeRuleCalVo> l = ruleList.stream().map(r -> copy(r, FeeRuleCalVo.class)).collect(Collectors.toList());
        return l;
    }
    private static <T> T copy(Object source, Class<T> targetClass){
        if(source == null) {
            return null;
        }
        Assert.notNull(targetClass, "targetClass不能为空");
        T target = org.springframework.beans.BeanUtils.instantiate(targetClass);
        try {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new FatalBeanException("复制新对象失败."+e.getMessage(), e);
        }
    }*/

    /*
      1. List stream 经自定义方法转 dto 再组成List
    List<PharmacyDeliverFee> convertDtAndDgRuleHour2Fee(Long shopId, List<?> ruleHourList){
        List<FeeRuleHourCalVo> hourCalVos = this.convert2HourCalVos(ruleHourList);
        *//*时间规则转换为运费对象, 方便统一返回PharmacyDeliverFee *//*
        if(CollectionUtils.isEmpty(hourCalVos)) {
            return Collections.emptyList();
        }
        List<PharmacyDeliverFee> feeList = hourCalVos.stream().map(
                h -> this.buildPharmacyDeliverFee(false, shopId, h.getFreeDelivery(), h.getDeliveryFee(), "", h.getHourStart(), h.getHourEnd())
        ).collect(Collectors.toList());
        return feeList;
    }
    private PharmacyDeliverFee buildPharmacyDeliverFee(
            boolean freeShipping,
            Long shopId, Long freeFee,
            Long deliverFee, String timeLimit,
            String hourStart, String hourEnd
    ) {
        PharmacyDeliverFee fee = new PharmacyDeliverFee();
        fee.setFreeShipping(freeShipping);
        fee.setPharmacyId(shopId);
        fee.setFreeDeliverFee(freeFee == null ? 0 : freeFee);
        fee.setDeliverFee(deliverFee == null ? 0 : deliverFee);
        fee.setTimeLimit(!StringUtils.isBlank(timeLimit) ? timeLimit : "");
        fee.setFromDeliverFeeHours(hourStart);
        fee.setToDeliverFeeHours(hourEnd);
        return fee;
    }*/

    @Test
    public void testFindAny(){
        /*
        1.stream findAny 查询第一个

        LocationInfo normalO2o = locationShops.stream().filter(locationInfo -> locationInfo.getType() == PharmacyTypeUtils.SUITE_NORMAL_O2O).findAny().orElse(null);*/
        List<Integer> list = Arrays.asList(1, 3, 5, 7);
        Integer integer = list.stream().findAny().orElse(null);
        System.out.println("result:"+integer);
    }

    @Test
    public void testForeach(){

        /*
        1.map转list
        private List<PharmacyGoodsQueryParam> initParamList(Map<Long, List<Long>> skuIdListMap, Map<Long, PharmacyInfoSelfVo> pharmacyInfoMap) {
            List<PharmacyGoodsQueryParam> paramList = Lists.newArrayList();
            skuIdListMap.forEach((pharmacyId, skuList)->{
                PharmacyInfoSelfVo pharmacyInfoSelfVo = pharmacyInfoMap.get(pharmacyId);
                PharmacyGoodsQueryParam param = new PharmacyGoodsQueryParam();
                param.setPharmacyId(pharmacyId);
                param.setCompanyId(pharmacyInfoSelfVo.getCompanyId());
                param.setSkuIdList(skuList);
                paramList.add(param);
            });
            return paramList;
        }*/
    }

    @Test
    public void removeIf(){
        /*
           1. list.removeIf()
        private void clearPayForGroupBuying(List<PaymentTypeVo> paymentList, Long sourceType) {
            // 拼团只支持在线支付
            if (sourceType != null && sourceType == OrderSource.GROUP_BUYING.getCode()) {
                paymentList.removeIf(payment->payment.getPaymentId() != PaymentType.onlinePayment.getCode());
            }
        }*/
    }











}
