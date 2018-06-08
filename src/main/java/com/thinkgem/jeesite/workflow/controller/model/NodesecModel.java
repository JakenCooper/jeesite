package com.thinkgem.jeesite.workflow.controller.model;

import java.util.List;

public class NodesecModel {

	private String nodeid;
	
	private String flowid;
	
	private List<String> ids;

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
