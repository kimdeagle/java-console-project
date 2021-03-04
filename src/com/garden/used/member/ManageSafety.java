package com.garden.used.member;

import java.util.Scanner;

public class ManageSafety extends UpdateGoods {

	
	public void manageBuySafetyGoods(Goods goods, String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
				System.out.println("=============================================");
				System.out.println("             안전거래 관리");
				System.out.println("=============================================");
				System.out.println("1. 입금하기\t\t 2. 구매 확정하기");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input > 0 && input <= 2) {
					if (input == 1) { //입금하기
						System.out.println("입금이 완료되었습니다.");
						goods.setDealState("예약중");
						//실제 파일에 적용(writer 덮어쓰기)
						updateGoodsList(goods);
						flag = false;
						pause();
					} else if (input == 2) { //구매확정
						System.out.println("구매확정 되었습니다.");
						goods.setDealState("거래완료");
						//실제 파일에 적용(writer 덮어쓰기)
						updateGoodsList(goods);
						flag = false;
						pause();
					}
				
				} else if (input == 0) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("ManageSafety.manageBuySafetyGoods()");
				wrongInput();
			}
		}
	
	}
	
	public void manageSellSafetyGoods(Goods goods, String memberNumber, String nickname) {
		
		boolean flag = true;
		
		while (flag) {
			try {
				
				viewGoodsInfo(goods, memberNumber, nickname);
				
				System.out.println("=============================================");
				System.out.println("             안전거래 관리");
				System.out.println("=============================================");
				System.out.println("1. 입금 확인하기");
				System.out.println();
				System.out.println("뒤로가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================");
				System.out.print("■입력 : ");
				
				Scanner scan = new Scanner(System.in);
				int input = Integer.parseInt(scan.nextLine());
				
				if (input == 1) { //입금 확인하기
					System.out.println("입금이 확인되었습니다.");
					//실제 파일에 적용(writer 덮어쓰기)
					updateGoodsList(goods);
					flag = false;
					pause();
				} else if (input == 0) { //뒤로가기
					flag = false;
				} else { //잘못 입력
					wrongInput();
				}
				
			} catch (Exception e) {
				System.out.println("ManageSafety.manageSellSafetyGoods()");
				wrongInput();
			}
		}
	
	}
	
}
