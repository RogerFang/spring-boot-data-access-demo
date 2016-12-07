package com.roger.dao.jpa;

import com.roger.model.Product;
import com.roger.support.jpa.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * 继承自定义的Repository接口, 即可以使用在自定义的Repository中实现的功能
 * Created by Roger on 2016/12/6.
 */
// 注解的path属性修改默认的rest访问路径
@RepositoryRestResource(path = "products")
public interface ProductRepository extends JpaRepository<Product, Long>, CustomRepository<Product, Long>{

    // 排序
    @RestResource(path = "sortByName", rel = "sortByName")
    List<Product> findByName(@Param("name") String name, @Param("sort") Sort sort);

    // 分页
    Page<Product> findByName(String name, Pageable pageable);
}
