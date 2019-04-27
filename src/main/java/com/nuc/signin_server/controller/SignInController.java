package com.nuc.signin_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuc.signin_server.entity.SignIn;
import com.nuc.signin_server.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 19:01
 * @Description:
 */
@RestController
@RequestMapping("sign_in")
public class SignInController {
    @Autowired
    SignInService signInService;

    @RequestMapping("create")
    public JSONObject createSignInRecord(SignIn signIn){
        int res = 0;
        try {
            res = signInService.createSignIn(signIn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加记录失败");
        }

        if (res!=0){
            System.out.println("添加记录成功");
            HashMap<String,Object> map = new HashMap<>();
            map.put("signInId",signIn.getSignInId());
            JSONObject json = new JSONObject(map);
            return json;
        }
        return null;
    }

    @RequestMapping("updateSignInData")
    public JSONObject updateSignInData(Integer signInId, Integer arriveNumber, Integer studentNumber){
        int res = 0;
        try {
            res = signInService.updateSignInData(signInId,arriveNumber,studentNumber);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新签到结果失败");
        }
        if (res != 0){
            System.out.println("更新签到结果成功");
            HashMap<String,Object> map = new HashMap<>();
            map.put("signInId",String.valueOf(signInId));
            JSONObject json = new JSONObject(map);
            return json;
        }
        return null;
    }

    @RequestMapping("getSignInList")
    public List<SignIn> getSignInList(Integer courseId){
        List<SignIn> list = signInService.getSignInList(courseId);
        for (SignIn signIn :
                list) {
            System.out.println(signIn.toString());
        }
        System.out.println("获取 "+courseId+" 课的签到的信息");
        return list;
    }
}
