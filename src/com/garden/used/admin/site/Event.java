package com.garden.used.admin.site;

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

import com.garden.used.admin.member.InPut;
import com.garden.used.data.Data;

public class Event {
	static File file = new File(Data.EVENT);

	public static void Event() throws IOException {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();// 
			String state= "";
			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
			
			
			
			System.out.println();
			System.out.println();
			System.out.println("===============================================================================");
			System.out.println("                                         이벤트");
			System.out.println("===============================================================================");
			System.out.printf("%-10s%-12s%-12s%-12s%-10s%s \n", "[글번호]", "[시작날짜]", "[종료날짜]", "[등록일]","[진행상태]", "[제목]");
			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				String[] birth = each_Info[3].split("-");// 종료일
				int year = Integer.parseInt(birth[0]);
				int month = Integer.parseInt(birth[1]);
				int date = Integer.parseInt(birth[2]);
				c2.set(year,month-1,date);// 종료일 설정하기
				if(c2.compareTo(c1)==1) {// 종료일 > 금일 => 1 
					state = "진행중";
				}else {
					state = "이벤트종료";
				}
				
				System.out.printf("%-10s%-14s%-14s%-14s%-10s%s\n", each_Info[0]
						                                    , each_Info[2]
						                                    , each_Info[3]
						                                    , each_Info[4]
						                                    , state
						                                    , each_Info[1]);
			}
			System.out.println("===============================================================================");
			System.out.println(
					"    1.상세조회   2.등록      3수정       4 삭제       0.뒤로가기      H.홈으로                                                         ");
			System.out.println("===============================================================================");

			InputSite i = new InputSite();
			i.InputEvent();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 이벤트 리스트

	public static void DetailSearch(String PostNum, int a) throws IOException { // -------상세조회

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String state= "";
			String temp = ""; // 행당되는 회원정보 넣기
			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = Post.get(i);
					String[] birth = each_Info[3].split("-");
					
					int year = Integer.parseInt(birth[0]);
					int month = Integer.parseInt(birth[1]);
					int date = Integer.parseInt(birth[2]);
					c2.set(year,month-1,date);
					if(c2.compareTo(c1)==1) {
						state = "진행중";
					}else {
						state = "이벤트종료";
					}
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
			for (int i = 0; i < each_Info[5].length(); i++) { // 내용 줄바꿔주기

				String[] words = each_Info[5].split("");
				temp1 += words[i];
				if (count == 20) {
					temp1 += "\n" + "          ";
					count = 0;
				}
				count++;
			}

			System.out.println();
			System.out.println();
			System.out.println("===============================================================================");
			System.out.println("                                    이벤트 정보");
			System.out.println("===============================================================================");
			System.out.printf("%-10s%s\n", "[글번호]", each_Info[0]);
			System.out.printf("%-10s%s\n", "[제목]", each_Info[1]);
			System.out.printf("%-10s%s\n", "[등록일]", each_Info[4]);
			System.out.printf("%-10s%s\n", "[진행상태]", state);
			System.out.printf("%-10s   %s  ~  %s\n", "[날짜]", each_Info[3], each_Info[4]);
			System.out.printf("%-10s%s\n", "[내용]", temp1);
			System.out.println("===============================================================================");
			System.out.println(
					"    1.수정   2.삭제    0.뒤로가기    H.홈으로                                                         ");
			System.out.println("===============================================================================");
			InputSite i = new InputSite();
			i.DtailEvent(PostNum);
			
//			String input = read.readLine();
//			if(input.equals("1")||input.equals("수정")) {
//				EditEvent(PostNum);
//			}else if(input.equals("2")||input.equals("삭제")){
//				
//			}else if(input.equals("0")||input.equals("뒤로가기")){
//				
//			}else if(input.toUpperCase().equals("h")||input.equals("홈으로")){
//				
//			}else {
//				
//			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 상세조회

	public static void EnrollEvent() throws IOException { // ------이벤트 등록

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			
			// 등록날짜
			String nowDate = c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) + 1) + "-" + c1.get(Calendar.DATE);

			ArrayList<String> Post = new ArrayList<String>();

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
			String temp = "";
			int count = Post.size();
			if (count == Post.size()) {
				if (Post.size() < 100) {
					temp = "000000" + count;
				} else if (Post.size() < 1000) {
					temp = "00000" + count;
				}

			}
			Integer.parseInt(temp);
			for (int i = 0; i < Post.size(); i++) {

			}

			String[] FindMax = Post.get(Post.size()-1).split("■");//마지막 등록번호
			
			//마지막 등록번호(String -> int+1-> String)
			String max =Integer.toString((Integer.parseInt(FindMax[0].substring(5, 8))+1));
			String max2 = "000000"+max;
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			System.out.println();
			System.out.println();
			System.out.println("===============================================================================");
			System.out.println("                                이벤트 등록");
			System.out.println("===============================================================================");
			System.out.println("[글번호]         " + max2);
			System.out.print("[제목]          :");
			String title = read.readLine();
			System.out.print("[시작날짜]        :");
			String start = read.readLine();
			System.out.print("[종료날짜]        :");
			String end = read.readLine();
			System.out.printf("[내용]           :");
			String contents = read.readLine();
			System.out.println("=================================================================");
			System.out.println(" 최종 등록 하시겠습니까 ?       Y / N                                               ");
			System.out.println("=================================================================");
			System.out.print("■Y/N: ");
			String YN = read.readLine(); // Y / N
			if (YN.toUpperCase().equals("Y")) {
				
				bw.write(max2);
				bw.write("■");
				bw.write(title);
				bw.write("■");
				bw.write(start);
				bw.write("■");
				bw.write(end);
				bw.write("■");
				bw.write(nowDate);// 임시 데이터
				bw.write("■");
				bw.write(contents);
				bw.close();

				Event();
			} else if (YN.toUpperCase().equals("N")) {
				System.out.println("취소되었습니다.");
				pause("0", 1);
			}else {
				System.out.println("잘못누르셨습니다. 뒤로갑니다.");
				pause("0", 1);
			}

			br.close();
			br1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 상세조회

	public static void EditEvent(String PostNum) throws IOException {// ---------------이벤트 수정
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
			for (int i = 0; i < each_Info[5].length(); i++) { // 내용 줄바꿔주기

				String[] words = each_Info[5].split("");
				temp1 += words[i];
				if (count == 20) {
					temp1 += "\n" + "          ";
					count = 0;
				}
				count++;
			}
			String state = "";
			String[] birth = each_Info[3].split("-");// 종료일
			int year = Integer.parseInt(birth[0]);
			int month = Integer.parseInt(birth[1]);
			int date = Integer.parseInt(birth[2]);
			c2.set(year,month-1,date);// 종료일 설정하기
			if(c2.compareTo(c1)==1) {// 종료일 > 금일 => 1 
				state = "[진행중]";
			}else {
				state = "[이벤트 종료]";
			}
			
			System.out.println();
			System.out.println();
			System.out.println("===============================================================================");
			System.out.println("                                이벤트 수정");
			System.out.println("===============================================================================");
			System.out.printf("%-10s%s\n", "[글번호]", each_Info[0]);
			System.out.printf("%-10s%s\n", "[제목]", each_Info[1]);
			System.out.printf("%-10s%s\n", "[등록일]", each_Info[4]);
			System.out.printf("%-10s%s\n", "[진행상태]", state);
			System.out.printf("%-10s   %s  ~  %s\n", "[진행날짜]", each_Info[2], each_Info[3]);
			System.out.printf("%-10s%s\n", "[내용]", temp1);
			System.out.println("=================================================================");
			System.out.println(
					"    1.제목   2.시작날짜  3.종료날짜  4.내용   0.뒤로가기   H.홈으로                                                         ");
			System.out.println("=================================================================");
			br.close();
			br1.close();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			InputSite i = new InputSite();
			System.out.println("[수정할 번호를 입력해주세요]");
			System.out.print("■입력: ");
			String input = read.readLine();
			i.EditInputEvent(input, PostNum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 이벤트 수정

	public static void EditEvent(String PostNum, int each_Num, String same) throws IOException {// 진짜로 수정하는 메서드
		try { // (게시판 정보를 불러오기위한 , 정보위치 , 수정할)
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();
			ArrayList<String> RPost = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기

			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(PostNum)) {
					each_Info = Post.get(i).split("■");
					each_Info[each_Num] = same;
					temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2] + "■" + each_Info[3] + "■"
							+ each_Info[4] + "■" + each_Info[5];
					RPost.add(temp);
					i++;
				}
				RPost.add(Post.get(i));
			}


			br.close();
			br1.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < RPost.size(); i++) {
				String a = RPost.get(i);
				bw.write(a);
				bw.newLine();
			}
			bw.close();



			EditEvent(PostNum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 리얼 수정하는 메서드

	public static void DeltEvent(String PostNum) throws IOException { // 이벤트 지우기

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));

			ArrayList<String> Post = new ArrayList<String>();// 멤버파일 불러넣기
			ArrayList<String> RPost = new ArrayList<String>();// 수정된멤버 담아넣기
			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
			
			for (int i = 0; i < Post.size(); i++) {// 삭제할 금지어를 제외하고 넣기
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

			Event();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 이벤트 지우기

	public static void pause(String PostNum, int num) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (num) {
		case 1:// -------------------- 등록 Y/N  화면으로 돌아가기
			Event();
			break;

		case 2:// ---------------------Event 화면으로 돌아가기
		
			Event();
		case 3:// ---------------------Event 화면으로 돌아가기
			
			Event();
		default:
			break;
		}

	} // pause

}
