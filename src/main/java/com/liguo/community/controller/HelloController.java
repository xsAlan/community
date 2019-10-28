package com.liguo.community.controller;

import com.liguo.community.bean.GithubUser;
import com.liguo.community.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dogbro on 2019-10-24 10:42
 */
@Controller
public class HelloController {

    @Autowired
    private OauthService oauthService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }

    @Deprecated
    @GetMapping("/oauth/redirect")
    public ModelAndView oauth(String code, Model model){
        String clientId = "36cb4ca807a322ad365b";
        String clientSecret = "7a55d04abd9a4fb6242e68fb0f054cfd68535f31";
        GithubUser githubUser = oauthService.loginAndRetrieveUserInfo(clientId, clientSecret, code);
        if(githubUser  != null){
            model.addAttribute("name", githubUser.getLogin());
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", githubUser.getLogin());
        mv.setViewName("index");
        return mv;
    }
}
