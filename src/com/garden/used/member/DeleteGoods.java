package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.garden.used.data.Data;

public class DeleteGoods extends UpdateGoods {

	//해당하는 상품을 list에서 지우고 나머지를 상품정보에 저장
	
	private boolean flagExit;
	
	public void delete(Goods goods, String memberNumber, String nickname) {
		
		this.flagExit = false;
		
		while (!flagExit) {
			
			viewGoodsInfo(goods, memberNumber, nickname);
			
			System.out.println("============================================================");
			System.out.println("                        삭제 확인");
			System.out.println("============================================================");
			System.out.println("상품을 삭제하면 되돌릴 수 없습니다.");
			System.out.printf("\"%s\" 상품을 삭제하시겠습니까?", goods.getGoodsNumber());
			System.out.println();
			System.out.println("1. 예\t\t 2. 아니오");
			System.out.println("============================================================");
			System.out.print("■입력 : ");
			
			Scanner scan = new Scanner(System.in);
			int input = Integer.parseInt(scan.nextLine());
			
			if (input > 0 && input <= 2) {
				if (input == 1) { //상품 삭제하기
					deleteGoodsList(goods);
					System.out.println("상품이 삭제되었습니다.");
					flagExit = true;
					pause();
				} else if (input == 2) { //삭제 취소하기
					System.out.println("삭제를 취소합니다.");
					flagExit = true;
					pause();
				}
			} else { //잘못 입력
				wrongInput();
			}
			
			
		}
		
		
		
		
	}

	private void deleteGoodsList(Goods goods) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
			
			ArrayList<String> list = new ArrayList<String>();
			
			String line = null;
			
			while ((line = reader.readLine()) != null) { //상품정보DB 읽어오기
				String[] temp = line.split("■", 16);
				if (goods.getGoodsNumber().equals(temp[0])) { //상품번호가 같으면
					continue; //넘어가기(리스트에 추가하지 않기)
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
		
	} //deleteGoodsList
	
	
	
	
	
	
	
	
	
}
