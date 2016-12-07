package com.roger.support.jpa.filter;

public class CSearchFilter {

	public enum COperator {
		EQ, LIKE, GT, LT, GTE, LTE, NEQ, NOTNULL, NOTEMPTY
	}

	public String fieldName;
	public Object value;
	public COperator operator;

	public CSearchFilter(String fieldName, COperator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}
}
