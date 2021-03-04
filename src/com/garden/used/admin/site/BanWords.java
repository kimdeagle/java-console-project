package com.garden.used.admin.site;
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

import com.garden.used.admin.member.Member;

public class BanWords {// 금지어 관리
	static File file = new File(Data.BANWORD);
	static int nowPage = 0;
	public static void BWMain(int page) throws IOException {// 금지어 메인화면 메서드

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));

			ArrayList<String> BWords = new ArrayList<String>(); // 수정할 데이터를 찾기위한 배열

			while (br.readLine() != null) {
				BWords.add(br1.readLine());
			}

			

			System.out.println();
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("                       금지어 관리");
			System.out.println("===================================================================");
			System.out.printf(" %s    %s    %s\n", "[번호]", "[등록일]", "[금지어]\n");
			int nowPage = nowPage(page);
			if(nowPage < 0) {
				nowPage =0;
			}else if(nowPage > BWords.size()){
				nowPage = 0;
			}
			
			for (int i = nowPage; i < nowPage + 10; i++) {
				String[] each_Info = BWords.get(i).split("■");
				System.out.printf("%s  %s   %s\n", each_Info[0], each_Info[2], each_Info[1]);
			}
			System.out.println("===================================================================");
			System.out.println("1.검색   2.수정   3.삭제    4.등록    P.이전페이지  N.다음페이지  0.뒤로가기   H.홈으로    ");
			System.out.println("===================================================================");
			InputSite i = new InputSite();
			i.InPutBanWords();

		} catch (FileNotFoundException e) {
			BWMain(0);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 금지어 화면

	public void sameFind(int num, String same) {// 검색 메서드

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
					break;
				}
			}

			System.out.println();
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("                       금지어 관리");
			System.out.println("===================================================================");
			System.out.printf(" %s    %s    %s\n", "[번호]", "[등록일]", "[금지어]\n");
			for (int i = 0; i < sameWords.size(); i++) {
				String[] each_Info = sameWords.get(i).split("■");
				System.out.printf("%s  %s   %s\n", each_Info[0], each_Info[2], each_Info[1]);
			}
			System.out.println("===================================================================");
			System.out.println("1.검색   2.수정   3.삭제    4.등록    0.뒤로가기   H.홈으로    ");
			System.out.println("===================================================================");
			InputSite i = new InputSite();
			i.InPutBanWords();

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void EditBanwords(String PostNum) throws IOException { // 금지어 수정

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
					pause(PostNum, 0);
				}
			}
			String[] each_Info = temp.split("■");

			System.out.println();
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("                       금지어 수정");
			System.out.println("===================================================================");
			System.out.printf("%-10s%s\n", "[글번호]", each_Info[0]);
			System.out.printf("%-10s%s\n", "[금지어]", each_Info[1]);
			System.out.printf("%-10s%s\n", "[등록일]", each_Info[2]);
			System.out.println("===================================================================");

			br.close();
			br1.close();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			BanWords b = new BanWords();
			System.out.println("[수정할 금지어를 입력해주세요]");
			System.out.println("[뒤로가실려면 \"0\"을 입력해주세요]");
			System.out.print("■입력: ");
			String input = read.readLine();
			if (input.equals("0")) {
				BWMain(0);
			}
			b.EditBanWords(PostNum, 1, input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 금지어 수정

	public static void EditBanWords(String PostNum, int each_Num, String same) throws IOException {// 진짜로 수정하는 메서드
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
				if (each_Info[0].equals(PostNum)) {
					each_Info = Post.get(i).split("■");
					each_Info[each_Num] = same;
					temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2];
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


			EditBanwords(PostNum);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 리얼 수정하는 메서드

	public static void DeltBanWords(String PostNum) throws IOException {// 삭제 메서드

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			
			ArrayList<String> Post = new ArrayList<String>();// 멤버파일 불러넣기
			
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

			br.close();
			br1.close();

			BWMain(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 삭제 메서드

	public static void EnrollBanWords() throws IOException {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			Calendar c1 = Calendar.getInstance();
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

			// 등록날짜
			String nowDate = c1.get(Calendar.YEAR) + "-" 
			               + (c1.get(Calendar.MONTH)+1) + "-" 
					       + c1.get(Calendar.DATE);

			//일련번호
			ArrayList<String> Post = new ArrayList<String>(); //배열 복사
			
			int nums = 0;
			while (br.readLine() != null) {
				Post.add(br1.readLine());
			}
			
			String[] FindMax = Post.get(Post.size()-1).split("■");//마지막 등록번호

			//마지막 등록번호(String -> int+1-> String)
			String max =Integer.toString((Integer.parseInt(FindMax[0].substring(4, 7))+1));
			String max2 = "0000"+max;
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			System.out.println();
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("                       금지어 관리");
			System.out.println("===================================================================");
			System.out.println("[일련번호]:" + max2);
			System.out.printf("[등록일]\t:%tF\n", c1);
			System.out.printf("[금지어] \t:");
			String ban = read.readLine();
			System.out.println("===================================================================");
			System.out.println(" 최종 등록 하시겠습니까 ?       Y / N                                               ");
			System.out.println("===================================================================");

			String YN = read.readLine(); // Y / N
			if (YN.toUpperCase().equals("Y")||YN.toUpperCase().equals("네")) {

				bw.newLine();
				bw.write(max2);// 글번호
				bw.write("■");
				bw.write(ban);// 내용 쓰기
				bw.write("■");
				bw.write(nowDate);// 등록날짜
				bw.close();
				System.out.println("성공");
				BWMain(0);
			} else if (YN.toUpperCase().equals("N")||YN.toUpperCase().equals("아니요")) {
				System.out.println("실패");
				pause("0", 0);
			} else {
				System.out.println("해당하지 않는 버튼입니다.");
				int num = 2;
				pause("0", 2);
			}

			
			br.close();
			br1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void pause(String PostNum, int num) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (num) {
		case 0:// 금지어 화면
			BWMain(0);
		case 1:// 금지어 수정
			EditBanwords(PostNum);
		case 2:// 등록화면
			EnrollBanWords();
		default:

		}

	} // pause

	
	private static int nowPage(int page) {//페이지 넘기기

		if (page == 1) {
			nowPage += 10;
		} else if (page == -1) {
			nowPage -= 10;
		} else if( page == 0) {
			nowPage = 0;
		}
		return nowPage;
	}
}
