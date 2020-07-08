package tk.mybatis.springboot.model;

public class OrderInfoExcelVo extends OrderInfoExcel{

	//private OrderInfoExcel orderInfoExcel;
	
	private Long createTime;
	private Long id;
	private String orderUniqueId;
	
	
	public Long getCreateTime() {
		return System.currentTimeMillis();
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderUniqueId() {
		return orderUniqueId;
	}
	public void setOrderUniqueId(String orderUniqueId) {
		this.orderUniqueId = orderUniqueId;
	}
	/*public OrderInfoExcel getOrderInfoExcel() {
		return orderInfoExcel;
	}
	public void setOrderInfoExcel(OrderInfoExcel orderInfoExcel) {
		this.orderInfoExcel = orderInfoExcel;
	}*/
	
	
	
	
}
