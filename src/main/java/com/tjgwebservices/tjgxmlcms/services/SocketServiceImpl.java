package com.tjgwebservices.tjgxmlcms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SocketServiceImpl  {
    
    RestTemplate restTemplate;
    
    @Autowired
    public SocketServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    
    public String testSocketService(){
        final String uri = "/sockets";
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
            
}
