package com.example.classes.web;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

//        return restTemplate.getForObject(
//                "http://teachers/teachers/teacherName/{teacherId}", String.class, teacherId
//        );
        return restTemplate.exchange("http://teachers/teachers/teacherName/" + teacherId,
                HttpMethod.GET, entity, String.class).getBody();
    }

    public String  getTeacherNameByIdFallback(long teacherId) {
        return "not available";
    }


}
