package com.example.myeurekaclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eureka-client-service-provider")
public interface FeignClientTest {
	@RequestMapping("/test")
	public String test();
}
