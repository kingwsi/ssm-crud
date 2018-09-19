package com.wang.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.crud.bean.Employee;
import com.wang.crud.bean.EmployeeExample;
import com.wang.crud.dao.EmployeeMapper;

@Service
public class EmpLoyeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
		
	}
	
	/**
	 * 删除员工信息
	 */
	
	public void deleteEmp(int empId) {
		employeeMapper.deleteByPrimaryKey(empId);
	}

	//保存
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		example.createCriteria().andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

}
