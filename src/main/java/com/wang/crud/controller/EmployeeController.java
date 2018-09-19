package com.wang.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.crud.bean.Employee;
import com.wang.crud.bean.Msg;
import com.wang.crud.service.EmpLoyeeService;

/**
 * 处理员工CRUD请求
 * @version 1.0.0
 * @author Koi
 * @date 2018-05-23 23:59
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmpLoyeeService employeeService;
	/**
	 * 查询员工信息 分页查询
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model) {
		//引入PageHelper分页插件
		//PageHelper使用方法，在查询前调用,传入页码、pagesize
		PageHelper.startPage(pn, 5);
		//后面紧跟查询，这个查询就是个分页查询
		List<Employee> emps = employeeService.getAll();
		
		//用PageInfo 包装查询结果
		//里面封装了详细的分页信息, 连续显示页码数为5
		PageInfo page = new PageInfo(emps, 5);
		model.addAttribute("pageInfo", page);
		System.out.println(page.getList().get(1));
		return "list";
	}

	
	@RequestMapping(value="/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//PageHelper使用方法，在查询前调用,传入页码、pagesize
				PageHelper.startPage(pn, 5);
				//后面紧跟查询，这个查询就是个分页查询
				List<Employee> emps = employeeService.getAll();
				
				//用PageInfo 包装查询结果
				//里面封装了详细的分页信息, 连续显示页码数为5
				PageInfo page = new PageInfo(emps, 5);
//				System.out.println(page.getList().get(1));
				return Msg.success().add("pageInfo", page);
		
	}
	
	/**
	 * 员工保存
	 * JSR303校验
	 * 1、需要导入Hibernate-Validator
	 * @param employee
	 * @return
	 */
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			//JSR校验失败
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println("错误字段名"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	@RequestMapping(value="/checkUser")
	@ResponseBody
	public Msg checkUser(String empName) {
		String validate = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(validate)) {
			return Msg.fail().add("msg", "请输入6-18位数字字母组合或2-5位中文");
		}
		boolean result = employeeService.checkUser(empName);
		if(result) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	@RequestMapping("/emp-del")
	public String delEmp(@RequestParam(value="empId",defaultValue="0")Integer empId, Model model) {
		employeeService.deleteEmp(empId);
		getEmps(1, model);
		return "list";
	}
}
