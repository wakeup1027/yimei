package com.lxq.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * –À»§∞Æ∫√
 */

@Entity
@Table(name="T_BANNER")
public class Banner {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Column(name = "title1", length=200)
	private String title1;
	
	@Column(name = "title2", length=200)
	private String title2;
	
	@Column(name = "title3", length=200)
	private String title3;
	
	@Column(name = "title4", length=200)
	private String title4;
	
	@Column(name = "title5", length=200)
	private String title5;
	
	@Column(name = "title6", length=200)
	private String title6;
	
	@Column(name = "title7", length=200)
	private String title7;
	
	@Column(name = "banerpath", length=200)
	private String banerpath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public String getTitle5() {
		return title5;
	}

	public void setTitle5(String title5) {
		this.title5 = title5;
	}

	public String getTitle6() {
		return title6;
	}

	public void setTitle6(String title6) {
		this.title6 = title6;
	}

	public String getTitle7() {
		return title7;
	}

	public void setTitle7(String title7) {
		this.title7 = title7;
	}

	public String getBanerpath() {
		return banerpath;
	}

	public void setBanerpath(String banerpath) {
		this.banerpath = banerpath;
	}
	
}
