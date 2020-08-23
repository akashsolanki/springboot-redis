
package com.springboot.redisintegration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.redisintegration.service.RedisService;

@RestController("redis")
public class RedisController {

	private final String KEY = "GROCERIES";

	@Lazy

	@Autowired
	private RedisService<String> redisService;

	@GetMapping("hello")
	public String hello() {
		return "Hello Akash";
	}

	@GetMapping
	public Map<Object, String> getMap() {
		return redisService.getMapAsAll(KEY);
	}

	@PostMapping
	public void postMap(@RequestBody Map<String, String> body) {
		redisService.putAll(KEY, body);
	}
}
