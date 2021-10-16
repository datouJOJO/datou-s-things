package com.example.demo.vo;


public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
