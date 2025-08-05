package com.example.demo.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/redis")
@Slf4j
@Tags(value = {
@Tag(name = "RedisController", description = "Redis接口")
})
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/set", consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String set() {
//        redisUtils.setString("name", "zhangsan");
        System.out.println(stringRedisTemplate.getConnectionFactory().getConnection().ping());
        System.out.println(stringRedisTemplate.opsForValue().get("test"));
        stringRedisTemplate.opsForValue().set("name", "lisi");
        return "success";
    }

    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String, Object> testPipeline() {
        Long start = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    operations.opsForValue().set((K) ("pipeline_" + i), (V) ("value_" + i));
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 10000) {
                        System.out.println("Command is on queue, so all values are empty. " + value);
                    }
                }
                return null;
            }
        });
        System.out.println("Pipeline time cost: " + (System.currentTimeMillis() - start) + " ms");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua")
    @ResponseBody
    public Map<String, Object> testLua() {
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        // 设置Lua脚本
//        defaultRedisScript.setScriptSource(new org.springframework.core.io.ClassPathResource("test.lua").getResource());
        defaultRedisScript.setScriptText("return 'Hello World'");
        // 定义返回类型，这个是必须的，否则Spring不会返回结果
        defaultRedisScript.setResultType(String.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(defaultRedisScript, redisSerializer, redisSerializer, null);
        Map<String, Object> map = new HashMap<>();
        map.put("str", str);
        return map;
    }
}
