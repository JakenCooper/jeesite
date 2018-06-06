package com.thinkgem.jeesite.workflow.common.definition;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String id;
	
	private String name;
	
	private String desc;
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

	public Node() {
		super();
	}



	private List<NodeSec> secs = new ArrayList<NodeSec>();
	
	public void addNodeSec(NodeSec ns){
		secs.add(ns);
	}
	
	private boolean canGetBack;
	
	
	public boolean isCanGetBack() {
		return canGetBack;
	}

	public void setCanGetBack(boolean canGetBack) {
		this.canGetBack = canGetBack;
	}

	private boolean readyToBegin;
	
	private boolean readyToFinish;
	
	private boolean canFlowBack;
	
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

	public boolean isReadyToFinish() {
		return readyToFinish;
	}

	public void setReadyToFinish(boolean readyToFinish) {
		this.readyToFinish = readyToFinish;
	}

	public Node(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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
