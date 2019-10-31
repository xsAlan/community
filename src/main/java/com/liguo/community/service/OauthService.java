package com.liguo.community.service;

import com.alibaba.fastjson.JSON;
import com.liguo.community.dto.AccessTokenDto;
import com.liguo.community.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by dogbro on 2019-10-24 17:51
 */
@Service
@Slf4j
public class OauthService {

    private String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    /**
     * Get GitHub access token
     * @param accessTokenDto
     * @return
     */
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url(GITHUB_ACCESS_TOKEN_URL)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
            String repsonseStr = response.body().string();
            String token = repsonseStr.substring(repsonseStr.indexOf("=") + 1, repsonseStr.indexOf("&"));
            log.info("token: {}", token);
            return token;
        } catch (IOException e) {
        }
        return "";
    }


    /**
     * Get gitub user details via token
     * @param token
     * @return
     */
    public GithubUser getGithubUser(String token){
        if(token == null || token.length() == 0){
            return  null;
        }
        String url = "https://api.github.com/user?access_token=" + token;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);
            log.info("avatarUrl length : {}", githubUser.getAvatar_url().length());
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * old version
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
//    @Deprecated
//    public GithubUser loginAndRetrieveUserInfo(String clientId, String clientSecret, String code){
//        HttpGet userGet = null;
//        GithubUser githubUser = null;
//        String tokenUrl = "https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code;
//        HttpPost post = new HttpPost(tokenUrl);
//        post.addHeader("accept", "application/json");
//        try {
//            HttpResponse response = HttpClientUtils.getClient().execute(post);
//            if(response != null
//                    && response.getEntity() != null
//                    && response.getStatusLine().getStatusCode() == 200){
//               HttpEntity httpEntity =  response.getEntity();
//               GithubToken token = JSON.parseObject(EntityUtils.toString(httpEntity, "UTF-8"), GithubToken.class);
//               if(token != null &&  !StringUtils.isEmptyOrWhitespace(token.getAccess_token())){
//                   String userUrl = "https://api.github.com/user";
//                   userGet = new HttpGet(userUrl);
//                   userGet.addHeader("accept", "application/json");
//                   userGet.addHeader("Authorization", "token " + token.getAccess_token());
//                   HttpResponse useGetRes = HttpClientUtils.getClient().execute(userGet);
//                   if(useGetRes != null
//                           && useGetRes.getEntity() != null
//                           && useGetRes.getStatusLine().getStatusCode() == 200){
//                       githubUser =  JSON.parseObject(EntityUtils.toString(useGetRes.getEntity(), "UTF-8"), GithubUser.class);
//                       System.out.println(githubUser.toString());
//                   }
//               }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(post != null){
//                post.releaseConnection();
//            }
//            if(userGet != null){
//                userGet.releaseConnection();
//            }
//        }
//        return  githubUser;
//    }
}
