package com.yfbx.rxdemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Author: Edward
 * Date: 2017/11/18 18:54
 * Description:
 */
public class NetResult<T> {

    @SerializedName(value = "code", alternate = {"ret", "status"})
    public int code;

    @SerializedName(value = "msg", alternate = {"info"})
    public String msg;

    @SerializedName(value = "data", alternate = {"contents"})
    public T data;


}
