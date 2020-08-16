package com.cls.clock.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

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
            "VALUES (#{open_id},#{wx_nick_name},#{gender},CURRENT_TIMESTAMP,CURRENT_TIMESTAMP) ")
    void saveUser(JSONObject params);

    @Update("update clock_users set wx_nick_name = #{wx_nick_name},update_time = current_timestamp where open_id = #{open_id}")
    void updateUser(JSONObject params);
}
