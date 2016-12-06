package com.roger.support.jpa.specs;

import com.google.common.collect.Iterables;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Specification
 * Created by Roger on 2016/12/6.
 */
public class CustomSpecification {

    /**
     * 定义自动模糊查询
     * 即对任意的实体对象进行查询, 对象有几个值就查几个值, 当值为字符型时就自动like查询, 其余的类型使用等于查询
     * @param entityManager
     * @param example
     * @param <T>
     * @return
     */
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example){
        final Class<T> type = (Class<T>) example.getClass();

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                // 获得实体类的EntityType, 可以从EntityType获取到实体类的属性
                EntityType<T> entity = entityManager.getMetamodel().entity(type);
                // 对实体类的所有属性循环
                for (Attribute<T, ?> attr: entity.getDeclaredAttributes()){
                    Object attrValue = getAttributeValue(attr, example);
                    if (attrValue != null){
                        if (attr.getJavaType() == String.class){
                            if (!StringUtils.isEmpty(attrValue)){
                                predicates.add(criteriaBuilder.like(root.get(attribute(entity, attr.getName(), String.class)), patternLike((String) attrValue)));
                            }
                        }else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
                        }
                    }
                }
                return predicates.isEmpty()?criteriaBuilder.conjunction():criteriaBuilder.and(Iterables.toArray(predicates, Predicate.class));
            }
        };
    }

    private static <T> Object getAttributeValue(Attribute<T, ?> attr, T example){
        return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
    }

    private static <T,E> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass){
        return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
    }

    private static String patternLike(String str){
        return "%" + str + "%";
    }
}
