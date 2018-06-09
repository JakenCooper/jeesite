package com.thinkgem.jeesite.workflow.common.definition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="t_workflow_nodesec")
public class NodeSec {

	@Id
	@Column(name="id")
	private String id;
	@Column(name="node_id")
	private String nodeId;
	@Column(name="next_node_id")
	private String nextNodeId;
	@Column(name="next_node_name")
	private String nextNodeName;
	
	public NodeSec(String id, String nodeId, String nextNodeId) {
		super();
		this.id = id;
		this.nodeId = nodeId;
		this.nextNodeId = nextNodeId;
	}
	public NodeSec() {
		super();
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
