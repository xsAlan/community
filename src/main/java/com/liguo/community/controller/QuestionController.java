package com.liguo.community.controller;

import com.liguo.community.model.Question;
import com.liguo.community.model.User;
import com.liguo.community.service.QuestionService;
import com.liguo.community.service.UserService;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/publish")
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String publish(){
        return "publish";
    }

    /**
     * 发布问题
     * @param title
     * @param description
     * @param tag
     * @return
     */
    @PostMapping("")
    public String add(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String tag,
            HttpServletRequest request,
            Model model){

        //设置回显
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        //获取当前用户
        int creator = 0;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    User currentUser = userService.getByToken(cookie.getValue());
                    creator = currentUser.getId();
                    break;
                }
            }
        }
        if(creator == 0){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        if(StringHelper.isNullOrEmptyString(title)){
            model.addAttribute("error","标题不能未空");
            return "publish";
        }
        if(StringHelper.isNullOrEmptyString(description)){
            model.addAttribute("error","描述能未空");
            return "publish";
        }
        questionService.add(new Question(title, description, tag, creator));
        return "redirect:/";
    }
}
