package com.roger.dao.jpa;

import com.roger.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Roger on 2016/12/6.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 排序
    List<Product> findByName(String name, Sort sort);

    // 分页
    Page<Product> findByName(String name, Pageable pageable);
}
