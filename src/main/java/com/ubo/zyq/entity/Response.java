package com.ubo.zyq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success; // 处理是否成功
    private String message; // 处理后消息提示
    private Object body; // 返回数据

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
