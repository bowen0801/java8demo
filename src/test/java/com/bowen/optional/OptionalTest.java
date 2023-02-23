package com.bowen.optional;

import com.bowen.User;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 处理空指针导致的各种冗余的判断
 * @author DELL
 *
 */
public class OptionalTest {
	@Test
	public void test1() {
		Optional<User> emptyOpt = Optional.empty();
	    emptyOpt.get();
	}
	
	@Test
	public void test2() {
		User user = null;
		Optional<User> emptyOpt = Optional.of(user);
	    emptyOpt.get();
	}
	
	@Test
	public void test3() {
		User user = null;
		Optional<User> emptyOpt = Optional.ofNullable(user);
		User user1 = emptyOpt.get();
	}

	//optional的空判断
	//Optional.ofNullable(i).ifPresent(x -> doBlah(x));
	// 替代
	//if (i != null) {
	//  doBlah(i);
	//}
	@Test
	public void test4(){
		//optional 遍历List集合 省去了if对集合的空判断2
		//List<String> citys = Lists.newArrayList("北京市","上海市","广州市");
		//List<String> citys = null;
		List<String> citys = Lists.newArrayList();
		Optional.ofNullable(citys).ifPresent(todoTasks ->{
			todoTasks.forEach(task ->{
				System.out.println("城市名称："+task);
			});
		});
	}

	@Test
	public void testNull(){
		/*
		1. 使用Object.isNull 替代 null != ex.getShopId()
		if(Objects.isNull(ex.getShopId())){
			continue;
		}
		Pharmacy shop = oPharmacyService.getPharmacyById(ex.getShopId());
		if(Objects.isNull(shop)) {
			logger.info("POP O2O店铺[{}]无效", ex.getShopId());
			continue;
		}*/
	}




	

}
