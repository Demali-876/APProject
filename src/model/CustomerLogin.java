package Hibernate;


import java.io.Serializable;

public class CustomerLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerId;
	private String customerPassword;
	
	public CustomerLogin() {
		this.id = 0;
		this.customerId = 0;
		this.customerPassword = "";
	}

	public CustomerLogin(int id, int customerId, String customerPassword) {
		this.id = id;
		this.customerId = customerId;
		this.customerPassword = customerPassword;
	}
	
	public CustomerLogin(CustomerLogin obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.customerPassword = obj.customerPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomerId: " + customerId + "\ncustomerPassword: " + customerPassword + "\n";
	}
	
}
