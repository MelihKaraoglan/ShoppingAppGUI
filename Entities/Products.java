package Entities;

public class Products {
	
	private int productId;
	private String productName;
	private double productPrice;
	private int productStock;
	private String productCategory;
	
	public Products(int productId ,String productName, double productPrice, int productStock, String productCategory) {
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productStock = productStock;
	}
	
	
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

}
