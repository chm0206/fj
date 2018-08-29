package ac.cn.chm.fj.uc.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ac.cn.chm.fj.consts.ParamConst;
import ac.cn.chm.fj.result.ResultInfo;
import ac.cn.chm.fj.uc.controller.base.BaseController;
import ac.cn.chm.fj.uc.redis.JedisCacheClient;

//@Controller
@RestController//rest接口必备
@RequestMapping(value="redis")
public class RedisController extends BaseController {
	
	/*@Autowired
    private JedisPool jedisPool;*/
	
	@Autowired
    private JedisCacheClient jedis;
	
	@RequestMapping(value="toredisList")
	public ModelAndView toRedisList() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redis/redisList");
		return mv;
	}
	@RequestMapping(value="redis")
	public ResultInfo test() throws Exception{
		ResultInfo result = new ResultInfo();
		jedis.setVExpire("chm", "chmVlaue", ParamConst.EXPIRE_30_MINUTE, 0);
		String v = jedis.getV("chm", 0);
		System.out.println(v);
		//获取ShardedJedis对象
//		// 存入键值对
//		shardJedis.set("key1", "hello jedis");
//		// 回收ShardedJedis实例
//		shardJedis.close();
		return result;
	}

}
