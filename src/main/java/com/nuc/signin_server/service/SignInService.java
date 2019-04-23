package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.SignIn;
import com.nuc.signin_server.mapper.SignInMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 18:59
 * @Description:
 */
@Service
public class SignInService {

    @Autowired
    SignInMapper signInMapper;

    public int createSignIn(SignIn signIn){
        return signInMapper.createSignIn(signIn);
    }
}
