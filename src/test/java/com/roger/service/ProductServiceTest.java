package com.roger.service;

import com.roger.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Roger on 2016/12/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findByIdJdbc() throws Exception {
        Product product = productService.findByIdJdbc(1);
        System.out.println(product);
    }

    @Test
    public void findByIdJpa() throws Exception {
        Product product = productService.findByIdJpa(1);
        System.out.println(product);
    }

    @Test
    public void findByName() throws Exception {
        List<Product> products = productService.findByName("电脑");
        System.out.println(products.size());
    }

    @Test
    public void pageByName() throws Exception {
        Page<Product> productPage = productService.pageByName("电脑", 1, 10);
        System.out.println(productPage.getTotalElements());
    }
}