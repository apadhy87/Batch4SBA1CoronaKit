package com.iiht.evaluation.coronokit.model;

public class KitDetail {

	private int id;
	private int coronaKitId;
	private ProductMaster product;
	private int quantity;
	private int amount;
	
	public KitDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public KitDetail(int id, int coronaKitId, ProductMaster product, int quantity, int amount) {
		this.id = id;
		this.coronaKitId = coronaKitId;
		this.product = product;
		this.quantity = quantity;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoronaKitId() {
		return coronaKitId;
	}
	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}
	public ProductMaster getProduct() {
		return product;
	}
	public void setProduct(ProductMaster product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}