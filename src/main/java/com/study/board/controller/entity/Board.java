package com.study.board.controller.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity  //테이블을 의미
@Data
public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //mysql  이니까. //컬럼명을 적어주기

    private Integer id;
    private String title;
    private String content;
    private String time;

}
