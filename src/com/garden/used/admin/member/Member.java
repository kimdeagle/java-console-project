package com.garden.used.admin.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import com.garden.used.data.Data;

public class Member {
	static int nowPage = 0;
	static File mainFile = new File(Data.MEMBERINFO);
	static File subFile = new File(Data.ADDITIONALINFO);

//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		// file [0]회원번호[1]이름 [2]성별 [3]생일 [4]휴대폰번호 [5]인증번호 [6]가입일 [7]탈퇴일 [8]아이디 [9] 비밀번호
//		// [10]닉네임
//
//		boolean home = true;// 홈으로(입력분 벗어나기)
//		int page = 0;
//
//		Home();
//
//	}// main;

	private static void input() {// ----------------------------------------------------------------------회원리스트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			if (input.equals("1") || input.equals("검색")) {// 검색
				System.out.println();
				System.out.println("번호를 선택해주세요");
				System.out.println("1.이름   2.휴대폰번호  3.생년월일   4.나이   0.취소 ");
				System.out.print("■입력: ");
				input = br.readLine();
				Find(input);
			} else if (input.equals("2") || input.equals("상세조회")) {// 상세조회
				System.out.print("■[회원번호]: ");
				input = br.readLine();
				SearchDtail(input);
			} else if (input.equals("3") || input.equals("수정")) {// 수정
				System.out.println("[수정할 회원정보를 입력해주세요]");
				System.out.print("■[회원번호]: ");
				input = br.readLine();
				EditMember(input);
			} else if (input.equals("4") || input.equals("삭제")) {// ---------삭제
				System.out.println("[삭제할 회원번호를 입력해주세요]");
				System.out.print("■[회원번호]: ");
				input = br.readLine();
				System.out.println("정말 삭제하시겠습니까? [Y/N]");
				System.out.print("■Y/N: ");
				String YN = br.readLine();
				if (YN.toUpperCase().equals("Y")) {
					DeletMember(input);
				} else {
					System.out.println("취소하셨습니다");
					list(0);
				}
			} else if (input.toUpperCase().equals("P") || input.equals("이전페이지")) {
				list(-1);
			} else if (input.toUpperCase().equals("N") || input.equals("다음페이지")) {
				list(1);

			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				;
				h.Home();
			} else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			pause();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void DeletMember(String IdCode) throws IOException {

		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));// 주정보
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));
			BufferedReader ar = new BufferedReader(new FileReader(subFile));// 부정보
			BufferedReader ar1 = new BufferedReader(new FileReader(subFile));

			ArrayList<String> mainInfo = new ArrayList<String>();// 주정보 배열
			ArrayList<String> subInfo = new ArrayList<String>();// 부정보 배열

			while (br.readLine() != null) {// 주정보 넣기
				mainInfo.add(br1.readLine());
			}
			while (ar.readLine() != null) {// 부정보 넣기
				subInfo.add(ar1.readLine());
			}
			

			for (int i = 0; i < mainInfo.size(); i++) {
				String[] each_Info = mainInfo.get(i).split("■");
				if (each_Info[0].equals(IdCode)) {
					mainInfo.remove(i);
				}
			}
			for (int i = 0; i < subInfo.size(); i++) {
				String[] each_Info = subInfo.get(i).split("■");
				if (each_Info[8].equals(IdCode)) {
					subInfo.remove(i);
				}
			}
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(mainFile));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(subFile));

			for (int i = 0; i < mainInfo.size(); i++) {
				bw.write(mainInfo.get(i));
				bw.newLine();
			}
			bw.close();
			for (int i = 0; i < subInfo.size(); i++) {
				bw1.write(subInfo.get(i));
				bw1.newLine();
			}
			bw1.close();

			br.close();
			br1.close();
			ar.close();
			ar1.close();

			list(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void EditMember(String IdCode) throws IOException {// ----------------------------------------------------------수정
		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));// 주정보
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));
			BufferedReader ar = new BufferedReader(new FileReader(subFile));// 부정보
			BufferedReader ar1 = new BufferedReader(new FileReader(subFile));

			ArrayList<String> mainInfo = new ArrayList<String>();// 주정보 배열
			ArrayList<String> subInfo = new ArrayList<String>();// 부정보 배열
			ArrayList<String> main = new ArrayList<String>();// 주정보 부정보 합치기

			while (br.readLine() != null) {// 주정보 넣기
				mainInfo.add(br1.readLine());
			}
			while (ar.readLine() != null) {// 부정보 넣기
				subInfo.add(ar1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기
			for (int i = 0; i < mainInfo.size(); i++) {

				if (mainInfo.get(i).substring(0, 7).equals(IdCode)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = mainInfo.get(i);
					break;
				} else if (i == mainInfo.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					pause();
				}
			}

			String[] main_Info = temp.split("■"); // 각각의 회원정보 split으로 잘라 넣을 배열
			String[] sub_Info = null;
			for (int i = 0; i < subInfo.size(); i++) {
				sub_Info = subInfo.get(i).split("■");
				if (main_Info[0].equals(sub_Info[8])) {
					sub_Info = subInfo.get(i).split("■");
					break;
				}

			}

			System.out.println();
			System.out.println();
			System.out.println(
					"====================================================================================================");
			System.out.println("                                    회원정보수정");
			System.out.println(
					"====================================================================================================");
			System.out.printf("아이디:%s \n", main_Info[8]);
			System.out.printf("닉네임:%s \n", main_Info[10]);
			System.out.printf("비밀번호:%s \n", main_Info[9]);
			System.out.printf("이름:%s \n", main_Info[1]);
			System.out.printf("상태정보:%s \n", main_Info[11]);
			System.out.printf("등급:%s \n", sub_Info[5]);
			System.out.printf("휴대폰 번호:%s \n", phone_H(main_Info[4]));
			System.out.printf("생년월일:%s\n", main_Info[3]);
			System.out.printf("나이: %s\n", age_calc(main_Info[3]));
			System.out.printf("가입일:%s\n", main_Info[6]);
			System.out.println(
					"====================================================================================================");
			System.out.println(
					"1.아이디   2.닉네임   3.비밀번호   4.이름   5.등급   6.휴대번호   7.생년월일   8.가입일자  9.상태정보   H.홈으로   0.뒤로가기");
			System.out.println(
					"====================================================================================================");
			Editinput(IdCode);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// EditMember

	private static void Editinput(String Idcode) throws IOException {// --------------------------------------------------------------------------------수정입력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("■입력: ");
		String input = br.readLine();
		if (input.equals("1")) {
			System.out.print("■[아이디]:");
			input = br.readLine();
			EditMember(8, input, Idcode);
		} else if (input.equals("2")) {
			System.out.print("■[닉네임]:");
			input = br.readLine();
			EditMember(10, input, Idcode);
		} else if (input.equals("3")) {
			System.out.print("■[비밀번호]:");
			input = br.readLine();
			EditMember(9, input, Idcode);
		} else if (input.equals("4")) {
			System.out.print("■[이름]:");
			input = br.readLine();
			EditMember(1, input, Idcode);
		} else if (input.equals("5")) {
			System.out.println("[씨앗] [새싹] [풀] [꽃] [나무]");
			System.out.print("■[등급]:");
			input = br.readLine();
			EditMember(5, input, Idcode);
		} else if (input.equals("6")) {
			System.out.println("['-'없이 적어주세요]");
			System.out.print("■[휴대번호]:");
			input = br.readLine();
			EditMember(4, input, Idcode);
		} else if (input.equals("7")) {
			System.out.print("■[생년월일]:");
			input = br.readLine();
			EditMember(3, input, Idcode);
		} else if (input.equals("8")) {
			System.out.print("■[가입일자]:");
			input = br.readLine();
			EditMember(6, input, Idcode);
		} else if (input.equals("9")) {
			System.out.println("[일반] [블랙] [관리자] [휴면]");
			System.out.print("■[회원상태]:");
			input = br.readLine();
			EditMember(11, input, Idcode);
		} else if (input.equals("h") || input.equals("H")) {
			Home h = new Home();
			h.Home();
		} else if (input.equals("0")) {
			int page = 0;
			list(page);
		}

	}

	private static void EditMember(int num, String same, String IdCode) throws IOException {// ---------------------------진짜
																							// 수정하기 (비교값, 입력값)
		if (num == 5) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(subFile));
				BufferedReader br1 = new BufferedReader(new FileReader(subFile));

				ArrayList<String> member = new ArrayList<String>(); // 수정할 데이터를 찾기위한 배열
				ArrayList<String> rearM = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열

				System.out.println();

				String temp = "";
				while (br.readLine() != null) {
					member.add(br1.readLine());
				}

				for (int i = 0; i < member.size(); i++) {
					String[] each_Info = member.get(i).split("■");
					if (each_Info[0].equals(IdCode)) {
						each_Info = member.get(i).split("■");
						each_Info[num] = same;
						temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2] + "■" + each_Info[3] + "■"
								+ each_Info[4] + "■" + each_Info[5] + "■" + each_Info[6] + "■" + each_Info[7] + "■"
								+ each_Info[8];
						rearM.add(temp);
						i++;
					}

					rearM.add(member.get(i));
				}
//				System.out.println(rearM.get(999));
				br.close();
				br1.close();

				BufferedWriter bw = new BufferedWriter(new FileWriter(subFile));
				for (int i = 0; i < rearM.size(); i++) {
					String a = rearM.get(i);
					bw.write(a);
					bw.newLine();
				}
				System.out.println("");

				bw.close();

				EditMember(IdCode);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(mainFile));
				BufferedReader br1 = new BufferedReader(new FileReader(mainFile));

				ArrayList<String> member = new ArrayList<String>(); // 수정할 데이터를 찾기위한 배열
				ArrayList<String> rearM = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열

				System.out.println();
//			System.out.print("입력받은 회원번호");
//			System.out.println(IdCode);

				String temp = "";
				while (br.readLine() != null) {
					member.add(br1.readLine());
				}

				for (int i = 0; i < member.size(); i++) {
					String[] each_Info = member.get(i).split("■");
					if (each_Info[0].equals(IdCode)) {
						each_Info = member.get(i).split("■");
						each_Info[num] = same;
						temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2] + "■" + each_Info[3] + "■"
								+ each_Info[4] + "■" + each_Info[5] + "■" + each_Info[6] + "■" + each_Info[7] + "■"
								+ each_Info[8] + "■" + each_Info[9] + "■" + each_Info[10] + "■" + each_Info[11];
						rearM.add(temp);
						i++;
					}

					rearM.add(member.get(i));
				}

				br.close();
				br1.close();

				BufferedWriter bw = new BufferedWriter(new FileWriter(mainFile));
				for (int i = 0; i < rearM.size(); i++) {
					String a = rearM.get(i);
					bw.write(a);
					bw.newLine();
				}
				System.out.println("");

				bw.close();

				EditMember(IdCode);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}// EditMember(int, string,IdCode)

	private static String Find(String input) throws IOException {// 검색 리스트에서 해당하는 회원정보 리스트만 불러오기
		try {

			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			switch (input) {
			case "1":
				System.out.print("■[이름]: ");
				String same = read.readLine();
				sameFind(1, same);
				break;

			case "2":
				System.out.println("['-'없이 적어주세요]");
				System.out.print("■[휴대폰번호]: ");
				same = read.readLine();
				sameFind(4, same);
				break;

			case "3":
				System.out.println("생년월일 여섯자를 적어주세요");
				System.out.print("■[생년월일]: ");
				same = read.readLine();
				sameFind(3, same);
				break;

			case "4":

				System.out.print("■[나이]: ");
				same = read.readLine();
				sameFind(6, same);
			case "0":
				System.out.print("■[취소]: ");
				int page = 0;
				list(page);
				break;

			default:
				System.out.println("일치하는 번호가 없습니다.");
				pause();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static void sameFind(int num, String same) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));// 주정보
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));
			BufferedReader ar = new BufferedReader(new FileReader(subFile));// 부정보
			BufferedReader ar1 = new BufferedReader(new FileReader(subFile));

			ArrayList<String> mainInfo = new ArrayList<String>();// 주정보 배열
			ArrayList<String> subInfo = new ArrayList<String>();// 부정보 배열
			ArrayList<String> main = new ArrayList<String>();// 주정보 부정보 합치기

			while (br.readLine() != null) {// 주정보 넣기
				mainInfo.add(br1.readLine());
			}
//			System.out.println(mainInfo.size());
			while (ar.readLine() != null) {// 부정보 넣기
				subInfo.add(ar1.readLine());
			}
//			System.out.println(subInfo.size());
			ArrayList<String> searchMember = new ArrayList<String>();// 일치하는 회원 넣는
//			System.out.println(same);//====입력받은 이름
//			System.out.println(num);// == 입력받은 숫자
			String[] main_Info = null;
			String[] sub_Info = null;
			for (int i = 0; i < mainInfo.size(); i++) {// 일치하는 회원 정보 searchMember에 넣기

				main_Info = mainInfo.get(i).split("■");
//				System.out.println(main_Info[1]);
				if (num == 6) {// 나이 찾아주는 조건문
					if (age_calc(main_Info[3]).equals(same)) {
						searchMember.add(mainInfo.get(i));
					}
				} else if (main_Info[num].equals(same)) {

					searchMember.add(mainInfo.get(i));

				}

			}

			System.out.println();
			System.out.println();
			System.out.println(
					"====================================================================================================");
			System.out.println("                                                    회원정보");
			System.out.println(
					"====================================================================================================");
			System.out.printf("  %-9s%-8s%-10s%-20s%-20s%-10s%-10s\n", "[회원번호]", "[등급]", "[이름]", "[가입일]", "[휴대번호]",
					"[생년월일]", "[나이]");

			for (int i = 0; i < searchMember.size(); i++) {// member에 회원 정보 한 줄씩 넣기 반복문
				main_Info = searchMember.get(i).split("■");
				for (int j = 0; j < subInfo.size(); j++) {
					sub_Info = subInfo.get(j).split("■");
					if (main_Info[0].equals(sub_Info[8])) {
						sub_Info = subInfo.get(j).split("■");
						break;
					}

				}
				System.out.printf("  %-11s%-15s%-15s%-18s%-27s%-12s%-10s", main_Info[0]// 번호
						, sub_Info[5]// 등급
						, main_Info[1]// 이름
						, main_Info[6]// 가입일
						, phone_H(main_Info[4])// 휴대번호
						, main_Info[3]// 생년월일
						, age_calc(main_Info[3]));// 나이
				System.out.println();

			}

			System.out.println(
					"====================================================================================================");
			System.out.println("1.검색     2.상세조회     3.수정     4.삭제     H.홈으로");
			System.out.println(
					"====================================================================================================");
			input();

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void search(String input) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));

			int member_Count = 0;// 회원의 숫자 카운트 변수

			while (br.readLine() != null) {// 회원의 숫자
				member_Count++;
			}

			String[] member = new String[member_Count]; // 회원 정보 한줄 넣는 배열
			ArrayList<String> searchMember = new ArrayList<String>();// 일치하는 회원 넣는
			String[] each_Info = new String[11];// 각각의 회원정보 split으로 잘라 넣을 배열

			for (int i = 0; i < member_Count; i++) {
				member[i] = br1.readLine();
			}

			System.out.println(
					"====================================================================================================");
			System.out.println("                                                                                 회원정보");
			System.out.println(
					"====================================================================================================");
			System.out.printf("  %-9s%-8s%-10s%-20s%-20s%-10s%-10s%-20s\n", "[회원번호]", "[등급]", "[이름]", "[가입일]", "[휴대번호]",
					"[생년월일]", "[나이]", "[주소]");

			for (int i = nowPage; i < nowPage + 10; i++) {// member에 회원 정보 한 줄씩 넣기 반복문
				each_Info = member[i].split("■");

				System.out.printf("  %s\t%s\t%s\t%s\t%s\t%s\t%s", each_Info[0], each_Info[1], each_Info[10],
						phone_H(each_Info[4]), each_Info[3], age_calc(each_Info[3]), each_Info[6]);
				System.out.println();

			}

			System.out.println(
					"====================================================================================================");
			System.out.println("1.검색	2.상세조회	3.수정	4.삭제	P.이전페이지	N.다음페이지	H.홈으로");
			System.out.println(
					"====================================================================================================");
			input();

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// search

	private static void SearchDtail(String input) throws IOException {// -------------------------------------------상세정보조회
		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));// 주정보
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));
			BufferedReader ar = new BufferedReader(new FileReader(subFile));// 부정보
			BufferedReader ar1 = new BufferedReader(new FileReader(subFile));

			ArrayList<String> mainInfo = new ArrayList<String>();// 주정보 배열
			ArrayList<String> subInfo = new ArrayList<String>();// 부정보 배열
			ArrayList<String> main = new ArrayList<String>();// 주정보 부정보 합치기

			while (br.readLine() != null) {// 주정보 넣기
				mainInfo.add(br1.readLine());
			}
			while (ar.readLine() != null) {// 부정보 넣기
				subInfo.add(ar1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기
			for (int i = 0; i < mainInfo.size(); i++) {

				if (mainInfo.get(i).substring(0, 7).equals(input)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = mainInfo.get(i);
					break;
				} else if (i == mainInfo.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					pause();
				}
			}

			String[] main_Info = temp.split("■"); // 각각의 회원정보 split으로 잘라 넣을 배열
			String[] sub_Info = null;
			for (int i = 0; i < subInfo.size(); i++) {
				sub_Info = subInfo.get(i).split("■");
				if (main_Info[0].equals(sub_Info[8])) {
					sub_Info = subInfo.get(i).split("■");
					break;
				}

			}
			System.out.println();
			System.out.println();
			System.out.println(
					"====================================================================================================");
			System.out.println("                                    회원상세정보");
			System.out.println(
					"====================================================================================================");
			System.out.printf("아이디:%s \n", main_Info[8]);
			System.out.printf("닉네임:%s \n", main_Info[10]);
			System.out.printf("비밀번호:%s \n", main_Info[9]);
			System.out.printf("이름:%s \n", main_Info[1]);
			System.out.printf("상태정보:%s \n", main_Info[11]);
			System.out.printf("등급:%s \n", sub_Info[5]);
			System.out.printf("휴대폰 번호:%s \n", phone_H(main_Info[4]));
			System.out.printf("생년월일:%s\n", main_Info[3]);
			System.out.printf("나이: %s\n", age_calc(main_Info[3]));
			System.out.printf("가입일:%s\n", main_Info[6]);
			System.out.println(
					"====================================================================================================");
			System.out.println("1.회원정보 수정  2.회원정보 삭제    H.홈으로  0.뒤로가기");
			System.out.println(
					"====================================================================================================");

			inputDetail(input);

			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// DtailSearch

	private static void inputDetail(String Idcode) {// ---------------------------상세조회에서 사용하는 인풋
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			if (input.equals("1")) { // 수정

				EditMember(Idcode);
			} else if (input.equals("2")) {// 삭제
				System.out.println("정말 삭제하시겠습니까? [Y/N]");
				System.out.print("■Y/N: ");
				input = br.readLine();
				if (input.toUpperCase().equals("Y")) {
					DeletMember(Idcode);
				} else {
					System.out.println("취소하셨습니다");
					SearchDtail(Idcode);
				}
//				SearchDtail(input);
			} else if (input.equals("0")) {
				int page = 0;
				list(page);
			} else if (input.equals("H") || input.equals("h")) {
				Home h = new Home();
				h.Home();
			} else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			pause();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void list(int page) { // -----------------------------------------------------------회원 리스트
		try {
			BufferedReader br = new BufferedReader(new FileReader(mainFile));// 주정보
			BufferedReader br1 = new BufferedReader(new FileReader(mainFile));
			BufferedReader ar = new BufferedReader(new FileReader(subFile));// 부정보
			BufferedReader ar1 = new BufferedReader(new FileReader(subFile));

			ArrayList<String> mainInfo = new ArrayList<String>();// 주정보 배열
			ArrayList<String> subInfo = new ArrayList<String>();// 부정보 배열
			ArrayList<String> main = new ArrayList<String>();// 주정보 부정보 합치기

			while (br.readLine() != null) {// 주정보 넣기
				mainInfo.add(br1.readLine());
			}
			while (ar.readLine() != null) {// 부정보 넣기
				subInfo.add(ar1.readLine());
			}
//			for(int i=0; i<mainInfo.size(); i++	) {
//				String[] each_main = mainInfo.get(i).split("■");
//				String[] each_Sub = subInfo.get(i).split("■");
//				if(each_main[0].equals(each_Sub)) {
//					main.add(mainInfo.get(i)+"■"+subInfo.get(i));
//				}
//			}

			System.out.println();
			System.out.println();

			System.out.println(
					"====================================================================================================");
			System.out.println("                                                                      회원정보");
			System.out.println(
					"====================================================================================================");
			System.out.printf("  %-9s%-8s%-10s%-20s%-20s%-10s%-10s\n", "[회원번호]", "[등급]", "[이름]", "[가입일]", "[휴대번호]",
					"[생년월일]", "[나이]");

			int nowPage = nowPage(page);
			if (nowPage < 0) {
				nowPage = 0;
			} else if (nowPage > mainInfo.size() / 10) {
				nowPage = mainInfo.size() / 10;
			}
			String[] sub_Info = null;
			for (int i = nowPage; i < nowPage + 10; i++) {// 페이지당 10명의 회원을 출력하는 반복문
				String[] main_Info = mainInfo.get(i).split("■");
				for (int j = 0; j < subInfo.size(); j++) {
					sub_Info = subInfo.get(j).split("■");

					if (main_Info[0].equals(sub_Info[8])) {
						sub_Info = subInfo.get(j).split("■");
						break;
					}
				}

				System.out.printf("  %-11s%-15s%-15s%-18s%-27s%-12s%-10s", main_Info[0]// 번호
						, sub_Info[5]// 등급
						, main_Info[1]// 이름
						, main_Info[6]// 가입일
						, phone_H(main_Info[4])// 휴대번호
						, main_Info[3]// 생년월일
						, age_calc(main_Info[3]));// 나이
				System.out.println();

			}

			System.out.println(
					"====================================================================================================");
			System.out.println("1.검색     2.상세조회     3.수정     4.삭제     P.이전페이지     N.다음페이지     H.홈으로");
			System.out.println(
					"====================================================================================================");
			input();

			br.close();
			br1.close();
			ar.close();
			ar1.close();
		} catch (Exception e) {
//			Home();

//			list(0);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// list()

	private static Object phone_H(String phoneNum) { // 폰 하이픈 추가

//		StringBuffer origin = new StringBuffer(phoneNum);

		String temp = ""; // 수정된 핸드폰 번호 넣기
		if (phoneNum.length() == 11) {
			temp = phoneNum.substring(0, 3) + "-" + phoneNum.substring(3, 7) + "-" + phoneNum.substring(7, 11);
//		origin.insert(3, "-");
//		origin.insert(8, "-");
		} else {
			temp = null;
		}

		return temp;
	}

	private static Object age_calc(String string) { // 나이 출력 메소드
		String ageString = "";
		if (string.equals("null")) {

			ageString = null;
		} else {

			String c = string.substring(0, 2);
			int changeInt = Integer.parseInt(c);
			String a = "";
			if (changeInt > 10) {
				a = "19";
			} else {
				a = "20";
			}

			String str = string; // ex) 801011 (생년월일)
			String add = a.concat(str);// a+str = 19801011

			String birth_Year = add.substring(0, 4); // 1980

			int birth_int = Integer.parseInt(birth_Year); // 문자형->정수형

			Calendar c1 = Calendar.getInstance();
			int now_Year = c1.get(Calendar.YEAR);// 현재 년도

			int age = now_Year - birth_int + 1;

			ageString = age + "";
		}

		return ageString;
	}

	private static void pause() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int page = 0;
		list(page);
	} // pause

	private static void pauseHome() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Home h = new Home();
		h.Home();
	} // pause

	private static int nowPage(int page) {// 페이지 넘기기

		if (page == 1) {
			nowPage += 10;
		} else if (page == -1) {
			nowPage -= 10;
		} else if (page == 0) {
			nowPage = 0;
		}
		return nowPage;
	}
}