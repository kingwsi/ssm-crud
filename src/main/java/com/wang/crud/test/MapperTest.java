package com.wang.crud.test;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.bridge.MessageWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wang.crud.bean.Department;
import com.wang.crud.bean.Employee;
import com.wang.crud.dao.DepartmentMapper;
import com.wang.crud.dao.EmployeeMapper;

/**
 * 测试dao层
 *
 * @author Koi
 * @version 1.0.0
 * @date 2018-05-23 21:26 使用SpringTest测试 1、导入springTest模块
 * 2、@ContextConfiguration指定Spring配置文件的位置 3、直接autowwired要使用的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    // 自动装配
            DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    // 测试DepartmentMapper
    @Test
    public void testCurd() {
        // 原生测试
        // 1、创建SpringIOC容器
        /*
         * ApplicationContext ioc = new ClassPathXmlApplicationContext("");
         * //2、从容器中获取mapper Deprecated deprecated = ioc.getBean(Deprecated.class);
         * System.out.println(deprecated);
         */

        // 1、插入几个部门
        // departmentMapper.insertSelective(new Department(null, "开发部"));
        // departmentMapper.insertSelective(new Department(null, "测试部"));

        // 2、插入员工信息
        // employeeMapper.insertSelective(new Employee(null, "Tom", "M", "Tom@qq.com",
        // 1));

        // 3、sqlSession批量插入
        // EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // for (int i = 0; i < 500; i++) {
        // String uid = UUID.randomUUID().toString().substring(0, 5) + i;
        // String gander = new Random().nextInt(2)+1==1 ? "M" : "F";
        // mapper.insertSelective(new Employee(null, uid, gander, uid+"@qq.com", new
        // Random().nextInt(2)+1));
        // }
        Department department = departmentMapper.selectByPrimaryKey(1);
        Employee selectByPrimaryKey = employeeMapper.selectByPrimaryKeyWithDept(1);
        System.out.println(department);
        System.out.println(selectByPrimaryKey);
    }

    @Test
    public void testSqlSession() {
        Param param = new Param();
        param.setEmpId(1);
        Object selectOne = this.sqlSession.selectOne("com.wang.crud.dao.DepartmentMapper.selectByPrimaryKey", param);
        System.out.print(selectOne);
    }

    class Param {
        Integer deptId;

        public void setEmpId(Integer deptId) {
            this.deptId = deptId;
        }

        public Integer getEmpId() {
            return this.deptId;
        }
    }
}
