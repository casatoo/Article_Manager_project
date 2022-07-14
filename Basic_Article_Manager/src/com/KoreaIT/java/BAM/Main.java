package com.KoreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {

			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				LocalDateTime now = LocalDateTime.now();
				String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

				Article article = new Article(id, title, body, formatedNow);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {
				if (articles.size() > 0) {
					System.out.println("번호  /  제목");
					for (int i = articles.size() - 1; i >= 0; i--) {
						System.out.println(articles.get(i).id + "      " + articles.get(i).title);
					}
				} else {
					System.out.println("게시글이 없습니다.");
				}

			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				boolean foundArticle = false;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = true;
						System.out.println("번호 : "+article.id );
						System.out.println("날짜 : "+article.date );
						System.out.println("제목 : "+article.title );
						System.out.println("내용 : "+article.body );
						break;
					}
				}
				if (foundArticle == false) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}
			}
				else if(cmd.startsWith("article delete ")) {
					String[] cmdBits = cmd.split(" ");
					int id = Integer.parseInt(cmdBits[2]);
					boolean foundAticle = false;
					
					for(int i =0;i<articles.size();i++) {
						Article article = articles.get(i);
						if(article.id == id) {
							foundAticle = true;
							articles.remove(i);
							System.out.println(id+"번 글 삭제");
							break;
						}
					}
					if(foundAticle ==false) {
						System.out.println(id+"번째 글이 없습니다.");
					}
					
				}

				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}

			System.out.println("==프로그램 끝==");
			sc.close();
		}

}

class Article {
	int id;
	String title;
	String body;
	String date;

	Article(int id, String title, String body, String date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
	}

}