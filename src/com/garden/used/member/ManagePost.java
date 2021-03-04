package com.garden.used.member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.garden.used.data.Data;

public class ManagePost extends MemberLoginSuccess {
	
	private int pageNumber; //현재 페이지 번호
	private int totalPage; //총 페이지 수
	private int totalGoods; //등록상품 총 개수
	private String goodsBuyOrSell;
	private String buyerNumber;
	private String sellerNumber;
	
	ArrayList<Goods> buyList = new ArrayList<Goods>();
	ArrayList<Goods> sellList = new ArrayList<Goods>();
	ArrayList<Goods> buySafetyList = new ArrayList<Goods>();
	ArrayList<Goods> sellSafetyList = new ArrayList<Goods>();
    
    public ManagePost() {
    	this.pageNumber = 1;
    	this.totalPage = 0;
    	this.totalGoods = 0;
    	this.goodsBuyOrSell = "";
    	this.buyerNumber = "";
    	this.sellerNumber = "";
    }
	
    
	public void viewGoodsPost(String memberNumber, String nickname) {
		
		try {
			boolean flag = true;
			
			while (flag) {
				//회원이 작성한 상품글 가져오기
				getGoodsList(memberNumber);
				
				//전체 페이지수 계산하기
				this.totalPage = calTotalPage(memberNumber);
				
				//구매글 출력(5개씩)
				viewBuyGoodsList(pageNumber, nickname);
				
				//판매글 출력(5개씩)
				viewSellGoodsList(pageNumber, nickname);
				
				System.out.printf("\t\t\t\t\t\t<%d페이지 중 %d페이지>\n", totalPage, pageNumber);
				System.out.println("==========================================================================================================================");
				System.out.println("이전페이지 : P\t\t다음페이지 : N\t\t첫페이지 : F\t\t 홈으로 : H");
				System.out.println("==========================================================================================================================");
				System.out.println("1. 상품등록\t\t2. 상품수정\t\t3. 상품삭제\t\t4. 안전거래 관리");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("==========================================================================================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				
				String input = scan.nextLine(); //입력받기
				
				if (input.toUpperCase().equals("P")) { //이전페이지
					if (pageNumber != 1) { //업무코드
						pageNumber--;					
					} else { //1페이지에서 이전페이지로 가려고 하는 경우
						System.out.println("현재 페이지가 첫 페이지입니다.");
						pause();
					}
					viewGoodsPost(memberNumber, nickname);
				} else if (input.toUpperCase().equals("N")) { //다음페이지
					if (pageNumber != totalPage) { //업무코드
						pageNumber++;										
					} else { //마지막페이지에서 다음페이지로 가려고 하는 경우
						System.out.println("현재 페이지가 마지막 페이지입니다.");
						pause();
					}
					viewGoodsPost(memberNumber, nickname);
				} else if (input.toUpperCase().equals("F")) { //첫페이지
					pageNumber = 1;
					viewGoodsPost(memberNumber, nickname);
				} else if (input.toUpperCase().equals("H")) { //홈으로(뒤로가기)
					flag = false;
				} else if (input.equals("1")) { //상품 등록
					selectBuyOrSell(memberNumber, nickname); //구매 or 판매 선택하기
				} else if (input.equals("2")) { //상품 수정
					editGoods(memberNumber, nickname); //상품 수정 메서드
				} else if (input.equals("3")) { //상품 삭제
					deleteGoods(memberNumber, nickname); //상품 삭제 메서드
				} else if (input.equals("4")) { //안전거래 관리
					manageSafetyGoods(memberNumber, nickname);
				} else if (input.equals("0")) { //뒤로가기
					flag = false;
				} else { //잘못 입력한 경우
					wrongInput();
				}
			}
			
		} catch (Exception e) {
			System.out.println("ManagePost.viewGoodsList()");
			wrongInput();
		}
		
	} //viewGoodsList


	private void manageSafetyGoods(String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {
			
			try {
				
				//회원이 작성한 상품글 가져오기
				getGoodsList(memberNumber);
				
				//전체 페이지수 계산하기
				this.totalPage = calTotalPage(memberNumber);
				
				//구매글 출력(5개씩)
				viewBuySafetyGoodsList(pageNumber, nickname);
				
				//판매글 출력(5개씩)
				viewSellSafetyGoodsList(pageNumber, nickname);
				
				System.out.println("==============================================");
				System.out.println("              안전거래 관리");
				System.out.println("==============================================");
				System.out.println("상품 번호를 선택해주세요.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("==============================================");
				System.out.print("■입력 : ");
				
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				
				if (input > 0 && input <=5) { //구매상품
					if (buySafetyList.size() > (pageNumber-1)*5 + input - 1) {
						Goods goods = buySafetyList.get((pageNumber-1)*5 + input - 1); //선택한 번호의 상품 객체를 만든다.
						ManageSafety manageSafety = new ManageSafety();
						manageSafety.manageBuySafetyGoods(goods, memberNumber, nickname);

					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input > 5 && input <=10) { //판매상품
					if (sellSafetyList.size() > (pageNumber-1)*5 + input - 6) {
						Goods goods = sellSafetyList.get((pageNumber-1)*5 + input - 6);
						ManageSafety manageSafety = new ManageSafety();
						manageSafety.manageSellSafetyGoods(goods, memberNumber, nickname);
					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input == 0) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();			
				}
				
				
			} catch (Exception e) {
				System.out.println("ManagePost.deleteGoods()");
				wrongInput();
			}
			
		}
		
	} //manageSafetyGoods


	private void viewSellSafetyGoodsList(int pageNumber, String nickname) {
		
		int select = 6;
		
		System.out.println("[판매 목록]");
		System.out.println("====================================================================================================================================");
		System.out.println("[선택]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\t\t\t[상품가격]\t[거래상태]\t [닉네임]\t[최종등록일]\t");
		System.out.println("====================================================================================================================================");
		
		//(pageNumber-1)*5 -> 
		//현재 페이지에 표시할 상품 가져오기 (5개)
		for (int i=(pageNumber-1)*5; i<pageNumber*5; i++) {
			if (i < sellSafetyList.size()) {
				if (sellSafetyList.get(i).getWayOfDeal().equals("안전거래")) {
					System.out.printf("%3d\t %s\t%.6s\t %s\t%.10s\t %,d원\t %s\t%s\t %s\n"
							, select
							, sellSafetyList.get(i).getGoodsNumber()
							, sellSafetyList.get(i).getGoodsCategory()
							, sellSafetyList.get(i).getAddress().substring(0, 2)
							, sellSafetyList.get(i).getGoodsTitle()
							, Integer.parseInt(sellSafetyList.get(i).getGoodsPrice())
							, sellSafetyList.get(i).getDealState()
							, nickname
							, sellSafetyList.get(i).getRegisterDate());					
				}
			}
			select++;
		}
		System.out.println();
		
	}


	private void viewBuySafetyGoodsList(int pageNumber, String nickname) {
		
		int select = 1;
		
		System.out.println("[구매 목록]");
		System.out.println("====================================================================================================================================");
		System.out.println("[선택]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\t\t\t[상품가격]\t[거래상태]\t [닉네임]\t[최종등록일]\t");
		System.out.println("====================================================================================================================================");
		
		//(pageNumber-1)*5 -> 
		//현재 페이지에 표시할 상품 가져오기 (5개)
		for (int i=(pageNumber-1)*5; i<pageNumber*5; i++) {
			if (i < buySafetyList.size()) {
				if (buySafetyList.get(i).getWayOfDeal().equals("안전거래")) {
					System.out.printf("%3d\t %s\t%.6s\t %s\t%.10s\t %,d원\t %s\t%s\t %s\n"
							, select
							, buySafetyList.get(i).getGoodsNumber()
							, buySafetyList.get(i).getGoodsCategory()
							, buySafetyList.get(i).getAddress().substring(0, 2)
							, buySafetyList.get(i).getGoodsTitle()
							, Integer.parseInt(buySafetyList.get(i).getGoodsPrice())
							, buySafetyList.get(i).getDealState()
							, nickname
							, buySafetyList.get(i).getRegisterDate());					
				}
			}
			select++;
		}
		System.out.println();
		
	}


	private void deleteGoods(String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {
			
			try {
				
				//회원이 작성한 상품글 가져오기
				getGoodsList(memberNumber);
				
				//전체 페이지수 계산하기
				this.totalPage = calTotalPage(memberNumber);
				
				//구매글 출력(5개씩)
				viewBuyGoodsList(pageNumber, nickname);
				
				//판매글 출력(5개씩)
				viewSellGoodsList(pageNumber, nickname);
				
				System.out.println("=============================================");
				System.out.println("                상품 삭제");
				System.out.println("=============================================");
				System.out.println("상품 번호를 선택해주세요.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				//거래상태가 거래가능인 상품만 삭제 가능
				
				if (input > 0 && input <=5) { //구매상품
					if (buyList.size() > (pageNumber-1)*5 + input - 1) {
						Goods goods = buyList.get((pageNumber-1)*5 + input - 1);
						if (goods.getDealState().equals("거래가능")) { //거래상태 == 거래가능
							DeleteGoods delete = new DeleteGoods();
							delete.delete(goods, memberNumber, nickname);
						} else { //거래상태 != 거래가능
							System.out.println("거래가능인 상품만 삭제할 수 있습니다.");
							pause();
						}
					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input > 5 && input <=10) { //판매상품
					if (sellList.size() > (pageNumber-1)*5 + input - 6) {
						Goods goods = sellList.get((pageNumber-1)*5 + input - 6);
						if (goods.getDealState().equals("거래가능")) { //거래상태 == 거래가능
							DeleteGoods delete = new DeleteGoods();
							delete.delete(goods, memberNumber, nickname);			
						} else { //거래상태 != 거래가능
							System.out.println("거래가능인 상품만 삭제할 수 있습니다.");
							pause();
						}
					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input == 0) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();			
				}
				
			} catch (Exception e) {
				System.out.println("ManagePost.deleteGoods()");
				wrongInput();
			}
			
		}
		
	} //deleteGoods


	public void editGoods(String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {

			try {
				
				viewBuyGoodsList(pageNumber, nickname);
				viewSellGoodsList(pageNumber, nickname);
				
				System.out.println("=============================================");
				System.out.println("                상품 수정");
				System.out.println("=============================================");
				System.out.println("상품 번호를 선택해주세요.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <=5) { //구매상품
					if (buyList.size() > (pageNumber-1)*5 + input - 1) {
						Goods goods = buyList.get((pageNumber-1)*5 + input - 1);
						if (!goods.getDealState().equals("거래완료")) {
							UpdateGoods update = new UpdateGoods();
							update.update(goods, memberNumber, nickname);
						} else {
							System.out.println("거래완료 상품은 수정할 수 없습니다.");
							pause();
						}
					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input > 5 && input <=10) { //판매상품
					if (sellList.size() > (pageNumber-1)*5 + input - 6) {
						Goods goods = sellList.get((pageNumber-1)*5 + input - 6);
						if (!goods.getDealState().equals("거래완료")) {
							UpdateGoods update = new UpdateGoods();
							update.update(goods, memberNumber, nickname);				
						} else {
							System.out.println("거래완료 상품은 수정할 수 없습니다.");
							pause();
						}
					} else { //없는 방번호 접근
						wrongInput();
					}
				} else if (input == 0) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();			
				}
			
			} catch (Exception e) {
				System.out.println("ManagePost.editGoods()");
				wrongInput();
			}
		}
		
		
		
	} //editGoods


	public void selectBuyOrSell(String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {

			try {
				
				System.out.println("=============================================");
				System.out.println("            게시글 유형 선택");
				System.out.println("=============================================");
				System.out.println("1. 구매하기\t 2. 판매하기");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================");
				System.out.print("■입력 : ");
				
				Scanner registScan = new Scanner(System.in);
				String registInput = registScan.nextLine();

				if (registInput.equals("1")) {
					setGoodsBuyOrSell("구매");
					setBuyerNumber(memberNumber);
					setSellerNumber("");
					RegistGoods regist = new RegistGoods();
					regist.registration(totalGoods, goodsBuyOrSell, memberNumber, nickname);
				} else if (registInput.equals("2")){
					setGoodsBuyOrSell("판매");	
					setBuyerNumber("");
					setSellerNumber(memberNumber);
					RegistGoods regist = new RegistGoods();
					regist.registration(totalGoods, goodsBuyOrSell, memberNumber, nickname);
				} else if (registInput.equals("0")) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();
				}
			
			} catch (Exception e) {
				System.out.println("ManagePost.selectBuyOrSell()");
				wrongInput();
			}
		}
		
	} //selectBuyOrSell


	private void getGoodsList(String memberNumber) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
			
			totalGoods = 0;
			
			String line = null;
			
			buyList.clear();
			sellList.clear();
			buySafetyList.clear();
			sellSafetyList.clear();
			
			//상품들 가져오기
			while ((line = reader.readLine()) != null) {
				totalGoods++;
				String[] temp = line.split("■", 16);
				
				if (temp[14].equals(memberNumber)) { //구매글 가져오기
					buyList.add(new Goods(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15]));
					if (temp[3].equals("안전거래")) {
						buySafetyList.add(new Goods(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15]));
					}
				} else if (temp[15].equals(memberNumber)) { //판매글 가져오기
					sellList.add(new Goods(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15]));
					if (temp[3].equals("안전거래")) {
						sellSafetyList.add(new Goods(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15]));
					}
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("ManagePost.getGoodsList()");
			e.printStackTrace();
		}
		
	} //getGoodsList

	private int calTotalPage(String memberNumber) {
		
		int result = 0;
		
		if (buyList.size() > sellList.size()) {
			result = buyList.size() / 6 + 1;
		} else {
			result = sellList.size() / 6 + 1;
		}
		
		return result;
		
	} //calTotalPage()

	public void viewBuyGoodsList(int pageNumber, String nickname) { //구매상품 보기
		
		int select = 1;
		
		System.out.println("[구매 목록]");
		System.out.println("====================================================================================================================================");
		System.out.println("[선택]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\t\t\t[상품가격]\t[거래상태]\t [닉네임]\t[최종등록일]\t");
		System.out.println("====================================================================================================================================");
		
		//(pageNumber-1)*5 -> 
		//현재 페이지에 표시할 상품 가져오기 (5개)
		for (int i=(pageNumber-1)*5; i<pageNumber*5; i++) {
			if (i < buyList.size()) {
				
				System.out.printf("%3d\t %s\t%.6s\t %s\t%.10s\t %,d원\t %s\t%s\t %s\n"
						, select
						, buyList.get(i).getGoodsNumber()
						, buyList.get(i).getGoodsCategory()
						, buyList.get(i).getAddress().substring(0, 2)
						, buyList.get(i).getGoodsTitle()
						, Integer.parseInt(buyList.get(i).getGoodsPrice())
						, buyList.get(i).getDealState()
						, nickname
						, buyList.get(i).getRegisterDate());	
			}
			select++;
		}
		System.out.println();

	} //viewBuyGoodsList
	
	public void viewSellGoodsList(int pageNumber, String nickname) {

		int select = 6;
		
		System.out.println("[판매 목록]");
		System.out.println("====================================================================================================================================");
		System.out.println("[선택]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\t\t\t[상품가격]\t[거래상태]\t [닉네임]\t[최종등록일]\t");
		System.out.println("====================================================================================================================================");
		
		for (int i=(pageNumber-1)*5; i<pageNumber*5; i++) {
			if (i < sellList.size()) {
				System.out.printf("%3d\t %s\t%.6s\t %s\t%.10s\t %,d원\t %s\t%s\t %s\n"
						, select
						, sellList.get(i).getGoodsNumber()
						, sellList.get(i).getGoodsCategory()
						, sellList.get(i).getAddress().substring(0, 2)
						, sellList.get(i).getGoodsTitle()
						, Integer.parseInt(sellList.get(i).getGoodsPrice())
						, sellList.get(i).getDealState()
						, nickname
						, sellList.get(i).getRegisterDate());						
			}
			select++;
		}
		System.out.println();

	} //viewSellGoodsList

	public void wrongInput() {
		System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		pause();
	} //wringInput


	public String getGoodsBuyOrSell() {
		return goodsBuyOrSell;
	}


	public String getBuyerNumber() {
		return buyerNumber;
	}


	public String getSellerNumber() {
		return sellerNumber;
	}


	public void setGoodsBuyOrSell(String goodsBuyOrSell) {
		this.goodsBuyOrSell = goodsBuyOrSell;
	}


	public void setBuyerNumber(String buyerNumber) {
		this.buyerNumber = buyerNumber;
	}


	public void setSellerNumber(String sellerNumber) {
		this.sellerNumber = sellerNumber;
	}


	public ArrayList<Goods> getBuyList() {
		return buyList;
	}


	public ArrayList<Goods> getSellList() {
		return sellList;
	}


	public int getTotalPage() {
		return totalPage;
	}
	
	
} //Class End
