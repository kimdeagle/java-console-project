package com.garden.used.admin.site;
import com.garden.used.admin.member.Home;
import com.garden.used.data.*;
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

public class QnA {
	static File file = new File(Data.QNA);
	static int nowPage = 0;
	
	
	
	public static void QnA(int page) throws IOException {
		
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedReader br1 = new BufferedReader(new FileReader(file));

				ArrayList<String> BWords = new ArrayList<String>(); // 수정할 데이터를 찾기위한 배열

				while (br.readLine() != null) {
					BWords.add(br1.readLine());
				}

			

				System.out.println();
				System.out.println();
				System.out.println("==============================================================================================");
				System.out.println("                                         Q&A");
				System.out.println("==============================================================================================");
				System.out.printf(" %s    %s    %s    %s    %s\n", "[일련번호]"
						                                   , "[날짜]"
						                                   , "[카테고리]"
						                                   , "[답변상태]"
						                                   , "[제목]");
				
				int nowPage = nowPage(page);
				if (nowPage < 0) {
					nowPage = 0;
				} else if (nowPage > BWords.size() / 10) {
					nowPage = BWords.size() / 10;
				}
				for (int i = nowPage; i < nowPage + 10; i++) {
					String[] each_Info = BWords.get(i).split("■");
					System.out.printf("%s  %s   %s         %s         %s\n", each_Info[0]
							, each_Info[1]
							, each_Info[3]
							, each_Info[2]
							, each_Info[4]);
				}
				System.out.println("==============================================================================================");
				System.out.println("1.검색   2.답변하기  P.이전페이지  N.다음페이지  0.뒤로가기   H.홈으로        ");
				System.out.println("==============================================================================================");
				InputSite i = new InputSite();
				i.InPutQnA();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}//Q&A
	
	
	
	public static void sameFind(int num, String same) {// 리스트 찾아주는 메서드
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));

			ArrayList<String> searchWords = new ArrayList<String>();// 일치하는 회원 넣는
			ArrayList<String> sameWords = new ArrayList<String>();

			while (br.readLine() != null) {// 회원의 숫자
				searchWords.add(br1.readLine());
			}

			for (int i = 0; i < searchWords.size(); i++) {// 일치하는 회원 정보 넣기
				String[] each_Info = searchWords.get(i).split("■");
				if (each_Info[num].equals(same)) {
					sameWords.add(searchWords.get(i));
				}
			}

			System.out.println();
			System.out.println();
			System.out.println("==============================================================================================");
			System.out.println("                                                        Q&A");
			System.out.println("==============================================================================================");
			System.out.printf(" %s    %s    %s    %s    %s\n", "[일련번호]"
					                                   , "[날짜]"
					                                   , "[카테고리]"
					                                   , "[답변상태]"
					                                   , "[제목]\n");
			for (int i = 0; i < sameWords.size(); i++) {
				String[] each_Info = sameWords.get(i).split("■");
				System.out.printf("%s  %s   %s         %s         %s\n", each_Info[0]
															, each_Info[1]
															, each_Info[3]
															, each_Info[2]
															, each_Info[4]);
			}
			System.out.println("==============================================================================================");
			System.out.println("1.검색   2.답변하기   0.뒤로가기   H.홈으로     ");
			System.out.println("==============================================================================================");
			InputSite i = new InputSite();
			i.InPutQnA();

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 리스트 찾기
	
	public static void answer(String QnANum, int a) throws IOException { // ------ㅅ상세보기

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
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
				if (each_Info[0].equals(QnANum)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = Post.get(i);
					String[] Date = each_Info[1].split("-");
//					System.out.println(each_Info[3]);
//					int year = Integer.parseInt(Date[0]);
//					int month = Integer.parseInt(Date[1]);
//					int date = Integer.parseInt(Date[2]);
//					c2.set(year,month-1,date);
//					if(c2.compareTo(c1)==1) {
//						state = "진행중";
//					}else {
//						state = "이벤트종료";
//					}
					break;
				} else if (i == Post.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					System.out.println(QnANum);
					int num = 2;
					pause(QnANum, num);
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
			System.out.println("==============================================================================================");
			System.out.println("                                                             Q&A");
			System.out.println("==============================================================================================");
			System.out.printf("%-9s%s\n", "[제목]", each_Info[4]);
			System.out.printf("%-9s%s\n", "[등록일]", each_Info[1]);
			System.out.printf("%-7s%s\n", "[카테고리]", each_Info[3]);
			System.out.printf("%-5s%s\n", "[내용]", temp1);
			System.out.println("==============================================================================================");
			System.out.println(
					"    1.답변하기     0.뒤로가기      H.홈으로                                                     ");
			System.out.println("==============================================================================================");
			
			InputSite i = new InputSite();
			i.InPutAnswer(QnANum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// QnA 답변하기
	
	
	
	public static void answerAdmin(String QnANum, int a) throws IOException { // ------관리자의 답변하기

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
				if (each_Info[0].equals(QnANum)) {// 0000001■선우민후■ - 앞에 7자리만 빼와서 비교하기
					temp = Post.get(i);
					String[] Date = each_Info[1].split("-");
//					System.out.println(each_Info[3]);
//					int year = Integer.parseInt(Date[0]);
//					int month = Integer.parseInt(Date[1]);
//					int date = Integer.parseInt(Date[2]);
//					c2.set(year,month-1,date);
//					if(c2.compareTo(c1)==1) {
//						state = "진행중";
//					}else {
//						state = "이벤트종료";
//					}
					break;
				} else if (i == Post.size() - 1) {

					System.out.println("일치하는 회원번호가 없습니다.");
					System.out.println(QnANum);
					int num = 2;
					pause(QnANum, num);
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
			System.out.println("==============================================================================================");
			System.out.println("                                                   Q&A");
			System.out.println("==============================================================================================");
			System.out.printf("%-9s%s\n", "[제목]", each_Info[4]);
			System.out.printf("%-9s%s\n", "[등록일]", each_Info[1]);
			System.out.printf("%-7s%s\n", "[카테고리]", each_Info[3]);
			System.out.printf("%-5s%s\n", "[내용]", temp1);
			System.out.println("==============================================================================================");
			System.out.println();
			System.out.println("==============================================================================================");
			System.out.println("                                                   Q&A하기");
			System.out.println("==============================================================================================");
			System.out.print("■답변: ");
			String answer = read.readLine();
			System.out.println("==============================================================================================");
			System.out.println(
					"    답변하시겠습니까? <Y/N>를 입력해주세요                                                     ");
			System.out.println("==============================================================================================");
			System.out.print("■입력: ");
			String YN = read.readLine();
			
			if (YN.toUpperCase().equals("Y")) {
				System.out.println("관리자의 답변이 이메일로 보내졌습니다.");
				EditState(QnANum);
				pause("0", 0);
			} else if (YN.toUpperCase().equals("N")) {
				System.out.println("답변이 취소되었습니다");
				pause("0", 0);
			}
			br.close();
			br1.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// QnA 답변하기
	
	public static void EditState(String QnANum) throws IOException {//답변 완료 시 답변 완료로 변화
		
		try { // (게시판 정보를 불러오기위한 , 정보위치 , 수정할)
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();

			ArrayList<String> Post = new ArrayList<String>();
			ArrayList<String> Rpost = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열

			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}

			String temp = ""; // 행당되는 회원정보 넣기

			for (int i = 0; i < Post.size(); i++) {
				String[] each_Info = Post.get(i).split("■");
				if (each_Info[0].equals(QnANum)) {
					each_Info[2] = "답변완료";
					temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2]+ "■" + each_Info[3]+ "■" + each_Info[4]+ "■" + each_Info[5]+ "■" + each_Info[6];
					Rpost.add(temp);
					i++;
				}
				Rpost.add(Post.get(i)); // 수정시킨 
			}

//		System.out.println();
			br.close();
			br1.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < Rpost.size(); i++) {
				String a = Rpost.get(i);
				bw.write(a);
				bw.newLine();
			}
			bw.close();
			System.out.println("");

			pause("0", 0);
			QnA(0);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	public static void pause(String PostNum, int num) throws IOException {//일시멈춤

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (num) {
		case 0://QnA 화면
			QnA(0);
		case 1:			
		case 2:
			
		default:

		}

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
