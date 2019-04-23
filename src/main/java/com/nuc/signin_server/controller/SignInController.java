package com.nuc.signin_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuc.signin_server.entity.SignIn;
import com.nuc.signin_server.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
            System.out.println("res0 = " + res);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("res1 = " + res);
            System.out.println("添加记录失败");
        }

        if (res!=0){
            System.out.println("res2 = " + res);
            System.out.println("添加记录成功");
            HashMap<String,Object> map = new HashMap();
            map.put("signInId",signIn.getSignInId());
            JSONObject json = new JSONObject(map);
            return json;
        }

        return null;
    }
}
