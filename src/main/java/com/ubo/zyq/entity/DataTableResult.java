package com.ubo.zyq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTableResult {
    private Integer code;
    private String msg;
    private Long count;
    private List data;
}
