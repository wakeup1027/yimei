package com.lxq.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ��������
 */

@Entity
@Table(name="T_EXPERIENCE")
public class myExperience {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Column(name = "title", length=100)
	private String title;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "creantime")
	private Timestamp creantime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreantime() {
		return creantime;
	}

	public void setCreantime(Timestamp creantime) {
		this.creantime = creantime;
	}

}
