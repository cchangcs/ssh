package com.vincent.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.vincent.entities.Employee;
import com.vincent.service.DepartmentService;
import com.vincent.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,
	ModelDriven<Employee>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String list(){
		request.put("employees",employeeService.getAll());
		return "list";
	}

	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String delete(){
		employeeService.delete(id);
		try {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	
	public String input(){
		request.put("departments", departmentService.getAll());
		return INPUT;
	}
	
	public void prepareInput(){
		if(id != null){
			model = (Employee) employeeService.get(id);
		}
	}
	
	private Map<String,Object> request;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public String save(){
		
		if(id == null){
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	/**
	 * 可以根据id 来判断为 save 方法准备的model 是 new 的还是从数据库获取的！
	 * 在有缓存的情况下推荐使用
	 */
	public void prepareSave(){
		if(id == null){
			model = new Employee();
		}else{
			model = employeeService.get(id);
		}
	}
	
	@Override
	public void prepare() throws Exception {
		
	}

	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}
	
	private String lastName;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String validateLastName() throws UnsupportedEncodingException{
		if(employeeService.lastNameIsValid(lastName))
		{
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}
}
