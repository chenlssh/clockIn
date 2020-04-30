package com.cls.clock.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * @version 1.0
 * @Description
 * @Author chenLongshun
 * @DATE 2020/4/9
 */
@Data
public class ClockTask {
    private String user_id;
    private String task_id;
    private String task_name;
    private Date create_time;
}
