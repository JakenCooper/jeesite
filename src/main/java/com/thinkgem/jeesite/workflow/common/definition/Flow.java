package com.thinkgem.jeesite.workflow.common.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.thinkgem.jeesite.workflow.common.exception.FlowIllegalArgumentException;

@Entity(name="t_workflow_flow")
public class Flow {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Transient
	private List<Node> nodes = new ArrayList<Node>();
	@Transient
	private Node startNode;
	@Transient
	private Map<String, Node> nodemap = new HashMap<String,Node>();
	/**
	 * business related
	 * */
	@Column(name="buss_table")
	private String bussTable; //业务表表名
	@Transient
	private List<String> columns; //业务表字段
	
	public Flow(String id) {
		super();
		this.id = id;
	}
	public Flow() {
		super();
	}
	
	public Node getNodeById(String nodeId){
		return nodemap.get(nodeId);
	}
	
	public void addNode(Node node){
		nodes.add(node);
		if(startNode != null){
			throw new FlowIllegalArgumentException("startnode exists");
		}
		if(node.isReadyToBegin()){
			startNode=node;
		}
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


	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public Node getStartNode() {
		return startNode;
	}


	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}


	public Map<String, Node> getNodemap() {
		return nodemap;
	}


	public void setNodemap(Map<String, Node> nodemap) {
		this.nodemap = nodemap;
	}
	
	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getBussTable() {
		return bussTable;
	}

	public void setBussTable(String bussTable) {
		this.bussTable = bussTable;
	}

	public static void main(String[] args) {
	/*	Node node1 = new Node("n1","n1");
		node1.setReadyToBegin(true);
		Node node2 = new Node("n2","n2");
		Node node3 = new Node("n3","n3");
		Node node4 = new Node("n4","n4");
		node4.setReadyToFinish(true);
		node1.addNodeSec(new NodeSec("n1_s1",node1.getId(),node2.getId()));
		node2.addNodeSec(new NodeSec("n2_s1",node2.getId(),node3.getId()));
		node2.addNodeSec(new NodeSec("n2_s2",node2.getId(),node4.getId()));
		node3.addNodeSec(new NodeSec("n3_s1",node3.getId(),node4.getId()));*/
	}
}
