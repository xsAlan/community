package com.liguo.community.mapper;

import com.liguo.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by dogbro on 2019-10-31 15:01
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name, account_id, token, avatar_url, gmt_create_time, gmt_update_time) values(#{name},#{accountId},#{token},#{avatarUrl},#{gmtCreateTime},#{gmtUpdateTime})")
    void addUser(User user);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "token", column = "token"),
            @Result(property = "avatarUrl", column = "avatar_url"),
            @Result(property = "gmtCreateTime", column = "gmt_create_time"),
            @Result(property = "gmtUpdateTime", column = "gmt_update_time"),
    })
    @Select("select id, name, account_id, token, avatar_url, gmt_create_time, gmt_update_time from user where token = #{token}")
    User getByToken(String token);
}
