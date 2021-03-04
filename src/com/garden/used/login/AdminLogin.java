package com.garden.used.login;

import java.util.Scanner;

public class AdminLogin {
	
	public AdminLogin() {
		
		adminMenu();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("■ 입력 : ");
		String input = scan.nextLine();
		Start.line();
		
		if (input.equals("1")) {
			//로그인하기
			MemberLogin.login();
			
		} else if (input.equals("2")) {
			//아이디 찾기
			FindId findId = new FindId();
			
		} else if (input.equals("3")) {
			//비밀번호 찾기
			FindPassword findPassword = new FindPassword();
			
		} else if (input.equals("0")) {
			//뒤로 가기
			Start start = new Start();
			
		} else {
			System.out.println("0에서 3사이의 숫자만 입력해주세요.");
			Start.line();
			
			MemberLogin memberLogin = new MemberLogin();
		}
	}

	//관리자 로그인 메뉴
	private void adminMenu() {
		
		Start.line();
		System.out.println("\t\t\t관리자 로그인");
		Start.line();
		System.out.println("1. 로그인하기");
		System.out.println("2. 아이디 찾기");
		System.out.println("3. 비밀번호 찾기");
		System.out.println("0. 뒤로 가기");
		Start.line();
	}
}
