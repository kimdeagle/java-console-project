package com.garden.used.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.garden.used.data.Data;

//회원 가입 클래스
public class SignUp {

	//회원 고유 번호
	private int memberNum = 0;
	private String phoneNumber = "";
	private int validNumber = 0;
	private String id = "";
	private String password = "";
	private String name = "";
	private int gender = 0;
	private String birth = "";
	private String nickname = "";
	
	Scanner scan = new Scanner(System.in);
	
	//생성자
	public SignUp() {
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("중고 가든 회원 가입을 시작합니다.");
		System.out.println("휴대폰 번호는 아이디/비밀번호 찾기에 사용됩니다.");
		System.out.println();
		System.out.print("휴대폰 번호 : ");
		
		String phoneNumber2 = scan.nextLine();
	
		//H : 시작 화면으로
		if (phoneNumber2.toUpperCase().equals("H")) {
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}
	
		phoneNumber2 = phoneNumber2.replace("-", "");
		
		//휴대폰 번호 유효성 검사
		phoneNumberValid(phoneNumber2);
		
	}//생성자

	//휴대폰 번호 유효성 검사
	private void phoneNumberValid(String phoneNumber) {

		boolean flag = false;
		
		if (!(phoneNumber.substring(0, 3)).equals("010")) {

			//010으로 시작하지 않을 경우
			System.out.println();
			System.out.println("휴대폰 번호가 잘못되었습니다.");
			System.out.println("010으로 시작해야 합니다.");
			Start.pause();
			flag = true;
			return;
		} 
		
		if (phoneNumber.length() == 11) {
			
			//하이픈 제외 11자인 경우
			int count = 0;
			for (int k=0; k<phoneNumber.length(); k++) {
				char c = phoneNumber.charAt(k);
				
				if (c >= '0' && c <= '9') {
					//숫자일 때만 카운트
					count++;
				}
			}
		
			if (count != 11) {
			
				//숫자로만 11자가 아닐 시 메소드 종료
				System.out.println();
				System.out.println("휴대폰 번호에 하이픈 외 다른 문자를 사용할 수 없습니다.");
				System.out.println("다시 입력해주세요.");
				Start.pause();
				flag = true;
				SignUp signUp = new SignUp();
				return;

			} else if (count == 11) {
			
				//숫자로만 11자인 경우
				flag = false;
				
			}
		} else if (phoneNumber.length() != 11) {

			//하이픈 제외 11자가 아닌 경우
			System.out.println();
			System.out.println("휴대폰 번호는 하이픈(-)을 제외한 11자의 숫자");
			System.out.println("또는 하이픈(-)을 포함한 13자의 문자와 숫자로 입력해주세요.");
			Start.pause();
			flag = true;
			SignUp signUp = new SignUp();
			return;
		}
		
		ArrayList<String> data1 = memberData();
		
		for (int i=0; i<data1.size(); i++) {
			
			String[] phoneList = data1.get(i).split("■");
	 
			if (phoneList[4].equals(phoneNumber) && !(phoneList[7].equals(""))) {
	    		
	    		//중복된 휴대폰 번호고 탈퇴한 회원일 경우
	    		int year = Integer.parseInt(phoneList[7].substring(0, 4));
				int month = Integer.parseInt(phoneList[7].substring(5, 7));
				int date = Integer.parseInt(phoneList[7].substring(8));
		
				//탈퇴한 지 7일 되는 날
				String withdrawalDate = String.format("%04d-%02d-%02d", year, month, date + 7);
				Calendar now = Calendar.getInstance();
				String nowDate = String.format("%04d-%02d-%02d", now.get(Calendar.YEAR), now.get(Calendar.MONTH)+1, now.get(Calendar.DATE));
				
				if (withdrawalDate.compareTo(nowDate) > 0) {
					System.out.println();
					System.out.println("탈퇴 후 같은 번호로의 재가입은 7일이 지나야 가능합니다.");
					Start.pause();
					SignUp signUp = new SignUp();
					flag = true;
					break;
				
				} 
				
			} else if (phoneList[4].equals(phoneNumber) && (phoneList[7].equals(""))) {
				
				//중복된 휴대폰 번호고 탈퇴하지 않은 회원일 경우
				System.out.println();
				System.out.println("이미 등록된 휴대폰 번호입니다.");
				Start.pause();
				SignUp signUp = new SignUp(); //-> 화면 재출력
				flag = true;
				break;
			
			} else if (i >= data1.size()-2 && flag == false) {
				flag = false;
				break;
			}
		}
		
		
		if (flag == false) {
			
			//중복된 휴대폰 번호가 없을 때

			//인증 번호 난수 생성
			int validNum = (int)(Math.random()*999995) + 1;
			System.out.println();
			System.out.printf("인증 번호 [%06d]를 정확히 입력해주세요.\n", validNum);
			System.out.print("인증 번호 : ");
			
			String input = scan.nextLine();
						

			if (input.toUpperCase().equals("H")) {
						
				//H : 시작 화면으로
				System.out.println();
				System.out.println("시작 화면으로 돌아갑니다.");
				Start.pause();
				Start start = new Start();
				return;
			}
					
			//숫자 아닌 문자 카운트 변수
			int numNotCount = 0;
			for (int j=0; j<input.length(); j++) {
				char c = input.charAt(j);
				if (!(c >= '0' && c <= '9')) {
					numNotCount++;
				}
			}
			//숫자 아닌 문자 입력했을 시
			int notNumber = 0;
			for (int j=0; j<phoneNumber.length(); j++) {
				char c = phoneNumber.charAt(j);
				if (!(c >= '0' && c <= '9')) {
					notNumber++;
				}
			}
			if ((notNumber != 0) && (phoneNumber.equals(""))) {
				System.out.println();
				System.out.println("인증 번호는 숫자로만 정확히 입력해주세요.");
				SignUp signUp = new SignUp();
			}
			if (numNotCount > 0) {
				//숫자 아닌 문자 있을 시
				System.out.println();
				System.out.println("인증 번호가 일치하지 않습니다.");
				System.out.println("인증 번호를 확인 후 다시 입력해주세요.");
				phoneNumberValid(phoneNumber);
				
			} else if (numNotCount == 0) {
				//숫자만 있을 시
				int number = Integer.parseInt(input);
				if (number == validNum) {
					//인증 번호를 올바르게 입력했을 경우
					System.out.println();
					System.out.println("인증되었습니다.");
					Start.line();
					this.phoneNumber = phoneNumber;
					this.validNumber = validNum;
					
					//아이디 유효성 검사 메소드
					idValid();
					flag = true;
					
				} else if (number != validNum) {
				
					//인증 번호를 올바르지 않게 입력했을 경우
					System.out.println();
					System.out.println("인증 번호가 일치하지 않습니다.");
					System.out.println("인증 번호를 확인 후 다시 입력해주세요.");
					phoneNumberValid(phoneNumber);
					flag = true;
				}
			}
		}
	}

	//아이디 유효성 검사
	private void idValid() {
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("아이디는 6~10자 영문 소문자와 숫자 조합으로 입력해주세요.");
		System.out.println();
		
		System.out.print("아이디 : ");
		String id2 = scan.nextLine();
		
		//H : 시작 화면으로
		if (id2.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}
		
		int numCount = 0;
		int engCount = 0;
		
		if (id2.length() < 6 || id2.length() > 10) {
			System.out.println("사용할 수 없는 아이디입니다.\n아이디는 6~10자로 입력해주세요.");
			Start.pause();
			idValid(); //-> 화면 재출력
		}
		
		//6~10자의 영문 소문자와 숫자 조합인지?
		for (int i=0; i<id2.length(); i++) {
			char c = id2.charAt(i);
			
			if ((c >= '0' && c <= '9')) {
				numCount++;
				
			} else if ((c >= 'a' && c <= 'z')) {
				engCount++;
			}
		}
		
		//영문이나 숫자가 없을 경우
		if ((numCount == 0)||(engCount == 0)) {
			System.out.println("사용할 수 없는 아이디입니다.");
			System.out.println("아이디는 영문 소문자와 숫자 조합으로 입력해주세요.");
			Start.pause();
			idValid(); //-> 화면 재출력
		}

		System.out.println("중복 확인을 하시려면 엔터 키를 누르세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		
		boolean flag = false;
		
		ArrayList<String> idData = memberData();
		
		for (int i=0; i<idData.size(); i++) {
			
			String[] idList = idData.get(i).split("■");
	 
			if (idList[8].equals(id2)) {
				
				//중복된 아이디가 있는 경우
				System.out.println("사용 중인 아이디입니다.");
				Start.pause();
				flag = true;
				idValid();
				break;
		
			} else if (i >= idData.size()-2 && flag == false) {
			
				flag = false;
				break;
			}
		}
			
		//중복된 아이디가 없을 경우
			
		if (flag == false) {
				
			System.out.printf("아이디 [%s]는 사용할 수 있는 아이디입니다.\n", id2);
			Start.line();
			this.id = id2;
			passwordValid();
		}
	}

	private void passwordValid() {
		
		Scanner scan = new Scanner(System.in);
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("비밀번호는 10~16자 영문 대소문자와 숫자 중 2가지 이상 조합으로 설정해주세요.");
		System.out.println();

		System.out.print("비밀번호 : ");
		String password2 = scan.nextLine();
		
		
		//H : 시작 화면으로
		if (password2.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}
		System.out.print("비밀번호 확인 : ");
		String password3 = scan.nextLine();
		
		//H : 시작 화면으로
		if (password3.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			return;
		}
		
		int engCount = 0;
		int smallCount = 0;
		int numCount = 0;
		
		boolean flag = false;
		
		if (password2.length() < 10 || password2.length() > 16) {
			System.out.println();
			System.out.println("사용할 수 없는 비밀번호입니다.");
			Start.pause();
			passwordValid();
		}
		
		if (password2.equals(password3)) {
			System.out.println();
			System.out.println("비밀번호가 일치합니다.");
			flag = true;
		}
		
		if (flag == false) {
			System.out.println();
			System.out.println("비밀번호가 일치하지 않습니다.");
			System.out.println("비밀번호를 확인 후 다시 입력해주세요.");
			Start.pause();
			passwordValid(); //-> 화면 재출력
		}
		
		for (int i=0; i<password2.length(); i++) {
			char c = password2.charAt(i);
			
			if (c >= 'A' && c <= 'Z') {
				engCount++;
			} else if (c >= 'a' && c <= 'z') {
				smallCount++;
			} else if (c >= '0' && c <= '9') {
				numCount++;
			}
		}
		
		if ((engCount == 0 && smallCount == 0 && numCount != 0)
			|| (engCount == 0 && smallCount != 0 && numCount == 0)
			|| (engCount != 0 && smallCount == 0 && numCount == 0)
			|| (engCount == 0 && smallCount == 0 && numCount == 0)) {
			
			System.out.println("사용할 수 없는 비밀번호입니다.");
			System.out.println("영문 대소문자와 숫자 중 2가지 이상 조합으로 설정해주세요.");
			Start.line();
			
			passwordValid(); //-> 화면 재출력

		} else {
			this.password = password2;
			nameValid();
			return;
		}
		
	}

	private void nameValid() {
		
		Scanner scan = new Scanner(System.in);
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("이름은 2~5자 한글만 입력해주세요.");
		System.out.println();
		System.out.print("이름 : ");
		String name2 = scan.nextLine();
		boolean flag = false;
		
		//H : 시작 화면으로
		if (name2.toUpperCase().equals("H")) {		
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			flag = true;
			return;
		}
		
		if (!flag) {
			if (name2.length() < 2 || name2.length() > 5) {
				System.out.println();
				System.out.println("이름은 2~5자로 입력해주세요.");
				Start.pause();
				nameValid();
				flag = true;
			}

			for (int i=0; i<name2.length(); i++) {
				char c = name2.charAt(i);
				
				
				if (c >= '가' && c <= '힣') {

					//이름 조건에 맞을 경우
					this.name = name2;
					genderValid();
					flag = true;
					break;
				
				} else {
					
					//한글이 아닐 경우
					System.out.println();
					System.out.println("올바르지 않은 이름입니다.");
					System.out.println("이름을 확인 후 한글만 사용하여 다시 입력해주세요.");
					Start.pause();
					nameValid();

				}
			}
		}
	}

	private void genderValid() {
		
		Scanner scan = new Scanner(System.in);
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("성별은 해당하는 번호를 입력해주세요.");
		System.out.println();
		System.out.print("1. 남자\t\t\t\t2.여자\n");
		Start.line();
		System.out.print("■ 입력 : ");
		String input = scan.nextLine();
		boolean flag = false;
		
		//H : 시작 화면으로
		if (input.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			flag = true;
			Start start = new Start();
			return;
			
		} else {
			int numNotCount = 0;
			
			for (int i=0; i<input.length(); i++) {
				char c = input.charAt(i);
				
				if (!(c >= '0' && c <= '9')) {
					numNotCount++;
				}
			}
			
			if (numNotCount > 0) {
				System.out.println();
				System.out.println("번호를 확인 후 다시 입력해주세요.");
				Start.pause();
				flag = true;
				genderValid();
			}
		}
		
		if (!flag) {
			int gender2 = Integer.parseInt(input);

			if (gender2 == 1 || gender2 == 2) {
				
				this.gender = gender2;
				birthValid();
				flag = true;
			
			} else {
				System.out.println();
				System.out.println("번호를 확인 후 다시 입력해주세요.");
				Start.pause();
				genderValid();
				flag = true;
			}
		}
	}

	private void birthValid() {
		
		Scanner scan = new Scanner(System.in);
		
		Start.line();
		System.out.println("\t\t\t회원 가입");
		Start.line();
		System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
		System.out.println();
		System.out.println("주민등록번호 앞 6자리(예: 901225)를 입력해주세요.");
		System.out.println();
		System.out.print("생년월일 : ");
		String birth2 = scan.nextLine();
		
		boolean flag = false;
		
		//H : 시작 화면으로
		if (birth2.toUpperCase().equals("H")) {	
			System.out.println();
			System.out.println("시작 화면으로 돌아갑니다.");
			Start.pause();
			Start start = new Start();
			flag = true;
			return;
			
		}
		
		if (birth2.length() != 6) {
			System.out.println();
			System.out.println("생년월일은 6자리만 입력해주세요.");
			Start.pause();
			flag = true;
			birthValid();
		}
		
		int count = 0;
		
		for (int i=0; i<birth2.length(); i++) {
			char c = birth2.charAt(i);
			
			if (c >= '0' && c <= '9') {
				count++;
			}
		}
		
		if (count != 6) {
			System.out.println();
			System.out.println("숫자만 사용하여 다시 입력해주세요.");
			flag = true;
			birthValid();
			
		} else if (count == 6) {
			
			flag = false;
		}
		
		if (flag == false) {
			
			if (birth2.substring(2, 4).equals("01") 
				|| birth2.substring(2, 4).equals("02")
				|| birth2.substring(2, 4).equals("03")
				|| birth2.substring(2, 4).equals("04")
				|| birth2.substring(2, 4).equals("05")
				|| birth2.substring(2, 4).equals("06")
				|| birth2.substring(2, 4).equals("07")
				|| birth2.substring(2, 4).equals("08")
				|| birth2.substring(2, 4).equals("09")
				|| birth2.substring(2, 4).equals("10")
				|| birth2.substring(2, 4).equals("11")
				|| birth2.substring(2, 4).equals("12")
				) {
				
				if (birth2.substring(4, 6).equals("01")
					|| birth2.substring(4, 6).equals("02")
					|| birth2.substring(4, 6).equals("03")
					|| birth2.substring(4, 6).equals("04")
					|| birth2.substring(4, 6).equals("05")
					|| birth2.substring(4, 6).equals("06")
					|| birth2.substring(4, 6).equals("07")
					|| birth2.substring(4, 6).equals("08")
					|| birth2.substring(4, 6).equals("09")
					|| birth2.substring(4, 6).equals("10")
					|| birth2.substring(4, 6).equals("11")
					|| birth2.substring(4, 6).equals("12")
					|| birth2.substring(4, 6).equals("13")
					|| birth2.substring(4, 6).equals("14")
					|| birth2.substring(4, 6).equals("15")
					|| birth2.substring(4, 6).equals("16")
					|| birth2.substring(4, 6).equals("17")
					|| birth2.substring(4, 6).equals("18")
					|| birth2.substring(4, 6).equals("19")
					|| birth2.substring(4, 6).equals("20")
					|| birth2.substring(4, 6).equals("21")
					|| birth2.substring(4, 6).equals("22")
					|| birth2.substring(4, 6).equals("23")
					|| birth2.substring(4, 6).equals("24")
					|| birth2.substring(4, 6).equals("25")
					|| birth2.substring(4, 6).equals("26")
					|| birth2.substring(4, 6).equals("27")
					|| birth2.substring(4, 6).equals("28")
					|| birth2.substring(4, 6).equals("29")
					|| birth2.substring(4, 6).equals("30")
					|| birth2.substring(4, 6).equals("31")) {
					
					this.birth = birth2;
					nicknameValid();
					
				} else {
					System.out.println();
					System.out.println("날짜가 잘못 입력되었습니다.");
					System.out.println("1일부터 31일까지 입력 가능합니다.");
					System.out.println("확인 후 다시 입력해주세요.");
					Start.pause();
					birthValid();
					return;
				}
			} else {
				System.out.println();
				System.out.println("날짜가 잘못 입력되었습니다.");
				System.out.println("1월부터 12월까지 입력 가능합니다.");
				System.out.println("확인 후 다시 입력해주세요.");
				Start.pause();
				birthValid();
				return;
			}
		}
	}

	private void nicknameValid() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.BANWORD));
			BufferedReader memberReader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
			Scanner scan = new Scanner(System.in);
			String line = null;
			
			Start.line();
			System.out.println("\t\t\t회원 가입");
			Start.line();
			System.out.printf("%53s\n","H : 시작 화면으로 돌아가기");
			System.out.println();
			System.out.println("닉네임은 2~8자 영문 소문자나 한글을 사용하여 입력해주세요.");
			System.out.println();
			System.out.print("닉네임 : ");
			String nickname2 = scan.nextLine();
			
			boolean flag = false;
			
			//H : 시작 화면으로
			if (nickname2.toUpperCase().equals("H")) {
				System.out.println();
				System.out.println("시작 화면으로 돌아갑니다.");
				Start.pause();
				Start start = new Start();
				return;
			}
			
			while ((line = reader.readLine()) != null) {
				String[] list = line.split("■");
				
				if (nickname2.indexOf(list[1]) > -1) {
					
					System.out.println();
					System.out.printf("[%s]는 금지어로 닉네임에 사용할 수 없습니다.\n", list[1]);
					System.out.println("다시 입력해주세요.");
					Start.pause();
					nicknameValid();
					break;
				}
			}
			
			int memberCount = 0;
			while ((memberReader.readLine()) != null) {
				memberCount++;
			}
			
			this.memberNum = memberCount;
		
			if (nickname2.length() < 2 || nickname2.length() > 8) {
				System.out.println();
				System.out.println("닉네임은 2~8자로 입력해주세요.");
				Start.pause();
				nicknameValid();
			} else {
				
			}
			
			int engCount = 0;
			int korCount = 0;
			
			for (int i=0; i<nickname2.length(); i++) {
				char c = nickname2.charAt(i);
				
				if (c >= '가' && c <= '힣') {
					korCount++;
				}
				if (c >= 'a' && c <= 'z') {
					engCount++;
				}
			}
			
			if (nickname2.length() != engCount+korCount) {

				System.out.println();
				System.out.println("사용할 수 없는 닉네임입니다.");
				Start.pause();
				nicknameValid();
				
			} else if (nickname2.length() == engCount+korCount) {
				
				this.nickname = nickname2;
				Calendar now = Calendar.getInstance();
				
				//{phoneNumber=01012345678, validNumber=483412, id=dkssud12, password=dkssudgktp12, name=안녕, gender=1, birth=920811, nickname=안녕, scan=java.util.Scanner[delimiters=\p{javaWhitespace}+][position=31][match valid=true][need input=false][source closed=false][skipped=false][group separator=\x{2c}][decimal separator=\x{2e}][positive prefix=][negative prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E][infinity string=\Q∞\E]}

				writer.write(String.format("%07d■%s■%d■%"
						+ "6s■%s■%06d■%tF■■%s■%s■%s■%s\r\n"
							, this.memberNum+1
							, this.name
							, this.gender
							, this.birth
							, this.phoneNumber
							, this.validNumber
							, now
							, this.id
							, this.password
							, this.nickname
							, "일반"));
				
				System.out.println();
				System.out.println("중고 가든 회원 가입이 완료되었습니다.");
				System.out.println("시작 화면으로 돌아갑니다.");

				flag = true;
				
				Start.pause();		
				
			}
			
			reader.close();
			writer.close();
			Start start = new Start();
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<String > memberData() {
		
		ArrayList<String> memberData2 = new ArrayList<String>();
	
		int lineNumber = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			String line = null;
			boolean flag = true;
			
			while (flag) {
				
				if ((line = reader.readLine()) == null) {
					flag = false;
				}
				
				memberData2.add(line);
				lineNumber++;
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberData2;
	}
	
	
	@Override
	public String toString() {
		return "{phoneNumber=" + phoneNumber + ", validNumber=" + validNumber + ", id=" + id + ", password=" + password
				+ ", name=" + name + ", gender=" + gender + ", birth=" + birth + ", nickname=" + nickname + ", scan="
				+ scan + "}";
	}
	
	
	
} //class
