package com.garden.used.nonmember;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SellList { //상품 목록 확인
	
	//전체 상품을 GoodsObject에 저장
	//회원이 클릭한 상품 보여주기
	
	
	public SellList() { //상품 목록 게시판
		Scanner scanner = new Scanner(System.in);
		ArrayList<GoodsObject> total = new ArrayList<GoodsObject>();
		
		
		try {
		BufferedReader reader = 
				new BufferedReader(new FileReader(Data.GOODSINFO));
		
		
		//상품정보 가져오기
		String str = "";
		
		while((str = reader.readLine()) != null) {//ArrayList에 요소 추가
			
			String[] temp = str.split("■");
			
					int price = Integer.parseInt(temp[6]);
					total.add(new GoodsObject(temp[0], temp[2], temp[8], temp[5], price, temp[4], temp[18], temp[1]));
				}
				
		
		}
		
		catch(Exception e) {
			System.out.println("버퍼드리더 에러");
		}
		
		
		try {
			
		
		for(int i=0; i<total.size()/10 + 1;) {
			
			int cnum = 1;
			
			
			//상품리스트 게시판
			System.out.println("===========================================================================================================================================");
			System.out.printf("[선택]\t[상품번호]\t[카테고리]\t[지역]\t\t[제목]\t\t\t\t\t\t[상품가격]\t[거래상태]\t[닉네임]\t[최종등록일]\n");
			System.out.println("===========================================================================================================================================");
			
			for(int j=0+i*10; j<10+i*10; j++) {
				if(j>total.size()) {
					break;
				}
				
				
				String page = "";
				page += String.format("%-2d\t%-8s\t%7s\t%-6s\t%-30s\t%,d원\t%-5s\t%-6s\t%8s\n" , cnum
						, total.get(j).getNumGoods() //상품번호
						, total.get(j).getCategory() //카테고리
						, total.get(j).getArea() //지역
						, total.get(j).getTitle() //제목
						, total.get(j).getPrice() //가격
						, total.get(j).getState() //거래상태
						, total.get(j).getNickName() //닉네임
						, total.get(j).getDate()); //등록일
				System.out.print(page);
			
					cnum++;
					
			}
			
			System.out.println("====================================================================================================================================================");
			System.out.printf("\t\t\t<현재 페이지는 %d페이지입니다. 마지막 페이지는 %d페이지입니다.>\n", i+1 , total.size()/10+1);
			System.out.println("====================================================================================================================================================");
			System.out.println("이전페이지: P\t\t\t다음페이지: N\t\t\t첫페이지: F\t\t\t홈으로: H");
			System.out.println("====================================================================================================================================================");
			System.out.println("게시글 보기를 원하시면 게시글 앞의 선택 번호를 입력해주세요.");
			System.out.println(	"뒤로가기를 원하시면 숫자 ‘0’을 입력해주세요.");
			System.out.println("====================================================================================================================================================");
			System.out.print("■ 입력: ");
				
			String input = scanner.nextLine();
			String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			
			switch (input){
			
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
				goodsDetail(total.get(1));
				break;
			case "h":
			case "H":
				NonMember nonMember = new NonMember();
				break;
			case "0":	
				Sell sell = new Sell();
				break;
			
			}
			
			
		}
		
		} catch (Exception e) {
			System.out.println("게시판 에러");
		}
		
	
	
	}
		
		
		
		
	

	
	public void goodsDetail(GoodsObject numsGoods) { //상품 상세 페이지
		

		Scanner scanner = new Scanner(System.in);
		
		try {
			BufferedReader reader = 
					new BufferedReader(new FileReader(Data.GOODSINFO));
			
			String str = "";
			
			ArrayList<GoodsObject> total;
			while((str = reader.readLine()) != null) {//ArrayList에 요소 추가
				
				ArrayList<String> arr = new ArrayList<String>();
				arr.add(str);
				
				String[] temp = arr.get(i).split("■");
				
				
				//멤버인포에서 연락처 가져오기
				String str2 = "";
				BufferedReader read = new BufferedReader(new FileReader(Data.MEMBERINFO));
			
				ArrayList<String> tell = new ArrayList<String>();
				arr.add(str);
				
				String[] tempp = tell.get(i).split("■");
				
				for(int i=0; i<arr.size(); i++) {
				if(temp[15].equals(tempp[0])) {
					System.out.println(tempp[4]);
				}
				}
				
//				String[] temp2 = str2.split("■");
//				if(temp[19].equals(temp[1])) {
//					System.out.println(temp);
//				}
				
				
				int price = Integer.parseInt(temp[6]);
				int like = Integer.parseInt(temp[13]);
				total.add(new GoodsObject(temp[5] //제목
						, temp[5] //상품번호
						, temp[2] //카테고리
						, price //상품가격
						, temp[7] //상품상태
						, temp[4] //거래상태
						, temp[3] //거래방식
						, temp[1] //등록일
						, temp[20] //닉네임
						, temp[2] //연락처
						, temp[10] //선호거래지역
						, like 	//찜하기
						, temp[11]	//상품설명
						, temp[12]));	//연관태그 
				
				//0. 상품번호 1. 등록일 2. 카테고리 3. 거래방식 4. 거래상태 5. 제목 6. 가격 7. 상품상태 8. 9. 10. 지역 11. 제목 12. 해시태그 13. 찜하기 14. 판매자번호 15. 구매자번호
				//16. 리뷰 17. 구매/판매 18. 구매자번호 19. 판매자번호 20. 닉네임
			}
		
		int i = 0;
		
		System.out.println("========================================================================================================================");
		System.out.printf("%s\n", total.get(i).getTitle());
		System.out.println("========================================================================================================================");
		System.out.printf("%s\t\t %s\t\t %,d원\n", total.get(i).getNumGoods(), total.get(i).getCategory(), total.get(i).getPrice());
		System.out.printf("%s\t\t %s\t\t %s\t\t\n", total.get(i).getLevelGoods(), total.get(i).getState(), total.get(i).getDealWay());
		System.out.printf("%s\t\t %s\t\t %s\t\t\n", total.get(i).getDate(), total.get(i).getNickName(), total.get(i).getPhoneNum());
		System.out.printf("%s\t\t %s\n", total.get(i).getPreferenceArea(), total.get(i).getLike());
		System.out.println("========================================================================================================================");
		System.out.printf("상품설명:\n%s\n", total.get(i).getDetail());
		System.out.println("========================================================================================================================");
		System.out.println("1. 거래신청");
		System.out.println("2. 찜하기");
		System.out.println("========================================================================================================================");
		System.out.println("뒤로가기를 원하시면 숫자 '0'을 입력해주세요.");
		System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
		System.out.print("■ 입력: ");
		
		String input = scanner.nextLine();
		
		switch(input) {
		
		case"h" :
		case"H" :
			NonMember nonMember = new NonMember();
		case"0" :
			SellList SellList = new SellList();
		case "1":
		case "2":
			pleaseSignUp();
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			goodsDetail(numsGoods);
		}
		
		}
			
		catch(Exception e) {
			
			System.out.println("goodsDetail 에러");
		}
	}
	
	
	public static void pleaseSignUp() {
		
		System.out.println();
		System.out.println();
		System.out.println("============================================================");
		System.out.println("\t\t<회원만 이용할 수 있습니다.>");
		System.out.println("============================================================");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("============================================================");
		System.out.println("뒤로가기를 원하시면 숫자 '0'을 입력해주세요.");
		System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
		System.out.println("============================================================");
		System.out.println("■ 입력: ");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		
		switch(input) {
		
			case "1" : //회원가입 메서드
			case "2" : //로그인 메서드
			case "0" : //뒤로가기 메서드
			case "H" : //홈으로 메서드
			case "h" : //홈으로 메서드
			
			default :
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
				pleaseSignUp();
		}
		
	}
	
	private void pause() { //일시 정지 메서드
		Scanner scanner = new Scanner(System.in);
		System.out.println("계속 하려면 엔터를 눌러주세요.");
		scanner.nextLine();
		scanner.skip("\r\n");
		
	}
}	
