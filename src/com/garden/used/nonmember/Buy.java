package com.garden.used.nonmember;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Buy {
	
	
	static ArrayList<GoodsObject> total = new ArrayList<GoodsObject>();
	
	public Buy() throws IOException {
		
		System.out.println();
		System.out.println();
		System.out.println("==============================================================");
		System.out.println("\t\t\t[삽니다]");
		System.out.println("==============================================================");
		System.out.println();
		System.out.println("1. 등록된 게시글");
		System.out.println("2. 카테고리별 확인");
		System.out.println("3. 지역별 확인");
		System.out.println();
		System.out.println("==============================================================");
		System.out.println("뒤로가기를 원하시면 숫자 '0'을 입력해주세요.");
		System.out.println("홈으로 가시려면 'H'를 입력해주세요.");
		System.out.println("==============================================================");
		System.out.printf("■ 입력: ");
		
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		switch(input) {
		
		case "1":
			viewBuyList();
		case "2":
			CategoryView categoryView = new CategoryView();
		case "3":
			AreaView areaView = new AreaView();
		case "0":
			SellOrBuy sellOrBuy = new SellOrBuy();
		case "H" :
		case "h" :
			NonMember n = new NonMember();
		default :
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			Buy buy = new Buy();
			
		
		}
	}

	
	public void viewBuyList() throws IOException {
		//14
		
		
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSDETAIL));
		
		String line = "";
		
		while((line = reader.readLine()) != null) {
		
			String[] temp = line.split("■");
			
			
			if(temp[15].equals("구매")) {
				
				int price = Integer.parseInt(temp[6]);
				total.add(new GoodsObject(temp[0], temp[2], temp[8], temp[5], price, temp[4], temp[18], temp[1]));
			}
			
	
		}
			System.out.println("에러");
		
					
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
									, total.get(j).getNumGoods()
									, total.get(j).getCategory()
									, total.get(j).getArea()
									, total.get(j).getTitle()
									, total.get(j).getPrice()
									, total.get(j).getState()
									, total.get(j).getNickName()
									, total.get(j).getDate());
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
			//TODO 
					break;
				case "h":
				case "H":
					NonMember nonMember = new NonMember();
					break;
				case "0":	
					Buy buy = new Buy();
					break;
				}		
				
				System.out.println("게시판 에러");
	}
		
	}
	private Object getNumGoods(String numsGoods) {
		// TODO Auto-generated method stub
		return null;
	}
}
