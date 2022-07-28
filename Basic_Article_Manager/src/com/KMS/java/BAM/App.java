package com.KMS.java.BAM;

import java.util.Scanner;
import com.KMS.java.BAM.controller.ArticleController;
import com.KMS.java.BAM.controller.Controller;
import com.KMS.java.BAM.controller.ExportController;
import com.KMS.java.BAM.controller.MemberController;

public class App {

	public App() {
	}

	public void run() {
		System.out.println("====== 프로그램 시작 ======");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();
		ExportController exportController = new ExportController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {

			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			String[] cmdBits = cmd.split(" "); // article detail

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}

			String controllerName = cmdBits[0]; // article
			String actionMethodName = cmdBits[1]; // detail

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("export")) {
				controller = exportController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String cmdCombine = cmdBits[0] + "/" + cmdBits[1];

			switch (cmdCombine) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
			case "member/profile":
			case "member/leave":
				if (Controller.logincheck() == false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				break;
			}
			switch (cmdCombine) {
			case "member/login":
			case "member/join":
				if (Controller.logincheck()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(cmd, actionMethodName);

		}

		System.out.println("====== 프로그램 끝 ======");
		sc.close();
	}

}
