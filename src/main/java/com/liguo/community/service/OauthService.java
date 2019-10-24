package com.liguo.community.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liguo.community.bean.GithubToken;
import com.liguo.community.bean.GithubUser;
import com.liguo.community.utils.HttpClientUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

/**
 * Created by dogbro on 2019-10-24 17:51
 */
@Service
public class OauthService {



    public GithubUser loginAndRetrieveUserInfo(String clientId, String clientSecret, String code){
        HttpGet userGet = null;
        GithubUser githubUser = null;
        String tokenUrl = "https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code;
        HttpPost post = new HttpPost(tokenUrl);
        post.addHeader("accept", "application/json");
        try {
            HttpResponse response = HttpClientUtils.getClient().execute(post);
            if(response != null
                    && response.getEntity() != null
                    && response.getStatusLine().getStatusCode() == 200){
               HttpEntity httpEntity =  response.getEntity();
                GithubToken token = JSON.parseObject(EntityUtils.toString(httpEntity, "UTF-8"), GithubToken.class);
               if(token != null &&  !StringUtils.isEmptyOrWhitespace(token.getAccess_token())){
                   String userUrl = "https://api.github.com/user";
                   userGet = new HttpGet(userUrl);
                   userGet.addHeader("accept", "application/json");
                   userGet.addHeader("Authorization", "token " + token.getAccess_token());
                   HttpResponse useGetRes = HttpClientUtils.getClient().execute(userGet);
                   if(useGetRes != null
                           && useGetRes.getEntity() != null
                           && useGetRes.getStatusLine().getStatusCode() == 200){
                       githubUser =  JSON.parseObject(EntityUtils.toString(useGetRes.getEntity(), "UTF-8"), GithubUser.class);
                       System.out.println(githubUser.toString());
                   }
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(post != null){
                post.releaseConnection();
            }
            if(userGet != null){
                userGet.releaseConnection();
            }
        }
        return  githubUser;
    }
}
