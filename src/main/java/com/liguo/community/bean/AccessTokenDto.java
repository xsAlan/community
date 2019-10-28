package com.liguo.community.bean;


public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;

    public AccessTokenDto(){}

    public AccessTokenDto(String client_id, String client_secret, String code) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
