package com.flycorp.lib.fly;

import org.springframework.web.client.RestTemplate;

public class FlyClient {

    public static ResponseGetFlies getFlies(RestTemplate restTemplate) {
        return restTemplate.getForObject(
                "http://FLY/api/v1/fly",
                ResponseGetFlies.class
        );
    }

}
