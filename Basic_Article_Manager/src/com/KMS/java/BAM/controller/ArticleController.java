package com.KMS.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KMS.java.BAM.dto.Article;
import com.KMS.java.BAM.util.Util;

public class ArticleController extends Controller {
	private String cmd;
	private String actionMethodName;
	int id = 0;
	Scanner sc = new Scanner(System.in);
	String writeName = null;
	
	public ArticleController() {
		makeTestData();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default :
			System.out.println("존재하지 않는 명령어입니다.");
		}
	}


	public void doWrite() {
		int writeid = loginedMember.id;
		id += 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body, writeid, 0);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		String searchKeyword = cmd.substring("article list".length()).trim();

		System.out.printf("검색어 : %s\n", searchKeyword);

		List<Article> forPrintArticles = articles;
		

		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}

			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다");
				return;
			}
		}

		System.out.println("   번호   |    제목    |          날짜        |   작성자   |  조회수  ");
		System.out.println("---------------------------------------------------------------");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			for(int j = 0;j<members.size();j++) {
				if(members.get(j).id == article.writeid) {
					writeName = members.get(j).name;
				}
			}
			
			if(article.title.length()>4) {
				String subtitle = article.title.substring(0,3)+"..";
				System.out.printf("  %3d    |    %3s  | %4s |%5s    | %4d\n", article.id, subtitle, article.regDate, writeName , article.hit);
			}
			else {
				System.out.printf("  %3d    |    %3s   | %4s |%5s    | %4d\n", article.id, article.title, article.regDate, writeName , article.hit);
			}
		}
		System.out.println("---------------------------------------------------------------");

	}

	public void showDetail() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		for(int i = 0;i<members.size();i++) {
			if(members.get(i).id == foundArticle.writeid) {
				writeName = members.get(i).name;
			}
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("작성자 : %s\n", writeName);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		if(matchId(id)) {
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();

			foundArticle.title = title;
			foundArticle.body = body;

			System.out.printf("%d번 게시물을 수정했습니다\n", id);
		}
		else {
			System.out.println("회원님이 작성한 글이 아닙니다.");
		}

	}

	public void doDelete() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		int foundIndex = getArticleIndexById(id);

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		if(matchId(id)) {
		articles.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
		}
		else {
			System.out.println("회원님이 작성한 글이 아닙니다.");
		}

	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
	
	private boolean matchId(int id) {
		Article foundArticle = getArticleById(id);
		if(foundArticle.writeid ==loginedMember.id) {
			return true;
		}
		return false;
	}
	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		id += 1;
		articles.add(new Article(id, Util.getNowDateStr(), "제목1", "내용1", 1,11));
		id += 1;
		articles.add(new Article(id, Util.getNowDateStr(), "제목2", "내용2", 2,22));
		id += 1;
		articles.add(new Article(id, Util.getNowDateStr(), "제목3", "내용3", 3,33));
	}
	
}
