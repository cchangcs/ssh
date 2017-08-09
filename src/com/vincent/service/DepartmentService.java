package com.vincent.service;

import java.util.List;

import com.vincent.dao.DepartmentDao;
import com.vincent.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
} 
