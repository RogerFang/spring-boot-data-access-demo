package com.roger.web;

import com.roger.model.Product;
import com.roger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roger on 2016/12/6.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findByIdJpa(id);
    }
}
