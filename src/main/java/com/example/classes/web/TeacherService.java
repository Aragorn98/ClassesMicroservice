package com.example.classes.web;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeacherService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getTeacherNameByIdFallback",
            threadPoolKey = "getTeacherNameById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            }
    )


    public String  getTeacherNameById(long teacherId) {
        return restTemplate.getForObject(
                "http://teachers/teachers/teacherName/{teacherId}", String.class, teacherId
        );
    }

    public String  getTeacherNameByIdFallback(long teacherId) {
        return "not available";
    }


}
