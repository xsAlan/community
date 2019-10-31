package com.liguo.community.dto;

import lombok.Data;
import lombok.Getter;

/**
 * Created by dogbro on 2019-10-24 19:30
 * @Deprecated
 */
@Data
public class GithubToken {

    private String access_token;
    private String token_type;
    private String scope;
}
