package com.xyz66.service;

import org.springframework.stereotype.Service;

/**
 * @author Gjkt
 * @since 2023/12/25 17:05
 */
@Service("test")
public class TestService {
    public boolean cs(String s){
        System.out.println(s);
        return true;
    }
}
