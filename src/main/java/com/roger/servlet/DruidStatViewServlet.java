package com.roger.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Roger on 2016/12/6.
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                // IP白名单, 没有配置则允许所有访问
                // @WebInitParam(name = "allow", value = "192.168.1.72,192.168.1.73"),
                // IP黑名单, 存在时deny优先于allow
                // @WebInitParam(name = "deny", value = "192.168.1.71"),
                @WebInitParam(name = "loginUsername", value = "admin"),
                @WebInitParam(name = "loginPassword", value = "admin")
        })
public class DruidStatViewServlet extends StatViewServlet {
}
