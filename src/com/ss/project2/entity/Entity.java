package com.ss.project2.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 7542779143569905984L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
