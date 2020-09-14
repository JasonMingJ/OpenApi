package com.fucode.controller;

import org.springframework.web.bind.annotation.*;


/**
 * @ClassName CommonController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@RestController
@RequestMapping("/request")
public class CommonController {

    @GetMapping("/{tableType}")
    public void getTableData(@PathVariable("tableType")String tablType,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "5") Integer limit){

    }

}
