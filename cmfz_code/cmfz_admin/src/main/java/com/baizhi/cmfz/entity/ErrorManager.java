package com.baizhi.cmfz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorManager implements Serializable {
    private  String message;
    private boolean success =true;
    private ArrayList<String> data;
    private Integer errno;
}
