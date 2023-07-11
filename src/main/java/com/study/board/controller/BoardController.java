package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @GetMapping("/")  //8080 경로로 들어오면
    @ResponseBody // 이 글자들을 띄워줘라.
    public String main(){

        return "hello world";
    }
}
