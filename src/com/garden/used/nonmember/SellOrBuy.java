package com.garden.used.nonmember;

import java.util.Scanner;

public class SellOrBuy {

	Scanner scanner = new Scanner(System.in);
	
	public SellOrBuy() {
		
		Boolean start = true;
		
		while(start) { 
			
			System.out.println("========================================");
			System.out.println("1. 팝니다");
			System.out.println("2. 삽니다");
			System.out.println("0. 뒤로가기");
			System.out.println("H. 홈으로");
			System.out.println("========================================");
			System.out.print("■ 입력: ");
			
			String input = scanner.nextLine();
			
			
			if(input.equals("1")) {
				Sell sell  = new Sell(); //팝니다 클래스 이동
				break;
			} else if(input.equals("2")) {
				Buy buy = new Buy(); //삽니다 클래스 이동
				break;
			} else if(input.equals("0")) {
				NonMember nonMember = new NonMember(); //비회원 시작화면 이동
				break;
			} else if(input.equals("h") || input.equals("H")) {
				NonMember nonMember = new NonMember();
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.\n");
				continue;
			}

	}
}
	
}