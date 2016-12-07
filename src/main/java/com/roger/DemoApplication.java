package com.roger;

import com.roger.support.jpa.CustomRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Roger on 2016/12/6.
 */
@SpringBootApplication
// @ServletComponentScan是的spring能够扫描到自己编写的servlet和filter
@ServletComponentScan(basePackages = {"com.roger.servlet","com.roger.filter"})
// 使得自定义的Repository实现起效
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
