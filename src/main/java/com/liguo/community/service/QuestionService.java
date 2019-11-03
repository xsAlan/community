package com.liguo.community.service;

import com.liguo.community.mapper.QuestionMapper;
import com.liguo.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.Validate;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public void add(Question question){
//        Validate.notEmpty(question.getTitle(), "标题不能为空");
//        Validate.notEmpty(question.getDescription(), "描述不能为空");
        question.setGmtCreateTime(System.currentTimeMillis());
        question.setGmtUpdateTime(System.currentTimeMillis());
        questionMapper.add(question);
//        return question.getId();
    }
}
