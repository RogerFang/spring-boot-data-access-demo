package com.roger.service;

import com.roger.dao.jdbc.ProductDao;
import com.roger.dao.jpa.ProductRepository;
import com.roger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 排序
     * 根据价格降序
     * @param name
     * @return
     */
    public List<Product> findByName(String name){
        return productRepository.findByName(name, new Sort(Sort.Direction.DESC, "price"));
    }

    /**
     * 分页
     * @param name
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Product> pageByName(String name, int pageNumber, int pageSize){
        return productRepository.findByName(name, new PageRequest(pageNumber, pageSize));
    }
}
