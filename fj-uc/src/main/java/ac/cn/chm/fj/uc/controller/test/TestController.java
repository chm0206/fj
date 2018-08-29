package com.chm.fj.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chm.fj.Result.ResultInfo;
import com.chm.fj.controller.base.BaseController;

//@Controller
@RestController//rest接口必备
@RequestMapping(value="test")
public class TestController extends BaseController {
	
	@RequestMapping(value="redis")
	public ResultInfo test() throws Exception{
		ResultInfo result = new ResultInfo();
		/*ShardedJedis shardJedis = shardedJedisPool.getResource();
		// 存入键值对
		shardJedis.set("key1", "hello jedis");
		// 回收ShardedJedis实例
		shardJedis.close();*/
		return result;
	}

}
