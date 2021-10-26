package com.example.dididemo.picture;

import java.io.Serializable;

public class SinaIpVo implements Serializable {
    private Integer ret;
    private String province;
    private String city;

    public SinaIpVo(Integer ret, String province, String city) {
        this.ret = ret;
        this.province = province;
        this.city = city;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
