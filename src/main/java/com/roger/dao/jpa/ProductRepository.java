package com.roger.dao.jpa;

import com.roger.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/6.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
