package com.kienle.monxaovn.entity;

import java.io.Serializable;

public class MonXao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String THUMB = "thumb";
	public static final String IMAGE = "image";
	public static final String NAME = "name";
	
	private String thumb;
	private String image;
	private String name;

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
