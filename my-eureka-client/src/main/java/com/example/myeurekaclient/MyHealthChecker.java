package com.example.myeurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义Health Checker状态变化
 */
@Component
public class MyHealthChecker implements HealthIndicator {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FeignClientTest feignClient;

    private volatile boolean up = true;

    @Override
    public Health health() {
        if (up) {
            return new Health.Builder().withDetail("aaa_cnt", 10) //自定义监控内容
                    .withDetail("bbb_status", "up").up().build();
        } else {
            return new Health.Builder().withDetail("error", "client is down").down().build();
        }

    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
        
        //测试自己调用自己
        String s = restTemplate.getForObject("http://eureka-client-service-provider/test", String.class);
        
        System.out.println(s);
        
        System.out.println(feignClient.test());
    }
}

