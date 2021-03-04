package com.garden.used.nonmember;

public class GoodsObject { //상품 목록을 확인 할 수 있는 객체 클래스
	
	private String numGoods;
	private String category;
	private String area;
	private String title;
	private int price;
	private String state;
	private String nickName; 
	private String date;
	private String levelGoods;
	private String dealWay;
	private String preferenceArea;
	private int like;
	private String detail;
	private String tag;
	private String phoneNum;

	
	//상품 게시물 리스트
	public GoodsObject(String numsGoods, String category, String area, String title, int price, String state, String nickName, String date) {
		
		this.numGoods = numsGoods;
		this.category = category;
		this.area = area;
		this.title = title;
		this.price = price;
		this.state = state;
		this.nickName = nickName;
		this.date = date;
	
	}
	
	//상품 상세 게시물 오버로딩
	public GoodsObject(String title, String numsGoods, String category, int price 
			, String levelGoods
			, String state
			, String dealWay
			, String date
			, String nickName
			, String phoneNum
			, String preferenceArea
			, int like
			, String detail
			, String tag) 
	
	{
	
		this.title = title;
		this.numGoods = numsGoods;
		this.category = category;
		this.price = price;
		this.levelGoods = levelGoods;
		this.state = state;
		this.dealWay = dealWay;
		this.nickName = nickName;
		this.phoneNum = phoneNum;
		this.preferenceArea = preferenceArea;
		this.like = like;
		this.detail = detail;
		this.tag = tag;
		
		
		
		
		
	}
	
	
	
	
	
	//getter and setter
	public String getNumGoods() {
		return numGoods;
	}
	public void setNumGoods(String numGoods) {
		this.numGoods = numGoods;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getLevelGoods() {
		return levelGoods;
	}

	public void setLevelGoods(String levelGoods) {
		this.levelGoods = levelGoods;
	}

	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPreferenceArea() {
		return preferenceArea;
	}

	public void setPreferenceArea(String preferenceArea) {
		this.preferenceArea = preferenceArea;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
}
