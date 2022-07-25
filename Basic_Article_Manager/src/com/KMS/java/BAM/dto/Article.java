package com.KMS.java.BAM.dto;

public class Article extends Dto {

	public String title;
	public String body;
	public int hit;
	public int writeid;


	public Article(int id, String regDate, String title, String body, int writeid, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writeid = writeid;
	}

	public void increaseHit() {
		hit++;
	}
}