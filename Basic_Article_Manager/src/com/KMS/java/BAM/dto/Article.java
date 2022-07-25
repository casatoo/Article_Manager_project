package com.KMS.java.BAM.dto;

public class Article extends Dto {

	public String title;
	public String body;
	public int hit;
	public String writename;

	public Article(int id, String regDate, String title, String body, String writename) {
		this(id, regDate, title, body, writename, 0);
	}

	public Article(int id, String regDate, String title, String body, String writename, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writename = writename;
	}

	public void increaseHit() {
		hit++;
	}
}