package member.model.vo;

import java.sql.Date;

public class ProductIO {
	
	private int ioNo;
	private String productId;
	private Date iodate;
	private int amount;
	private char status;
	
	public ProductIO() {
	}
	public ProductIO(int ioNo, String productId, Date iodate, int amount, char status) {
		super();
		this.ioNo = ioNo;
		this.productId = productId;
		this.iodate = iodate;
		this.amount = amount;
		this.status = status;
	}
	public int getIoNo() {
		return ioNo;
	}
	public void setIoNo(int ioNo) {
		this.ioNo = ioNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getIodate() {
		return iodate;
	}
	public void setIodate(Date iodate) {
		this.iodate = iodate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductIO [ioNo=" + ioNo + ", productId=" + productId + ", iodate=" + iodate + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
}
