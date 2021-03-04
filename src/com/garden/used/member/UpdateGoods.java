package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;

import com.garden.used.data.Data;

public class UpdateGoods extends RegistGoods {
	
	private String bannedWord;
	private boolean flagCategory;
	private boolean flagSecondCategory;
	private boolean flagGoodsTitle;
	private boolean flagHashtag;
	private boolean flagGoodsDetail;
	private boolean flagGoodsPrice;
	private boolean flagAddress;
	private boolean flagGoodsState;
	private boolean flagWayOfDeal;
	private boolean flagDealState;
	private boolean flagExit;
	
	public UpdateGoods() {
		
		this.flagCategory = true;
		this.flagSecondCategory = true;
		this.flagGoodsTitle = true;
		this.flagHashtag = true;
		this.flagGoodsDetail = true;
		this.flagGoodsPrice = true;
		this.flagAddress = true;
		this.flagGoodsState = true;
		this.flagWayOfDeal = true;
		this.flagDealState = true;
		this.flagExit = false;
		
	}
	//상품수정
	//전체 상품 list에 저장(Goods 클래스 타입으로)
	//회원 상품 가져와서 출력해주고
	//선택한 상품에 대한 정보들 가져와서
	//수정 항목 선택한 걸 update해주고
	//DB파일에 다시 적용(list를 순차적으로)

	
	public void update(Goods goods, String memberNumber, String nickname) {
		
		String[] select = { "카테고리", "상품 제목", "연관 태그", "상품 설명", "상품 가격", "선호거래지역", "상품 상태", "거래 방식", "거래 상태" };
		
		while (!flagExit) {
			
			this.flagCategory = true;
			this.flagSecondCategory = true;
			this.flagGoodsTitle = true;
			this.flagHashtag = true;
			this.flagGoodsDetail = true;
			this.flagGoodsPrice = true;
			this.flagAddress = true;
			this.flagGoodsState = true;
			this.flagWayOfDeal = true;
			this.flagDealState = true;
			this.flagExit = false;
			
			viewGoodsInfo(goods, memberNumber, nickname);
			
			System.out.println("==============================================================================");
			System.out.println("\t\t\t\t수정 항목 선택");
			System.out.println("==============================================================================");
			
			
			for (int i=0; i<select.length; i++) {
				System.out.printf("%d. %s\t", i+1, select[i]);
				if (i % 5 == 4) {
					System.out.println();
				}
				if (i == select.length-1) {
					System.out.println();
				}
			}
			
			System.out.println();
			System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
			System.out.println("==============================================================================");
			System.out.print("■입력 : ");
			
			try {
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <= select.length) {
					//"카테고리", "상품 제목", "연관 태그", "상품 설명", "상품 가격", "선호거래지역", "상품 상태", "거래 방식", "거래 상태"
					switch (input) {
						case 1:
							updateCategory(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 2:
							updateTitle(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 3:
							updateHashtag(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 4:
							updateDetail(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 5:
							updatePrice(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 6:
							updateAddress(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 7:
							updateGoodsState(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 8:
							updateWayOfDeal(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
						case 9:
							updateDealState(goods, memberNumber, nickname);
							updateGoodsList(goods);
							break;
					}

					
				} else if (input == 0) { //뒤로가기
					flagExit = true;
				} else {
					wrongInput();				
				}
				
			} catch (Exception e) {
				wrongInput();
			}
			
		}
		
	} //update
	
	private void updateDealState(Goods goods, String memberNumber, String nickname) {
		
		while (flagDealState) {
			
			try {
				
				if (goods.getWayOfDeal().equals("직거래")) {
					
					System.out.println("============================================================");
					System.out.println("                     거래 상태 선택");
					System.out.println("============================================================");
					System.out.println("1. 거래가능\t\t 2. 예약중\t\t 3. 거래완료");
					System.out.println("★★★주의!! 거래완료로 변경 시 상품을 더이상 수정할 수 없습니다.");
					System.out.println();
					System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
					System.out.println("나가기를 원하시면 -1을 입력하세요.");
					System.out.println("============================================================");
					System.out.print("■입력 : ");
					
					Scanner scan = new Scanner(System.in);
					int input = Integer.parseInt(scan.nextLine());
					
					if (input > 0 && input <= 3) {
						if (input == 1) { //거래가능
							boolean flag = true;
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     거래 상태 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 거래 상태 : %s\n", goods.getDealState());
								System.out.printf("수정 후 거래 상태 : %s\n", "거래가능");
								System.out.println();
								System.out.println("수정하시겠습니까?");
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input2 = Integer.parseInt(scan.nextLine());
								
								if (input2 == 1 || input2 == 2) {
									if (input2 == 1) { //예
										goods.setDealState("거래가능");
										System.out.printf("거래 상태에 \"%s\" 입력되었습니다.\n", "거래가능");
										flag = false;
										flagDealState = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagDealState = false;
										pause();
									}
								} else { //잘못입력
									wrongInput();
								}
							}
						} else if (input == 2) { //예약중
							boolean flag = true;
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     거래 상태 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 거래 상태 : %s\n", goods.getDealState());
								System.out.printf("수정 후 거래 상태 : %s\n", "예약중");
								System.out.println();
								System.out.println("수정하시겠습니까?");
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input2 = Integer.parseInt(scan.nextLine());
								
								if (input2 == 1 || input2 == 2) {
									if (input2 == 1) { //예
										goods.setDealState("예약중");
										System.out.printf("거래 상태에 \"%s\" 입력되었습니다.\n", "예약중");
										flag = false;
										flagDealState = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagDealState = false;
										pause();
									}
								} else { //잘못입력
									wrongInput();
								}
							}
						} else { //거래완료
							boolean flag = true;
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     거래 상태 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 거래 상태 : %s\n", goods.getDealState());
								System.out.printf("수정 후 거래 상태 : %s\n", "거래완료");
								System.out.println();
								System.out.println("★★★주의!! 거래완료로 변경 시 상품을 더이상 수정할 수 없습니다.");
								System.out.println("수정하시겠습니까?");
								System.out.println();
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input2 = Integer.parseInt(scan.nextLine());
								
								if (input2 == 1 || input2 == 2) {
									if (input2 == 1) { //예
										goods.setDealState("거래완료");
										System.out.printf("거래 상태에 \"%s\" 입력되었습니다.\n", "거래완료");
										flag = false;
										flagDealState = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagDealState = false;
										pause();
									}
								} else { //잘못입력
									wrongInput();
								}
							}
						}
						
					} else if (input == 0) { //뒤로가기
						flagDealState= false;
					} else if (input == -1) { //나가기
						flagDealState = false;
						flagExit = true;
					} else {
						wrongInput();
					}
					
				} else { //안전거래
					System.out.println("안전거래의 경우 거래상태를 변경할 수 없습니다.");
					flagDealState = false;
					pause();
				}
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateDealState()");
				wrongInput();
			}
			
		}	
		
	} //updateDealState

	private void updateWayOfDeal(Goods goods, String memberNumber, String nickname) {
		
		while (flagWayOfDeal) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
						boolean flag = true;
						while (flag) {
							System.out.println("============================================================");
							System.out.println("                     거래 방식 수정");
							System.out.println("============================================================");
							System.out.printf("수정 전 거래 방식 : %s\n", goods.getWayOfDeal());
							System.out.printf("수정 후 거래 방식 : %s\n", "직거래");
							System.out.println();
							System.out.println("수정하시겠습니까?");
							System.out.println("1. 예 \t\t 2. 아니오");
							System.out.print("■입력 : ");
							
							int input2 = Integer.parseInt(scan.nextLine());
							
							if (input2 == 1 || input2 == 2) {
								if (input2 == 1) { //예
									goods.setWayOfDeal("직거래");
									System.out.printf("거래 방식에 \"%s\" 입력되었습니다.\n", "직거래");
									flag = false;
									flagWayOfDeal = false;
									pause();
								} else { //아니오
									System.out.println("수정이 취소되었습니다.");
									flag = false;
									flagWayOfDeal = false;
									pause();
								}
							} else { //잘못입력
								wrongInput();
							}
						}
						
					} else { //안전거래
						if (goods.getGoodsBuyOrSell().equals("판매")) { //판매자
							//계좌 검사
							if (isAccount(memberNumber, nickname) == true) { //계좌 있음
								boolean flag = true;
								while (flag) {
									System.out.println("============================================================");
									System.out.println("                     거래 방식 수정");
									System.out.println("============================================================");
									System.out.printf("수정 전 거래 방식 : %s\n", goods.getWayOfDeal());
									System.out.printf("수정 후 거래 방식 : %s\n", "안전거래");
									System.out.println();
									System.out.println("수정하시겠습니까?");
									System.out.println("1. 예 \t\t 2. 아니오");
									System.out.print("■입력 : ");
									
									int input2 = Integer.parseInt(scan.nextLine());
									
									if (input2 == 1 || input2 == 2) {
										if (input2 == 1) { //예
											goods.setWayOfDeal("안전거래");
											System.out.printf("거래 방식에 \"%s\" 입력되었습니다.\n", "안전거래");
											flag = false;
											flagWayOfDeal = false;
											pause();
										} else { //아니오
											System.out.println("수정이 취소되었습니다.");
											flag = false;
											flagWayOfDeal = false;
											pause();
										}
									} else { //잘못입력
										wrongInput();
									}
								}
								
							} else { //계좌 없음
								//계좌 입력받기
								HashSet<String> virtualAccount = new HashSet<String>();
								if (addAccountInfo(memberNumber, nickname, virtualAccount) == true) { //계좌등록 성공
									boolean flag = true;
									while (flag) {
										System.out.println("============================================================");
										System.out.println("                     거래 방식 수정");
										System.out.println("============================================================");
										System.out.printf("수정 전 거래 방식 : %s\n", goods.getWayOfDeal());
										System.out.printf("수정 후 거래 방식 : %s\n", "안전거래");
										System.out.println();
										System.out.println("수정하시겠습니까?");
										System.out.println("1. 예 \t\t 2. 아니오");
										System.out.print("■입력 : ");
										
										int input2 = Integer.parseInt(scan.nextLine());
										
										if (input2 == 1 || input2 == 2) {
											if (input2 == 1) { //예
												goods.setWayOfDeal("안전거래");
												System.out.printf("거래 방식에 \"%s\" 입력되었습니다.\n", "안전거래");
												flag = false;
												flagWayOfDeal = false;
												pause();
											} else { //아니오
												System.out.println("수정이 취소되었습니다.");
												flag = false;
												flagWayOfDeal = false;
												pause();
											}
										} else { //잘못입력
											wrongInput();
										}
									}
																	
								}
							}
						} else { //구매자가 안전거래 선택
							boolean flag = true;
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     거래 방식 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 거래 방식 : %s\n", goods.getWayOfDeal());
								System.out.printf("수정 후 거래 방식 : %s\n", "안전거래");
								System.out.println();
								System.out.println("수정하시겠습니까?");
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input2 = Integer.parseInt(scan.nextLine());
								
								if (input2 == 1 || input2 == 2) {
									if (input2 == 1) { //예
										goods.setWayOfDeal("안전거래");
										System.out.printf("거래 방식에 \"%s\" 입력되었습니다.\n", "안전거래");
										flag = false;
										flagWayOfDeal = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagWayOfDeal = false;
										pause();
									}
								} else { //잘못입력
									wrongInput();
								}
							}
							
						}
					}
				} else if (input == 0) { //뒤로가기
					flagWayOfDeal = false;
				} else if (input == -1) { //나가기
					flagWayOfDeal = false;
					flagExit = true;
				} else { //잘못입력
					wrongInput();
				}
				
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateWayOfDeal()");
				wrongInput();
			}
			
		}
		
	} // updateWayOfDeal

	private void updateGoodsState(Goods goods, String memberNumber, String nickname) {
	
		while (flagGoodsState) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
					boolean flag = true;
					
					while (flag) {
						System.out.println("============================================================");
						System.out.println("                     상품 상태 수정");
						System.out.println("============================================================");
						System.out.printf("수정 전 상품 상태 : %s\n", goods.getGoodsState());
						System.out.printf("수정 후 상품 상태 : %s\n", temp[input-1]);
						System.out.println();
						System.out.println("수정하시겠습니까?");
						System.out.println("1. 예 \t\t 2. 아니오");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 == 1 || input2 == 2) {
							if (input2 == 1) { //예
								goods.setGoodsState(temp[input-1]);
								System.out.printf("상품 상태에 \"%s\" 입력되었습니다.\n", temp[input-1]);
								flag = false;
								flagGoodsState = false;
								pause();
							} else { //아니오
								System.out.println("수정이 취소되었습니다.");
								flag = false;
								flagGoodsState = false;
								pause();
							}
						} else {
							wrongInput();
						}
					}
					
				} else if (input == 0) { //뒤로가기
					flagGoodsState = false;
				} else if (input == -1) { //나가기
					flagGoodsState = false;
					flagExit = true;
				} else { //잘못입력
					wrongInput();
				}		
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateGoodsState()");
				wrongInput();
			}
			
		}
		
	} //updateGoodsState

	private void updateAddress(Goods goods, String memberNumber, String nickname) {
		
		while (flagAddress) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
							result.add(line); //검색한 동/읍/면과 일치하는 주소 결과 저장하기
						}
					}
					reader.close();
					
					if (result.size() != 0) { //검색결과 있음
						System.out.println("[검색 결과]");
						for (int i=0; i<result.size(); i++) {
							System.out.printf("%d. %s\n", i+1, result.get(i).replace("■", " "));
						}
						System.out.println();
						System.out.println("검색 결과에서 번호를 선택해주세요.");
						System.out.print("■입력 : ");
						
						int choiceNumber = Integer.parseInt(scan.nextLine());
						
						
						if (choiceNumber > 0 && choiceNumber <= result.size()) { //검색결과 내 번호 선택

							String address = result.get(choiceNumber-1).replace("■", " "); //서울특별시■강남구■역삼동 -> 서울특별시 강남구 역삼동
							
							boolean flag = true;
							
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     선호거래지역 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 선호거래지역 : %s\n", goods.getAddress());
								System.out.printf("수정 후 선호거래지역 : %s\n", address);
								System.out.println();
								System.out.println("수정하시겠습니까?");
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input2 = Integer.parseInt(scan.nextLine());
								
								if (input2 == 1 || input2 == 2) {
									if (input2 == 1) { //예
										goods.setAddress(address);
										System.out.printf("선호거래지역에 \"%s\" 입력되었습니다.\n", address);
										flag = false;
										flagAddress = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagAddress = false;
										pause();
									}
								} else {
									wrongInput();
								}
							}
							
						} else { //검색결과 번호 이외 입력
							wrongInput();
						}
						
					} else { //검색결과 없음
						System.out.println("검색 결과가 없습니다. 다시 입력해주세요.");
						pause();
					}
					
				} else if (input.equals("0")) { //뒤로가기
					flagAddress = false;
				} else if (input.equals("-1")) { //나가기
					flagAddress = false;
					flagExit = true;
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateAddress()");
				wrongInput();
			}
			
		}
		
	} //updateAddress

	private void updatePrice(Goods goods, String memberNumber, String nickname) {
		
		while (flagGoodsPrice) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
					
					boolean flag = true;
					
					while (flag) {
						System.out.println("============================================================");
						System.out.println("                     상품 가격 수정");
						System.out.println("============================================================");
						System.out.printf("수정 전 상품 가격 : %,d원\n", Integer.parseInt(goods.getGoodsPrice()));
						System.out.printf("수정 후 상품 가격 : %,d원\n", input);
						System.out.println();
						System.out.println("수정하시겠습니까?");
						System.out.println("1. 예 \t\t 2. 아니오");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 == 1 || input2 == 2) {
							if (input2 == 1) { //예
								goods.setGoodsPrice(input+"");
								System.out.printf("상품 가격에 \"%,d원\" 입력되었습니다.\n", input);
								flag = false;
								flagGoodsPrice = false;
								pause();
							} else { //아니오
								System.out.println("수정이 취소되었습니다.");
								flag = false;
								flagGoodsPrice = false;
								pause();
							}
						} else { //잘못입력
							wrongInput();
						}
					}
					
				} else if (input == 0) { //뒤로가기
					flagGoodsPrice = false;
				} else if (input == -1) { //나가기
					flagGoodsPrice = false;
					flagExit = true;
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updatePrice()");
				wrongInput();
			}
			
		}
		
	} //updatePrice

	private void updateDetail(Goods goods, String memberNumber, String nickname) {
		
		while (flagGoodsDetail) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
				
				if (temp.length() <= 500 && !input.equals("0") && !input.equals("-1") && checkBannedWord(input)) { //500자 이하이고, 0 아니고, -1 아니고, 금지어 없으면
					
					boolean flag = true;
					
					while (flag) {
						System.out.println("============================================================");
						System.out.println("                     상품 설명 수정");
						System.out.println("============================================================");
						
						String[] beforeDetail = goods.getGoodsDetail().split("@");
						System.out.println("수정 전 상품 설명 : " + beforeDetail[0]);
						System.out.println("=========================================================");
						for (int i=1; i<beforeDetail.length; i++) {
							System.out.println("\t\t    " + beforeDetail[i]);
						}
						System.out.println("=========================================================");
						
						String[] afterDetail = input.split("@");
						System.out.println("수정 후 상품 설명 : " + afterDetail[0]);
						System.out.println("=========================================================");
						for (int i=1; i<afterDetail.length; i++) {
							System.out.println("\t\t    " + afterDetail[i]);
						}
						System.out.println("=========================================================");
						
						System.out.println();
						System.out.println("수정하시겠습니까?");
						System.out.println("1. 예 \t\t 2. 아니오");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 == 1 || input2 == 2) {
							if (input2 == 1) { //예
								goods.setGoodsDetail(input);
								System.out.println("상품 설명에 다음과 같이 입력되었습니다.");
								System.out.println("=========================================================");
								for (int i=0; i<afterDetail.length; i++) {
									System.out.println(afterDetail[i]);
								}
								System.out.println("=========================================================");
								flag = false;
								flagGoodsDetail = false;
								pause();
							} else { //아니오
								System.out.println("수정이 취소되었습니다.");
								flag = false;
								flagGoodsDetail = false;
								pause();
							}
						} else { //잘못입력
							wrongInput();
						}
					}
					
				} else if (temp.length() > 500) { //500자 초과
					System.out.println("500자 초과되었습니다. 다시 입력해주세요.");
					pause();
				} else if (input.equals("0")) { //뒤로가기
					flagGoodsDetail = false;
				} else if (input.equals("-1")) { //나가기
					flagGoodsDetail = false;
					flagExit = true;
				} else if (checkBannedWord(input) == false) { //금지어가 있는 경우
					System.out.printf("상품 설명에 금지어 \"%s\" 입력되었습니다. 다시 입력해주세요.\n", bannedWord);
					pause();
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateDetail()");
				wrongInput();
			}
			
		}
		
	} //updateDetail

	private void updateHashtag(Goods goods, String memberNumber, String nickname) {
		
		while (flagHashtag) {
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);

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
					
					boolean flag = true;
					
					while (flag) {
						System.out.println("============================================================");
						System.out.println("                     연관 태그 수정");
						System.out.println("============================================================");
						System.out.printf("수정 전 연관 태그 : %s\n", goods.getGoodsHashtag());
						System.out.printf("수정 후 연관 태그 : %s\n", input);
						System.out.println();
						System.out.println("수정하시겠습니까?");
						System.out.println("1. 예 \t\t 2. 아니오");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 == 1 || input2 == 2) {
							if (input2 == 1) { //예
								goods.setGoodsHashtag(input);
								System.out.printf("연관 태그에 \"%s\" 입력되었습니다.\n", input);
								flag = false;
								flagHashtag = false;
								pause();
							} else { //아니오
								System.out.println("수정이 취소되었습니다.");
								flag = false;
								flagHashtag = false;
								pause();
							}
						} else { //잘못입력
							wrongInput();
						}
					}
					
				} else if (countHashtag > 5) { //5개 이상
					System.out.println("연관 태그를 5개 이하로 적어주세요.");
					pause();				
				} else if (checkBannedWord(input) == false) { //금지어 입력
					System.out.printf("연관 태그에 금지어 \"%s\" 입력되었습니다. 다시 입력해주세요.\n", bannedWord);
					pause();
				} else if (input.equals("0")) { //뒤로가기
					flagHashtag = false;
				} else if (input.equals("-1")) { //나가기
					flagHashtag = false;
					flagExit = true;
				} else { //잘못입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("UpdateGoods.updateHashtag()");
				wrongInput();
			}
			
		}
		
	} //updateHashtag

	private void updateTitle(Goods goods, String memberNumber, String nickname) {

		while (flagGoodsTitle) {	
			
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);

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
					
					boolean flag = true;
					
					while (flag) {

						System.out.println("============================================================");
						System.out.println("                     제목 수정");
						System.out.println("============================================================");
						System.out.printf("수정 전 제목 : %s\n", goods.getGoodsTitle());
						System.out.printf("수정 후 제목 : %s\n", input);
						System.out.println();
						System.out.println("수정하시겠습니까?");
						System.out.println("1. 예 \t\t 2. 아니오");
						System.out.print("■입력 : ");
						
						int input2 = Integer.parseInt(scan.nextLine());
						
						if (input2 == 1 || input2 == 2) {
							if (input2 == 1) { //예
								goods.setGoodsTitle(input);
								System.out.printf("제목에 \"%s\" 입력되었습니다.\n", input);
								flag = false;
								flagGoodsTitle = false;
								pause();
							} else { //아니오
								System.out.println("수정이 취소되었습니다.");
								flag = false;
								flagGoodsTitle = false;
								pause();
							}
						} else { //잘못입력
							wrongInput();
						}
					}

				} else if (input.equals("0")) { //뒤로가기
					flagGoodsTitle = false;
				} else if (input.equals("-1")) { //나가기
					flagGoodsTitle = false;
					flagExit = true;
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
				System.out.println("UpdateGoods.updateTitle()");
				wrongInput();
			}
			
		}
		
		
	} //updateTitle

	private void updateCategory(Goods goods, String memberNumber, String nickname) {
		
		while (flagCategory) {
			
			flagSecondCategory = true;
			
			try {
				
				//상품 정보 보기
				viewGoodsInfo(goods, memberNumber, nickname);
				
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
							//카테고리 수정하기
							boolean flag = true;
							
							while (flag) {
								System.out.println("============================================================");
								System.out.println("                     카테고리 수정");
								System.out.println("============================================================");
								System.out.printf("수정 전 카테고리 : %s\n", goods.getGoodsCategory());
								System.out.printf("수정 후 카테고리 : %s\n", temp[input2-1]);
								System.out.println();
								System.out.println("수정하시겠습니까?");
								System.out.println("1. 예 \t\t 2. 아니오");
								System.out.print("■입력 : ");
								
								int input3 = Integer.parseInt(scan.nextLine());
								
								if (input3 == 1 || input3 == 2) {
									if (input3 == 1) { //예
										goods.setGoodsCategory(temp[input2-1]);
										System.out.printf("카테고리에 \"%s\" 입력되었습니다.\n", temp[input2-1]);
										flag = false;
										flagCategory = false;
										flagSecondCategory = false;
										pause();
									} else { //아니오
										System.out.println("수정이 취소되었습니다.");
										flag = false;
										flagCategory = false;
										flagSecondCategory = false;
										pause();
									}
								} else {
									wrongInput();
								}
							}

						} else if (input2 == 0) { //뒤로가기
							flagSecondCategory = false;
						} else if (input2 == -1) { //나가기
							flagCategory = false;
							flagSecondCategory = false;
							flagExit = true;
						} else { //잘못입력
							wrongInput();
						}
						
					}

				} else if (input == 0) { //뒤로가기
					flagCategory = false;
				} else if (input == -1) { //나가기
					flagCategory = false;
					flagExit = true;
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
	} //updateCategory

	public void viewGoodsInfo(Goods goods, String memberNumber, String nickname) {

		System.out.println("============================================================");
		System.out.println("                       상품 정보");
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
	
	public void updateGoodsList(Goods goods) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
			
			ArrayList<String> list = new ArrayList<String>();
			
			goods.setRegisterDate(String.format("%tF", Calendar.getInstance()));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) { //상품정보DB 읽어오기
				String[] temp = line.split("■", 16);
				if (goods.getGoodsNumber().equals(temp[0])) { //상품번호가 같으면
					line = String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s"
										, goods.getGoodsNumber()
										, goods.getRegisterDate()
										, goods.getGoodsCategory()
										, goods.getWayOfDeal()
										, goods.getDealState()
										, goods.getGoodsTitle()
										, goods.getGoodsPrice()
										, goods.getGoodsState()
										, goods.getAddress()
										, goods.getGoodsDetail()
										, goods.getGoodsHashtag()
										, goods.getGoodsLikeCount()
										, goods.getGoodsComment()
										, goods.getGoodsBuyOrSell()
										, goods.getBuyerNumber()
										, goods.getSellerNumber());
				}
				list.add(line);
			}
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.GOODSINFO));
			
			for (int i=0; i<list.size(); i++) {
				writer.write(list.get(i));
				writer.newLine();
			}
			
			writer.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //getGoodsList
	
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
	} //checkBannedWord
	
} //UpdateGoods
