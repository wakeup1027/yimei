package com.lxq.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_INMESSGES")
public class Inmessges {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Column(name = "name", length=10)
	private String name;
	
	@Column(name="EngName", length=50)
	private String EngName;
	
	@Column(name = "Email", length=30)
	private String Email;

	@Column(name = "birthday", length=15)
	private String birthday;
	
	@Column(name = "Address", length=25)
	private String Address;
	
	@Column(name = "Website", length=100)
	private String Website;
	
	@Column(name = "phone", length=15)
	private String phone;
	
	@Column(name = "major", length=15)
	private String major;
	
	@Column(name = "Lifelang", length=2000)
	private String Lifelang;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEngName() {
		return EngName;
	}

	public void setEngName(String engName) {
		EngName = engName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getLifelang() {
		return Lifelang;
	}

	public void setLifelang(String lifelang) {
		Lifelang = lifelang;
	}

}
