package com.garden.used.nonmember;

import java.io.IOException;
import java.util.Scanner;

import com.garden.used.admin.site.Event;
import com.garden.used.admin.site.Notice;
import com.garden.used.login.Start;

//비회원이 접속시 보게되는 메뉴 클래스

public class NonMember {

	public NonMember() throws IOException { //비회원 첫화면 생성자
		
		Scanner scan = new Scanner(System.in);
		
		boolean flag = true;
		
		
		while(flag) {
			
			NonMemberMenu(); //비회원 첫화면
			
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				SellOrBuy sellOrBuy = new SellOrBuy(); //팝니다 or 삽니다
			} else if(input.equals("2")) {
				SellOrBuy sellOrBuy = new SellOrBuy(); //팝니다 or 삽니다
			} else if(input.equals("3")) {
				Notice notice = new Notice(); //공지사항 클래스
				Notice n = new Notice();
			} else if(input.equals("4")) {
				Event event = new Event(); //이벤트 클래스
				break;
			} else if(input.equals("0")) {
				Start start = new Start(); //시작화면으로 돌아가기
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.\n");
				continue;
			}
		}
	}

	private void NonMemberMenu() {
		
		System.out.println();
		System.out.println();
		System.out.println("================================================================");
		System.out.println("\t\t비회원으로 로그인되었습니다.");
		System.out.println("================================================================");
		System.out.println("1. 상품목록확인");
		System.out.println("2. 상품검색");
		System.out.println("3. 공지사항");
		System.out.println("4. 이벤트");
		System.out.println("0. 뒤로가기");
		System.out.println("================================================================");
		System.out.print("■ 입력: ");
	
	}
	
}
