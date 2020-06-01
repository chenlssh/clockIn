package com.cls.clock.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description 统计打卡次数
 * @Author chenLongshun
 * @DATE 2020/5/20
 */
@Mapper
public interface StatisticCardDAO {

    @Select("select t1.task_name,\n" +
            "       DATE_FORMAT(t1.create_time, '%Y-%m-%d') startdate,\n" +
            "       (select count(*)\n" +
            "        from clock_card t2\n" +
            "        where t1.user_id = t2.user_id\n" +
            "          and t1.task_id = t2.task_id) as totalnum\n" +
            "from clock_task t1\n" +
            "where t1.user_id = #{userId}")
    List<Map<String,Object>> getStatisticCardByUserId(@Param("userId") String userId);

}
