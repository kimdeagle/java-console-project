package com.garden.used.member;

public class Goods { //상품정보

	private String goodsNumber;
	private String registerDate;
	private String goodsCategory;
	private String wayOfDeal;
	private String dealState;
	private String goodsTitle;
	private String goodsPrice;
	private String goodsState;
	private String address;
	private String goodsDetail;
	private String goodsHashtag;
	private String goodsLikeCount;
	private String goodsComment;
	private String goodsBuyOrSell;
	private String buyerNumber;
	private String sellerNumber;
	
	public Goods(String goodsBuyOrSell, String buyerNumber, String sellerNumber) {
		
		this.goodsNumber = "";
		this.registerDate = "";
		this.goodsCategory = "";
		this.wayOfDeal = "";
		this.dealState = "";
		this.goodsTitle = "";
		this.goodsPrice = "0";
		this.goodsState = "";
		this.address = "";
		this.goodsDetail = "";
		this.goodsHashtag = "";
		this.goodsLikeCount = "0";
		this.goodsComment = "";
		this.goodsBuyOrSell = goodsBuyOrSell;
		this.buyerNumber = buyerNumber;
		this.sellerNumber = sellerNumber;
		
	}
	
	public Goods(String goodsNumber, String registerDate, String category, String wayOfDeal, String dealState,
			String goodsTitle, String goodsPrice, String goodsState, String address, String goodsDetail, String hashtag,
			String goodsLikeCount, String goodsComment, String goodsBuyOrSell, String buyerNumber,
			String sellerNumber) {

		this.goodsNumber = goodsNumber;
		this.registerDate = registerDate;
		this.goodsCategory = category;
		this.wayOfDeal = wayOfDeal;
		this.dealState = dealState;
		this.goodsTitle = goodsTitle;
		this.goodsPrice = goodsPrice;
		this.goodsState = goodsState;
		this.address = address;
		this.goodsDetail = goodsDetail;
		this.goodsHashtag = hashtag;
		this.goodsLikeCount = goodsLikeCount;
		this.goodsComment = goodsComment;
		this.goodsBuyOrSell = goodsBuyOrSell;
		this.buyerNumber = buyerNumber;
		this.sellerNumber = sellerNumber;
		
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getWayOfDeal() {
		return wayOfDeal;
	}

	public void setWayOfDeal(String wayOfDeal) {
		this.wayOfDeal = wayOfDeal;
	}

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getGoodsHashtag() {
		return goodsHashtag;
	}

	public void setGoodsHashtag(String goodsHashtag) {
		this.goodsHashtag = goodsHashtag;
	}

	public String getGoodsLikeCount() {
		return goodsLikeCount;
	}

	public void setGoodsLikeCount(String goodsLikeCount) {
		this.goodsLikeCount = goodsLikeCount;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public String getGoodsBuyOrSell() {
		return goodsBuyOrSell;
	}

	public void setGoodsBuyOrSell(String goodsBuyOrSell) {
		this.goodsBuyOrSell = goodsBuyOrSell;
	}

	public String getBuyerNumber() {
		return buyerNumber;
	}

	public void setBuyerNumber(String buyerNumber) {
		this.buyerNumber = buyerNumber;
	}

	public String getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(String sellerNumber) {
		this.sellerNumber = sellerNumber;
	}

	
}
