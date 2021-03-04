package com.garden.used.member;

import java.util.Calendar;
import java.util.Scanner;

public class MemberLoginSuccess {

	private boolean flagMenu;
	
	
	
	public void viewMenu(String memberNumber, String nickname) {
		
		flagMenu = true;
		
		while (flagMenu) {
			
			System.out.println("**********************");
			System.out.println(nickname + "님. 안녕하세요!");
			System.out.printf("로그인 시간 : %tF %tT\n", Calendar.getInstance(), Calendar.getInstance());
			System.out.println("**********************");
			
			System.out.println("======================");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("======================");
			System.out.println("1. 상품 목록");
			System.out.println("2. 상품 검색");
			System.out.println("3. 게시글 관리");
			System.out.println("4. 내정보");
			System.out.println("5. 공지사항");
			System.out.println("6. Q&A");
			System.out.println("7. 로그아웃");
			System.out.println("8. 프로그램 종료");
			System.out.println("======================");
			System.out.print("■입력 : ");
			
			Scanner scan = new Scanner(System.in);
			int input = Integer.parseInt(scan.nextLine());
			
			if (input == 3) { //게시글 관리
				ManagePost post = new ManagePost();
				post.viewGoodsPost(memberNumber, nickname);
			} else if (input == 7) {
				System.out.println(nickname + "님. 로그아웃 되었습니다.");
				pause();
				flagMenu = false;

			} else if (input == 8) {
				System.out.println("프로그램을 종료합니다.");
				System.out.println("중고가든을 방문해주셔서 감사합니다.\n안녕히 가세요.");
				System.exit(0);
			} else {
				System.out.println("구현 전");		
				pause();
			}
			
		}
		
		
	} //viewMenu
	
	public void pause() {
		
		Scanner scan = new Scanner(System.in);
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			scan.nextLine();
		
	} //pause
	
}
