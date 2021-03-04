package com.garden.used.member;

public class MemberAddInformation {

	/*
	
	실계좌은행명, 실계좌번호, 가상계좌은행명, 가상계좌번호, 매너온도(0~5), 회원등급(씨앗,새싹,풀,꽃,나무), 구매횟수, 판매횟수, 회원고유번호(1-1)
	
	*/
	
	private String sellerBankName; //판매자 은행명
	private String sellerAccount; //판매자 실계좌(13자리)
	private String virtualBankName; //가상 은행명
	private String virtualAccount; //가상 계좌(12자리)
	private int mannerScore; //매너온도
	private String memberGrade; //회원등급
	private int buyCount; //구매횟수
	private int sellCount; //판매횟수
	private String memberNumber; //회원 고유번호
	
	public String getSellerBankName() {
		return sellerBankName;
	}
	public void setSellerBankName(String sellerBankName) {
		this.sellerBankName = sellerBankName;
	}
	public String getSellerAccount() {
		return sellerAccount;
	}
	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	public String getVirtualBankName() {
		return virtualBankName;
	}
	public void setVirtualBankName(String virtualBankName) {
		this.virtualBankName = virtualBankName;
	}
	public String getVirtualAccount() {
		return virtualAccount;
	}
	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}
	public int getMannerScore() {
		return mannerScore;
	}
	public void setMannerScore(int mannerScore) {
		this.mannerScore = mannerScore;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	
}
