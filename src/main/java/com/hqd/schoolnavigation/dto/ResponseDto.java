package com.hqd.schoolnavigation.dto;

//import io.swagger.annotations.ApiModelProperty;
import org.mybatis.generator.config.Context;

import java.util.Date;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class ResponseDto<T> {
//    @ApiModelProperty(value = "返回码", example = "200")
    private String code;
//    @ApiModelProperty(value = "返回码描述", example = "ok")
    private String desc;
//    @ApiModelProperty(value = "响应时间戳", example = "2020-08-12 14:37:11")
    private Date timestamp = new Date();
//    @ApiModelProperty(value = "返回结果")
    private T data;

    public String getCode() { return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
