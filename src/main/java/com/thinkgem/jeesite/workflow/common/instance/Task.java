package com.thinkgem.jeesite.workflow.common.instance;

public class Task {

	private String nodeId;
	
	private String assignee;
	
	private String currentAssignee;
	
	private String nextNodeId;
	
	private Object bussObj;
	
	public Task(String nodeId) {
		super();
		this.nodeId = nodeId;
	}
	
	public String getCurrentAssignee() {
		return currentAssignee;
	}

	public void setCurrentAssignee(String currentAssignee) {
		this.currentAssignee = currentAssignee;
	}

	public String getNextNodeId() {
		return nextNodeId;
	}

	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Object getBussObj() {
		return bussObj;
	}

	public void setBussObj(Object bussObj) {
		this.bussObj = bussObj;
	}
}
