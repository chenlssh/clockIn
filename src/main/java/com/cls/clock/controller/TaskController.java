package com.cls.clock.controller;

import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.ClockTaskDao;
import com.cls.clock.model.ClockTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description 获取用户目标相关信息
 * @Author chenLongshun
 * @DATE 2020/4/9
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private ClockTaskDao clockTaskDao;

    @RequestMapping(value = "/getTaskList/{userId}",method = RequestMethod.GET)
    public List<ClockTask> getTaskListById(@PathVariable("userId") String userId){
        return this.clockTaskDao.getTaskByUserID(userId);
    }
    @RequestMapping(value = "/saveUserTask",method = RequestMethod.POST)
    public void saveUserTask(@RequestBody JSONObject jsonParams){
        System.out.println("用户："+jsonParams.getString("user_id")+"立下了任务："+jsonParams.getString("task_name"));
        ClockTask clockTask = new ClockTask();
        clockTask.setUser_id(jsonParams.getString("user_id"));
        clockTask.setTask_name(jsonParams.getString("task_name"));
        clockTask.setTask_id(String.valueOf(System.currentTimeMillis()));
        System.out.println(clockTask.getTask_id());
        clockTaskDao.saveUserTask(clockTask);
    }

    @RequestMapping(value = "/deleteUserTask",method = RequestMethod.POST)
    public void deleteUserTask(@RequestBody JSONObject jsonParams){
        System.out.println("用户："+jsonParams.getString("user_id")+"申请删除了任务："+jsonParams.getString("task_id"));
        clockTaskDao.deleteUserTask(jsonParams.getString("user_id"),jsonParams.getString("task_id"));
    }
}
