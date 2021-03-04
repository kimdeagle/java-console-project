package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.garden.used.data.Data;

public class QnA {
	
	static int count =0;
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) throws IOException {
		

			
			boolean run = true;
			while (run) {
				System.out.println("======================================================");
				System.out.println("1. 자주하는 질문들");
				System.out.println("2. 관리자에게 문의하기");
				System.out.println();
				System.out.println();
				System.out.println("======================================================");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("======================================================");
				System.out.print("■ 입력: ");
		
		
				int selectNo = scanner.nextInt();

				if (selectNo == 1) {
					Ask();
				} else if (selectNo == 2) {
					Ask();
				} else if (selectNo == 0) {
					main(null);
				} else {
					System.out.println("잘못입력하셨습니다.");
					
				}
				
			}
			
		}


	private static void Ask() throws IOException {
		
		boolean run = true;
		while (run) {
			System.out.println("======================================================");
			System.out.println("1. 운영/정책");
			System.out.println("2. 계정/인증");
			System.out.println("3. 사기 신고");
			System.out.println("4. 이벤트");
			System.out.println("5. 구매/판매");
			System.out.println("6. 이용제재");
			System.out.println("======================================================");
			System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
			System.out.println("======================================================");
			System.out.print("■ 입력: ");
	
	
			int selectNo = scanner.nextInt();

			if (selectNo >=1 && selectNo <=6) {
				AskInfo();
			} else if (selectNo == 0) {
				main(null);
			} else {
				System.out.println("잘못입력하셨습니다.");
				
			}
			
		}
		
	}


	private static void AskInfo() throws IOException {
		boolean run = true;
		//while (run) {
			
		    String contentToBeSaved = null;
	        Scanner scanner = new Scanner(System.in);
	       
	        System.out.println("======================================================");
	        System.out.print("제목: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.print("날짜: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.print("내용: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.println();
	        System.out.println("======================================================");
	        
	       
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
	        bufferedWriter.write("■" + contentToBeSaved);
	        bufferedWriter.close();
	        System.out.println("문의 결과는 등록하신 휴대폰 문자를 통해 확인 가능합니다.");
	        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
	        
			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			      try {
			         reader.readLine();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }
			       
			      }
		
		
	}
		
	
	

