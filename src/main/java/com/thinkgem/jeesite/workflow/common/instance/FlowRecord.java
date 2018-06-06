package com.thinkgem.jeesite.workflow.common.instance;

// 业务，权限
public class FlowRecord {

	private String nodeId;
	
	private String assignee;
	
	private String nextNodeId;
	
	private Object bussObj;
	
	private String currentAssignee;

	public String getCurrentAssignee() {
		return currentAssignee;
	}

	public void setCurrentAssignee(String currentAssignee) {
		this.currentAssignee = currentAssignee;
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


	public String getNextNodeId() {
		return nextNodeId;
	}

	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}

	public Object getBussObj() {
		return bussObj;
	}

	public void setBussObj(Object bussObj) {
		this.bussObj = bussObj;
	}
	
}
