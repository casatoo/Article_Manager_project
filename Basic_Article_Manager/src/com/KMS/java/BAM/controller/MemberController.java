package com.KMS.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KMS.java.BAM.dto.Member;
import com.KMS.java.BAM.util.Util;

public class MemberController extends Controller {
	Scanner sc = new Scanner(System.in);
	private String cmd;
	private String actionMethodName;
	int id = 0;

	public MemberController() {
		makeTestData();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showFrofile();
			break;
		case "leave":
			doLeave();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
		}
	}

	public void doJoin() {
		id += 1;
		String regDate = Util.getNowDateStr();
		String loginId = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {

			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);
		
		loginedMember = member;

		System.out.printf("%d번 회원님 환영합니다\n", id);
	}

	void doLogin() {
		String loginId;
		String loginPw;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (loginId.replace(" ", "") == "") {
				System.out.println("아이디가 입력되지 않았습니다.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			if (loginPw.replace(" ", "") == "") {
				System.out.println("비밀번호가 입력되지 않았습니다.");
				continue;
			}
			break;
		}
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println(" 일치하는 회원이 없습니다.");
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println(" 비밀번호가 틀렸습니다");
			return;
		}
		loginedMember = member;
		System.out.printf("%s 님 환영합니다.\n", member.name);
	}

	void showFrofile() {
		System.out.printf("아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);

	}

	void doLogout() {
		loginedMember = null;
		System.out.println("성공적으로 로그아웃 되었습니다.");

	}

	void doLeave() {
		System.out.printf("비밀번호 확인 :");
		String loginPw = sc.nextLine();
		if (loginPw == "") {
			System.out.println("비밀번호가 입력되지 않았습니다.");
			return;
		}
		if (loginedMember.loginPw.equals(loginPw) == false) {
			System.out.println(" 비밀번호가 틀립니다.");
			return;
		}
		int id = getMemberIndexByLoginId(loginedMember.loginId);
		members.remove(id);
		loginedMember = null;
		System.out.println("정상적으로 탈퇴, 로그아웃 되었습니다.");
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		id += 1;
		members.add(new Member(id, Util.getNowDateStr(), "id1", "pw1", "홍길동"));
		id += 1;
		members.add(new Member(id, Util.getNowDateStr(), "id2", "pw2", "김철수"));
		id += 1;
		members.add(new Member(id, Util.getNowDateStr(), "id3", "pw3", "김영희"));
	}

}
