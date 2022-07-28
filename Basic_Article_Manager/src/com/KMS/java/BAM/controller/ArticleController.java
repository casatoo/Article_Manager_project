package com.KMS.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KMS.java.BAM.container.Container;
import com.KMS.java.BAM.dao.ArticleDao;
import com.KMS.java.BAM.dto.Article;
import com.KMS.java.BAM.service.ArticleService;
import com.KMS.java.BAM.util.Util;

public class ArticleController extends Controller {
	private String cmd;
	private String actionMethodName;
	Scanner sc = new Scanner(System.in);
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.articleService;
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
		int id = articleService.setNewId();
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body, writeid, 0, loginedMember.name);
		articleService.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	public void showList() {

		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> forPrintArticles = articleService.getForPrintArticles(searchKeyword);
		
		if (forPrintArticles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("   번호   |    제목    |          날짜        |   작성자   |  조회수  ");
		System.out.println("---------------------------------------------------------------");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			if(article.title.length()>4) {
				String subtitle = article.title.substring(0,3)+"..";
				System.out.printf("  %3d    |    %3s  | %4s |%5s    | %4d\n", article.id, subtitle, article.regDate, article.writerName , article.hit);
			}
			else {
				System.out.printf("  %3d    |    %3s   | %4s |%5s    | %4d\n", article.id, article.title, article.regDate, article.writerName , article.hit);
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

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("작성자 : %s\n", foundArticle.writerName);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		if(articleService.matchId(id)) {
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

		Article foundIndex = articleService.getArticleById(id);

		if (foundIndex == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		if(articleService.matchId(id)) {
		articleService.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
		}
		else {
			System.out.println("회원님이 작성한 글이 아닙니다.");
		}

	}

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		articleService.add(new Article(articleService.setNewId(), Util.getNowDateStr(), "제목1", "내용1", 1,11,"홍길동"));
		articleService.add(new Article(articleService.setNewId(), Util.getNowDateStr(), "제목2", "내용2", 2,22,"김철수"));
		articleService.add(new Article(articleService.setNewId(), Util.getNowDateStr(), "제목3", "내용3", 3,33,"김영희"));
	}
	
}
