package com.roger.support.jpa.specs;

import com.google.common.collect.Lists;
import com.roger.support.jpa.filter.CSearchFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

public class CDynamicSpecifications {

	/**
	 * 非类型安全的查询
	 * @param filters
	 * @param entityClazz
	 * @param <T>
     * @return
     */
	public static <T> Specification<T> bySearchFilter(final Collection<CSearchFilter> filters,
													  final Class<T> entityClazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (!filters.isEmpty()) {
					List<Predicate> predicates = Lists.newArrayList();
					for (CSearchFilter filter : filters) {
						// nested path translate
						/*String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}*/
						Path expression = root.get(filter.fieldName);

						// logic operator
						switch (filter.operator) {
							case EQ:
								predicates.add(builder.equal(expression, filter.value));
								break;
							case NEQ:
								predicates.add(builder.notEqual(expression, filter.value));
								break;
							case NOTNULL:
								predicates.add(builder.isNotNull(expression));
								break;
							case NOTEMPTY:
								predicates.add(builder.isNotEmpty(expression));
								break;
							case LIKE:
								predicates.add(builder.like(expression, "%" + filter.value + "%"));
								break;
							case GT:
								predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
								break;
							case LT:
								predicates.add(builder.lessThan(expression, (Comparable) filter.value));
								break;
							case GTE:
								predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case LTE:
								predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
								break;
						}
					}

					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
}
