package com.liguo.community.controller;

import com.liguo.community.dto.AccessTokenDto;
import com.liguo.community.dto.GithubUser;
import com.liguo.community.mapper.UserMapper;
import com.liguo.community.model.User;
import com.liguo.community.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class OauthController {

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 1> github登录获取用户信息成功之后，保存用户信息到数据库，并且手动创建并返回token(UUID)，
     * 2> 重定向到主页 "/"
     * 3> 主页 index方法中根据request里面的token查询数据库校验用户合法性，并且写回session到客户端
     * @param code
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/callback")
    @ApiOperation("Github鉴权回调")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
       String accessToken =  oauthService.getAccessToken(new AccessTokenDto(clientId, clientSecret, code));
       GithubUser githubUser = oauthService.getGithubUser(accessToken);
       if(githubUser != null){
           User user = githubUser.toUser();
           userMapper.addUser(user);
//           request.getSession().setAttribute("user", user);
           //github登录成功之后返回用户token，重定向到/ （HelloController）
           response.addCookie(new Cookie("token", user.getToken()));
       }
        return "redirect:/";

    }
}
