package com.thinkgem.jeesite.workflow.common.definition;

public class NodeSec {

	private String id;
	
	private String nodeId;
	
	private String nextNodeId;
	
	private String nextNodeName;
	
	public NodeSec(String id, String nodeId, String nextNodeId) {
		super();
		this.id = id;
		this.nodeId = nodeId;
		this.nextNodeId = nextNodeId;
	}
	

	public String getNextNodeName() {
		return nextNodeName;
	}


	public void setNextNodeName(String nextNodeName) {
		this.nextNodeName = nextNodeName;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNextNodeId() {
		return nextNodeId;
	}

	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}
}
