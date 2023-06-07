package com.core.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.core.domain.services.entidies.TokenObject;
import com.core.domain.services.entidies.UserAuthentication;

@Service
public class PostApiSuap {

    @Value("${url.suap}")
    private static String url;
    
    public TokenObject requestPost (String urlBody, UserAuthentication request){
        RestTemplate requestPost = new RestTemplate();
        TokenObject userToken = requestPost.postForObject( url+urlBody, request, TokenObject.class);

        return userToken;
    }
}
