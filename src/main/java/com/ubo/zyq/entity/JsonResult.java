package com.ubo.zyq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {
    /**
     * 错误码.
     */
    private Integer errno;
    /**
     * 具体的内容.
     */
    private String[] data;

}
