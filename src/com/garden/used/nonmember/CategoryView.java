package com.garden.used.nonmember;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryView {

	public CategoryView() throws IOException { //카테고리별 확인 첫 화면
	
		Scanner scan = new Scanner(System.in);
		
		BufferedReader bb = new BufferedReader(new FileReader(Data.CATEGORY));
		
		
		
		String str = "";
		
		ArrayList<String> first = new ArrayList<String>();
		ArrayList<String> sec = new ArrayList<String>();
		
		
		while ((str = bb.readLine()) != null) {
			String[] strr = str.split("@");
			//String firstCategory;
			
			first.add(strr[0]);
			sec.add(strr[1]);
		}//while
		bb.close();
		
		
		System.out.println("=====================================================");
		System.out.println("상위 카테고리를 선택해주세요.");
		System.out.println("=====================================================");
		
		for (int i=0; i<first.size(); i++) { //상위 카테고리 출력
			System.out.printf("%d. %s\t\n", i+1, first.get(i));
		
		}
		
		System.out.println("=====================================================");
		System.out.print("■ 입력: ");
		
		Scanner scanner = new Scanner(System.in);
		int input = Integer.parseInt(scanner.nextLine());
		
		
		if (input > 0 && input <= first.size()) {
			
			//2단계 카테고리 선택하기
			System.out.println("=====================================================");
			System.out.println("하위 카테고리를 선택해주세요.");
			System.out.println("=====================================================");
			
			String[] temp = sec.get(input-1).split("■"); 
			
			for (int i=0; i<temp.length; i++) { //하위 카테고리 출력
				System.out.printf("%d. %s\t\n", i+1, temp[i]);
				
			}
			
			
			System.out.println();
			System.out.println("뒤로가기를 원하시면 숫자 '0'을 입력해주세요.");
			System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
			System.out.println("=====================================================");
			System.out.print("■ 입력 : ");
		
			String put = scanner.nextLine();
			if(put.equals("H") || put.equals("h")) { //비회원 홈화면
				NonMember n = new NonMember();
			}
			
			int secPut = Integer.parseInt(put);
			
			if(secPut>=1 && secPut <= sec.size()) { //상품 리스트
				totalView(temp); //TODO 다시하기
			} else { 
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				CategoryView n = new CategoryView();
			}
			
		
	}
		
	}
	
	
	public void totalView(String[] temp) {
		try {
			
		
		BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
		ArrayList<String> arr = new ArrayList<String>();
		
		String str = "";
		while((str = reader.readLine()) != null) {
			arr.add(str);
			
			for(int i=0; i<arr.size(); i++) {
				String[] ttt = arr.get(i).split("■");
				System.out.println(ttt[2]); //TODO
			}
			
			
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		//상품 게시판
	}
}