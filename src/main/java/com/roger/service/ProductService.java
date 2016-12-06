package com.roger.service;

import com.roger.dao.jdbc.ProductDao;
import com.roger.dao.jpa.ProductRepository;
import com.roger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/6.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductRepository productRepository;

    public Product findByIdJdbc(long id){
        return productDao.findById(id);
    }

    public Product findByIdJpa(long id){
        return productRepository.findOne(id);
    }
}
