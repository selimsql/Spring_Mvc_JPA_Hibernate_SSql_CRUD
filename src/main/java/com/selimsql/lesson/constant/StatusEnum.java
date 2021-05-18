package com.selimsql.lesson.constant;

public enum StatusEnum {
	PASSIVE(0),
	ACTIVE(1),
	LOCKED(7),
	DELETED(9);

	private Integer value;

	StatusEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return name();
	}
}
