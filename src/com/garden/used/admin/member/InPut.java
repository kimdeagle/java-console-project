package com.garden.used.admin.member;

import com.garden.used.admin.site.*;
import com.garden.used.admin.preference.*;
import com.garden.used.login.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InPut {
	
	public static void inputHome() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();

			if (input.equals("1") || input.trim().equals("회원관리")) {
				Member m = new Member();
				m.list(0);
			} else if (input.equals("2") || input.trim().equals("사이트관리")) {
				SiteMain s = new SiteMain();
				s.SiteMain();
			} else if (input.equals("3") || input.trim().equals("선호도분석")) {
				Preference p = new Preference();
				p.preferenceMenu();
				
			} else if (input.equals("0") || input.trim().equals("로그아웃")) {
				System.out.printf("로그아웃 하시겠습니까? [Y/N]\n");
				System.out.print("■Y/N: ");
				input = br.readLine();
				
				if (input.toUpperCase().equals("Y")) {
					Start s = new Start();
					s.startMenu();
					System.out.println();
				}else if(input.toUpperCase().equals("N")) {
					System.out.println("최소되었습니다. ");
					SiteMain s = new SiteMain();
					s.SiteMain();
				}
//				Home();
			}else {
				Notice n = new Notice();//홈 일시멈춤 용
				n.pause("0", 3);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void input() {// ----------------------------------------------------------------------회원리스트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			if (input.equals("1")||input.equals("검색")) {// 검색
				System.out.println();
				System.out.println("번호를 선택해주세요");
				System.out.println("1.이름 2.등급 3.주소. 4.휴대폰번호 5.생년월일 6.나이 0.취소 ");
				System.out.print("■입력: ");
				input = br.readLine();
//				Find(input);
			} else if (input.equals("2")||input.equals("상세조회")) {// 상세조회
				System.out.print("■[회원번호]: ");
				input = br.readLine();
//				SearchDtail(input);
			} else if (input.equals("3")||input.equals("수정")) {// 수정
				System.out.println("[수정할 회원정보를 입력해주세요]");
				System.out.print("■[회원번호]: ");
				input = br.readLine();
//				EditMember(input);
			} else if (input.equals("4")||input.equals("삭제")) {// ---------삭제
				System.out.println("[삭제할 회원정보를 입력해주세요]");
				System.out.print("■[회원번호]: ");
				input = br.readLine();
//				DeletMember(input);
			} else if (input.toUpperCase().equals("P")||input.equals("이전페이지")) {
				int page = -1;
//				list(page);
			} else if (input.toUpperCase().equals("N")||input.equals("다음페이지")) {
				int page = 1;
//				list(page);

			} else if (input.equals("H") || input.equals("h")) {
//				Home();
			} else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
//			pause();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}


