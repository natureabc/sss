package tk.mybatis.springboot.model;

public class CustomerInfo {

	private Long customerGroup;
	private Long customerId;
	private String itemUniqueId;
	private String name;
	private String orderUniqueId;
	private Long sequence;
	public Long getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(Long customerGroup) {
		this.customerGroup = customerGroup;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getItemUniqueId() {
		return itemUniqueId;
	}
	public void setItemUniqueId(String itemUniqueId) {
		this.itemUniqueId = itemUniqueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderUniqueId() {
		return orderUniqueId;
	}
	public void setOrderUniqueId(String orderUniqueId) {
		this.orderUniqueId = orderUniqueId;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	
	
}
