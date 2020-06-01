package com.cls.clock.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description 每日打卡操作
 * @Author chenLongshun
 * @DATE 2020/5/20
 */
@Mapper
public interface ClockTaskDAO {

    @Select("select t1.*, ifnull((select 1\n" +
            "              from clock_card t2\n" +
            "              where t1.user_id = t2.user_id\n" +
            "                and t1.task_id = t2.task_id\n" +
            "                and t2.clock_date = curdate()),0) as is_clock\n" +
            "from clock_task t1\n" +
            "where t1.user_id = #{userId}")
    List<Map<String,Object>> getTodayTaskByUserId(@Param("userId") String userId);

    @Insert("insert into clock_card (user_id, task_id, clock_date, clock_time)"+
            "value (#{userId}, #{taskId}, current_date, current_time)")
    void clockTodayTask(@Param("userId") String userId,@Param("taskId") String taskId);
}
