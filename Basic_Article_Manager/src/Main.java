import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		System.out.println("== 명령어 모음 ==\n1. 게시글 리스트 : article list\n2. 새글쓰기     : article write\n3. 글 지우기    : article remove");
		System.out.printf("명령어를 입력하세요 :");
		Scanner sc = new Scanner(System.in);
		
		String command = sc.nextLine();
		
		System.out.println("입력된 명령어 : "+command);
		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}
