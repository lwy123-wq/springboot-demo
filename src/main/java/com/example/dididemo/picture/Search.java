package com.example.dididemo.picture;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
//ip
public class Search {
    static String MYAK = "G5HoLoRll9Gugi1sa8ceGASeozGQebnl"; // 百度地图密钥

   /* public static void main(String[] args) {
        String dom = "安徽省蚌埠市固镇县";
        Map map = getCoordinate(dom);
        System.out.println("map: "+map);
    }*/

    //单个地址查询
    public static Map getCoordinate(String address) {
        Map<String,String> map = new HashMap<>();
        if (address != null && !"".equals(address)) {
            String url = "http://api.map.baidu.com/place/v2/search?query="+address+"&region=全国&extensions_adcode=true&output=json&ak="+MYAK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.parseObject(json);
                JSONArray array = obj.getJSONArray("results");    //如果json格式的字符串里含有数组格式的属性，将其转换成JSONArray，以方便后面转换成对应的实体
                if ("0".equals(obj.getString("status"))) {
                    JSONObject jsonObject =(JSONObject ) array.get(0);     //将array中的数据进行逐条转换
                    Picture singleAddressBean =JSONObject.toJavaObject(jsonObject, Picture.class);  //通过JSONObject.toBean()方法进行对象间的转换
                    String area = singleAddressBean.getArea();
                    String uid = singleAddressBean.getUid();
                    String province = singleAddressBean.getProvince();
                    String city = singleAddressBean.getCity();
                    String adcode = singleAddressBean.getAdcode();
                    String name = singleAddressBean.getName();
                    String location = singleAddressBean.getLocation();
                    if(location==null||location.isEmpty()||"".equals(location)){
                        return map;
                    }
                    String lng = cutString(location,"lng",",");
                    String lat = cutString(location,"lat","}");
                    String detail = singleAddressBean.getDetail();
                    location = lat+","+lng;
                    map.put("area",area);
                    map.put("uid",uid);
                    map.put("province",province);
                    map.put("city",city);
                    map.put("adcode",adcode);
                    map.put("name",name);
                    map.put("location",location);
                    map.put("detail",detail);
                }
            }
            return map;
        }
        return null;
    }

    public static String cutString(String allStr,String startLog,String endLog){
        allStr = allStr.substring(allStr.indexOf(startLog),allStr.indexOf(endLog));
        allStr = allStr.substring(startLog.length()+2,allStr.length());
        return allStr;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }

}
