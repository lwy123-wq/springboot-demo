package com.example.dididemo.picture;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dididemo.config.IpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

@Component
public class AddressedInterceptor  implements HandlerInterceptor {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(Boolean.parseBoolean(jsonText));
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getIpAddr(request);
        if (ip.equals("0:0:0:0:0:0:0:1")){
            ip = "";//如果本机地址，ip设为空
        }
        String url = "https://api.map.baidu.com/location/ip?ip=" + ip + "&ak=G5HoLoRll9Gugi1sa8ceGASeozGQebnl&coor=bd09ll";
        JSONObject jsonObject = readJsonFromUrl(url);
        System.out.println(jsonObject.toString());
        String place = (String) ((JSONObject) jsonObject.get("content")).get("address");
        System.out.println(place);
        return true;
    }
    /*@Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
                System.out.println(ipAddress+"ssssssssssssss");
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        String sina = restTemplate.getForObject("https://api.map.baidu.com/location/ip?ak=G5HoLoRll9Gugi1sa8ceGASeozGQebnl&ip={ipAddress}&coor=bd09ll ", String.class,ipAddress);
        SinaIpVo sinaIpVo = new Gson().fromJson(sina, SinaIpVo.class);
//        if(sinaIpVo.getRet()!=-1){
            System.out.println(sinaIpVo.getProvince());
            System.out.println(sinaIpVo.getCity());
        *//*else{
            String object = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip={ip}", String.class,ip);
            IpVo ipVo = new Gson().fromJson(object, IpVo.class);
            // XX表示内网
            if(ipVo.getCode()==0 && !ipVo.getAddress().getRegion().equals("XX")){
                System.out.println(ipVo.getAddress().getRegion());
                System.out.println(ipVo.getAddress().getCity());
            }
        }*//*
        return true;
    }*/

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
