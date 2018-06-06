package com.thinkgem.jeesite.workflow.common.exception;

public class FlowOperArgumentException extends RuntimeException{

	private String nextId;

	public FlowOperArgumentException(String nextId) {
		super();
		this.nextId = nextId;
	}
	
}
