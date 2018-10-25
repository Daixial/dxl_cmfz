package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logs implements Serializable {
    private String id;
    private String user;
    private Date time;
    private String resource;
    private String action;
    private String message;
    private String result;
}
