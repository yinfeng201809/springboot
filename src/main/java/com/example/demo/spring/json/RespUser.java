package com.example.demo.spring.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 返回值entity
 */
@Data
public class RespUser {

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = JsonDateSerializer2.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
