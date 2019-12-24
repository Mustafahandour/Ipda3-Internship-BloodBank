
package com.ghandour.bbank.data.model.login.restPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestPassword {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private PasswordData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PasswordData getData() {
        return data;
    }

    public void setData(PasswordData data) {
        this.data = data;
    }

}
