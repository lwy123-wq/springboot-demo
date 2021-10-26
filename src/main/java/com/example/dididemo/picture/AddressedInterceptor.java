package com.example.dididemo.picture;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AddressedInterceptor  implements HandlerInterceptor {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }

        String sina = restTemplate.getForObject("http://api.map.baidu.com/location/ip?ak=G5HoLoRll9Gugi1sa8ceGASeozGQebnl&ip={ip}", String.class,ip);
        SinaIpVo sinaIpVo = new Gson().fromJson(sina, SinaIpVo.class);
        if(sinaIpVo.getRet()!=-1){
            System.out.println(sinaIpVo.getProvince());
            System.out.println(sinaIpVo.getCity());
        }/*else{
            String object = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip={ip}", String.class,ip);
            IpVo ipVo = new Gson().fromJson(object, IpVo.class);
            // XX表示内网
            if(ipVo.getCode()==0 && !ipVo.getAddress().getRegion().equals("XX")){
                System.out.println(ipVo.getAddress().getRegion());
                System.out.println(ipVo.getAddress().getCity());
            }
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
