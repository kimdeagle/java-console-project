package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import com.garden.used.data.Data;

public class RegistGoods extends ManagePost {

	private Goods goods = new Goods(getGoodsBuyOrSell(), getBuyerNumber(), getSellerNumber());
	
	private String bannedWord; //금지어
	private boolean flagCategory;
	private boolean flagSecondCategory;
	private boolean flagGoodsTitle;
	private boolean flagHashtag;
	private boolean flagGoodsDetail;
	private boolean flagGoodsPrice;
	private boolean flagAddress;
	private boolean flagGoodsState;
	private boolean flagWayOfDeal;
	private boolean flagFinalChoice;
	
	public RegistGoods() {
		
		this.flagCategory = true;
		this.flagSecondCategory = true;
		this.flagGoodsTitle = true;
		this.flagHashtag = true;
		this.flagGoodsDetail = true;
		this.flagGoodsPrice = true;
		this.flagAddress = true;
		this.flagGoodsState = true;
		this.flagWayOfDeal = true;
		this.flagFinalChoice = true;
		
	}
	

	public void registration(int totalGoods, String goodsBuyOrSell, String memberNumber, String nickname) {
		//초기값 설정
		
		//상품 번호		
		goods.setGoodsNumber(String.format("%08d", totalGoods + 1));
		
		//거래 상태
		goods.setDealState("거래가능");
		
		//최종 등록일
		goods.setRegisterDate(String.format("%tF", Calendar.getInstance())); //2020-11-20
		
		//판매 or 구매
		goods.setGoodsBuyOrSell(goodsBuyOrSell);
		
		//카테고리 설정
		setCategory(memberNumber, nickname);		
		
	}

	private void setCategory(String memberNumber, String nickname) {
		
		while (flagCategory) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {			
			
				//상품 정보 보기
				viewGoodsInfo(nickname);
				
				BufferedReader categoryReader = new BufferedReader(new FileReader(Data.CATEGORY));
				
				String line = null;
				
				ArrayList<String> firstCategory = new ArrayList<String>();
				ArrayList<String> secondCategory = new ArrayList<String>();
				
				while ((line = categoryReader.readLine()) != null) {
					String[] temp = line.split("@");
					firstCategory.add(temp[0]); //상위 카테고리 저장
					secondCategory.add(temp[1]); //하위 카테고리 저장
				}
				
				categoryReader.close();
				
				//카테고리 1단계 선택하기
				System.out.println("============================================================");
				System.out.println("                     카테고리 선택");
				System.out.println("============================================================");
				
				for (int i=0; i<firstCategory.size(); i++) { //상위 카테고리 출력
					System.out.printf("%d. %s\t", i+1, firstCategory.get(i));
					if (i % 3 == 2) {
						System.out.println();
					}
				}
				
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <= firstCategory.size()) {
					
					while (flagSecondCategory) {
						
						//2단계 카테고리 선택하기
						System.out.println("============================================================");
						System.out.println("                     카테고리 선택");
						System.out.println("============================================================");
						
						String[] temp = secondCategory.get(input-1).split("■"); //선택한 상위 카테고리의 하위 카테고리들 담기
						
						for (int i=0; i<temp.length; i++) { //하위 카테고리 출력
							System.out.printf("%d. %s\t", i+1, temp[i]);
							if (i % 3 == 2) {
								System.out.println();
							}
						}
						
						System.out.println();
						System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
						System.out.println("나가기를 원하시면 -1을 입력하세요.");
						System.out.println("============================================================");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 > 0 && input2 <= temp.length) {
							//카테고리 입력하기
							goods.setGoodsCategory(temp[input2-1]);
							System.out.printf("카테고리에 \"%s\" 입력되었습니다.\n", temp[input2-1]);
							pause();
							setGoodsTitle(memberNumber, nickname);
							flagSecondCategory = false;
						} else if (input2 == 0) { //뒤로가기
							flagSecondCategory = false;
						} else if (input2 == -1) { //나가기
							flagCategory = false;
							flagSecondCategory = false;
						} else { //잘못입력
							wrongInput();
						}
						
					}
					
				} else if (input == 0) { //뒤로가기
					flagCategory = false;
				} else if (input == -1) { //나가기
					flagCategory = false;
				} else { //잘못 입력
					wrongInput();
				}
				
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setCategory()");
				wrongInput();
			}
		}

		
	} //setCategory

	private void setGoodsTitle(String memberNumber, String nickname) {
		
		while (flagGoodsTitle) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {			
				
				viewGoodsInfo(nickname);

				System.out.println("============================================================");
				System.out.println("                     제목 입력");
				System.out.println("============================================================");
				System.out.println("(최대 50자까지 입력 가능)");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				String input = scan.nextLine();
				
				if (!input.equals("0") && !input.equals("-1") && !input.equals("") && input.length() <= 50 && checkBannedWord(input)) { //업무
					goods.setGoodsTitle(input);
					System.out.printf("제목에 \"%s\" 입력되었습니다.\n", input);
					pause();
					setHashtag(memberNumber, nickname);
				} else if (input.equals("0")) { //뒤로가기
					goods.setGoodsCategory("");
					flagGoodsTitle = false;
				} else if (input.equals("-1")) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
				} else if (input.length() > 50) { //50자 초과
					System.out.println("50자 초과하였습니다. 다시 입력해주세요.");
					pause();
				} else if (checkBannedWord(input) == false) { //금지어 입력
					System.out.printf("제목에 금지어 \"%s\" 입력되었습니다. 다시 입력해주세요.\n", bannedWord);
					pause();			
				} else { //잘못 입력
					wrongInput();
				}
				
	
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setTitle()");
				wrongInput();
			}
		}
		
		
	} //setTitle

	private void setHashtag(String memberNumber, String nickname) {
		
		while (flagHashtag) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {
				
				viewGoodsInfo(nickname);

				System.out.println("============================================================");
				System.out.println("                     연관 태그 입력");
				System.out.println("============================================================");
				System.out.println("(최대 5개까지 입력 가능)");
				System.out.println("입력하지 않으시면 다음으로 넘어갑니다.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				String input = scan.nextLine();
				
				int countHashtag = 0;
				
				char[] temp = input.toCharArray();
				for (int i=0; i<temp.length; i++) {
					if (temp[i] == '#') {
						countHashtag++;
					}
				}
				
				if (countHashtag <= 5 && !input.equals("0") && !input.equals("-1") && checkBannedWord(input)) {
					goods.setGoodsHashtag(input);
					System.out.printf("연관 태그에 \"%s\" 입력되었습니다.\n", input);
					pause();
					setGoodsDetail(memberNumber, nickname);
				} else if (countHashtag > 5) { //5개 이상
					System.out.println("연관 태그를 5개 이하로 적어주세요.");
					pause();
				} else if (input.equals("0")) { //뒤로가기
					goods.setGoodsTitle("");
					flagHashtag = false;
				} else if (input.equals("-1")) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
				} else if (checkBannedWord(input) == false) { //금지어 입력
					System.out.printf("제목에 금지어 \"%s\" 입력되었습니다. 다시 입력해주세요.\n", bannedWord);
					pause();
				} else {
					wrongInput();
				}
			
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setHashtag()");
				wrongInput();
			}
		}
		
		
	} //setHashtag

	private void setGoodsDetail(String memberNumber, String nickname) {
		
		while (flagGoodsDetail) {

			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {
			
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                     상품 설명 입력");
				System.out.println("============================================================");
				System.out.println("(최대 500자까지 입력 가능)");
				System.out.println("입력을 마치려면 엔터를 한번 더 눌러주세요.");
				System.out.println("입력하지 않으시면 다음으로 넘어갑니다.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				//다중입력 받기
				Scanner scan = new Scanner(System.in);
				String input = "";
				
				while (true) {
					String temp = scan.nextLine();
					if (temp.equals("")) {
						break;
					}
					input += temp;
					input += "@";
				}
				
				if (!input.equals("")) {
					input = input.substring(0, input.length()-1); //뒤에 @ 1개 지움			
				}
				
				String temp = input.replace("@", " ");
				
				//500자 이하이고, 0 아니고, -1 아니고, 금지어 없으면
				if (temp.length() <= 500 && !input.equals("0") && !input.equals("-1") && checkBannedWord(input)) { 
					goods.setGoodsDetail(input);
					
					String[] detail = input.split("@");
					System.out.println("상품 설명에 다음과 같이 입력되었습니다.");
					System.out.println("=========================================================");
					for (int i=0; i<detail.length; i++) {
						System.out.println(detail[i]);
					}
					System.out.println("=========================================================");
					pause();
					setGoodsPrice(memberNumber, nickname);
				} else if (temp.length() > 500) { //500자 초과
					System.out.println("500자 초과되었습니다. 다시 입력해주세요.");
					pause();
				} else if (input.equals("0")) { //뒤로가기
					goods.setGoodsHashtag("");
					flagGoodsDetail = false;
				} else if (input.equals("-1")) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
				} else if (checkBannedWord(input) == false) { //금지어가 있는 경우
					System.out.printf("상품 설명에 금지어 \"%s\" 입력되었습니다. 다시 입력해주세요.\n", bannedWord);
					pause();
				} else { //잘못 입력
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setGoodsDetail()");
				wrongInput();
			}
		}
		
	} //setGoodsDetail

	private void setGoodsPrice(String memberNumber, String nickname) {
		
		while (flagGoodsPrice) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;

			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                     상품 가격 입력");
				System.out.println("============================================================");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (!(input == 0) && !(input == -1)) {
					goods.setGoodsPrice(input+"");
					System.out.printf("상품 가격에 \"%,d원\" 입력되었습니다.\n", input);
					pause();
					setAddress(memberNumber, nickname);
				} else if (input == 0) { //뒤로가기
					goods.setGoodsDetail("");
					flagGoodsPrice = false;
				} else if (input == -1) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
				} else { //잘못 입력
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setGoodsPrice()");
				wrongInput();
			}
		}
		
	} //setGoodsPrice

	private void setAddress(String memberNumber, String nickname) {
		
		
		while (flagAddress) {

			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			boolean flagResult = true;
			
			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                     선호거래지역 입력");
				System.out.println("============================================================");
				System.out.println("검색 방법 : 읍/면/동 단위로 입력해주세요.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				String input = scan.nextLine();
				
				if (!input.equals("0") && !input.equals("-1") && !input.equals("")) {
					
					BufferedReader reader = new BufferedReader(new FileReader(Data.ADDRESS));
					
					ArrayList<String> result = new ArrayList<String>();
					
					String line = null;
					
					while((line = reader.readLine()) != null) {
						String[] temp = line.split("■");
						if (temp[2].indexOf(input) > -1) {
							result.add(line);
						}
					}
					reader.close();
					
					while (flagResult) {
						if (result.size() != 0) { //검색결과 있음
							System.out.println("[검색 결과]");
							for (int i=0; i<result.size(); i++) {
								System.out.printf("%d. %s\n", i+1, result.get(i).replace("■", " "));
							}
							System.out.println();
							System.out.println("검색 결과에서 번호를 선택해주세요.");
							System.out.print("■입력 : ");
							
							int choiceNumber = Integer.parseInt(scan.nextLine());
							
							if (choiceNumber > 0 && choiceNumber <= result.size()) {
								String address = result.get(choiceNumber-1).replace("■", " "); //서울특별시■강남구■역삼동 -> 서울특별시 강남구 역삼동
								goods.setAddress(address);
								System.out.printf("선호거래지역에 \"%s\" 입력되었습니다.\n", address);
								pause();
								setGoodsState(memberNumber, nickname);
								flagResult = false;
							} else { //잘못 선택
								wrongInput();	
							}
							
						} else { //검색결과 없음
							System.out.println("검색 결과가 없습니다. 다시 입력해주세요.");
							pause();
							flagResult = false;
						}
					}
					
				} else if (input.equals("0")) { //뒤로가기
					goods.setGoodsPrice("");
					flagAddress = false;
				} else if (input.equals("-1")) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
					flagAddress = false;
					
				} else { //잘못 입력
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setAddress()");
				wrongInput();
			}
		}
		
	} //setAddress

	private void setGoodsState(String memberNumber, String nickname) {
		
		while (flagGoodsState) {

			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                     상품 상태 선택");
				System.out.println("============================================================");
				System.out.println("1. 새상품\t 2. 중고S급\t 3. 중고A급\t 4. 중고B급 이하");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				String[] temp = { "새상품", "중고S급", "중고A급", "중고B급 이하" };
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <= temp.length) {
					goods.setGoodsState(temp[input-1]);
					System.out.printf("상품 상태에 \"%s\" 입력되었습니다.\n", temp[input-1]);
					pause();
					setWayOfDeal(memberNumber, nickname);
				} else if (input == 0) { //뒤로가기
					goods.setAddress("");
					flagGoodsState = false;
				} else if (input == -1) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
					flagAddress = false;
					flagGoodsState = false;
				} else { //잘못입력
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setGoodsState()");
				wrongInput();
			}		
		}
		
		
	} //setGoodsState

	public void setWayOfDeal(String memberNumber, String nickname) {
		
		while (flagWayOfDeal) {

			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                     거래 방식 선택");
				System.out.println("============================================================");
				System.out.println("1. 직거래\t 2. 안전거래");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input == 1 || input == 2) {
					if (input == 1) { //직거래
						goods.setWayOfDeal("직거래");
						System.out.println("거래 방식에 \"직거래\" 입력되었습니다.");
						pause();
						registerFinalChoice(memberNumber, nickname);
					} else { //안전거래
						if (goods.getGoodsBuyOrSell().equals("판매")) { //판매자
							//계좌 검사
							if (isAccount(memberNumber, nickname) == true) { //계좌 있음
								goods.setWayOfDeal("안전거래");
								System.out.println("거래 방식에 \"안전거래\" 입력되었습니다.");
								pause();
								registerFinalChoice(memberNumber, nickname);
							} else { //계좌 없음
								//계좌 입력받기
								HashSet<String> virtualAccount = new HashSet<String>();
								if (addAccountInfo(memberNumber, nickname, virtualAccount) == true) { //계좌등록 성공
									goods.setWayOfDeal("안전거래");
									System.out.println("거래 방식에 \"안전거래\" 입력되었습니다.");
									pause();
									registerFinalChoice(memberNumber, nickname);									
								}
							}
						} else { //구매자가 안전거래 선택
							goods.setWayOfDeal("안전거래");
							System.out.println("거래 방식에 \"안전거래\" 입력되었습니다.");
							pause();
							registerFinalChoice(memberNumber, nickname);
							
						}
					}
				} else if (input == 0) { //뒤로가기
					goods.setGoodsState("");
					flagWayOfDeal = false;
				} else if (input == -1) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
					flagAddress = false;
					flagGoodsState = false;
					flagWayOfDeal = false;
				} else { //잘못입력
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.setWayOfDeal()");
				wrongInput();
			}
		}
		
		
	} //setWayOfDeal

	private void registerFinalChoice(String memberNumber, String nickname) {
		
		while (flagFinalChoice) {

			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;
			
			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                 최종 등록 하시겠습니까? ");
				System.out.println("============================================================");
				System.out.println("1. 예\t\t 2. 아니오");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input == 1 || input == 2) {
					
					if (input == 1) { //최종등록
						
						//상품 정보 파일에 쓰기
						BufferedWriter writer = new BufferedWriter(new FileWriter(Data.GOODSINFO, true));
						
						writer.write(goods.getGoodsNumber()); //상품번호
						writer.write("■");
						writer.write(goods.getRegisterDate()); //등록일
						writer.write("■");
						writer.write(goods.getGoodsCategory()); //카테고리
						writer.write("■");
						writer.write(goods.getWayOfDeal()); //거래방식
						writer.write("■");
						writer.write(goods.getDealState()); //거래상태
						writer.write("■");					
						writer.write(goods.getGoodsTitle()); //제목
						writer.write("■");
						writer.write(goods.getGoodsPrice()); //가격
						writer.write("■");
						writer.write(goods.getGoodsState()); //상품상태
						writer.write("■");
						writer.write(goods.getAddress()); //주소
						writer.write("■");
						writer.write(goods.getGoodsDetail()); //상품설명
						writer.write("■");
						writer.write(goods.getGoodsHashtag()); //연관태그
						writer.write("■");
						writer.write(goods.getGoodsLikeCount()); //찜한횟수
						writer.write("■");
						writer.write(goods.getGoodsComment()); //상품후기
						writer.write("■");
						writer.write(goods.getGoodsBuyOrSell()); //구매or판매
						writer.write("■");
						
						if (goods.getGoodsBuyOrSell().equals("구매")) {
							goods.setBuyerNumber(memberNumber);
							writer.write(goods.getBuyerNumber()); //구매자 회원번호						
							writer.write("■");
							writer.write(goods.getSellerNumber()); //판매자 회원번호
						} else {
							goods.setSellerNumber(memberNumber);
							writer.write(goods.getBuyerNumber()); //구매자 회원번호						
							writer.write("■");
							writer.write(goods.getSellerNumber()); //판매자 회원번호						
						}
						
						writer.newLine();
						
						writer.close();
						
						System.out.println("최종 등록되었습니다. 이전화면으로 돌아갑니다.");
						pause();
						this.flagCategory = false;
						this.flagSecondCategory = false;
						this.flagGoodsTitle = false;
						this.flagHashtag = false;
						this.flagGoodsDetail = false;
						this.flagGoodsPrice = false;
						this.flagAddress = false;
						this.flagGoodsState = false;
						this.flagWayOfDeal = false;
						this.flagFinalChoice = false;
						
					} else { //아니오
						System.out.println("등록이 취소되었습니다. 이전 화면으로 돌아갑니다.");
						pause();
						this.flagCategory = false;
						this.flagSecondCategory = false;
						this.flagGoodsTitle = false;
						this.flagHashtag = false;
						this.flagGoodsDetail = false;
						this.flagGoodsPrice = false;
						this.flagAddress = false;
						this.flagGoodsState = false;
						this.flagWayOfDeal = false;
						this.flagFinalChoice = false;
					}
				} else {
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.registerFinalChoice()");
				wrongInput();
			}
		}
		

		
	} //registerfinalChoice

	public boolean addAccountInfo(String memberNumber, String nickname, HashSet<String> virtualAccount) {
		
		boolean flag = true;
		
		while (flag) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;

			try {
				
				viewGoodsInfo(nickname);
				
				MemberAddInformation memberAddInfo = new MemberAddInformation();
				
				//은행명 선택하기
				String[] bankName = { "국민", "우리", "기업", "수협", "카카오뱅크", "씨티", "광주", "부산", "제주", "신한", "농협", "SC제일", "산업", "K뱅크", "경남", "대구", "전북" };
				
				System.out.println("============================================================");
				System.out.println("                 계좌 정보 입력 ");
				System.out.println("============================================================");
				System.out.println("계좌 정보가 없습니다. 계좌 정보를 입력합니다.");
				System.out.println("<은행 선택>");
				
				for (int i=0; i<bankName.length; i++) {
					System.out.printf("%d. %s\t", i+1, bankName[i]);
					if (i % 5 == 4) {
						System.out.println();
					}
				}
				System.out.println();
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.println("============================================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <= bankName.length) {
					memberAddInfo.setSellerBankName(bankName[input-1]); //은행명 저장
					//계좌번호 입력하기
					addAccountNumber(memberNumber, nickname, memberAddInfo, virtualAccount);	
				} else if (input == 0) { //뒤로가기
					flag = false;	
				} else if (input == -1) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
					flagAddress = false;
					flagGoodsState = false;
					flagWayOfDeal = false;
					flag = false;
				} else {
					wrongInput();
				}
				
			
			} catch (Exception e) {
				System.out.println("RegistGoods.addAccountInfo()");
				wrongInput();
			}
		}
		
		return flag;
		
	} //addAccountInfo

	public void addAccountNumber(String memberNumber, String nickname, MemberAddInformation memberAddInfo, HashSet<String> virtualAccount) {
		
		boolean flag = true;
		
		while (flag) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagFinalChoice = true;

			try {
				
				viewGoodsInfo(nickname);
				
				System.out.println("============================================================");
				System.out.println("                 계좌 정보 입력 ");
				System.out.println("============================================================");
				System.out.printf("은행명 : %s\n", memberAddInfo.getSellerBankName());
				System.out.println("계좌번호 13자리 입력해주세요.");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("나가기를 원하시면 -1을 입력하세요.");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				String input = scan.nextLine();
				String account = input.replace("-", "");
				
				if (account.length() == 13) { //13자리이면
					
					boolean flag2 = true;
					
					for (int i=0; i<account.length(); i++) { //숫자인지 검사
						if (account.charAt(i) >= '0' && account.charAt(i) <= '9') { //숫자
						} else { //숫자아님
							flag2 = false;
							break;
						}
					}

					if (flag2 == true) { //13자리 숫자
						//검사 끝(13자리 숫자O)
						memberAddInfo.setSellerAccount(account); //계좌번호 저장
						
						memberAddInfo.setVirtualBankName("가든"); //가상은행명 저장
						
						Random rnd = new Random();
						
						BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERADDINFO));
						
						ArrayList<String> list = new ArrayList<String>(); //회원 부가정보 리스트
						
						String line = null;
						
						while ((line = reader.readLine()) != null) {
							list.add(line); //회원 부가정보 리스트 담기
							virtualAccount.add(line.split("■", 9)[3]);
							
						}
						//12자리 가상계좌 주기
						
						Iterator<String> iter = virtualAccount.iterator();
						
						while (iter.hasNext()) {
							String temp = "110" + (rnd.nextInt(900000000) + 100000000);
							if (!temp.equals(iter.next())) {
								memberAddInfo.setVirtualAccount(temp); //가상계좌 저장
								break;
							}
						}
						
						/*
						
						실계좌은행명, 실계좌번호, 가상계좌은행명, 가상계좌번호(완료)
						매너온도(0~5), 회원등급(씨앗,새싹,풀,꽃,나무), 구매횟수, 판매횟수, 회원고유번호(1-1)
						
						*/
						
						reader.close();
						
						//list : 회원 부가정보 리스트
						//해당 회원의 부가정보 저장하기(update)
						BufferedWriter writer = new BufferedWriter(new FileWriter(Data.MEMBERADDINFO));
						
						for (int i=0; i<list.size(); i++) {
							String[] temp = list.get(i).split("■", 9);
							if (memberNumber.equals(temp[8])) {
								list.set(i, String.format("%s■%s■%s■%s■%d■%s■%d■%d■%s"
										, memberAddInfo.getSellerBankName()
										, memberAddInfo.getSellerAccount()
										, memberAddInfo.getVirtualBankName()
										, memberAddInfo.getVirtualAccount()
										, Integer.parseInt(temp[4])
										, temp[5]
										, Integer.parseInt(temp[6])
										, Integer.parseInt(temp[7])
										, temp[8]));
							}
							
							writer.write(list.get(i));
							writer.newLine();
						}
						
						writer.close();

						System.out.println("계좌 정보가 저장되었습니다.");
						pause();
					} else { //숫자X
						wrongInput();
					}
					
				} else if (input.equals("0")) { //뒤로가기
					memberAddInfo.setSellerBankName("");
					flag = false;
				} else if (input.equals("-1")) { //나가기
					flagCategory = false;
					flagSecondCategory = false;
					flagGoodsTitle = false;
					flagHashtag = false;
					flagGoodsDetail = false;
					flagGoodsPrice = false;
					flagAddress = false;
					flagGoodsState = false;
					flagWayOfDeal = false;
					flag = false;
				} else { //13자리 아님
					wrongInput();
				}
				
			
				
			} catch (Exception e) {
				System.out.println("RegistGoods.addAccountNumber()");
				wrongInput();
			}
		}
		
		
	} //addAccountNumber

	public boolean isAccount(String memberNumber, String nickname) {
		
		boolean flag = false;
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERADDINFO));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■", 9);
				if (memberNumber.equals(temp[8])) { //일치
					flag = true;
					break;
				}
			}
			reader.close();
			
		} catch (Exception e) {
			wrongInput();
			isAccount(memberNumber, nickname);
		}
		
		return flag;
		
	} //isAccount

	public void viewGoodsInfo(String nickname) {
		System.out.println("============================================================");
		System.out.println("                    상품 등록 정보");
		System.out.println("============================================================");
		System.out.printf("상품 번호 : %s\n", goods.getGoodsNumber());
		System.out.printf("카테고리 : %s\n", goods.getGoodsCategory());
		System.out.printf("제목 : %s\n", goods.getGoodsTitle());
		System.out.printf("연관 태그 : %s\n", goods.getGoodsHashtag());
		
		String[] temp = goods.getGoodsDetail().split("@");
		System.out.println("상품 설명 : " + temp[0]);
		for (int i=1; i<temp.length; i++) {
			System.out.println("\t    " + temp[i]);
		}
		
		System.out.printf("상품 가격 : %,d원\n", Integer.parseInt(goods.getGoodsPrice()));
		System.out.printf("선호거래지역 : %s\n", goods.getAddress());
		System.out.printf("상품 상태 : %s\n", goods.getGoodsState());
		System.out.printf("거래 방식 : %s\n", goods.getWayOfDeal());
		System.out.printf("거래 상태 : %s\n", goods.getDealState());
		System.out.printf("최종 등록일 : %s\n", goods.getRegisterDate());
		System.out.printf("닉네임 : %s\n", nickname);
	} //viewGoodsInfo
	
	public boolean checkBannedWord(String input) {
		
		boolean flag = true;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.BANWORD));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				if (input.indexOf(temp[1]) != -1) { //금지어 목록에 있는 단어가 input에 포함되어 있으면
					this.bannedWord = temp[1];
					flag = false;
					break;
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
