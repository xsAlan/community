package com.liguo.community.controller;

import com.liguo.community.bean.AccessTokenDto;
import com.liguo.community.bean.GithubUser;
import com.liguo.community.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OauthController {

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;

    @Autowired
    private OauthService oauthService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request){
       String accessToken =  oauthService.getAccessToken(new AccessTokenDto(clientId, clientSecret, code));
       GithubUser githubUser = oauthService.getGihubUser(accessToken);
       if(githubUser != null){
           request.getSession().setAttribute("user", githubUser);
       }
        System.out.println(githubUser.toString());
        return "redirect:/";

    }
}
