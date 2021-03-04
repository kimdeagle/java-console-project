package com.garden.used.admin.member;
import com.garden.used.admin.site.BanWords;

public class Home {

public static void main(String[] args) {
	
	
	Home();
}
	
	public static void Home() {
		InPut input = new InPut();
		System.out.println();
		System.out.println();
		
		System.out.println("==============================================");
		System.out.println("                 관리자 메인화면");
		System.out.println("==============================================");
		System.out.println("1.회원관리");
		System.out.println("2.사이트관리");
		System.out.println("3.선호도분석");
		System.out.println("0.로그아웃");
		System.out.println("==============================================");
		input.inputHome();
		
	}
		
}