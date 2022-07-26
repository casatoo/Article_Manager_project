package com.KMS.java.BAM.dto;

public class Article extends Dto {

	public String title;
	public String body;
	public int hit;
	public int writeid;
	public String writerName;


	public Article(int id, String regDate, String title, String body, int writeid, int hit, String writerName) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writeid = writeid;
		this.writerName = writerName;
	}

	public void increaseHit() {
		hit++;
	}
}