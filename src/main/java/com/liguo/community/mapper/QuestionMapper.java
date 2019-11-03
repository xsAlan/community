package com.liguo.community.mapper;

import com.liguo.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, tag, creator, gmt_create_time, gmt_update_time) " +
            "values(#{title}, #{description}, #{tag}, #{creator}, #{gmtCreateTime}, #{gmtUpdateTime})")
    public void add(Question question);

}
