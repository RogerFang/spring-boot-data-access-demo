package com.roger.service;

import com.roger.dao.jdbc.ProductDao;
import com.roger.dao.jpa.ProductRepository;
import com.roger.model.Product;
import com.roger.support.jpa.filter.CSearchFilter;
import com.roger.support.jpa.specs.CDynamicSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    /**
     * 使用自定义的
     * @param example
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Product> findByAuto(Product example, int pageNumber, int pageSize){
        return productRepository.findByAuto(example, new PageRequest(pageNumber, pageSize));
    }

    public List<Product> findWithDynamicSpec(){
        List<CSearchFilter> filters = new ArrayList<>();
        // 非类型安全, price是double类型的
        filters.add(new CSearchFilter("price", CSearchFilter.COperator.EQ, "error"));
        Specification<Product> spec = CDynamicSpecifications.bySearchFilter(filters, Product.class);
        return productRepository.findAll(spec);
    }

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public Product saveWithRollback(Product product){
        Product p = productRepository.save(product);
        if (product.getName().startsWith("电脑")){
            throw new IllegalArgumentException("Product name cannot start with 电脑, 回滚");
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Product saveWithNoRollback(Product product){
        Product p = productRepository.save(product);
        if (product.getName().startsWith("电脑")){
            throw new IllegalArgumentException("Product name cannot start with 电脑, 不回滚");
        }
        return p;
    }
}
