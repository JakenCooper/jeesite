package com.thinkgem.jeesite.workflow.controller.model;

public class ReqResult {

	// 1:success 0:error
	private Integer code;
	
	// bussiness object
	private Object busObj;
	
	private String msg;
	
	public static ReqResult genSuccMessage(String msg){
		ReqResult message = new ReqResult();
		message.code = 1;
		message.msg = msg;
		return message;
	}
	
	public static ReqResult genSuccMessage(String msg,Object obj){
		ReqResult message = new ReqResult();
		message.code = 1;
		message.msg = msg;
		message.busObj = obj;
		return message;
	}
	
	public static ReqResult genErrMessage(String msg){
		ReqResult message = new ReqResult();
		message.code = 0;
		message.msg = msg;
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public Object getBusObj() {
		return busObj;
	}

	public String getMsg() {
		return msg;
	}
	
}
