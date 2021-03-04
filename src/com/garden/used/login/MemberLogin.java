package com.garden.used.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.garden.used.admin.member.Home;
import com.garden.used.data.Data;
import com.garden.used.member.ViewMyInfo;

public class MemberLogin {
	
	
	Scanner scan = new Scanner(System.in);
	//로그인 기본 생성자
	public MemberLogin() {
		
		//로그인 메뉴 출력 메소드 호출
		loginMenu();

		System.out.print("■ 입력 : ");
		String input = scan.nextLine();

		if (input.equals("1")) {
			//로그인 하기
			login();
			
		} else if (input.equals("2")) {
			//아이디 찾기
			FindId findId = new FindId();
			
		} else if (input.equals("3")) {
			//비밀번호 찾기
			FindPassword findPassword = new FindPassword();
			
		} else if (input.equals("4")) {
			//회원 가입하기
			SignUp signUp = new SignUp();

		} else if (input.equals("0")) {
			//뒤로 가기
			Start start = new Start();
			
		} else {
			System.out.println();
			System.out.println("0에서 4사이의 숫자만 입력해주세요.");
			Start.pause();
			MemberLogin memberLogin = new MemberLogin();
		}
	}
	
	//로그인
	public static void login() {
		Scanner scan = new Scanner(System.in);
		
		Start.line();
		System.out.println("\t\t\t  로그인");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("로그인하시려면 아이디와 비밀번호를 입력해주세요.");
		System.out.println();
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			String line = null;
			boolean flag = false;
			
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			
			//H : 시작 화면으로
			if (id.toUpperCase().equals("H")) {			
				Start start = new Start();
				return;
			}
			
			System.out.print("비밀번호 : ");
			String password = scan.nextLine();
			
			//H : 시작 화면으로
			if (password.toUpperCase().equals("H")) {			
				Start start = new Start();
				return;
			}
			
			//memberInfo.txt에서 한줄씩 읽어온다
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				
				if (temp[8].equals(id) && temp[9].equals(password)) {
					
					//아이디와 비밀번호가 일치하는 경우
					if (!temp[7].equals("")) {
						System.out.println();
						System.out.println("탈퇴한 계정입니다.");
						System.out.println("엔터 키를 누르시면 시작 화면으로 돌아갑니다.");
						Start.line();
						scan.nextLine();
						Start start = new Start();
						flag = true;
						break;
					}
					
					if (temp[11].equals("블랙")) {
						//블랙리스트에 오른 회원 로그인 시
						System.out.println();
						System.out.println("고객님의 계정이 차단되어 이용이 불가능합니다.");
						System.out.println("엔터 키를 누르시면 시작 화면으로 돌아갑니다.");
						Start.line();
						scan.nextLine();
						Start start = new Start();
						flag = true;
						break;
					}
					
					if (temp[11].equals("휴면")) {
						//휴면 회원 로그인 시
						System.out.println();
						System.out.println("고객님의 계정은 휴면 상태로 이용이 불가능합니다.");
						System.out.println("엔터 키를 누르시면 시작 화면으로 돌아갑니다.");
						Start.line();
						scan.nextLine();
						Start start = new Start();
						flag = true;
						break;
					}
					
					System.out.println();
					System.out.printf("%s %s님 로그인 되었습니다.\n", temp[10], temp[11].equals("일반") ? "일반 회원" : "관리자");
				
					System.out.println();
					flag = true;
					
					if (temp[11].equals("일반")) {
						
						//일반 회원 로그인 시
						Start.pause();
						System.out.println("일반 회원 로그인 성공 화면");
						
					}
					
					if (temp[11].equals("관리자")) {
						
						//관리자 로그인 시
						Start.pause();
						Home home = new Home();
						home.Home();
						
					}
					break;
				} 
			}
			
			//로그인 실패
			if (flag == false) {
				System.out.println();
				System.out.println("로그인 실패했습니다.");
				System.out.println("아이디나 비밀번호를 확인 후 다시 입력해주세요.");
				Start.line();
				login();
			}

			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//로그인 메뉴 출력
	private void loginMenu() {
		
		Start.line();
		System.out.println("\t\t\t회원 로그인");
		Start.line();
		System.out.println("1. 로그인하기");
		System.out.println("2. 아이디 찾기");
		System.out.println("3. 비밀번호 찾기");
		System.out.println("4. 회원 가입하기");
		System.out.println("0. 뒤로 가기");
		Start.line();
	}
}