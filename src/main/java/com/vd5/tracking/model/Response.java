package com.vd5.tracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author beou on 8/1/17 04:58
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable{

    private static final long serialVersionUID = 703261300465580561L;

    ResultCode result;
    Object data;
    String message;
}
