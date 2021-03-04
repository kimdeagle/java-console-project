package com.garden.used.admin.preference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.garden.used.admin.member.Home;
import com.garden.used.data.Data;
import com.garden.used.login.Start;

public class Preference {
	
	private ArrayList<String> preferenceList = new ArrayList<String>();

	public Preference() {

		int i = 0;
		for (String list : goodsData()) {
			preferenceList.add(list);
			i++;
		}

		//선호도 분석 메뉴 출력 메소드 호출
		preferenceMenu();
		
		Scanner scan = new Scanner(System.in);
		String number = scan.nextLine();
		
		if (number.equals("1")) {
			
			//카테고리 선호도 분석 출력
			Start.line();
			System.out.println("\t\t    카테고리 선호도 분석");
			Start.line();
			analysis(goodsData(), 2);
			
		} else if (number.equals("2")) {
			
			//지역 선호도 분석 출력
			Start.line();
			System.out.println("\t\t    지역 선호도 분석");
			Start.line();
			analysis(preferenceList, 8);
			
		} else if (number.equals("3")) {
			
			//거래 방식 선호도 분석 출력
			Start.line();
			System.out.println("\t\t  거래 방식 선호도 분석");
			Start.line();
			analysis(preferenceList, 3);
			
		} else if (number.equals("4")) {
			
			//거래 상황 통계 분석 출력
			Start.line();
			System.out.println("\t\t      거래 상황 통계");
			Start.line();
			analysis(preferenceList, 4);
			
		} else if (number.toUpperCase().equals("H")) {
			
			System.out.println();
			System.out.println("홈으로 돌아갑니다.");
			Start.pause();
			Home home = new Home();
			home.Home();
			
		} else {
			System.out.println();
			System.out.println("1에서 4사이의 숫자만 입력해주세요.");
			Start.pause();
			Preference pre = new Preference();
		}
	}

	private void preferenceMenu() {

		Start.line();
		System.out.println("\t\t\t선호도 분석");
		Start.line();
		System.out.println("\t\t1. 카테고리 선호도\n\t\t2. 지역 선호도\n\t\t3. 거래 방식 선호도\n\t\t4. 거래 상황 통계\n\t\tH : 홈으로");
		Start.line();
		System.out.println("       선호도 순으로 상위 5개만 노출됩니다.(단 %는 반올림 값)");
		System.out.println("\t    확인하실 번호를 골라 입력해주세요.");
		Start.line();
		System.out.print("■ 입력 : ");

	}

	private void analysis(ArrayList<String> goodsData, int indexNumber) {
		
		//항목 이름과 카운트 저장할 Map
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//선호도 분석할 항목 이름을 넣을 List
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i=0; i<goodsData.size()-1; i++) {
			String[] temp = goodsData.get(i).split("■");
			
			//지역 검색 시, 시나 도까지만 저장
			if (indexNumber == 8) {
				int toIndex = 0;
            	if (temp[8].contains("시") && temp[8].contains("도")) {
    				toIndex = temp[8].indexOf("시") > temp[8].indexOf("도") ? temp[8].indexOf("도") : temp[8].indexOf("시"); 
    			
            	} else if (temp[8].contains("시")) {
    				toIndex = temp[8].indexOf("시");
    			
            	} else if (temp[8].contains("도")) {
    				toIndex = temp[8].indexOf("도");
    			}
            	
            	temp[indexNumber] = temp[indexNumber].substring(0, toIndex+1);
			
			} 
			
			if (list.indexOf(temp[indexNumber]) == -1) {
			
				//항목 이름이 list에 없으면
				list.add(temp[indexNumber]);
			
			}
		}
		
		for (int i=0; i<goodsData.size()-1; i++) {
			String[] temp = goodsData.get(i).split("■");
			
			//지역 검색 시, 시나 도까지만 저장
			if (indexNumber == 8) {
				int toIndex = 0;
            	if (temp[8].contains("시") && temp[8].contains("도")) {
    				toIndex = temp[8].indexOf("시") > temp[8].indexOf("도") ? temp[8].indexOf("도") : temp[8].indexOf("시"); 
    			
            	} else if (temp[8].contains("시")) {
    				toIndex = temp[8].indexOf("시");
    			
            	} else if (temp[8].contains("도")) {
    				toIndex = temp[8].indexOf("도");
    			}
            	
            	temp[indexNumber] = temp[indexNumber].substring(0, toIndex+1);
			}
			
			if (map.get(temp[indexNumber]) == null) {
				map.put(temp[indexNumber], 1);
				
			} else {
				map.put(temp[indexNumber], map.get(temp[indexNumber]) +1);
			}
		}
		
		//선호도 항목 - 건수 순으로 정렬(건수, %가 가장 큰 순서)
		Collections.sort(list, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return map.get(o2).compareTo(map.get(o1));
			}
		});
		
		//출력할 개수
		int count = 0;
		
		//총 value 합
		Collection<Integer> values = map.values();
		Iterator<Integer> iter = values.iterator();
		int sum = 0;
		
		while (iter.hasNext()) {
			
			int value = iter.next();
			sum += value;
		}
		
		//%는 반올림하여 출력
		for (String key : list) {
			
			System.out.printf("          %6s\t\t\t%3d건(%2d%%)\n", key, map.get(key), (int)Math.round((double)map.get(key)/sum*100.0));
			
			count++;
			
			//3개까지만
			if (count == 5) {
				break;
			}
		}
		
		Start.line();
		System.out.println();
		System.out.println("뒤로 돌아갑니다.");
		Start.pause();
		Preference pre = new Preference();
		
	}
	

	@Override
	public String toString() {
		return "{preferenceList=" + preferenceList + "}";
	}

	//한줄씩 읽어 goodsData에 저장
	public ArrayList<String> goodsData() {
		ArrayList<String> goodsData = new ArrayList<String>();
		int lineNumber = 0;
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
			String line = null;
			boolean flag = true;
			
			while (flag) {
				if ((line = reader.readLine()) == null) {
				
					flag = false;
				}
				goodsData.add(line);
				lineNumber++;
				
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return goodsData;
	}
}
