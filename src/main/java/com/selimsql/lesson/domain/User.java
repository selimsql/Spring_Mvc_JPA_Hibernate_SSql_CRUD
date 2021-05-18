package com.selimsql.lesson.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable {

	private static final long serialVersionUID = 7847427564396352348L;

	@Id
	@Column(name="Id", unique=true, nullable=false)
	private Integer id; //uses SEQ_USERPK to get new Id!

	@Column(name="Code", unique=true, nullable=false, length=20)
	private String code;

	@Column(name="Name", nullable=false, length=20)
	private String name;

	@Column(name="Surname", nullable=false, length=20)
	private String surname;

	@Column(name="Password", nullable=false, length=100)
	private String password;

	@Column(name="Email", unique=true, nullable=false, length=50)
	private String email;

	@Column(name="Phone", length=20)
	private String phone;

	@Column(name="Status", nullable=false)
	private Integer status;


	public User() {
		id = null;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
