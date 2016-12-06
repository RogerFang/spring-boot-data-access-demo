package com.roger.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by Roger on 2016/12/6.
 */
@WebFilter(filterName = "druidWebStatFilter",
        urlPatterns = "/*",
        initParams = {
                // 配置忽略资源
                @WebInitParam(name = "exclusions", value = "*.js, *.css, *.jpg")
        })
public class DruidStatFilter extends WebStatFilter {
}
