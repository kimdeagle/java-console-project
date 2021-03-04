package com.garden.used.nonmember;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class AreaView {
	
	

	public void AreaView()  { //시/도 선택 생성자
	
		try {
					
				
			BufferedReader fileReader 
			= new BufferedReader(new FileReader(Data.ADDRESS)); //주소 파일 읽기
					
			LinkedHashSet<String> firstArea = new LinkedHashSet<String>(); //시, 도
			LinkedHashSet<String> secArea = new  LinkedHashSet<String>(); //시. 군, 구
			LinkedHashSet<String> thirdArea = new  LinkedHashSet<String>(); //동, 읍, 면
						
						 
			String line = null;
			while((line = fileReader.readLine()) != null) {
									
				String[] temp = line.split("■");
				firstArea.add(temp[0]);
				secArea.add(temp[1]);
				thirdArea.add(temp[2]);
						       
			 } fileReader.close();
						
						
			System.out.println("===============================================");
			System.out.println("시/도를 선택해주세요.");
			System.out.println("===============================================");
							
			Iterator<String> iter = firstArea.iterator();
			int i=1;
					
			while(iter.hasNext()) {
			System.out.printf("%d. %s\n", i, iter.next());
						i++;
			}
							
			System.out.println("===============================================");
			System.out.println("뒤로가기를 원하시면 숫자'0'을 입력해주세요.");
			System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
			System.out.println("===============================================");
			System.out.printf("■ 입력: ");
							
					
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			String [] str = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
						
			if(input.equals("0")) {
			Sell sell = new Sell();
			} else if(input.equals("H") || input.equals("h")) {
			NonMember nonMember = new NonMember();
			} else {
						
			} 
						
						
						
						
			//시, 군, 구 선택
			System.out.println("===============================================");
			System.out.println("시/군/구를 선택해주세요.");
			System.out.println("===============================================");
						
			Iterator<String> iter2 = secArea.iterator();
						
			int j=1;
			while(iter.hasNext()) {
			System.out.printf("%d. %s\n", j, iter.next());
			j++;
			}
						
			System.out.println("===============================================");
			System.out.println("뒤로가기를 원하시면 숫자'0'을 입력해주세요.");
			System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
			System.out.println("===============================================");
			System.out.printf("■ 입력: ");
						
						
						
						
						
			//동, 읍, 면 선택
			System.out.println("===============================================");
			System.out.println("동/읍/면을 선택해주세요.");
			System.out.println("===============================================");
						
			Iterator<String> iter3 = secArea.iterator();
						
			int k=1;
			while(iter.hasNext()) {
			System.out.printf("%d. %s\n", k, iter.next());
			k++;
			}
						
			System.out.println("===============================================");
			System.out.println("뒤로가기를 원하시면 숫자'0'을 입력해주세요.");
			System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
			System.out.println("===============================================");
			System.out.printf("■ 입력: ");
						
						
						
			} catch (Exception e) {
					
				
			}
				
					
			}
			
			
	public void viewAreaGoodsList() {	
				
			
					
					
			}
		
			
	}

