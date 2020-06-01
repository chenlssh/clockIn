package com.cls.clock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.ClockTaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description 每日打卡相关操作：查询每日打卡任务，打卡
 * @Author chenLongshun
 * @DATE 2020/5/20
 */
@RestController
@RequestMapping(value = "/clockTask")
public class ClockTaskController {

    @Autowired
    private ClockTaskDAO clockTaskDAO;

    @RequestMapping(value = "/getTodayTaskList/{userId}",method = RequestMethod.GET)
    public String getTodayTaskById(@PathVariable("userId") String userId){
        return JSON.toJSONString(this.clockTaskDAO.getTodayTaskByUserId(userId));
    }

    @RequestMapping(value = "/clockTodayTask",method = RequestMethod.POST)
    public void clockTodayTask(@RequestBody JSONObject jsonParams){
        System.out.println(jsonParams.toString());
        this.clockTaskDAO.clockTodayTask(jsonParams.getString("user_id"),jsonParams.getString("task_id"));
    }
}
