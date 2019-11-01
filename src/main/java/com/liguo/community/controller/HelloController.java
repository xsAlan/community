package com.liguo.community.controller;

import com.liguo.community.mapper.UserMapper;
import com.liguo.community.model.User;
import com.liguo.community.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dogbro on 2019-10-24 10:42
 */
@Controller
@Slf4j
@Api("首页")
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    @ApiOperation(value = "首页")
    public String index(@RequestParam(name = "name", required = false) String name,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        model.addAttribute("name", name);
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                //github登录鉴权成功
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.getByToken(token);
                    if(user == null){
                        return "index";
                    }
                    log.info("token:{}, user：{}", token, user.toString() );
                    request.getSession().setAttribute("user", user );
                    return "index";
                }
            }
        }
        return "index";
    }


}
