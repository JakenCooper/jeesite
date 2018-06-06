package com.thinkgem.jeesite.workflow.common.instance;

import java.util.List;

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;

public class FlowInstance {

	private String id;
	
	private Flow flow ;
	
	private Integer currentStatus;
	
	private Task currentTask;
	
	private Node currentNode;
	
	private List<FlowRecord> flowRecords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public FlowInstance(String id,Flow flow) {
		super();
		this.flow = flow;
		this.id=id;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public List<FlowRecord> getFlowRecords() {
		return flowRecords;
	}

	public void setFlowRecords(List<FlowRecord> flowRecords) {
		this.flowRecords = flowRecords;
	}
	
	
}
