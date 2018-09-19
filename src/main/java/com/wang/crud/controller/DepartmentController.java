package com.wang.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.crud.bean.Department;
import com.wang.crud.bean.Msg;
import com.wang.crud.service.DepartmentService;

/**
 * 获取dept信息
 * @version 1.0.0
 * @author Koi
 * @date 2018-05-26 00:48
 */

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDept() {
		List<Department> depts = departmentService.getAll();
		return Msg.success().add("depts", depts);
	}

}
