package com.fucode.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Human
 * @Description: TODO
 * @Author user
 * @Date 2020/9/22
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human {
    private Integer id;
    private Integer age;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

}
