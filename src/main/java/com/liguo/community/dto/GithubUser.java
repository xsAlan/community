package com.liguo.community.dto;

import com.liguo.community.model.User;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by dogbro on 2019-10-24 1928
 */
@Data
@Getter
public class GithubUser {
    private String login;
    private String id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url ; //https//api.github.com/users/xsAlan,
    private String html_url; // https//github.com/xsAlan,
    private String followers_url;// https//api.github.com/users/xsAlan/followers,
    private String following_url;// https//api.github.com/users/xsAlan/following{/other_user},
    private String gists_url; // https//api.github.com/users/xsAlan/gists{/gist_id},
    private String starred_url; // https//api.github.com/users/xsAlan/starred{/owner}{/repo},
    private String subscriptions_url; // https//api.github.com/users/xsAlan/subscriptions,
    private String organizations_url; // https//api.github.com/users/xsAlan/orgs,
    private String repos_url; // https//api.github.com/users/xsAlan/repos,
    private String events_url; // https//api.github.com/users/xsAlan/events{/privacy},
    private String received_events_url; // https//api.github.com/users/xsAlan/received_events,
    private String type;
    private boolean site_admin; // false,
    private String name; // dogbro,
    private String company; // ifreecomm,
    private String blog;
    private String location; // shenzhen China,
    private String email; // xsliguo@163.com,
    private String hireable; // null,
    private String bio; // dogbro,
    private Integer public_repos;
    private Integer public_gists;
    private Integer followers;
    private Integer following;
    private String created_at; // 2013-10-18T124542Z,
    private String updated_at; // 2019-10-24T021610Z


    public User toUser() {
        return new User(
                this.name,
                this.login,
                UUID.randomUUID().toString(),
                this.avatar_url,
                System.currentTimeMillis(),
                System.currentTimeMillis());
    }
}
