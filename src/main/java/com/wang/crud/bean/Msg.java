package com.wang.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回消息的类
 * @version 1.0.0
 * @author Koi
 * @date 2018-05-25 13:43
 */
public class Msg {
	
	//定义状态码
	private int code;
	
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> extend =  new HashMap<String, Object>();
	
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("success!");
		return result;
	}
	
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("fail!");
		return result;
	}
	
	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
}
