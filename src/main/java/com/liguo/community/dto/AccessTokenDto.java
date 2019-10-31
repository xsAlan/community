package com.liguo.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * github 获取token DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;
}
