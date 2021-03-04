package com.garden.used.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.garden.used.data.Data;

//아이디 찾기 클래스
public class FindId {
	
	private String name;
	private String phoneNumber;
	
	Scanner scan = new Scanner(System.in);
	
	//생성자
	public FindId(){
		Start.line();
		System.out.println("\t\t\t아이디 찾기");
		Start.line();		
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("아이디는 가입 시 입력하신 이름과 휴대폰 번호로 찾을 수 있습니다.");
		System.out.println();
		
		System.out.print("이름 : ");
		this.name = scan.nextLine();
		
		//H : 시작 화면으로
		if (this.name.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}
		
		System.out.print("휴대폰 번호 : ");
		String phoneNumber2 = scan.nextLine();
		this.phoneNumber = phoneNumber2.replace("-", "");

		//H : 시작 화면으로
		if (this.phoneNumber.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}

		
		findId(this.name, this.phoneNumber);
	}
	
	private void findId(String name, String phoneNumber) {
		
		try {
			Scanner scan = new Scanner(System.in);
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			String line = null;
			boolean flag = false;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				
				if (temp[1].equals(name) && temp[4].equals(phoneNumber)) {
					
					if (!(temp[7].equals(""))) {
						
						System.out.println();
						System.out.println("탈퇴한 계정의 아이디는 찾을 수 없습니다.");
						System.out.println("시작 화면으로 돌아갑니다.");
						Start.pause();
						Start start = new Start();
						return;
					}
					
					while (!flag) {
						System.out.println();
						System.out.printf("인증 번호 [%s]를 정확히 입력해주세요.\n", temp[5]);
						System.out.print("인증 번호 : ");
						String num = scan.nextLine();

						if (temp[5].equals(num)) {
							System.out.println();
							System.out.printf("고객님의 정보와 일치하는 아이디는 [%s]입니다.\n", temp[8]);
							System.out.println("로그인 화면으로 돌아갑니다.");
							Start.pause();
	
							//로그인 화면으로 이동
							MemberLogin.login();
							flag = true;
							break;

						} else if (!temp[5].equals(num)){
							System.out.println();
							System.out.println("인증 번호가 일치하지 않습니다.\n인증 번호를 확인 후 다시 입력해주세요.");
							Start.line();
						}
					}
				}
			}
			
			//이름, 휴대폰 번호 데이터가 조회되지 않을 때
			if (flag == false) {
				System.out.println();
				System.out.println("고객님의 정보와 일치하는 아이디가 없습니다.");
				System.out.println("이름이나 휴대폰 번호를 확인 후 다시 입력해주세요.");
				Start.line();
				
				//아이디 찾기로 다시 이동
				FindId findId = new FindId();
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
