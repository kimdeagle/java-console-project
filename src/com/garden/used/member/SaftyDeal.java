package com.garden.used.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.garden.used.data.Data;

public class SaftyDeal {

   public static void main(String[] args) {
      
      //구매목록 보일상품 5개, 판매목록 보일상품 5개
      //구매목록 선택번호 1~5
      //판매목록 선택번호 6~10
      
      int pageNumber = 1; //현재 페이지 번호
      int totalPage = 0; //총 페이지 수
      
      //총 페이지수 계산
      try {
         BufferedReader countBuyReader = new BufferedReader(new FileReader(Data.GOODSINFO));
         BufferedReader countSellReader = new BufferedReader(new FileReader(Data.GOODSINFO));
         
         String countBuyGoods = null;
         String countSellGoods = null;
         
         //구매상품 개수 구하기
         while ((countBuyGoods = countBuyReader.readLine()) != null) {
            totalPage++;
         }
         
         //판매상품 개수 구하기
         while ((countSellGoods = countSellReader.readLine()) != null) {
            totalPage++;
         } 
         
         
         countBuyReader.close();
         countSellReader.close();
         
         //총 페이지 수
         totalPage = totalPage / 10 + 1;
         
      } catch (Exception e1) {
         e1.printStackTrace();
      }
      
      //게시글 관리 화면
      viewGoodsList(pageNumber, totalPage);
      
      
      
      
   } //main

   private static int saftyCounter() {  //안전거래 페이지 수를 카운트 한다. 

	int totalSaftyPage=0;
	
	
	//안전거래 페이지 카운터 
	  try {
	         BufferedReader countSaftyDeal = new BufferedReader(new FileReader(Data.GOODSINFO));
	         
	         
	         String countPage = null;
	         
	         
	         //구매상품 개수 구하기
	         while ((countPage = countSaftyDeal.readLine()) != null) {
	             String[] temp = countPage.split("■");
	             
	        	 totalSaftyPage++;
	             
	             
	         }
	         
	         	         		         
	           totalSaftyPage = totalSaftyPage / 10 + 1;
	          
	         
	      } catch (Exception e1) {
	         e1.printStackTrace();
	      }
   
   
       return totalSaftyPage; 
}

private static void viewGoodsList(int pageNumber, int totalPage) { //메뉴 메서드 
      
      //구매 데이터(5개씩)
      viewBuyGoodsList(pageNumber);
      
      System.out.println();
      
      //판매 데이터(5개씩)
      viewSellGoodsList(pageNumber);
      
      
     System.out.println();
      
      System.out.printf("%d페이지 중 %d페이지\n", totalPage, pageNumber);
      System.out.println("==========================================================================================");
      System.out.println("이전페이지 : P\t\t다음페이지 : N\t\t첫페이지 : F\t\t 홈으로 : H");
      System.out.println("==========================================================================================");
      System.out.println("1. 상품등록\t\t2. 상품수정\t\t3. 상품삭제\t\t4. 안심거래 관리");
      System.out.println("==========================================================================================");
      System.out.print("■입력 : ");
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      
      try {
         String input = reader.readLine(); //입력받기
         
         if (input.toUpperCase().equals("P")) { //이전페이지
            if (pageNumber != 1) { //업무코드
               pageNumber--;               
            } else { //1페이지에서 이전페이지로 가려고 하는 경우
               System.out.println("현재 페이지가 첫 페이지입니다.");
               pause();
            }
            viewGoodsList(pageNumber, totalPage);
         } else if (input.toUpperCase().equals("N")) { //다음페이지
            if (pageNumber != totalPage) { //업무코드
               pageNumber++;                              
            } else { //마지막페이지에서 다음페이지로 가려고 하는 경우
               System.out.println("현재  페이지가 마지막 페이지입니다.");
               pause();
            }
            viewGoodsList(pageNumber, totalPage);
         } else if (input.toUpperCase().equals("F")) { //첫페이지
            pageNumber = 1;
            viewGoodsList(pageNumber, totalPage);
         } else if (input.toUpperCase().equals("H")) { //홈으로
            //회원 로그인 성공 후 메서드로 이동
         } else if (input.equals("1")) { //상품 등록
            //ManageGoods.registerGoods();
         } else if (input.equals("2")) { //상품 수정
            //editGoods();
         } else if (input.equals("3")) { //상품 삭제
            // deleteGoods();
         } else if (input.equals("4")) { //안심거래 관리 
             chooseSaftyNum(pageNumber, saftyCounter()); //saftyCounter(); //안전거래 총 페이지 값 
         } else { //잘못 입력한 경우
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            pause();
            viewGoodsList(pageNumber, totalPage); 
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      

      System.out.println(); //공백주기 
   } //registerGoods

private static void chooseSaftyNum(int pageNumber, int saftyCounter) { //안전 거래 상품 선택 메서드 
    
    pageNumber=1;
    //구매 데이터(5개씩)
    manageSafeDealBuy(pageNumber);
    
    System.out.println();
    
    //판매 데이터(5개씩)
    manageSafeDealSell(pageNumber);
    
    
   System.out.println();
    
    System.out.printf("%d페이지 중 %d페이지\n", saftyCounter, pageNumber); 
    System.out.println("==========================================================================================");
    System.out.println("이전페이지 : P\t\t다음페이지 : N\t\t첫페이지 : F\t\t 홈으로 : H");
    System.out.println("==========================================================================================");
    System.out.println("\t\t\t\t\t\t안심 거래 관리");
    System.out.println("상품 번호를 선택해 주세요");
    System.out.println("==========================================================================================");
    System.out.print("■입력 : ");
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
       String input = reader.readLine(); //입력받기
       
       if (input.toUpperCase().equals("P")) { //이전페이지
          if (pageNumber != 1) { //업무코드
             pageNumber--;               
          } else { //1페이지에서 이전페이지로 가려고 하는 경우
             System.out.println("현재 페이지가 첫 페이지입니다.");
             pause();
          }
          viewGoodsList(pageNumber, saftyCounter);
       } else if (input.toUpperCase().equals("N")) { //다음페이지
          if (pageNumber != saftyCounter) { //업무코드
             pageNumber++;                              
          } else { //마지막페이지에서 다음페이지로 가려고 하는 경우
             System.out.println("현재 페이지가 마지막 페이지입니다.");
             pause();
          }
          viewGoodsList(pageNumber, saftyCounter);
       } else if (input.toUpperCase().equals("F")) { //첫페이지
          pageNumber = 1;
          viewGoodsList(pageNumber, saftyCounter);
       } else if (input.toUpperCase().equals("H")) { //홈으로
          //회원 로그인 성공 후 메서드로 이동
       } else  if (input.length()<=8){  // 상품번호 길이랑 같은경우 안전거래 메서드로 넘어간다. 
	     		SearchSaftyPost(input,pageNumber); 
       } else { //잘못 입력한 경우
          System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
          pause();
          chooseSaftyNum(pageNumber, saftyCounter);
       }
    } catch (Exception e) {
       e.printStackTrace();
    }
    
    System.out.println();// 공백주기
    
} //chooseSaftyNum ////안전 거래 상품 선택 메서드 

private static void SearchSaftyPost(String input, int pageNumber) throws IOException { //안전구매 글 조회하는 메서드
    try {
        BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
        BufferedReader reader1 = new BufferedReader(new FileReader(Data.GOODSINFO));
     
        int postCount = 0;// 게시글 숫자 카운트 
                        
        if (input.length()==1) { //입력된 숫자에 상품번호 길이인 8자리를 맞추기 위해 0을 추가함.
            input="0000000"+input;
        } else if(input.length()==2) {
            input="000000"+input;
        } else if(input.length()==3) {
            input="00000"+input;
        } else if(input.length()==4) {
            input="0000"+input;
        } else if(input.length()==5) {
            input="000"+input;
        } else if(input.length()==6) {
            input="00"+input;
        } else if(input.length()==7) {
            input="0"+input;
        } else {
            input=""+input;
        }
        
        while (reader.readLine() != null) {// 게시글의 숫자
           postCount++;
        }

        String[] post = new String[postCount]; //게시글 정보를 넣는 배열

       
        for (int i = 0; i < postCount; i++) {
            post[i] = reader1.readLine();
        }
        String temp =""; // 모든 게시글 넣기 
        
        for (int i = 0; i < postCount; i++) {
	           String[] each_Info =  post[i].split("■");
//	           post[i] = reader.readLine();
	           if (each_Info[0].equals(input)) {
	             if(each_Info[3].equals("안전거래")) {
	               temp = post[i];
	              break;
	             }
           } else if (i == post.length - 1) {

              System.out.println("일치하는 글 번호가 없습니다.");
              pause();
              chooseSaftyNum(pageNumber, saftyCounter()); //안전거래 
        
           }
        }
        //String[] info = temp.split("■");
       String[] info = temp.split("■"); // 각각의 회원정보 split으로 잘라 넣을 배열

        System.out.println();
        System.out.println();
        System.out.println("================================================================================================");
        System.out.println("                                    상품 정보");
        System.out.println("================================================================================================");
        System.out.printf("상품번호: %s\t\t\t카테고리 : %s\t\t제목 : %s\n", info[0], info[2], info[5]);
        System.out.println();
        System.out.printf("연관 태그: %s\n", info[10]);
        System.out.println();
        System.out.printf("상품설명 : %s\n", info[9]);
        System.out.println();
        System.out.printf("상품 가격 : %s원\t\t선호거래지역 : %s\t\t상품상태 : %s\n", info[6], info[8], info[7] );
        System.out.println();
        System.out.printf("거래방식 : %s\t\t\t거래 상태 : %s\n", info[3], info[4]);
        System.out.println();
        String serialNum=info[14];//회원번호 
        String buyOrSell=info[13]; //판매인지 구매인지 나와있는 데이터 칸
        System.out.printf("최종 등록일: %s\t\t\t\t\t\t닉네임 : ", info[1]); nickNameSearch(serialNum);
        System.out.println();
        System.out.println();
        System.out.println("=================================================================================================");
        System.out.println("1. 입금하기 2. 구매확정하기 0. 뒤로 가기");
        System.out.println("==================================================================================================");
        System.out.print("■입력 : "); 
        
        
        inputSaftyDeal(input, pageNumber, buyOrSell, serialNum); // 1. 입금하기 2. 구매확정하기 값을 받는 메서드
        //TODO - 안전거래 입금하기 구매확정하기 선택 매서드 
        reader.close();
     } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     }

  }// DtailSearch

private static  void nickNameSearch(String serialNum) throws IOException { //닉네임을 찾아주는 메서드 
    
 //  try {
        BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERINFO));
        BufferedReader reader1 = new BufferedReader(new FileReader(Data.MEMBERINFO));
     
        int postCount = 0;// 게시글 숫자 카운트 
                        
        
        while (reader.readLine() != null) {// 게시글의 숫자
           postCount++;
        }
        
        String[] post = new String[postCount]; //게시글 정보를 넣는 배열
        
       
        for (int i = 0; i < postCount; i++) {
            post[i] = reader1.readLine();
        }
        String temp =""; // 모든 게시글 넣기 
        
        for (int i = 0; i < postCount; i++) {
            String[] each_Info =  post[i].split("■");
            
            if (each_Info[0].equals(serialNum)) {
        	temp = post[i];
        	break;
        	
         
        	
            }
           
            	
            }
        	
        	String[] info = temp.split("■");
//   		reader.close();
//   		reader1.close();
   		
   		
     		//} catch (FileNotFoundException e) {
     		    // TODO Auto-generated catch block
     		//    e.printStackTrace();
     		//}
   
    		
      
		
        	System.out.println(info[10]);
		
        
		
   
}

private static void inputSaftyDeal(String input, int pageNumber, String buyOrSell, String serialNum) throws IOException { //구매확정 메뉴의 입금하기, 구매확정하기에 대한 입력값을 받는 메서드 
    Scanner sc = new Scanner(System.in);
    
    

	
	while(true){
				
		String registerSel = sc.nextLine();
		
		if(registerSel.equals("1")) {
			 paymentCheck(input, pageNumber, buyOrSell, serialNum);
		}else if(registerSel.equals("2")){
		     	 affirmSaftyDeal(input, pageNumber);
		}else if(registerSel.equals("0")) { //이전 메서드로 이동 
		    chooseSaftyNum(pageNumber, saftyCounter());		       
		}else if(!((registerSel.equals("1"))&&(registerSel.equals("2")))||(registerSel.equals("3"))){ //숫자를 잘 못 입력할 경우 상품 정보를 다시 출력
		    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		    pause();
		    SearchSaftyPost(input, pageNumber);
		}	
		
	}
}// inputSaftyDeal //구매확정 메뉴의 입금하기, 구매확정하기에 대한 입력값을 받는 메서드 

private static void affirmSaftyDeal(String input, int pageNumber) throws IOException { //거래확정 메서드
    //2번 구매 확정을 눌렀을때 데이터 값이 1일경우 
    
    
    try {
        BufferedReader reader = new BufferedReader(new FileReader(Data.GOODSINFO));
        BufferedReader reader1 = new BufferedReader(new FileReader(Data.GOODSINFO));
     
        int postCount = 0;// 게시글 숫자 카운트 
                        
        while (reader.readLine() != null) {// 게시글의 숫자
           postCount++;
        }

        String[] post = new String[postCount]; //게시글 정보를 넣는 배열

       
        for (int i = 0; i < postCount; i++) {
            post[i] = reader1.readLine();
        }
        String temp =""; // 모든 게시글 넣기 
        
        for (int i = 0; i < postCount; i++) {
	           String[] each_Info =  post[i].split("■");
//	           post[i] = reader.readLine();
	           if (each_Info[0].equals(input)&&each_Info[12].equals("1")) {
	                 System.out.println("이미 구매확정 하셨습니다.");  	    
	        	 //temp = post[i];
	                 pause();
	                 SearchSaftyPost(input, pageNumber);
	             } else if(each_Info[0].equals(input)&&each_Info[12].equals("")){
	        	 System.out.println("구매확정 하셨습니다.");
	        	 
	        	 modify(input);
	        	 
	        	 pause();
	        	 SearchSaftyPost(input, pageNumber);
	             }      
        	     }
	           
        
        	   // pause();
	           //chooseSaftyNum(pageNumber, saftyCounter()); //안전거래 
	           //inputSaftyDeal(input, pageNumber); // 1. 입금하기 2. 구매확정하기 값을 받는 메서드
	           
       
        		reader.close();
     } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     }
    
    
    
}

private static void modify(String input) throws IOException { //거래 확정시 거래확정 데이터 "" 값을 1로 변경해주는 메서드
    
    try {
        BufferedReader br = new BufferedReader(new FileReader(Data.GOODSINFO));
        BufferedReader br1 = new BufferedReader(new FileReader(Data.GOODSINFO));

        ArrayList<String> safedeal = new ArrayList<String>();  //수정할 데이터를 찾기위한 배열
        ArrayList<String> rearS = new ArrayList<String>();   //수정되었고 최종적으로 쓰기할 배열
        
        
     
        
        String temp =""; 
        while (br.readLine() != null) {
           safedeal.add(br1.readLine());
        }
        
       
        
        for(int i=0; i< safedeal.size(); i++) {
           String[] each_Info = safedeal.get(i).split("■");
           if(each_Info[0].equals(input)) {
              each_Info = safedeal.get(i).split("■");
              each_Info[12] = "1";
              temp += each_Info[0] + "■" + each_Info[1] + "■" + each_Info[2]+"■"+ each_Info[3]+"■"+ each_Info[4]+"■"+ each_Info[5]
        	      +"■"+ each_Info[6]+"■"+ each_Info[7]+"■"+ each_Info[8]+"■"+ each_Info[9]+"■"+ each_Info[10]+"■"+ each_Info[11]+"■"+ each_Info[12]+"■"+ each_Info[13]+"■"+ each_Info[14];
              rearS.add(temp);
              i++;
           }

           rearS.add(safedeal.get(i));
        }
        
        br.close();
        br1.close();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(Data.GOODSINFO));
        for (int i = 0; i < rearS.size(); i++) {
           String a = rearS.get(i);
           bw.write(a);
           bw.newLine();
        }
        

        bw.close();
        

        
        
     } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     }
  } //modify // 거래확정 버튼을 눌렀을때 메모장 데이터 null을 1로 바꿔준다. 


private static void paymentCheck(String input, int pageNumber, String BuyOrSell, String serialNum) throws IOException { //가상계좌 표시 메서드
 
      
	 try {
	        BufferedReader reader = new BufferedReader(new FileReader(Data.MEMBERADDINFO));
	        BufferedReader reader1 = new BufferedReader(new FileReader(Data.MEMBERADDINFO));
	     
	        int postCount = 0;// 게시글 숫자 카운트 
	                        
	        while (reader.readLine() != null) {// 게시글의 숫자
	           postCount++;
	        }

	        String[] post = new String[postCount]; //게시글 정보를 넣는 배열

	       
	        for (int i = 0; i < postCount; i++) {
	            post[i] = reader1.readLine();
	        }
	        String temp =""; // 모든 게시글 넣기 
	        
	        for (int i = 0; i < postCount; i++) {
		           String[] each_Info =  post[i].split("■");
//		           post[i] = reader.readLine();
		           if (each_Info[8].equals(serialNum)) {
		                 System.out.printf("입금하실 계좌 : %s : %s\n",each_Info[2], each_Info[3]);  	    
		        	 
		                 pause();
		                 SearchSaftyPost(input, pageNumber);
		             
		             }      
	        	     }
		           
	        
	        	   // pause();
		           //chooseSaftyNum(pageNumber, saftyCounter()); //안전거래 
		           //inputSaftyDeal(input, pageNumber); // 1. 입금하기 2. 구매확정하기 값을 받는 메서드
		           
	       
	        		reader.close();
	     } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	     }
	    
	    
	    


     
       
} //paymentCheck 메서드 //
private static void manageSafeDealSell(int pageNumber) { //안전거래 판매리스트 
    
    try { 
        BufferedReader buyReader = new BufferedReader(new FileReader(Data.GOODSINFO));
        
        String line = null;
        int itemCount = 1;
        
        //상품 정보 버리기(해당 페이지 상품 목록 가져오기 위해)
        for (int i=0; i<(pageNumber-1)*5; i++) {
           line = buyReader.readLine();
        }

        System.out.println("==================================================================[판매/안심거래]====================================================================");
        System.out.println("선택\t상품번호\t카테고리\t제목\t\t\t\t\t\t지역\t상품가격\t거래상태\t\t최종등록일");
        System.out.println("=====================================================================================================================================================");
                 
        //상품 정보 출력하기
        while ((line = buyReader.readLine()) != null) { //상품이 있으면
           
            String[] temp = line.split("■");
         if (itemCount <= 5 ) {
             
         if (temp[3].equals("안전거래")&&temp[13].equals("판매")) {
             
             System.out.printf("%s\t%s\t%s\t%s\t\t%s\t\t%s\t%s\t%s\n"
        	     , itemCount++
        	     , temp[0]
                     , temp[2]
        	     , temp[8]
        	     , temp[5]
        	     , temp[6]
        	     , temp[4]
                     , temp[1]);
             
             
             
              
              
              
              
           
              }
//              itemCount++;          
         
         }
        }
        buyReader.close();
        
     } catch (Exception e) {
        e.printStackTrace();
     }
    
}

private static void manageSafeDealBuy(int pageNumber) { //안심거래 구매 메뉴 
    
    
    try { 
        BufferedReader buyReader = new BufferedReader(new FileReader(Data.GOODSINFO));
        
        String line = null;
        int itemCount = 1;
        
        //상품 정보 버리기(해당 페이지 상품 목록 가져오기 위해)
        for (int i=0; i<(pageNumber-1)*5; i++) {
           line = buyReader.readLine();
        }

        System.out.println("=================================================================[구매/안심거래]====================================================================");
        System.out.println("선택\t상품번호\t카테고리\t지역\t\t\t\t\t\t제목\t\t\t상품가격\t거래상태\t\t최종등록일");
        System.out.println("====================================================================================================================================================");
                 
        //상품 정보 출력하기
        while ((line = buyReader.readLine()) != null) { //상품이 있으면
           
            String[] temp = line.split("■");
         if (itemCount <= 5 ) {
             
         if (temp[3].equals("안전거래")&&temp[13].equals("구매")) {
    	     System.out.printf("%s\t%s\t%s\t%s\t\t%s\t\t%s\t%s\t%s\n"
                                     , itemCount++
                                     , temp[0]
                                     , temp[2]
                                     , temp[8]
                                     , temp[5]
                                     , temp[6]
                                     , temp[4]
                                     , temp[1]);
              
              
              
              
              
              
           
              }
//              itemCount++;          
         
         }
        }
        buyReader.close();
        
     } catch (Exception e) {
        e.printStackTrace();
     }
}

private static void viewBuyGoodsList(int pageNumber) { //구매목록
      try {
         BufferedReader buyReader = new BufferedReader(new FileReader(Data.GOODSINFO));
         
         String line = null;
         int itemCount = 1;
         
         //상품 정보 버리기(해당 페이지 상품 목록 가져오기 위해)
         for (int i=0; i<(pageNumber-1)*5; i++) {
            line = buyReader.readLine();
         }

         System.out.println("=================================================================[구매 목록]====================================================================");
         System.out.println("선택\t상품번호\t카테고리\t지역\t\t\t\t\t제목\t\t\t상품가격\t\t거래상태\t최종등록일");
         System.out.println("================================================================================================================================================");
                  
         //상품 정보 출력하기
         while ((line = buyReader.readLine()) != null) { //상품이 있으면
            
             String[] temp = line.split("■");
             if (itemCount <= 5) {
        	
        	 if(temp[13].equals("구매")) //구매글만 표시하기 위해서 
               System.out.printf("%s\t%s\t%s\t%s\t\t%s\t\t%s\t%s\t%s\n"
                                       , itemCount++
                                       , temp[0]
                                       , temp[2]
                                       , temp[8]
                                       , temp[5]
                                       , temp[6]
                                       , temp[4]
                                       , temp[1]);
                            
            }
         }
         buyReader.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   } //viewBuyGoodsList
   
private static void viewSellGoodsList(int pageNumber) {  //판매목록 
      try {
         BufferedReader sellReader = new BufferedReader(new FileReader(Data.GOODSINFO));
         
         String line = null;
         int itemCount = 6;
         
         //상품 정보 버리기
         for (int i=0; i<(pageNumber-1)*5; i++) {
            line = sellReader.readLine();
         }

         System.out.println("==================================================================[판매 목록]==================================================================");
         System.out.println("선택\t상품번호\t카테고리\t제목\t\t\t\t\t\t지역\t상품가격\t거래상태\t\t최종등록일");
         System.out.println("===============================================================================================================================================");
            
         //상품 정보 출력하기
         while ((line = sellReader.readLine()) != null) { //상품이 있으면
             String[] temp = line.split("■");
             if (itemCount <= 10 ) {
        	 if(temp[13].equals("판매")) //팬매글만 표시하기 위해서 
                     System.out.printf("%s\t%s\t%s\t%s\t\t%s\t\t%s\t%s\t%s\n"
                                             , itemCount++
                                             , temp[0]
                                             , temp[2]
                                             , temp[8]
                                             , temp[5]
                                             , temp[6]
                                             , temp[4]
                                             , temp[1]);
        	 }
        	 
         }
         sellReader.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   } //viewSellGoodsList
   
public static void pause() { //puase
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      try {
         System.out.println("계속하시려면 엔터를 눌러주세요.");
         reader.readLine();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   } //pause
   
   
}