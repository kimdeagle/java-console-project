package com.garden.used.admin.site;

import com.garden.used.admin.member.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.garden.used.admin.preference.*;
public class InputSite {

	public static void SiteMainInPut() { // 사이트 관리에서 사용하는 입력 메서드

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();

			if (input.equals("1") || input.trim().equals("게시판관리")) {

				Post p = new Post();
				p.postStart();
//				list(page);
			} else if (input.equals("2") || input.trim().equals("금지어관리")) {
				BanWords b = new BanWords();
				b.BWMain(0);// 금지어 관리로 이동
			} else if (input.equals("3") || input.equals("이벤트")) {
				Event e = new Event();
				e.Event();
			} else if (input.equals("4") || input.equals("공지사항")) {
				Notice n = new Notice();
				n.Notice();

			} else if (input.equals("5") || input.equals("QnA")) {
				QnA q = new QnA();
				q.QnA(0);

			} else if (input.equals("0") || input.equals("뒤로가기")) {
				Home h = new Home();
				h.Home();

			} else {
//				pauseHome();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// ------------메인 사이트 인풋

	public void InputEvent() { // 이벤트 리스트에서 사용하는 인풋

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			Event e = new Event();
			if (input.equals("1") || input.trim().equals("상세조회")) {
				System.out.println("[글번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				int a = 0;
				e.DetailSearch(input, a);

			} else if (input.equals("2") || input.trim().equals("등록")) {
				e.EnrollEvent();

			} else if (input.equals("3") || input.equals("수정")) {
				System.out.println("[수정할 번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				e.EditEvent(input);
			} else if (input.equals("4") || input.equals("삭제")) {
				System.out.println("[삭제할 번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				System.out.printf("[%s]번 이벤트를 지우시겠습니까? [Y/N]\n", input);
				System.out.print("■Y/N: ");
				String YN = br.readLine();
				if (YN.toUpperCase().equals("Y")) {
					e.DeltEvent(input);// 이벤트 삭제
				}else if(YN.toUpperCase().equals("N")) {
					System.out.println("최소되었습니다. ");
					e.pause("", 2);
				}
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else if (input.equals("0") || input.equals("뒤로가기")) {
				SiteMain s = new SiteMain();
				s.SiteMain();
			} else {
				System.out.println("잘못 누르셨습니다. 다시 시도해주세요");
				e.pause("0", 1);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 이벤트 리스트 인풋

	public static void DtailEvent(String PostNum) { // 이벤트 리스트에서 사용하는 인풋

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			Event e = new Event();
			if (input.equals("1") || input.trim().equals("수정")) {

				
				e.EditEvent(PostNum);
			} else if (input.equals("2") || input.trim().equals("삭제")) {
				
				System.out.printf("%s번 이벤트를 지우시겠습니까? [Y/N]", PostNum);
				System.out.print("■Y/N: ");
				input = br.readLine();
				if (input.toUpperCase().equals("Y")) {
					e.DeltEvent(PostNum);// 이벤트 삭제
				}else if(input.toUpperCase().equals("N")) {
					System.out.println("최소되었습니다. ");
					e.pause("", 2);
				}
			} else if (input.equals("0") || input.equals("뒤로가기")) {
				
				e.Event();
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else {
				System.out.println("잘못 누르셨습니다. 다시 시도해주세요");
				e.pause("0", 2);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 상세조회 인풋

	public static void EditInputEvent(String num, String PostNum) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {

			if (num.equals("1") || num.trim().equals("제목")) {
				System.out.println("[수정할 제목을 입력해주세요]");
				System.out.print("■제목: ");
				int each_Num = 1;
				num = br.readLine();
				Event e = new Event();
				e.EditEvent(PostNum, each_Num, num);

			} else if (num.equals("2") || num.trim().equals("시작날짜")) {
				System.out.println("[수정할 시작날짜를 입력해주세요]");
				System.out.print("■시작날짜: ");
				int each_Num = 2;
				num = br.readLine();
				Event e = new Event();
				e.EditEvent(PostNum, each_Num, num);

			} else if (num.equals("3") || num.equals("종료날짜")) {
				System.out.println("[수정할 종료날짜를 입력해주세요]");
				System.out.print("■종료날짜: ");
				int each_Num = 3;
				num = br.readLine();
				Event e = new Event();
				e.EditEvent(PostNum, each_Num, num);
			} else if (num.equals("4") || num.equals("내용")) {
				System.out.println("[수정할 내용를 입력해주세요]");
				System.out.print("■내용: ");
				int each_Num = 5;
				num = br.readLine();
				Event e = new Event();
				e.EditEvent(PostNum, each_Num, num);
			} else if (num.equals("0") || num.equals("뒤로가기")) {
				Event e = new Event();
				e.Event();
			} else if (num.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else {
				Event e = new Event();
				e.pause("0", 2);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void InputNotic() {// ----------공지사항 입력값

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();

			if (input.equals("1") || input.trim().equals("상세조회")) {
				System.out.println("[글번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				Notice n = new Notice();
				n.DetailSearch(input, 0);

			} else if (input.equals("2") || input.trim().equals("등록")) {

				Notice n = new Notice();
				n.EnrollNotic();

			} else if (input.equals("3") || input.equals("수정")) {
				Notice n = new Notice();
				System.out.println("[수정할 번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				n.EditNotice(input);

			} else if (input.equals("4") || input.equals("삭제")) {
				Notice n= new Notice();
				System.out.println("[삭제할 번호를 입력하세요]");
				System.out.print("■글번호: ");
				input = br.readLine();
				n.DeltNotice(input);
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else if (input.equals("0") || input.equals("뒤로가기")) {
				SiteMain s = new SiteMain();
				s.SiteMain();
			} else {
				Notice n = new Notice();
				System.out.println("잘못누르셨습니다. 다시 시도해주세요");
				n.pause("0", 1);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 공지사항 입력값

	public void DtailNotice(String PostNum) throws IOException { // 이벤트 리스트에서 사용하는 인풋
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("■입력: ");
		String input = br.readLine();
		
		try {
			Notice e = new Notice();
			if (input.equals("1") || input.trim().equals("수정")) {
				
				e.EditNotice(PostNum);
			} else if (input.equals("2") || input.trim().equals("삭제")) {

				e.DeltNotice(PostNum);

			} else if (input.equals("0") || input.equals("뒤로가기")) {
				Notice n = new Notice();
				n.Notice();
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();

			} else {

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 상세조회 인풋

	public static void EditInputNotice(String num, String PostNum) {// 수정버튼

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			if (num.equals("1") || num.trim().equals("제목")) {
				System.out.println("[수정할 제목을 입력해주세요]");
				System.out.print("■제목: ");
				String same = br.readLine();
				Notice n = new Notice();
				n.EditNotice(PostNum, 2, same);

			} else if (num.equals("2") || num.trim().equals("내용")) {
				System.out.println("[수정할 내용을 입력해주세요]");
				System.out.print("■내용: ");
				String same = br.readLine();
				Notice n = new Notice();
				n.EditNotice(PostNum, 3, same);

			} else if (num.equals("0") || num.equals("뒤로가기")) {
				Notice n = new Notice();
				n.Notice();
			} else if (num.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else {
				Notice n = new Notice();
				String PostNum1 = "0";
				int num1 = 0;
				n.pause(PostNum1, num1);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 수정 버튼

	public static void InPutBanWords() { // 금지어 화면 INPUT

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			BanWords b = new BanWords();
			if (input.equals("1") || input.trim().equals("검색")) {
				System.out.println();
				System.out.println("검색할 기준을 선택해주세요");
				System.out.println("1.금지어 2.일련번호  0.취소 ");
				System.out.print("■입력: ");
				input = br.readLine();
				Find(input);

			} else if (input.equals("2") || input.trim().equals("수정")) {
				System.out.println("[수정할 번호를 입력해주세요]");
				System.out.print("■일련번호: ");
				input = br.readLine();

				b.EditBanwords(input);

			} else if (input.equals("3") || input.equals("삭제")) {

				System.out.println("[삭제할 번호를 입력하세요]");
				System.out.print("■일련번호: ");
				input = br.readLine();
				System.out.println("정말 삭제하시겠습니까?   [Y/N]");
				System.out.print("■Y/N: ");
				String YN = br.readLine();
				if (YN.toUpperCase().equals("Y")) {
					b.DeltBanWords(input);
				} else {
					b.BWMain(0);
				}
			} else if (input.equals("4") || input.equals("등록")) {

				b.EnrollBanWords();
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else if (input.toUpperCase().equals("P") || input.equals("이전페이지")) {
				b.BWMain(-1);
			} else if (input.toUpperCase().equals("N") || input.equals("다음페이지")) {
				b.BWMain(1);

			} else if (input.equals("0") || input.equals("뒤로가기")) {
				SiteMain s = new SiteMain();
				s.SiteMain();
			} else {
				b.pause("0", 0);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 금지어 입력

	public static void Find(String num) throws IOException {// 금지어 검색

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BanWords b = new BanWords();
		switch (num) {
		case "1":
			System.out.print("■[금지어]: ");
			String same = read.readLine();
			b.sameFind(1, same);
			break;

		case "2":
			System.out.print("■[일련번호]: ");
			same = read.readLine();
			b.sameFind(0, same);
			break;

		case "0":
			System.out.print("■[취소]: ");
			int page = 0;
			b.BWMain(0);
			break;

		default:
			System.out.println("일치하는 번호가 없습니다.");
			int a = 0; // 일시멈춤 복귀 위치
			String X = "b"; // 필요없는 값
			b.pause(X, a);

		}
	}// 금지어 검색

	public static void InPutQnA() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();

			if (input.equals("1") || input.trim().equals("검색")) {
				System.out.println();
				System.out.println("검색할 기준을 선택해주세요");
				System.out.println("1.일련번호  2.카테고리  0.취소 ");
				System.out.print("■입력: ");
				input = br.readLine();
				FindQnA(input);

			} else if (input.equals("2") || input.trim().equals("답변하기")) {
				QnA q = new QnA();
				System.out.println("[답변할 일련번호를 입력해주세요]");
				System.out.print("■입력: ");
				input = br.readLine();
				q.answer(input, 0);

			} else if (input.toUpperCase().equals("P") || input.equals("이전페이지")) {
				QnA q = new QnA();
				q.QnA(-1);
			} else if (input.toUpperCase().equals("N") || input.equals("다음페이지")) {
				QnA q = new QnA();
				q.QnA(1);

			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();
			} else if (input.equals("0") || input.equals("뒤로가기")) {
				SiteMain s = new SiteMain();
				s.SiteMain();
			} else {
				Event e = new Event();
				e.Event();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void FindQnA(String num) throws IOException {// QnA 검색 기능

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		QnA q = new QnA();
		switch (num) {
		case "1":
			System.out.print("■[일련번호]: ");
			String same = read.readLine();
			q.sameFind(0, same);
			break;

		case "2":
			System.out.println("1.구매/판매   2.이용제재   3.계정/인증   4.운영정책   5.사기/신고");
			System.out.print("■[카테고리]: ");
			same = read.readLine();
			String category = "";
			switch (same) {
			case "1":
				category = "구매판매";
				break;
			case "2":
				category = "이용제재";
				break;
			case "3":
				category = "계정/인증";
				break;
			case "4":
				category = "운영정책";
				break;
			case "5":
				category = "사기/신고";
				break;
			default:
				System.out.println("해당하는 번호가 없습니다.");

			}
			q.sameFind(3, category);
		case "0":
			System.out.print("■[취소]: ");
			int page = 0;
			q.QnA(0);

		default:
			System.out.println("일치하는 번호가 없습니다.");
			int a = 0; // 일시멈춤 복귀 위치
			String X = "b"; // 필요없는 값
			q.pause(X, a);

		}
	}// QnA 검색 기능

	public static void InPutAnswer(String QnANum) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("■입력: ");
			String input = br.readLine();
			QnA q = new QnA();
			if (input.equals("1") || input.trim().equals("답변하기")) {
				System.out.println("답변하시겠습니까 ?  [Y/N]");
				System.out.print("■입력: ");
				String YN = br.readLine();
				if (YN.toUpperCase().equals("Y") || YN.equals("네")) {
					q.answerAdmin(QnANum, 0);
				} else if (YN.toUpperCase().equals("N") || YN.equals("아니요")) {
					q.answer(QnANum, 0);
				}

			} else if (input.equals("0") || input.equals("뒤로가기")) {
				Notice n = new Notice();
				n.Notice();
			} else if (input.toUpperCase().equals("H")) {
				Home h = new Home();
				h.Home();

			} else {

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
