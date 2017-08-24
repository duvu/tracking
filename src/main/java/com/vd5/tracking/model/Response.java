package com.vd5.tracking.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author beou on 8/1/17 04:58
 * @version 1.0
 */
@Data
public class Response implements Serializable{
    private static final long serialVersionUID = 703261300465580561L;

    ResultCode result;
    Object data;
    String message;

    public Response() {
    }

    public Response(ResultCode result, Object data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }
}
