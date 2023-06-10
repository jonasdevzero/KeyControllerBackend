package com.core.services.api;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.core.services.dto.TokenObject;
import com.core.services.dto.UserAuthentication;
import com.core.services.dto.userData.UserData;

@Service
public class SuapAPI {

    @Value("${url.suap}")
    private String url;


    public TokenObject authentication (UserAuthentication request){
        RestTemplate requestPost= new RestTemplate();
        TokenObject userToken = requestPost.postForObject( url+"/api/v2/autenticacao/token/", request, TokenObject.class);
        return userToken;
    }
    
    public UserData returnData (String request) throws RestClientException, URISyntaxException{
        RestTemplate requestPost= new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(request);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserData> user = requestPost.exchange( url+"/api/v2/minhas-informacoes/meus-dados/", HttpMethod.GET, requestEntity, UserData.class);

        return user.getBody();
    }
}