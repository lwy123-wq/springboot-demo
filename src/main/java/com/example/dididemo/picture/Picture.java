package com.example.dididemo.picture;

public class Picture {
    private String area;
    private String uid;
    private String address;
    private String province;
    private String city;
    private String adcode;
    private String name;
    private String location;
    private String detail;


    public Picture(){

    }

    public Picture(String area, String uid, String address, String province, String city, String adcode, String name, String location, String detail) {
        this.area = area;
        this.uid = uid;
        this.address = address;
        this.province = province;
        this.city = city;
        this.adcode = adcode;
        this.name = name;
        this.location = location;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "area='" + area + '\'' +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", adcode='" + adcode + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
