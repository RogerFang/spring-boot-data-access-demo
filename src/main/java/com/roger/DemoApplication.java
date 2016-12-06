package com.roger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Roger on 2016/12/6.
 */
@SpringBootApplication
// @ServletComponentScan是的spring能够扫描到自己编写的servlet和filter
@ServletComponentScan(basePackages = {"com.roger.servlet","com.roger.filter"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
