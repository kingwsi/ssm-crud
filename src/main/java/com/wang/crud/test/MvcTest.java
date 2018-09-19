package com.wang.crud.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

/**
 * 使用Spring测试模块提供的测试请求功能
 * @version 1.0.0
 * @author Koi
 * @date 2018-05-24 13:29
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	
	//传入SpringMvc的IOC
	@Autowired
	WebApplicationContext context;
	//使用虚拟MVC请求测试
	MockMvc mockMvc;
	
	@Before
	public void initMokcMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/emps").param("pn", "1"))
				.andReturn();
		
		//请求成功后，请求域中会有pageInfo
		PageInfo attribute = (PageInfo) result.getRequest()
				.getAttribute("pageInfo");
		System.out.println("当前页码："+attribute.getPageNum());
	}
}
