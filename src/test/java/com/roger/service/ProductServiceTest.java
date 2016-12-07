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
        Page<Product> productPage = productService.pageByName("电脑", 0, 10);
        System.out.println(productPage.getTotalElements());
    }

    @Test
    public void findByAuto() throws Exception {
        Product example = new Product();
        example.setDescription("电脑");
        example.setPrice(20000.0);
        Page<Product> productPage = productService.findByAuto(example, 1, 10);
        System.out.println(productPage.getContent().size());
    }

    @Test
    public void findWithDynamicSpec() throws Exception {
        productService.findWithDynamicSpec();
    }

    @Test
    public void saveWithRollback() throws Exception {
        Product example = new Product();
        example.setName("电脑, 回滚测试");
        example.setPrice(20000.0);
        productService.saveWithRollback(example);
    }

    @Test
    public void saveWithNoRollback() throws Exception {
        Product example = new Product();
        example.setName("电脑, 不回滚测试");
        example.setPrice(20000.0);
        productService.saveWithNoRollback(example);
    }
}