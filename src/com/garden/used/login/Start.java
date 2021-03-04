package com.garden.used.login;

import java.util.Scanner;

import com.garden.used.nonmember.NonMember;

public class Start {
	public static void main(String[] args) {

		boolean loop = true;
		
		//무한 루프
		while (loop) {
			
			//시작 화면 메뉴 출력 메소드 호출2
			startMenu();
			
			System.out.print("■ 입력 : ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			if (input.equals("1")) {
				//비회원 로그인
				NonMember non = new NonMember();
				
			} else if (input.equals("2")) {
				//회원 로그인
				MemberLogin memberLogin = new MemberLogin();
			
			} else if (input.equals("3")) {
				//관리자 로그인
				AdminLogin adminLogin = new AdminLogin();
			
			} else if (input.equals("4")) {
				//프로그램 종료 멘트 치기
				Start.line();
				System.out.println();
				System.out.println("중고가든을 방문해주셔서 감사합니다.\n안녕히 가세요.");
				loop = false;
			
			} else {
				System.out.println();
				System.out.println("1에서 4사이의 숫자만 입력해주세요.");
				Start.pause();
			}
		}
			
	} //main
	
	//시작 화면 메뉴 출력
	public static void startMenu() {
		
		Start.line();
		System.out.println("\t\t중고가든에 오신 것을 환영합니다!");
		Start.line();
		System.out.println("1. 비회원 로그인");
		System.out.println("2. 회원 로그인");
		System.out.println("3. 관리자 로그인");
		System.out.println("4. 프로그램 종료");
		Start.line();
	}

	//일시 정지
	public static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계속하시려면 엔터 키를 누르세요.");
		Start.line();
		scan.nextLine();
	}
	
	//라인 찍기
	public static void line() {

		String line = "";
		
		for (int i=0; i<65; i++) {
			line += "=";
		}
		System.out.println(line);
	}
}
