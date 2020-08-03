package com.cls.clock.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 微信用户登录，若第一次则为其注册信息
 * @version 1.0
 * @Description
 * @Author chenLongshun
 * @DATE 2020/7/26
 */
@Mapper
public interface LoginDAO {

    @Select("select count(1) from clock_users where open_id = #{openId}")
    int checkUserRegister(@Param("openId") String openId);

    @Insert("insert clock_users(open_id, wx_nick_name, gender, create_time, update_time) " +
            "VALUES (#{openId},null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP) ")
    void saveUser(@Param("openId") String openId);
}
