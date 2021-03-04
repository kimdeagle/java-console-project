package com.garden.used.admin.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SiteMain {
	
	public static void SiteMain() {
		
		InputSite i = new InputSite();
		
		System.out.println();
		System.out.println();
		System.out.println("==============================================================");
		System.out.println("                         사이트관리");
		System.out.println("==============================================================");
		System.out.println("1.게이시판 관리");
		System.out.println("2.금지어 설정 관리");
		System.out.println("3.이벤트");
		System.out.println("4.공지사항");
		System.out.println("5.QnA");
		System.out.println("0.뒤로가기");
		System.out.println("==============================================================");
		
		i.SiteMainInPut();
		
		
		
		
	}
	public static void pause(String PostNum, int num) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SiteMain();
			
		

	} // pause
}
