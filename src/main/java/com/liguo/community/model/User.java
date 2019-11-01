package com.liguo.community.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by dogbro on 2019-10-31 14:31
 */
@Data
@ToString
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String avatarUrl;
    private Long gmtCreateTime;
    private Long gmtUpdateTime;

    public User(String name, String accountId, String token, String avatarUrl, Long gmtCreateTime, Long gmtUpdateTime) {
        this.name = name;
        this.accountId = accountId;
        this.token = token;
        this.avatarUrl = avatarUrl;
        this.gmtCreateTime = gmtCreateTime;
        this.gmtUpdateTime = gmtUpdateTime;
    }
}
