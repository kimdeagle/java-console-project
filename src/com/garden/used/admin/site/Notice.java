package com.garden.used.admin.site;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;

import com.garden.used.admin.member.Home;
import com.garden.used.data.Data;

public class Notice {
	
	static File file = new File(Data.NOTICE);

	public static void Notice() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));

			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
			System.out.println();
			System.out.println();
			System.out.println("=================================================================");
			System.out.println("                   공지사항");
			System.out.println("=================================================================");
			System.out.printf("%-10s%-20s%-12s\n", "[글번호]", "[등록일]", "[제목]");
			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");

				System.out.printf("%-14s%-14s%-14s\n", each_Info[0], each_Info[1], each_Info[2]);
			}
			System.out.println("=================================================================");
			System.out.println(
					"    1.상세조회   2.등록      3.수정       4.삭제    0.뒤로가기   H.홈으로                                                         ");
			System.out.println("=================================================================");
			br.close();
			br1.close();

			InputSite i = new InputSite();
			i.InputNotic();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 공지사항 메인 화면

	public static void DetailSearch(String PostNum, int a) throws IOException { // -------상세조회

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기
			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {
					temp = Post.get(i);
					break;
				} else if (i == Post.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					int num = 2;
					pause(PostNum, num);
				}
			}
			String[] each_Info = temp.split("■");

			String temp1 = "";// 바꾼 줄을 넣기위해 ----시간남으면 하기
			int count = 0;

			for (int i = 0; i < each_Info[3].length(); i++) { // 내용 줄바꿔주기

				String[] words = each_Info[3].split("");
				temp1 += words[i];
				if (count == 20) {
					temp1 += "\n" + "          ";
					count = 0;
				}
				count++;
			}

			System.out.println();
			System.out.println();
			System.out.println("=================================================================");
			System.out.println("                                공지사항 ");
			System.out.println("=================================================================");
			System.out.printf("%-10s%s\n", "[글번호]", each_Info[0]);
			System.out.printf("%-10s%s\n", "[등록일]", each_Info[1]);
			System.out.printf("%-10s%s\n", "[제목]", each_Info[2]);
			System.out.printf("%-5s%s\n", "[내용]", temp1);
			System.out.println("=================================================================");
			System.out.println(
					"    1.수정   2.삭제    0.뒤로가기    H.홈으로                                                         ");
			System.out.println("=================================================================");

			InputSite i = new InputSite();
			i.DtailNotice(PostNum);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			int num = 1;
			pause(PostNum, num);
		}

	}// 상세조회

	public static void EnrollNotic() throws IOException {// -------공지사항 등록

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

			String nowDate = c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) + 1) + "-" + c1.get(Calendar.DATE);

			ArrayList<String> Post = new ArrayList<String>();
			int[] postCount = new int[1000];
			int nums = 0;
//			for(int i=0; i<postCount.length; i++) {
//				postCount[i] = 
//			}

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
//			String temp = "";
//			int count = Post.size();// 공지사항 게시글 수
//			if (count == Post.size()) {
//				if (Post.size() < 100) {//10단위
//					temp = "000000" + (count+1);
//				} else if (Post.size() < 1000) { //100단위
//					temp = "00000" + count;
//				} else if (Post.size() < 10000) {
//					temp = "0000" + count;
//				}
//
//			}
//			Integer.parseInt(temp);
			String[] FindMax = Post.get(Post.size() - 1).split("■");// 마지막 등록번호
			// 마지막 등록번호(String -> int+1-> String)
			String max = Integer.toString((Integer.parseInt(FindMax[0].substring(5, 8)) + 1));
			String max2 = "000000" + max;

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			System.out.println();
			System.out.println();
			System.out.println("=================================================================");
			System.out.println("                                공지사항 등록");
			System.out.println("=================================================================");
			System.out.println("[글번호]\t:" + max2);
			System.out.printf("[등록일]\t:%tF\n", c1);
			System.out.print("[제목] \t:");
			String title = read.readLine();
			System.out.printf("[내용] \t:");
			String contents = read.readLine();
			System.out.println("=================================================================");
			System.out.println(" 최종 등록 하시겠습니까 ?       Y / N                                               ");
			System.out.println("=================================================================");
			System.out.print("■입력: ");
			String YN = read.readLine(); // Y / N
			if (YN.toUpperCase().equals("Y")) {
				
				bw.write(max2);// 글번호
				bw.write("■");
				bw.write(nowDate);// 등록날짜
				bw.write("■");
				bw.write(title);// 제목
				bw.write("■");
				bw.write(contents);// 내용 쓰기
				bw.close();
				System.out.println("[등록에 성공하셨습니다].");
				pause("0", 1);
				Notice();

			} else if (YN.toUpperCase().equals("N")) {
				int num = 1;
				String PostNum = "0";
				pause(PostNum, num);
			} else {
				System.out.println("해당하지 않는 버튼입니다.");
				int num = 2;
				String PostNum = "0";
				pause(PostNum, num);
			}

			br.close();
			br1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 공지사항 등록

	public static void EditNotice(String PostNum) throws IOException { // ------------------------공지사항 수정
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기
			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = Post.get(i);
					break;
				} else if (i == Post.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					System.out.println(PostNum);
					int num = 2;
					pause(PostNum, num);
				}
			}
			String[] each_Info = temp.split("■");

			String temp1 = "";// 바꾼 줄을 넣기위해 ----시간남으면 하기
			int count = 0;
			for (int i = 0; i < each_Info[3].length(); i++) { // 내용 줄바꿔주기

				String[] words = each_Info[3].split("");
				temp1 += words[i];
				if (count == 20) {
					temp1 += "\n" + "          ";
					count = 0;
				}
				count++;
			}

			System.out.println();
			System.out.println();
			System.out.println("=================================================================");
			System.out.println("                                공지사항 수정");
			System.out.println("=================================================================");
			System.out.printf("%-10s%s\n", "[글번호]", each_Info[0]);
			System.out.printf("%-10s%s\n", "[제목]", each_Info[2]);
			System.out.printf("%-10s%s\n", "[등록일]", each_Info[1]);
			System.out.printf("%s \t%s\n", "[내용]", temp1);
			System.out.println("=================================================================");
			System.out.println(
					"    1.제목   2.내용  0.뒤로가기   H.홈으로                                                         ");
			System.out.println("=================================================================");
			br.close();
			br1.close();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			InputSite i = new InputSite();
			System.out.println("[수정할 번호를 입력해주세요]");
			System.out.print("■입력: ");
			String input = read.readLine();
			i.EditInputNotice(input, PostNum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 공지사항 수정

	public static void EditNotice(String PostNum, int each_Num, String same) throws IOException {// 진짜로 수정하는 메서드
		try { // (게시판 정보를 불러오기위한 , 정보위치 , 수정할)
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();
			ArrayList<String> rearP = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기

			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {
					each_Info = Post.get(i).split("■");
					each_Info[each_Num] = same;
					temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2] + "■" + each_Info[3];
					rearP.add(temp);
					i++;
				}
				rearP.add(Post.get(i));
			}

//			System.out.println();
			br.close();
			br1.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < rearP.size(); i++) {
				String a = rearP.get(i);
				bw.write(a);
				bw.newLine();
			}
			System.out.println("");

			bw.close();

			EditNotice(PostNum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 리얼 수정하는 메서드

	public static void DeltNotice(String PostNum) throws IOException { // 공지사항 지우기

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));

			ArrayList<String> Post = new ArrayList<String>();// 멤버파일 불러넣기
			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			
			for (int i = 0; i < Post.size(); i++) { //특정 라인 삭제
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {
					Post.remove(i);
					break;
				}

			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < Post.size(); i++) {
			
					bw.write(Post.get(i));
					bw.newLine();
			}
			bw.close();

//			for(int i=0; i<Post.size(); i++) {
//				bw.write(RPost.get(i));
//				bw.newLine();
//			}

			br.close();
			br1.close();

			Notice();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 공지사항 지우기

	public static void pause(String PostNum, int num) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (num) {
		case 0:// 수정화면
			EditNotice(PostNum);
		case 1:// 공지사항
			Notice();
			break;

		case 2:// 등록화면
			EnrollNotic();
		case 3:// 홈으로
			Home h = new Home();
			h.Home();
		default:
			Notice();
		}

	} // pause

}
