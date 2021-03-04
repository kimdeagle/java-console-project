package com.garden.used.admin.site;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.garden.used.data.Data;
import com.garden.used.login.Start;

public class Post {

	private ArrayList<String> menu = new ArrayList<String>();
	
	private int page = 1; //페이지 넘기는 횟수 
	private String memNum = ""; //회원 고유 번호
	private int choiceNum = 0; //선택 번호
	private String goodsNum = ""; //상품 번호
	private String title = ""; //제목
	private String category = ""; //카테고리
	private String tag = ""; //태그
	private String detail = ""; //상품 설명
	private String price = ""; //가격
	private String area = ""; //선호 거래 지역
	private String status = ""; //상품 상태
	private String dealMethod = ""; //거래 방식
	private String dealStatus = ""; //거래 상태
	private String date = ""; //최종 등록일
	private String nickName = ""; //닉네임
	
	public Post() {
		
		//게시판 목록 출력
		postList(goodsData(), "0" , -1);
	}

	public void postStart() {
		
		Scanner scan = new Scanner(System.in);
		
		//총 출력 가능한 페이지 수
		int totalPage = goodsData().size() / 10;
		
		Post.line();
		System.out.println("\t\t\t\t\t\t게시판 관리");
		Post.line();
		System.out.println("\t\t1. 게시글 선택   2. 상품 번호 검색   3. 카테고리 검색   4. 제목 검색");
		System.out.println("\t\tP : 이전 페이지\t\tH : 홈으로\t\tN : 다음 페이지" );
		Post.line();
		System.out.print("■ 입력 : ");
		
		//입력 받은 번호
		String input = scan.nextLine();
		
		if (input.toUpperCase().equals("P")) {

			//이전 페이지로 이동
			if (this.page == 1) {
				System.out.println();
				System.out.println("첫 페이지입니다.");
				Post.pause();
				
				postList(goodsData(), "0", -1);
			}
			
			this.page = this.page -1;
			
			postList(goodsData(), "0", -1);

		} else if (input.toUpperCase().equals("N")) {

			//다음 페이지로 이동
			if (this.page == totalPage-1) {
				System.out.println();
				System.out.println("마지막 페이지입니다.");
				System.out.println("홈으로 돌아갑니다.");
				Post.pause();
				
				Post post = new Post();
			}
			
			this.page = this.page +1;
			
			postList(goodsData(), "0", -1);

		} else if (input.toUpperCase().equals("H")) {
			
			//홈으로 - 사이트 관리 메뉴 나오는 창
			System.out.println();
			System.out.println("사이트 관리 메뉴로 돌아갑니다.");
			Post.pause();
			
			SiteMain main = new SiteMain();
			main.SiteMain();
			
		} else if (input.equals("1")) {

			//선택 번호(가장 좌측의 번호) 누르면 상세 게시글 조회
			System.out.println();
			System.out.print("선택 번호 : ");
			int number = Integer.parseInt(scan.nextLine());
			
			this.choiceNum = number;
			
			//게시글 상세 보기 메소드 호출
			postDetail(this.choiceNum);
			
			//게시글 관리 - 수정 및 삭제할 수 있는 메뉴와 호출 가능한 메소드
			postManage();
			return;
			
			
		} else if (input.equals("2")) {
			
			//상품 번호로 게시글 목록 검색
			System.out.println();
			System.out.print("상품 번호 : ");
			String goodsNumber = scan.nextLine();
			
			postList(goodsData(), goodsNumber, 0);
			
		} else if (input.equals("3")) {

			//카테고리로 게시글 목록 검색
			System.out.println();
			System.out.print("카테고리 : ");
			String category = scan.nextLine();
		
			postList(goodsData(), category, 2);
			
		}  else if (input.equals("4")) {

			//제목으로 게시글 목록 검색
			System.out.println();
			System.out.print("제목 : ");
			String title = scan.nextLine();
			
			postList(goodsData(), title, 5);
			
		} else {

			//1~4, p, P, n, N, h, H 이외의 문자를 입력했을 시
			System.out.println();
			System.out.println("잘못된 번호입니다.\n다시 입력해주세요.");
			postStart();
		}
	}

	//상세 게시글을 출력했을 때 하단에 나오는 게시글 관리 메뉴
	private void postManage() {
		
		Scanner scan = new Scanner(System.in);
		Post.line();
		System.out.println("\t\t\t\t"+"1. 게시글 수정   2. 게시글 삭제   H : 홈으로");
		Post.line();

		System.out.print("■ 입력 : ");
		String input = scan.nextLine();
		
		if (input.equals("1")) {
	
			//게시글 수정 선택 시 수정 목록과 수정 메소드 호출
			Post.line();
			System.out.println("\t\t\t\t\t\t게시글 수정");
			Post.line();
			System.out.println("\t\t\t1. 제목   2. 카테고리   3. 연관 태그   4. 상품 설명   5. 상품 가격");
			System.out.println("\t\t\t  6. 상품 상태   7. 거래 방식   8. 거래 상태   9. 최종 등록일");
			System.out.println("\t\t\t\t\t\tH : 홈으로");
			Post.line();
			
			postEdit(this.choiceNum);
		
		} else if (input.equals("2")) {
			
			//게시글 삭제 선택 시 삭제 삭제 메소드 호출
			Post.line();
			System.out.println("\t\t\t\t\t\t게시글 삭제");
			Post.line();
			
			postDelete();
		
		} else if (input.toUpperCase().equals("H")) {
			
			//홈으로 - 게시글 목록으로 돌아간다
			System.out.println();
			System.out.println("게시판 목록으로 돌아갑니다.");
			pause();
			
			Post post = new Post();
			return;
			
		} else {
			
			//1, 2, h, H 외의 문자 입력 시
			System.out.println();
			System.out.println("잘못된 입력입니다.");
			pause();
			postManage();
		}
	}

	//상세 보기에서 게시글 삭제 선택 시 호출되는 메소드
	private void postDelete() {

		ArrayList<String> deleteList = goodsData();
			
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.printf("삭제하려는 글 [상품 번호 : %s   제목 : %s]\n", this.goodsNum, this.title);
		System.out.println("삭제된 게시글은 복구할 수 없습니다.");
		System.out.println("정말로 삭제하시겠습니까?)");
		System.out.print("■ 입력 (y/n) : ");
		String input = scan.nextLine();
			
		
		if (input.toLowerCase().equals("n")) {

			//삭제 취소 시 홈으로(게시판 목록) 돌아간다
			System.out.println();
			System.out.println("삭제가 취소되었습니다.");
			System.out.println("홈으로 돌아갑니다.");
			Post.pause();
			Post post = new Post();
			return;
		
		} else if (input.toLowerCase().equals("y")) {  
			
			//삭제 진행 시 해당 게시글을 Data.GOODSINFO에 지운 상태로 덮어쓴다 
			System.out.println();
			System.out.println("삭제가 완료되었습니다.");
			deleteList.remove(this.choiceNum -1);
			
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(Data.GOODSINFO));

				for (int i=0; i<deleteList.size(); i++) {
						
					writer.write(deleteList.get(i)+"\r\n");
					goodsData().add(deleteList.get(i));
					menu.add(deleteList.get(i));
				}
					
				System.out.println("홈으로 돌아갑니다.");
			
				writer.close();
				
				Start.pause();
				Post post = new Post();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			System.out.println();
			System.out.println("잘못된 입력입니다.");
			pause();
			postDelete();
		}
	}

	//상세 보기에서 게시글 수정 선택 시 호출되는 메소드
	private void postEdit(int choiceNumber2) {

		Scanner scan = new Scanner(System.in);
		System.out.println("수정하실 목록을 골라 입력해주세요.");
		System.out.println();
		System.out.print("■ 입력 : ");
		String input = scan.nextLine();
		

		if (input.toUpperCase().equals("H")) {
			
			//홈으로 이동 - 게시판 목록으로
			System.out.println();
			System.out.println("게시판 목록으로 돌아갑니다.");
			Post.pause();
			Post post = new Post();
			
			return;
		}
		
		//줄 단위로 따온 상품 정보 데이터 호출
		ArrayList<String> goodsData = goodsData();
		String editLine = "";
				
		editLine = goodsData.get(choiceNumber2 -1);
		
		System.out.print("수정하실 내용 입력 : ");
		String edit = scan.nextLine();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.GOODSINFO));
			
			if (input.equals("1")) {
				
				//제목 수정 시 수정 후 내용으로 바꾼다
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.title, edit);
				editLine = editLine.replace(this.title, edit);
				this.title = edit;
				goodsData.set(this.choiceNum-1, editLine);

			} else if (input.equals("2")) {
				
				//카테고리 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.category, edit);
				editLine = editLine.replace(this.category, edit);
				this.category = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("3")) {
				
				//태그 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.tag, edit);
				editLine = editLine.replace(this.tag, edit);
				this.tag = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("4")) {
			
				//상품 설명 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.detail, edit);
				editLine = editLine.replace(this.detail, edit);
				this.detail = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("5")) {
				
				//가격 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.price, edit);
				editLine = editLine.replace(this.price, edit);
				this.price = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("6")) {
			
				//상품 상태 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.status, edit);
				editLine = editLine.replace(this.status, edit);
				this.status = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("7")) {
				
				//거래 방식 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.dealMethod, edit);
				editLine = editLine.replace(this.dealMethod, edit);
				this.dealMethod = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("8")) {
				
				//거래 상태 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.dealStatus, edit);
				editLine = editLine.replace(this.dealStatus, edit);
				this.dealStatus = edit;
				goodsData.set(this.choiceNum-1, editLine);
			
			} else if (input.equals("9")) {
				
				//최종 등록일 수정
				System.out.println();
				System.out.printf("[%s] 에서 [%s]으로 수정되었습니다.\n", this.date, edit);
				editLine = editLine.replace(this.date, edit);
				this.date = edit;
				goodsData.set(this.choiceNum-1, editLine);
				
			}

			//수정한 내용으로 덮어쓴다
			goodsData().clear();
			for (int i=0; i<goodsData.size(); i++) {
				writer.write(goodsData.get(i)+"\r\n");
				goodsData().add(goodsData.get(i));
			}
			
			System.out.println("홈으로 돌아갑니다.");
			Post.pause();

			writer.close();
			Post post = new Post();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//선택 번호로 게시글 선택 후 게시글 상세 보기
	private void postDetail(int listNumber) {
		
		ArrayList<String> dataLine = goodsData();
		
		String[] temp = dataLine.get(listNumber-1).split("■");
		
		Post.line();
		System.out.println("\t\t\t\t\t\t상품 등록 정보");
		Post.line();
		
		this.goodsNum = temp[0];
		this.date = temp[1];
		this.category = temp[2];
		this.dealMethod = temp[3];
		this.dealStatus = temp[4];
		this.title = temp[5];
		this.price = temp[6];
		this.status = temp[7];
		this.area = temp[8];
		this.detail = temp[9];
		this.tag = temp[10];
		this.dealStatus = temp[13];
		this.memNum = temp[13].equals("판매")? temp[15] : temp[14];

		System.out.println(String.format("상품 번호 : %s\t\t카테고리 : %s\t제목 : %-6s\n"
							+ "연관 태그 :%s\t\t\t\t선호 거래 지역 : %s\n"
							+ "상품 설명 : %s\n"
							+ "상품 가격 : %s원\t\t\t\t\t\t상품 상태 : %s\n"
							+ "거래 방식 : %s\t\t\t\t\t\t거래 상태 : %s\n"
							+ "최종 등록일 : %s\t\t\t\t\t  닉네임 : %s"
							, this.goodsNum //상품번호
							, this.category //카테고리
							, this.title //제목
							, this.tag //연관 태그
							, this.area //지역
							, this.detail //상품 설명
							, this.price //상품 가격
							, this.status //상품 상태
							, this.dealMethod //거래 방식
							, this.dealStatus //거래 상태
							, this.date //최종 등록일
							, membersMap().get(this.memNum)
							));
	}

	//게시판 목록
	private void postList(ArrayList<String> listView, String menuSel, int listNumber) {
		
		Post.line();
		System.out.println("[선택]\t[상품 번호]\t[카테고리]\t  [지역]\t\t\t[제목]");
        Post.line();

        ArrayList<String> data = goodsData();
        
        //기본 생성자 호출 시 출력되는 기본 화면
        if (listNumber == -1) {
        	
        	//페이지 넘기는 횟수에 따라 10페이지씩 출력
            for (int i=(this.page-1)*10; i<this.page*10; i++) {
            	
            	data.add(listView.get(i));
            	
            	String[] postList = data.get(i).split("■");

            	//상세 지역을 '도'나 '시'까지만 출력되게
            	int toIndex = 0;
            	if (postList[8].contains("시") && postList[8].contains("도")) {
    				toIndex = postList[8].indexOf("시") > postList[8].indexOf("도") ? postList[8].indexOf("도") : postList[8].indexOf("시"); 
    			
            	} else if (postList[8].contains("시")) {
    				toIndex = postList[8].indexOf("시");
    			
            	} else if (postList[8].contains("도")) {
    				toIndex = postList[8].indexOf("도");
    			}
            	
//            	System.out.print(String.format("%02d\t%s\t%s\t%-6s\t%-27s\n"
//						, i+1
//						, postList[0] //상품 번호
//						, postList[2] //카테고리
//						, postList[8].substring(0, toIndex+1) //지역
//						, postList[5] //제목
//						));
            	
             	System.out.print(String.format("%2d\t%s\t%s\t%-6s\t%-30s\n"
						, i+1
						, postList[0] //상품번호
						, postList[2] //카테고리
						, postList[8].substring(0, toIndex+1) //지역
						, postList[5])); //제목
            	

            }
            postStart();
            
        } else if (listNumber == 0) {

        	//원하는 상품 번호가 들어간 목록 출력
        	for (int j=(this.page-1)*10; j<this.page*10; j++) { 
        		
        		for (int i=0; i<goodsData().size(); i++) {
        		
        			String[] selList = goodsData().get(i).split("■");
        		
        			//상품 번호가 0이 포함된 숫자로 되어 있어 (00000006)
        			//정확히 입력하거나 00000006, 0을 제외한 숫자를 입력해도 6 검색 가능하게 한다
        			if ((selList[0].replace("0", "")).equals(menuSel) || selList[0].equals(menuSel)) {

        				//도나 시까지만 출력되게
        				int toIndex = 0;
        				if (selList[8].contains("시") && selList[8].contains("도")) {
        					toIndex = selList[8].indexOf("시") > selList[8].indexOf("도") ? selList[8].indexOf("도") : selList[8].indexOf("시"); 
                	
        				} else if (selList[8].contains("시")) {
        					toIndex = selList[8].indexOf("시");
                	
        				} else if (selList[8].contains("도")) {
        					toIndex = selList[8].indexOf("도");
        				}
                	
        				System.out.print(String.format("%2d\t%s\t%s\t%-6s\t%-30s\n"
        						, i+1
        						, selList[0] //상품 번호
        						, selList[2] //카테고리
        						, selList[8].substring(0, toIndex+1) //지역
        						, selList[5] //제목
        						));

        				postStart();
        				break;
        			}
        		}
        	}
        } else if (listNumber == 2) {
        	
        	//원하는 카테고리가 들어간 목록 출력
        	int printCount = 0;
        	
        	for (int j=(this.page-1)*10; j<this.page*10; j++) { 
        	
        		for (int i=0; i<goodsData().size(); i++) {
        		
        			String[] selList = goodsData().get(i).split("■");
        			
        			//입력 내용이 카테고리 명에 포함됐을 경우 출력한다
        			if (selList[2].contains(menuSel)) {

        				int toIndex = 0;
                    	if (selList[8].contains("시") && selList[8].contains("도")) {
            				toIndex = selList[8].indexOf("시") > selList[8].indexOf("도") ? selList[8].indexOf("도") : selList[8].indexOf("시"); 
            			
                    	} else if (selList[8].contains("시")) {
            				toIndex = selList[8].indexOf("시");
            			
                    	} else if (selList[8].contains("도")) {
            				toIndex = selList[8].indexOf("도");
            			}
                    	
                    	//최대 10개의 목록까지만 출력
                    	if (printCount < 10) {
                    		
                    		System.out.print(String.format("%2d\t%s\t%s\t%-6s\t%-30s\n"
            						, i+1
            						, selList[0] //상품 번호
            						, selList[2] //카테고리
            						, selList[8].substring(0, toIndex+1) //지역
            						, selList[5] //제목
            						));
                    		
                    		printCount++;
                    		
                    	} else if (printCount == 10) {
                    		postStart();
                    		break;
                    	}
        			}
        		}
        	}
        	
        } else if (listNumber == 5) {
        	
        	//원하는 제목이 들어간 목록 출력
        	int printCount = 0;
        	
        	for (int j=(this.page-1)*10; j<this.page*10; j++) { 
        	
        		for (int i=0; i<goodsData().size()-1; i++) {
        		
        			String[] selList = goodsData().get(i).split("■");
        			
        			//입력 내용이 제목에 포함됐을 경우 출력한다
        			if (selList[5].contains(menuSel)) {

        				int toIndex = 0;
                    	if (selList[8].contains("시") && selList[8].contains("도")) {
            				toIndex = selList[8].indexOf("시") > selList[8].indexOf("도") ? selList[8].indexOf("도") : selList[8].indexOf("시"); 
            			
                    	} else if (selList[8].contains("시")) {
            				toIndex = selList[8].indexOf("시");
            			
                    	} else if (selList[8].contains("도")) {
            				toIndex = selList[8].indexOf("도");
            			}
                    	
                    	//최대 10개의 목록까지만 출력
                    	if (printCount < 10) {

                    		System.out.print(String.format("%2d\t%s\t%s\t%-6s\t%-30s\n"
            						, i+1
            						, selList[0] //상품 번호
            						, selList[2] //카테고리
            						, selList[8].substring(0, toIndex+1) //지역
            						, selList[5] //제목
            						));
                    
                    		printCount++;
                    		
                    	} else if (printCount == 10) {
                    		postStart();
                    		break;
                    	}
        			}
        		}
        	}
        }
	}


	@Override
	public String toString() {
		return "{menu=" + menu + ", page=" + page + ", choiceNum=" + choiceNum + ", goodsNum=" + goodsNum + ", title="
				+ title + ", category=" + category + ", tag=" + tag + ", detail=" + detail + ", price=" + price
				+ ", area=" + area + ", status=" + status + ", dealMethod=" + dealMethod + ", dealStatus=" + dealStatus
				+ ", date=" + date + ", membersMap()=" + membersMap() + ", goodsData()=" + goodsData() + "}";
	}


	//고유 번호와 닉네임 저장할 회원 정보 Map
	public HashMap<String, String> membersMap() {

		HashMap<String, String> membersMap = new HashMap<String, String>();
		
		int lineNumber = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
			String line = null;
			boolean flag = true;
			
			while (flag) {
				
				if ((line = reader.readLine()) == null) {
					
					flag = false;
					
				} else if ((line = reader.readLine()) != null) {

					String[] temp = line.split("■");
					
					membersMap.put(temp[0], temp[10]);
					lineNumber++;
				}
			}

			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membersMap;
	}
	
	//상품 정보를 한줄씩 읽어 goodsData에 저장
	public ArrayList<String > goodsData() {
		
		ArrayList<String> goodsData = new ArrayList<String>();
	
		int lineNumber = 0;
		
		try {
			BufferedReader reader3 = new BufferedReader(new FileReader(Data.GOODSINFO));
			String line = null;
			boolean flag = true;
			
			while (flag) {
				
				if ((line = reader3.readLine()) == null) {
					flag = false;
				}
				
				goodsData.add(line);
				lineNumber++;
			}

			reader3.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return goodsData;
	}
	

	//일시 정지
	public static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계속하시려면 엔터 키를 누르세요.");
		Post.line();
		scan.nextLine();
		
	}
	
	//라인 찍기
	public static void line() {
		String line = "";
			
		for (int i=0; i<110; i++) {
			line += "=";
		}
		System.out.println(line);
	}
}
