package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import com.garden.used.data.Data;
import com.garden.used.nonmember.NonMember;
import com.garden.used.nonmember.Sell;

public class ViewMyInfo {
	static int count =0;
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		
		
		boolean run = true;
		while (run) {
			System.out.println("======================================================");
			System.out.println("1. 프로필");
			System.out.println("2. 구매 및 판매 내역");
			System.out.println("3. 관심목록");
			System.out.println("4. 동네설정");
			System.out.println("5. 모아보기");
			System.out.println("6. 계좌정보 관리");
			System.out.println("======================================================");
			System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
			System.out.println("======================================================");
			System.out.print("■ 입력: ");
	
	
			int selectNo = scanner.nextInt();

			if (selectNo == 1) {
				ViewMyProfile();
			} else if (selectNo == 2) {
				BuynSell();
			} else if (selectNo == 3) {
				FavoriteList();
			} else if (selectNo == 4) {
				SelectArea();
			} else if (selectNo == 5) {
				ViewTogether();
			} else if (selectNo == 6) {
				ManageMyAccount();
			} else if (selectNo == 0) {
				main(null);
			} else {
				System.out.println("잘못입력하셨습니다.");
				
			}
			
		}
		
	}

	//프로필
	private static void ViewMyProfile() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("1. 매너온도");
		System.out.println("2. 활동사항");
		System.out.println("3. 회원정보");
		System.out.println("4. 회원탈퇴");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
//		String input = scanner.next();
		
		int selectNo = scanner.nextInt();

		if (selectNo == 1) {
			ViewMyManner();
		} else if (selectNo == 2) {
			Footstep();
		} else if (selectNo == 3) {
			MyInfo();
		} else if (selectNo == 4) {
			DeleteInfo();
		} else if (selectNo == 0) {
			main(null);
		} else {
			System.out.println("잘못입력하셨습니다.");
		}
		}

		
	}

	//프로필 - 매너온도
	private static void ViewMyManner() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("1. 추천");
		System.out.println("2. 후기");
		System.out.println("3. 평가");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();

		if (selectNo == 1) {
			ViewMyRate();
		} else if (selectNo == 2) {
			ViewMyReview();
		} else if (selectNo == 3) {
			ViewMyJudge();
		} else if (selectNo == 0) {
			ViewMyProfile();
		} else {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	}

	

	//프로필 - 매너온도 - 추천
	private static void ViewMyRate() throws Exception {
		
		boolean run = true;
		while (run) {
		
		try{
	            //파일 객체 생성
	            File file = new File(Data.MEMBERADDINFO);
	            FileReader textRead = new FileReader(file);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
	            	System.out.println("======================================================"); 
	            	
	            	for (int i=0; i<6; i++) {
						String [] each_Info = lineArray.get(i).split("■");
			
						if (each_Info[4] == "1") {
							System.out.println("★☆☆☆☆");
						} else if (each_Info[4] == "2") {
							System.out.println("★★☆☆☆");
						} else if (each_Info[4] == "3") {
							System.out.println("★★★☆☆");
						} else if (each_Info[4] == "4") {
							System.out.println("★★★★☆");
						} else if (each_Info[4] == "5") {
							System.out.println("★★★★★");
						}	
						
					System.out.println("나의 추천별수: ★★☆☆☆");	
					//System.out.printf("나의 추천별수: %s", each_Info[4]);
	            	System.out.println();
	    			System.out.println();
	    			System.out.println("======================================================");
	    			System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
	    			System.out.println("======================================================");
	    			System.out.print("■ 입력: ");
	    			//String input = scanner.next();
						
	    			
	    			int selectNo = scanner.nextInt();

//					if (selectNo > 0 && selectNo <11) {
//						ViewMyWriting();
					if (selectNo == 0) {
						ViewMyManner();
					} else {
						System.out.println("잘못입력하셨습니다.");
					}
					
	            	}
				}
					} catch (FileNotFoundException e) {
					// 파일 없거나 이름 안맞으면 여기걸림.
					e.printStackTrace();
					System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
					} catch (IOException e) {
					e.printStackTrace();
					
		}
	}
	}
		

		
		
//	}
	//프로필 - 매너온도 - 후기
	private static void ViewMyReview() throws Exception {

		boolean run = true;
		while (run) {
		
			try {
				File text = new File(Data.GOODSINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("==============================================================================================================");
				//System.out.println("[확인 및 수정]\t[상품번호]\t[카테고리]\t[상품가격]\t[거래상태]\t[등록일]");
				System.out.println("[후기확인]\t\t[카테고리]\t\t\t[제목]\t\t[상품가격]\t[거래상태]\t\t[지역]\t\t[등록일]");
				for (int i=56; i<62; i++) {
					String [] each_Info = lineArray.get(i).split("■");
					System.out.printf("   %d\t\t%s\t%5s\t%s\t%s\t%s\t%s", i-55, each_Info[2], each_Info[5], 
																		each_Info[6], each_Info[4], each_Info[8], each_Info[1]);
			            System.out.println();
			           
				}
				System.out.println("==============================================================================================================");
				//System.out.println("확인 및 수정을 원하시면 숫자를 입력하세요.");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("==============================================================================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
				
				
				int selectNo = scanner.nextInt();

//				if (selectNo > 0 && selectNo <11) {
//					ViewMyWriting();
				if (selectNo == 1) {
					ViewMyWriting();
				} else if (selectNo == 2) {
					ViewMyWriting();
				} else if (selectNo == 3) {
					ViewMyWriting();
				} else if (selectNo == 4) {
					ViewMyWriting();
				} else if (selectNo == 5) {
					ViewMyWriting();
				} else if (selectNo == 6) {
					ViewMyWriting();
				} else if (selectNo == 0) {
					ViewMyManner();
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
				

				} catch (FileNotFoundException e) {
				// 파일 없거나 이름 안맞으면 여기걸림.
				e.printStackTrace();
				System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}

		
	//프로필 - 매너온도 - 후기확인 및 수정	
	private static void ViewMyWriting() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("닉네임: 당당한 싸다");
		System.out.println("후기: 친절했고 제품도 좋아서 완전 만족");
		System.out.println();
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 0) {
			ViewMyReview();
		} else {
			System.out.println("잘못입력하셨습니다.");
		}
		
		}
		
	}

		
	//프로필 - 매너온도 - 평가 
	private static void ViewMyJudge() throws Exception {
		
		boolean run = true;
		while (run) {
			
			try {
				File text = new File(Data.GOODSINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("==============================================================================================================");
				System.out.println("[평가하기]\t\t[카테고리]\t\t\t[제목]\t\t[상품가격]\t[거래상태]\t\t[지역]\t\t[등록일]");
//				System.out.println("[평가하기]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\\t[상품가격]\t[거래상태]\t[닉네임]\t[등록일]");
				for (int i=56; i<62; i++) {
					String [] each_Info = lineArray.get(i).split("■");
					System.out.printf("  %d\t\t%s\t%5s\t%s\t%s\t%s\t%s", i+1, each_Info[2], each_Info[5], 
																			each_Info[6], each_Info[4], each_Info[8], each_Info[1]);
			            System.out.println();
				}
				System.out.println("==============================================================================================================");
				System.out.println("평가를 원하시면 숫자를 입력하세요.");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("======================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
			
				int selectNo = scanner.nextInt();
				
				if (selectNo > 0 && selectNo < 11) {
					ViewMyJWriting();
				} else if (selectNo == 0) {
					ViewMyManner();
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			
				} catch (FileNotFoundException e) {
				// 파일 없거나 이름 안맞으면 여기걸림.
				e.printStackTrace();
				System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}
			

	//프로필 - 매너온도 - 평가 - 확인 및 수정
	private static void ViewMyJWriting() throws Exception {
		

		boolean run = true;
		//while (run) {
			
		    String contentToBeSaved = null;
	        Scanner scanner = new Scanner(System.in);
	       
	        System.out.println("======================================================");
	        System.out.print("평가 점수: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.println();
	        System.out.println("======================================================");
	        
	       
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\class\\memberInfo.txt", true));
	        bufferedWriter.write("■" + contentToBeSaved);
	        bufferedWriter.close();
	        System.out.println("저장이 완료되었습니다.");
	        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			      try {
			         reader.readLine();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }
			
			}
	//}


	//프로필 - 활동사항
	private static void Footstep() throws Exception {
		
		boolean run = true;
		//while (run) {
		
		
		try {
			File text = new File(Data.MEMBERADDINFO);
			FileReader textRead = new FileReader(text);
			BufferedReader bfReader = new BufferedReader(textRead);
			String line = "";
			List<String> lineArray = new ArrayList<String>();
			// null 이 아닐때까지 반복한다.
			while ( (line = bfReader.readLine()) != null ) {
			// System.out.println(line); 출력
			// 리스트 배열에 line 한줄한줄씩 추가.
			lineArray.add(line);
			}
			System.out.println("======================================================");
			for (int i=0; i<lineArray.size(); i++) {
				String [] each_Info = lineArray.get(i).split("■");
				System.out.printf("구매 횟수: %s\n", each_Info[6]);
				System.out.printf("판매 횟수: %s\n", each_Info[7]);
				System.out.printf("활동 지수: %d\n", (Integer.parseInt(each_Info[6]) + Integer.parseInt(each_Info[7]))*10);
				System.out.printf("회원 등급: %s\n", each_Info[5]);
				System.out.println();
				System.out.println("======================================================");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("======================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
				int selectNo = scanner.nextInt();
				//수정해야함
				if (selectNo == 0) {
					ViewMyProfile();
				} else {
					System.out.println("잘못입력하셨습니다.");
					System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
					   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					      try {
					         reader.readLine();
					      } catch (IOException e) {
					         e.printStackTrace();
					      }
					      ViewMyProfile();
				}
			}
		}
				catch (FileNotFoundException e) {
					// 파일 없거나 이름 안맞으면 여기걸림.
					e.printStackTrace();
					System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
					} catch (IOException e) {
					e.printStackTrace();
				
				}
	}
		
	//}
		

	//프로필 - 회원정보
	private static void MyInfo() throws Exception {
		
		boolean run = true;
		//while (run) {
			
			try {
				File text = new File(Data.MEMBERINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("======================================================");
				for (int i=1; i<lineArray.size(); i++) {
					String [] each_Info = lineArray.get(i).split("■");	
			
					System.out.printf("아이디:%s \n", each_Info[8]);
					System.out.printf("닉네임:%s \n", each_Info[10]);
					System.out.printf("비밀번호:%s \n", each_Info[9]);
					System.out.printf("이름:%s \n", each_Info[1]);
					System.out.printf("등급:%s \n", each_Info[11]);
					//System.out.printf("주소: \n");
					System.out.printf("휴대폰 번호:%s \n", phone_H(each_Info[4]));
					System.out.printf("생년월일:%s\n", each_Info[3]);
					System.out.printf("나이: %s\n", age_calc(each_Info[3]));
					System.out.printf("가입일:%s\n", each_Info[6]);
					System.out.println("======================================================");
					System.out.println("수정하기(닉네임: 1, 비밀번호: 2, 휴대폰 번호: 3)");
					System.out.println("======================================================");
					System.out.print("■ 입력: ");	
					//String input = scanner.next();
		
					int selectNo = scanner.nextInt();
					//수정해야함
					if (selectNo == 1) {
						modifyNname();
					} else if (selectNo == 2){
						modifyPword();
					} else if (selectNo == 3) {
						modifyPhoneNo();
						
					} else {
						System.out.println("잘못입력하셨습니다.");
						System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
						   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						      try {
						         reader.readLine();
						      } catch (IOException e) {
						         e.printStackTrace();
						      }
						      ViewMyProfile();
					}
				}
			}
					catch (FileNotFoundException e) {
						// 파일 없거나 이름 안맞으면 여기걸림.
						e.printStackTrace();
						System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
						} catch (IOException e) {
						e.printStackTrace();
					
					}
		}
				private static void modifyNname() throws IOException {
					
					boolean run = true;
					//while (run) {
						
					    String contentToBeSaved = null;
				        Scanner scanner = new Scanner(System.in);
				       
				        System.out.println("======================================================");
				        System.out.print("닉네임: ");
				        contentToBeSaved = scanner.nextLine();
				        System.out.println();
				        System.out.println("======================================================");
				        
				       
				        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
				        bufferedWriter.write("■" + contentToBeSaved);
				        bufferedWriter.close();
				        System.out.println("저장이 완료되었습니다.");
				        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
						   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						      try {
						         reader.readLine();
						      } catch (IOException e) {
						         e.printStackTrace();
						      }
						
						}
	//}

				private static void modifyPword() throws IOException {
					
					boolean run = true;
					//while (run) {
						
					    String contentToBeSaved = null;
				        Scanner scanner = new Scanner(System.in);
				       
				        System.out.println("======================================================");
				        System.out.print("비밀번호: ");
				        contentToBeSaved = scanner.nextLine();
				        System.out.println();
				        System.out.println("======================================================");
				        
				       
				        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
				        bufferedWriter.write("■" + contentToBeSaved);
				        bufferedWriter.close();
				        System.out.println("저장이 완료되었습니다.");
				        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
						   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						      try {
						         reader.readLine();
						      } catch (IOException e) {
						         e.printStackTrace();
						      }
						
						}
		
	//}

				private static void modifyPhoneNo() throws IOException {
					
					boolean run = true;
					//while (run) {
						
					    String contentToBeSaved = null;
				        Scanner scanner = new Scanner(System.in);
				       
				        System.out.println("======================================================");
				        System.out.print("휴대폰 번호: ");
				        contentToBeSaved = scanner.nextLine();
				        System.out.println();
				        System.out.println("======================================================");
				        
				       
				        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
				        bufferedWriter.write("■" + contentToBeSaved);
				        bufferedWriter.close();
				        System.out.println("저장이 완료되었습니다.");
				        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
				        
						   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						      try {
						         reader.readLine();
						      } catch (IOException e) {
						         e.printStackTrace();
						       
						      }
						      
						 
}
				
				//} 
		
	//}

				//}
//
//
//	private static void modifyPhoneNo(int line, String same, String IdCode) throws IOException {
//		
//		  
//			File file = new File("C:\\class\\memberInfo.txt"); 
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			BufferedReader br1 = new BufferedReader(new FileReader(file));
//
//			ArrayList<String> member = new ArrayList<String>(); // 수정할 데이터를 찾기위한 배열
//			ArrayList<String> rearM = new ArrayList<String>(); // 수정되었고 최종적으로 쓰기할 배열
//
//			System.out.println();
//			System.out.print("입력받은 회원번호");
//			System.out.println(IdCode);
//
//			String temp = "";
//			while (br.readLine() != null) {
//				member.add(br1.readLine());
//			}
//
//			for (int i = 0; i < member.size(); i++) {
//				String[] each_Info = member.get(i).split("■");
//				if (each_Info[0].equals(IdCode)) {
//					each_Info = member.get(i).split("■");
//					each_Info[line] = same;
//					temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2] + "■" + each_Info[3] + "■"
//							+ each_Info[4] + "■" + each_Info[5] + "■" + each_Info[6] + "■" + each_Info[7] + "■"
//							+ each_Info[8] + "■" + each_Info[9] + "■" + each_Info[10];
//					rearM.add(temp);
//					i++;
//				}
//
//				rearM.add(member.get(i));
//			}
//			System.out.println(rearM.get(999));
//			br.close();
//			br1.close();
//
//			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//			for (int i = 0; i < rearM.size(); i++) {
//				String a = rearM.get(i);
//				bw.write(a);
//				bw.newLine();
//			}
//			System.out.println("");
//
//			bw.close();
//
//			modifyPhoneNo(line, IdCode, temp);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
	private static Object age_calc(String string) {
		String ageString ="";
		if (string.equals("null")) {

			ageString = null;
		} else {

			String c = string.substring(0, 2);
			int changeInt = Integer.parseInt(c);
			String a = "";
			if (changeInt > 10) {
				a = "19";
			} else {
				a = "20";
			}

			String str = string; // ex) 801011 (생년월일)
			String add = a.concat(str);// a+str = 19801011

			String birth_Year = add.substring(0, 4); // 1980

			int birth_int = Integer.parseInt(birth_Year); // 문자형->정수형

			Calendar c1 = Calendar.getInstance();
			int now_Year = c1.get(Calendar.YEAR);// 현재 년도

			int age = now_Year - birth_int + 1;

			ageString = age + "";
		}
		
		return ageString;
	}

	private static Object phone_H(String phoneNum) {
		String temp = ""; //수정된 핸드폰 번호 넣기	
		if(phoneNum.length()==11) {
			temp = phoneNum.substring(0,3)+ "-" + phoneNum.substring(3, 7)+"-"+phoneNum.substring(7, 11);
		}else {
			temp = null;
		}
		
		return temp;
	}

	//프로필 - 회원탈퇴
	private static void DeleteInfo() throws Exception {
	
		boolean run = true;
		//while (run) {
		System.out.println("======================================================");
		System.out.println("회원을 탈퇴하실 경우 저장되어있는 모든 정보가 사라지게 됩니다.\n정말 탈퇴하시겠습니까?");
		System.out.println();
		System.out.println("======================================================");
		System.out.println("탈퇴를 진행하시려면 숫자 1을 입력하세요.");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			deleteMyInfo();
			
		} else if (selectNo == 0) {
			ViewMyProfile();
		} else {
			System.out.println("잘못입력하셨습니다.");
			
		}
		}
	

		
	//}
	
	private static void deleteMyInfo() throws IOException {
		
		boolean run = true;
		//while (run) {
			
			       
	        System.out.println("======================================================");
	        System.out.print("탈퇴되었습니다.");
	        System.out.println();
	        System.out.println("======================================================");
	        
	        System.out.println("그동안 이용해 주셔서 감사합니다.");
	       
	       
	          
	           
	           
	           }
//			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			      try {
//			         reader.readLine();
//			      } catch (IOException e) {
//			         e.printStackTrace();
//			      }
//			     
//			
//			}
		
	//}

	//구매 및 판매 내역
	private static void BuynSell() throws Exception {
		
		boolean run = true;
		while (run) {
			
			try {
				File text = new File(Data.GOODSINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("=============================================================================================================================");
				System.out.println("[후기]\t\t[상품번호]\t[카테고리]\t\t[지역]\t\t\t[제목]\t\t[상품가격][내역]\t[거래일]");
//				System.out.println("[평가하기]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\\t[상품가격]\t[거래상태]\t[닉네임]\t[등록일]");
				for (int i=56; i<60; i++) {
					String [] each_Info = lineArray.get(i).split("■");
					System.out.printf("  %d\t\t%s\t%5s\t%s\t%s\t%s\t%s\t%s", i-55, each_Info[0], each_Info[2], 
																			each_Info[8], each_Info[5], each_Info[6], each_Info[12], each_Info[1]);
			            System.out.println();
				}
				System.out.println("=============================================================================================================================");
				System.out.println("후기 작성을 원하시면 숫자를 입력하세요.");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================================================================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
			
				int selectNo = scanner.nextInt();
				
				if (selectNo > 0 && selectNo < 5) {
					ViewMyIReview();
				} else if (selectNo == 0) {
					ViewMyManner();
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			
				} catch (FileNotFoundException e) {
				// 파일 없거나 이름 안맞으면 여기걸림.
				e.printStackTrace();
				System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}
	//구매 및 판매 내역 - 후기
	private static void ViewMyIReview() throws Exception {

		boolean run = true;
		//while (run) {
			
		    String contentToBeSaved = null;
	        Scanner scanner = new Scanner(System.in);
	       
	        System.out.println("======================================================");
	        System.out.print("제목: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.print("후기: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.println();
	        System.out.println("======================================================");
	        
	       
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
	        bufferedWriter.write("■" + contentToBeSaved);
	        bufferedWriter.close();
	        System.out.println("저장이 완료되었습니다.");
	        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
	        
			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			      try {
			         reader.readLine();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }
			       
			      }

	//관심목록
	private static void FavoriteList() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("1. 옷");
		System.out.println("2. 잡화/뷰티");
		System.out.println("3. 디지털/가전");
		System.out.println("4. 생활/가공식품");
		System.out.println("5. 스포츠/건강");
		System.out.println("6. 반려동물용품");
		System.out.println("7. 홈데코");
		System.out.println("8. 게임/취미");
		System.out.println("9. 여행/도서/티켓/E쿠폰");
		System.out.println("10. 기타");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			ItemList(); //수정해야함
		} else if (selectNo == 2) {
			ItemList(); //수정해야함
		} else if (selectNo == 3) {
			ItemList(); //수정해야함
		} else if (selectNo == 4) {
			ItemList(); //수정해야함
		} else if (selectNo == 5) {
			ItemList(); //수정해야함
		} else if (selectNo == 6) {
			ItemList(); //수정해야함
		} else if (selectNo == 7) {
			ItemList(); //수정해야함
		} else if (selectNo == 8) {
			ItemList(); //수정해야함
		} else if (selectNo == 9) {
			ItemList(); //수정해야함	
		} else if (selectNo == 10) {
			ItemList(); //수정해야함	
		} else if (selectNo == 0) {
			main(null);
		} else {
			System.out.println("잘못입력하셨습니다.");
			
		}
		}
		
		
	}

	//관심목록 - 상품리스트
	private static void ItemList() throws Exception {
		
		boolean run = true;
		while (run) {
			
			try {
				File text = new File(Data.GOODSINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("=============================================================================================================================");
				System.out.println("[거래신청]\t[상품번호]\t[카테고리]\t\t[지역]\t\t\t[제목]\t\t[상품가격] [거래상태] [등록일]");
//				System.out.println("[평가하기]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\\t[상품가격]\t[거래상태]\t[닉네임]\t[등록일]");
				for (int i=56; i<60; i++) {
					String [] each_Info = lineArray.get(i).split("■");
					System.out.printf("  %d\t\t%s\t%5s\t%s\t%s\t%s\t%s\t%s", i-55, each_Info[0], each_Info[2], 
																			each_Info[8], each_Info[5], each_Info[6], each_Info[4], each_Info[1]);
			            System.out.println();
				}
				System.out.println("=============================================================================================================================");
				System.out.println("거래를 원하시면 숫자를 입력하세요.");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================================================================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
			
				int selectNo = scanner.nextInt();
				
				if (selectNo > 0 && selectNo < 5) {
					System.out.println("거래신청이 완료되었습니다.");
				} else if (selectNo == 0) {
					ViewMyManner();
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			
				} catch (FileNotFoundException e) {
				// 파일 없거나 이름 안맞으면 여기걸림.
				e.printStackTrace();
				System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}

	//동네설정
	private static void SelectArea() throws Exception {
		
		boolean run = true;
		while (run) {
		try {
			
			LinkedHashSet<String> hash = new LinkedHashSet<String>();
			HashMap<String, String> map = new HashMap<String, String>();
		
			 
			BufferedReader fileReader 
			= new BufferedReader(new FileReader(Data.ADDRESS));
			
			
			
				String line = null;
				
				while((line = fileReader.readLine()) != null) {
						
					       String[] temp = line.split("■");
					       hash.add(temp[0]);
					       map.put(temp[0], temp[1]);
			   }
				
				
					System.out.println("===============================================");
					System.out.println("시/도를 선택해주세요.");
					System.out.println("===============================================");
					Iterator<String> iter = hash.iterator();
					
					int i=1;
					while(iter.hasNext()) {
					System.out.printf("%d. %s\n", i, iter.next());
					i++;
					}
					System.out.println("===============================================");
					System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
					System.out.println("===============================================");
					System.out.printf("■ 입력: ");
					
					Scanner scan = new Scanner(System.in);
					String input = scan.nextLine();
					String [] str = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
					
					if(input.equals("0")) {
						main(null);
					} else {
					for(String string : str) {
						if(input.equals(string)) {
							SelectDArea();
						}
					}
					}
		}
				catch (Exception e) {
					// TODO: handle exception

				
				}
		}
			}

	//동네설정 - 동 설정하기
	private static void SelectDArea() throws Exception {
		
		boolean run = true;
		while (run) {
		try {
	
			BufferedReader fileReader 
			= new BufferedReader(new FileReader(Data.ADDRESS));
		
			System.out.println();
			System.out.println();
			System.out.println("===============================================");
			System.out.println("시/군/구를 선택해주세요.");
			System.out.println("===============================================");
			
			System.out.println("1. 동작구");
			System.out.println("2. 관악구");
			System.out.println("3. 강남구");
			System.out.println("4. 송파구");
			System.out.println("5. 은평구");
			System.out.println("6. 용산구");
			System.out.println("7. 마포구");
			System.out.println("8. 성북구");
			System.out.println("9. 영등포구");
			System.out.println("10. 강북구");
			System.out.println("11. 도봉구");
			System.out.println("12. 서초구");
			
			
			System.out.println("===============================================");
			System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
			System.out.println("===============================================");
			System.out.printf("■ 입력: ");
			
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			String [] str = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
			
			if(input.equals("0")) {
				SelectArea();
			} else {
			for(String string : str) {
				if(input.equals(string)) {
					thirdArea();
				}
			}
			}
}
		catch (Exception e) {
			// TODO: handle exception
	
		
		}
	}
}

	private static void thirdArea() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println();
		System.out.println();
		System.out.println("===============================================");
		System.out.println("동/읍/면을 선택해주세요.");
		System.out.println("===============================================");
		
		System.out.println("1. 역삼동 ");
		System.out.println("2. 삼성동");
		System.out.println("3. 개포동");
		System.out.println("4. 청담동");
		System.out.println("5. 삼성동");
		System.out.println("6. 대치동");
		System.out.println("7. 일원동");
		System.out.println("8. 수서동");
		
		System.out.println("===============================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("===============================================");
		System.out.printf("■ 입력: ");
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String [] str = { "1", "2", "3", "4", "5", "6", "7", "8"};
		
		if(input.equals("0")) {
			main(null);
		} else {
			System.out.println("동네설정이 완료되었습니다.");
			for(String string : str) {
			if(input.equals(string)) {
				break;
			} 
		}
		
	}
		}

}		 	
	

	//모아보기
	private static void ViewTogether() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("1. 옷");
		System.out.println("2. 잡화/뷰티");
		System.out.println("3. 디지털/가전");
		System.out.println("4. 생활/가공식품");
		System.out.println("5. 스포츠/건강");
		System.out.println("6. 반려동물용품");
		System.out.println("7. 홈데코");
		System.out.println("8. 게임/취미");
		System.out.println("9. 여행/도서/티켓/E쿠폰");
		System.out.println("10. 기타");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			Digital(); //수정해야함
		} else if (selectNo == 2) {
			Digital(); //수정해야함
		} else if (selectNo == 3) {
			Digital(); //수정해야함
		} else if (selectNo == 4) {
			Digital(); //수정해야함
		} else if (selectNo == 5) {
			Digital(); //수정해야함
		} else if (selectNo == 6) {
			Digital(); //수정해야함
		} else if (selectNo == 7) {
			Digital(); //수정해야함
		} else if (selectNo == 8) {
			Digital(); //수정해야함
		} else if (selectNo == 9) {
			Digital(); //수정해야함	
		} else if (selectNo == 10) {
			Digital(); //수정해야함	
		} else if (selectNo == 0) {
			main(null);
		} else {
			System.out.println("잘못입력하셨습니다.");
			
		}
		}
		
		
	}
	//모아보기 - 디지털/가전
	private static void Digital() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("1. 노트북/PC");
		System.out.println("2. 모니터/프린터");
		System.out.println("3. PC주변기기");
		System.out.println("4. 모바일/태블릿");
		System.out.println("5. 카메라");
		System.out.println("6. 게임");
		System.out.println("7. 음향기기");
		System.out.println("8. 영상가전");
		System.out.println("9. 기타");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			DigitalList(); //수정해야함
		} else if (selectNo == 2) {
			DigitalList(); //수정해야함
		} else if (selectNo == 3) {
			DigitalList(); //수정해야함
		} else if (selectNo == 4) {
			DigitalList(); //수정해야함
		} else if (selectNo == 5) {
			DigitalList(); //수정해야함
		} else if (selectNo == 6) {
			DigitalList(); //수정해야함
		} else if (selectNo == 7) {
			DigitalList(); //수정해야함
		} else if (selectNo == 8) {
			DigitalList(); //수정해야함
		} else if (selectNo == 9) {
			DigitalList(); //수정해야함		
		} else if (selectNo == 0) {
			ViewTogether();
		} else {
			System.out.println("잘못입력하셨습니다.");
			
		}
		}
		
		
	}

	private static void DigitalList() throws Exception {
		
		boolean run = true;
		while (run) {
			
			try {
				File text = new File(Data.GOODSINFO);
				FileReader textRead = new FileReader(text);
				BufferedReader bfReader = new BufferedReader(textRead);
				String line = "";
				List<String> lineArray = new ArrayList<String>();
				// null 이 아닐때까지 반복한다.
				while ( (line = bfReader.readLine()) != null ) {
				// System.out.println(line); 출력
				// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
				}
				System.out.println("=============================================================================================================================");
				System.out.println("[거래신청]\t[상품번호]\t[카테고리]\t\t[지역]\t\t\t[제목]\t\t[상품가격] [거래상태] [등록일]");
//				System.out.println("[평가하기]\t[상품번호]\t[카테고리]\t[지역]\t[제목]\\t[상품가격]\t[거래상태]\t[닉네임]\t[등록일]");
				for (int i=56; i<60; i++) {
					String [] each_Info = lineArray.get(i).split("■");
					System.out.printf("  %d\t\t%s\t%5s\t%s\t%s\t%s\t%s\t%s", i-55, each_Info[0], each_Info[2], 
																			each_Info[8], each_Info[5], each_Info[6], each_Info[4], each_Info[1]);
			            System.out.println();
				}
				System.out.println("=============================================================================================================================");
				System.out.println("거래를 원하시면 숫자를 입력하세요.");
				System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
				System.out.println("=============================================================================================================================");
				System.out.print("■ 입력: ");	
				//String input = scanner.next();
			
				int selectNo = scanner.nextInt();
				
				if (selectNo > 0 && selectNo < 5) {
					System.out.println("거래신청이 완료되었습니다.");
				} else if (selectNo == 0) {
					Digital();
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			
				} catch (FileNotFoundException e) {
				// 파일 없거나 이름 안맞으면 여기걸림.
				e.printStackTrace();
				System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}

	//계좌정보 관리
	private static void ManageMyAccount() throws Exception {
		
		boolean run = true;
		while (run) {
		System.out.println("======================================================");
		System.out.println("\t\t\t계좌정보\t\t\t");
		System.out.println("======================================================");
		System.out.println("[은행명]\t[계좌번호]");
		System.out.println();
		System.out.println();
		System.out.println("======================================================");
		System.out.println("1. 계좌 등록\t2. 계좌 수정\t3. 계좌 삭제");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");	
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			ResisterAccount(); //수정해야함
		} else if (selectNo == 2) {
			EditAccount(); //수정해야함
		} else if (selectNo == 3) {
			DeleteAccount(); //수정해야함
		} else if (selectNo == 0) {
			main(null);
		}
		}		
		
	}

	//계좌정보 등록
	private static void ResisterAccount() throws Exception {
	
//		boolean run = true;
//		while (run) {
		System.out.println("======================================================");
		System.out.println("[은행명]\t[계좌번호]");
		System.out.println();
		System.out.println();
		System.out.println("======================================================");
		System.out.println("<은행 선택>");
		System.out.println("1. 국민\t2. 우리\t3. 기업\t4. 수협\t5. 카카오뱅크\n"
							+ "6. 씨티\t7. 광주\t8. 부산\t9. 제주\t10. 신한\n"
							+ "11. 농협 12. SC제일 13. 산업 14. K뱅크 15. 경남\n"
							+ "16. 대구 17. 전북");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");
		//String input = scanner.next();
		
		int selectNo = scanner.nextInt();
		//수정해야함
		if (selectNo == 1) {
			ResisterMyAccount(); //수정해야함
		} else if (selectNo == 2) {
			ResisterMyAccount(); //수정해야함
		} else if (selectNo == 3) {
			ResisterMyAccount(); //수정해야함
		} else if (selectNo == 0) {
			main(null);
		}
		}		

			
      private static void ResisterMyAccount() throws IOException {
    	 
    	  
    	  	boolean run = true;
    		//while (run) {
    			
    		    String contentToBeSaved = null;
    	        Scanner scanner = new Scanner(System.in);
    	       
    	        System.out.println("======================================================");
    	        System.out.print("은행: ");
    	        contentToBeSaved = scanner.nextLine();
    	        System.out.print("계좌번호: ");
    	        contentToBeSaved = scanner.nextLine();
    	        System.out.println();
    	        System.out.println("======================================================");
    	        
    	       
    	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
    	        bufferedWriter.write("■" + contentToBeSaved);
    	        bufferedWriter.close();
    	        System.out.println("저장이 완료되었습니다.");
    	        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
    	        
    			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    			      try {
    			         reader.readLine();
    			      } catch (IOException e) {
    			         e.printStackTrace();
    			      }
    			       
    			      }
    				
		
	//}

	//계좌정보 관리 - 계좌 수정 - 등록된 계좌 있을시
	private static void EditAccount() throws Exception {
		
		boolean run = true;
		while (run) {
		
		System.out.println("======================================================");
		System.out.println("[은행명]\t[계좌번호]");
		System.out.println();
		System.out.println();
		System.out.println("======================================================");
		System.out.println("<은행 선택>");
		System.out.println("1. 국민\t2. 우리\t3. 기업\t4. 수협\t5. 카카오뱅크\n"
							+ "6. 씨티\t7. 광주\t8. 부산\t9. 제주\t10. 신한\n"
							+ "11. 농협 12. SC제일 13. 산업 14. K뱅크 15. 경남\n"
							+ "16. 대구 17. 전북");
		System.out.println("======================================================");
		System.out.println("뒤로 가기를 원하시면 숫자 0을 입력하세요.");
		System.out.println("======================================================");
		System.out.print("■ 입력: ");
		//String input = scanner.next();
		
				int selectNo = scanner.nextInt();
				//수정해야함
				if (selectNo == 1) {
					ModifyMyAccount(); //수정해야함
				} else if (selectNo == 2) {
					ModifyMyAccount(); //수정해야함
				} else if (selectNo == 3) {
					ModifyMyAccount(); //수정해야함
				} else if (selectNo == 0) {
					main(null);
				}
		}
	}
			
		

	private static void ModifyMyAccount() throws IOException {
		
		boolean run = true;
		//while (run) {
			
		    String contentToBeSaved = null;
	        Scanner scanner = new Scanner(System.in);
	       
	        System.out.println("======================================================");
	        System.out.print("은행: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.print("계좌번호: ");
	        contentToBeSaved = scanner.nextLine();
	        System.out.println();
	        System.out.println("======================================================");
	        
	       
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Data.MEMBERINFO, true));
	        bufferedWriter.write("■" + contentToBeSaved);
	        bufferedWriter.close();
	        System.out.println("저장이 완료되었습니다.");
	        System.out.print("계속하시려면 엔터를 눌러주세요(엔터를 누르시면 이전화면으로 돌아갑니다).");
	        
			   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			      try {
			         reader.readLine();
			      } catch (IOException e) {
			         e.printStackTrace();
			      }
			       
			      }
		

	//계좌정보 관리 - 계좌 삭제
	private static void DeleteAccount() {
	
		boolean run = true;
		while (run) {
		System.out.println("[은행명]\t[계좌번호]");
		System.out.println();
		System.out.println();
		System.out.println("======================================================");
		System.out.println("등록된 계좌를 삭제했습니다.");
		System.out.println();
		System.out.println("계속하시려면 엔터를 눌러주세요.");
		
		   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		      try {
		         reader.readLine();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		}
				}
		
	
	 public static void pause() {
	      
	 }//pause();
}
