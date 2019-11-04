package com.liguo.community.model;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {

    public Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Long gmtCreateTime;
    private Long gmtUpdateTime;

    public Question(String title, String description, String tag, Integer creator) {
        this.title = title;
        this.description = description;
        this.tag = tag;
//        this.creator = creator;
    }
}
