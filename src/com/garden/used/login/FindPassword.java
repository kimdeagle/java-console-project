package com.garden.used.login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.garden.used.data.Data;

//비밀번호 찾기 클래스
public class FindPassword {
	private String id;
	private String phoneNumber;
	
	Scanner scan  = new Scanner(System.in);
	
	//생성자
	public FindPassword() {
		Start.line();
		System.out.println("\t\t\t비밀번호 찾기");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("비밀번호는 가입 시 입력하신 아이디와 휴대폰 번호로 찾을 수 있습니다.");
		System.out.println();
		
		System.out.print("아이디 : ");
		this.id = scan.nextLine();
		
		//H : 시작 화면으로
		if (this.id.toUpperCase().equals("H")) {
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
			Start start = new Start();
			return;
		}
		
		findPassword(this.id, this.phoneNumber);
	}

	private void findPassword(String id, String phoneNumber) {
		
		
		try {
			Scanner scan = new Scanner(System.in);
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			String line = null;
			boolean flag = false;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				
				if (temp[8].equals(id) && temp[4].equals(phoneNumber)) {
					
					if (!(temp[7].equals(""))) {
						
						System.out.println();
						System.out.println("탈퇴한 계정의 비밀번호는 찾을 수 없습니다.");
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
							System.out.printf("고객님의 정보와 일치하는 비밀번호는 [%s]입니다.\n", temp[9]);
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
			
			//아이디, 휴대폰 번호 데이터가 조회되지 않을 때
			if (flag == false) {
				System.out.println();
				System.out.println("고객님의 정보와 일치하는 비밀번호가 없습니다.");
				System.out.println("아이디나 휴대폰 번호를 확인 후 다시 입력해주세요.");
				Start.line();
				
				//아이디 찾기로 다시 이동
				FindPassword findpassword = new FindPassword();
			}
			
			reader.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
