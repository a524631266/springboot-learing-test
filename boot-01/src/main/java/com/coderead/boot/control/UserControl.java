package com.coderead.boot.control;
/**
 * @Copyright 源码阅读网 http://coderead.cn
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 鲁班大叔
 * @date 2020
 */
@Controller
public class UserControl {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
       return "hello luban uncle";
    }
}
