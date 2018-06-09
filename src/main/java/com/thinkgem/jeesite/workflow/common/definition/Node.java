package com.thinkgem.jeesite.workflow.common.definition;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="t_workflow_node")
public class Node {

	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="node_desc")
	private String desc;
	@Column(name="flow_id")
	private String flowid;
	@Transient
	private List<NodeSec> secs = new ArrayList<NodeSec>();
	
	@Column(name="can_get_back")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean canGetBack;
	@Column(name="ready_to_begin")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean readyToBegin;
	@Column(name="ready_to_finish")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean readyToFinish;
	@Column(name="can_flow_back")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean canFlowBack;

	public Node(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Node() {
		super();
	}
	
	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void addNodeSec(NodeSec ns){
		secs.add(ns);
	}
	
	
	public boolean isCanGetBack() {
		return canGetBack;
	}

	public void setCanGetBack(boolean canGetBack) {
		this.canGetBack = canGetBack;
	}

	public boolean isReadyToFinish() {
		return readyToFinish;
	}

	public void setReadyToFinish(boolean readyToFinish) {
		this.readyToFinish = readyToFinish;
	}

	public boolean isCanFlowBack() {
		return canFlowBack;
	}

	public void setCanFlowBack(boolean canFlowBack) {
		this.canFlowBack = canFlowBack;
	}

	public boolean isReadyToBegin() {
		return readyToBegin;
	}

	public void setReadyToBegin(boolean readyToBegin) {
		this.readyToBegin = readyToBegin;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<NodeSec> getSecs() {
		return secs;
	}


	public void setSecs(List<NodeSec> secs) {
		this.secs = secs;
	}

	@Override
	public String toString() {
		return "[node] id--- >"+id+", name--->"+name;
	}
	
}
