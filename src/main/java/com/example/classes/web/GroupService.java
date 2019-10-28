package com.example.classes.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GroupService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getGroupNameByIdFallback",
            threadPoolKey = "getGroupNameById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            }
    )


    public String  getGroupNameById(long groupId) {
        return restTemplate.getForObject(
                "http://groups/group/groupName/{groupId}", String.class, groupId
        );
    }

    public String  getGroupNameByIdFallback(long groupId) {
        return "not available";
    }
}
