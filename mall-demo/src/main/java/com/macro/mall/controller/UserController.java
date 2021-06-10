package com.macro.mall.controller;


import com.macro.mall.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/demo")
public class UserController {
    //ribbon
    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/hello")
    public CommonResult hello(){
        return CommonResult.success(restTemplate.getForObject("http://MALL-PORTAL"
                +"/sso"
                +"/hello",CommonResult.class)
                ,"调用成功");
    }
}
