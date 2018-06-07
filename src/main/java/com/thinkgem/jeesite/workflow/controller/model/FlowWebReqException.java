package com.thinkgem.jeesite.workflow.controller.model;

public class FlowWebReqException extends RuntimeException{

	private static final long serialVersionUID = -601089657936922795L;
	
	private ReqResult reqResult;

	public FlowWebReqException(ReqResult reqResult) {
		super();
		this.reqResult = reqResult;
	}

	public ReqResult getReqResult() {
		return reqResult;
	}
}
