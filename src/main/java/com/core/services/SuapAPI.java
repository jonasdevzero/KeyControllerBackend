package com.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.core.services.entidies.TokenObject;
import com.core.services.entidies.UserAuthentication;

@Service
public class SuapAPI {

    @Value("${url.suap}")
    private String url;
    
    public TokenObject authentication (UserAuthentication request){
        RestTemplate requestPost = new RestTemplate();
        TokenObject userToken = requestPost.postForObject( url+"/api/v2/autenticacao/token/", request, TokenObject.class);
        
        return userToken;
    }
}
