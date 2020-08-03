package com.cls.clock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.TaskDao;
import com.cls.clock.model.ClockTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private TaskDao taskDao;
    private static final Log logger = LogFactory.getLog(TaskController.class);

    @RequestMapping(value = "/getTaskList",method = RequestMethod.POST)
    public List<ClockTask> getTaskListById(@RequestBody JSONObject jsonParams){
        return this.taskDao.getTaskByUserID(jsonParams.getString("user_id"));
    }


    @RequestMapping(value = "/saveUserTask",method = RequestMethod.POST)
    public void saveUserTask(@RequestBody JSONObject jsonParams){
        logger.info("用户添加任务："+jsonParams.toString());
        ClockTask clockTask = new ClockTask();
        clockTask.setUser_id(jsonParams.getString("user_id"));
        clockTask.setTask_name(jsonParams.getString("task_name"));
        clockTask.setTask_id(String.valueOf(System.currentTimeMillis()));
        System.out.println(clockTask.getTask_id());
        taskDao.saveUserTask(clockTask);
    }

    @RequestMapping(value = "/deleteUserTask",method = RequestMethod.POST)
    public void deleteUserTask(@RequestBody JSONObject jsonParams){
        logger.info("用户删除任务："+jsonParams.toString());
        taskDao.deleteUserTask(jsonParams.getString("user_id"),jsonParams.getString("task_id"));
    }
}
