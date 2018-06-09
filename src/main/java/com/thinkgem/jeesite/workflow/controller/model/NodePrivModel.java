package com.thinkgem.jeesite.workflow.controller.model;

import java.util.List;

import com.thinkgem.jeesite.workflow.entity.NodePriv;

public class NodePrivModel {

	private String flowid;
	private String nodeid;
	private boolean[] operCanReadA;
	private boolean[] operCanWriteA;
	private List<NodePriv> nps;
	
	public List<NodePriv> getNps() {
		return nps;
	}

	public void setNps(List<NodePriv> nps) {
		this.nps = nps;
	}

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

	public boolean[] getOperCanReadA() {
		return operCanReadA;
	}

	public void setOperCanReadA(boolean[] operCanReadA) {
		this.operCanReadA = operCanReadA;
	}

	public boolean[] getOperCanWriteA() {
		return operCanWriteA;
	}

	public void setOperCanWriteA(boolean[] operCanWriteA) {
		this.operCanWriteA = operCanWriteA;
	}
}
