package com.semeureka.frame.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_FRAME_PERMISSION")
public class Permission implements Serializable {
	@Id
	@GeneratedValue(generator = "PERMISSION")
	@SequenceGenerator(name = "PERMISSION", sequenceName = "HSEQ_PERMISSION", allocationSize = 1)
	private Integer id;
	@Column(name = "PERMISSION_VALUE")
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
