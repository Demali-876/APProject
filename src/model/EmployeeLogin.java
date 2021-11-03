package Hibernate;

import java.io.Serializable;

public class EmployeeLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int employeeId;
	private String employeePassword;
	
	public EmployeeLogin() {
		this.id = 0;
		this.employeeId = 0;
		this.employeePassword = "";
	}
	
	public EmployeeLogin(int id, int employeeId, String employeePassword) {
		this.id = id;
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
	}
	
	public EmployeeLogin(EmployeeLogin emplog) {
		this.id = emplog.id;
		this.employeeId = emplog.employeeId;
		this.employeePassword = emplog.employeePassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nemployeeId: " + employeeId + "\nemployeePassword: " + employeePassword + "\n";
	}
	
}
