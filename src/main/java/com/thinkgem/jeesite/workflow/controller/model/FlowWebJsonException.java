package com.thinkgem.jeesite.workflow.controller.model;

public class FlowWebJsonException extends RuntimeException{

	private static final long serialVersionUID = -601089657936922795L;
	
	private ReqResult reqResult;

	public FlowWebJsonException(ReqResult reqResult) {
		super();
		this.reqResult = reqResult;
	}

	public ReqResult getReqResult() {
		return reqResult;
	}
}
