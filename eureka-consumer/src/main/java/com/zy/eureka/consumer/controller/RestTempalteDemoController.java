package com.zy.eureka.consumer.controller;

import com.zy.eureka.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR zhangy
 * 2019-09-28  17:04
 */
@RestController
public class RestTempalteDemoController {


    private final RestTemplateBuilder builder;



    public RestTempalteDemoController(RestTemplateBuilder builder) {
        this.builder = builder;
    }


    @GetMapping("/api/test-resttemplate")
    public String testRestTemplate() {

        RestTemplate template = builder.build();

        ResponseEntity<String> responseEntity = template.getForEntity("http://localhost:8099/api/{name}", String.class, new Object[]{"dfdfdf"});

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }


    @GetMapping("/api/user/post")
    public ResponseEntity<User> postUser() {

        RestTemplate template = builder.build();

        User user = new User("zhangsan", "湖北巴东", "23423424", 26, "dfdfdf");

        ResponseEntity<User> responseEntity = template.postForEntity("http://localhost:8099/api/user/post", user, User.class);

        System.out.println(responseEntity.getBody());
        return responseEntity;
    }

    @GetMapping("/api/user/put")
    public ResponseEntity<User> puttUser() {

        RestTemplate template = builder.build();

        User user = new User("zhangsan", "湖北巴东", "23423424", 26, "dfdfdf");

        template.put("http://localhost:8099/api/user/put", user, User.class);

        return null;
    }
}
