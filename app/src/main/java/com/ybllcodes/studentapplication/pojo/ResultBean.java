package com.ybllcodes.studentapplication.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean flag;
    private String msg;

    private String result;
    private Integer msgType;

    public ResultBean(boolean flag, String msg,Integer msgType) {
        this.flag = flag;
        this.msg = msg;
        this.msgType = msgType;
    }
}