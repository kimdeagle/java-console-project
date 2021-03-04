package com.garden.used.nonmember;

import java.util.Scanner;

public class Sell {
	
	public Sell() { //팝니다 첫화면
		
		Scanner scanner = new Scanner(System.in);
		
		Boolean start = true;
		
		while(start) {
			
			System.out.println("========================================");
			System.out.println("1. 등록된 상품리스트");
			System.out.println("2. 카테고리별 확인");
			System.out.println("3. 지역별 확인");
			System.out.println("0. 뒤로가기");
			System.out.println("H. 홈으로");
			System.out.println("========================================");
			System.out.print("■ 입력: ");
			
			String input = scanner.nextLine();
			
			if(input.equals("1")) {
				SellList sellList = new SellList(); //등록된 상품리스트
				break;
			} else if(input.equals("2")) {
				SellSearch sellSearch = new SellSearch(); //카테고리별 확인
				break;
			} else if(input.equals("3")) {
				AreaView areaView = new AreaView();  //지역별 확인
				break;
			} else if(input.equals("0")) {
				SellOrBuy sellOrBuy = new SellOrBuy(); //뒤로가기
				break;
			} else if(input.equals("h") || input.equals("H")) {
				NonMember nonMember = new NonMember(); //비회원 시작화면
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.\n");
				continue;
			}
		
		}
	}
}