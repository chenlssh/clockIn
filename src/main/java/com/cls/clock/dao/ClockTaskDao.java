package com.cls.clock.dao;

import com.cls.clock.model.ClockTask;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0     2020/4/9
 * @Description 任务操作类
 * @Author chenLongshun
 */
@Mapper
public interface ClockTaskDao {

    @Select("select * from clock_task where user_id = #{userId}")
    List<ClockTask> getTaskByUserID(@Param("userId") String userId);


    @Insert("insert into clock_task (user_id, task_id, task_name, create_time)" +
            "values (#{user_id}, #{task_id}, #{task_name}, current_time)")
    void saveUserTask(ClockTask clockTask);

    @Delete("delete from clock_task where user_id = #{user_id} and task_id = #{task_id}")
    void deleteUserTask(@Param("user_id") String user_id,@Param("task_id") String task_id);
}
