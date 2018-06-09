package com.thinkgem.jeesite.workflow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="node_priv")
public class NodePriv {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "node_id")
	private String nodeId;
	@Column(name = "bus_table")
	private String busTable;
	@Column(name = "bus_column_name")
	private String busColumnName;
	@Column(name = "bus_model_name")
	private String busModelName;
	@Column(name = "oper_can_read")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean operCanRead;
	@Column(name = "oper_can_write")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean operCanWrite;
	@Column(name = "oper_can_readwrite")
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean operCanReadWrite;
	@Column(name = "oper_type")
	private Integer operType;
	@Column(name = "flow_id")
	private String flowId;
	@Column(name = "remain_column1")
	private String remainColumn1;
	@Column(name = "remain_column2")
	private String remainColumn2;
	@Column(name = "remain_column3")
	private String remainColumn3;
	@Column(name = "remain_column4")
	private String remainColumn4;
	@Column(name = "remain_column5")
	private String remainColumn5;

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

	public String getBusTable() {
		return busTable;
	}

	public void setBusTable(String busTable) {
		this.busTable = busTable;
	}

	public String getBusColumnName() {
		return busColumnName;
	}

	public void setBusColumnName(String busColumnName) {
		this.busColumnName = busColumnName;
	}

	public String getBusModelName() {
		return busModelName;
	}

	public void setBusModelName(String busModelName) {
		this.busModelName = busModelName;
	}

	public boolean isOperCanRead() {
		return operCanRead;
	}

	public void setOperCanRead(boolean operCanRead) {
		this.operCanRead = operCanRead;
	}

	public boolean isOperCanWrite() {
		return operCanWrite;
	}

	public void setOperCanWrite(boolean operCanWrite) {
		this.operCanWrite = operCanWrite;
	}

	public boolean isOperCanReadWrite() {
		return operCanReadWrite;
	}

	public void setOperCanReadWrite(boolean operCanReadWrite) {
		this.operCanReadWrite = operCanReadWrite;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getRemainColumn1() {
		return remainColumn1;
	}

	public void setRemainColumn1(String remainColumn1) {
		this.remainColumn1 = remainColumn1;
	}

	public String getRemainColumn2() {
		return remainColumn2;
	}

	public void setRemainColumn2(String remainColumn2) {
		this.remainColumn2 = remainColumn2;
	}

	public String getRemainColumn3() {
		return remainColumn3;
	}

	public void setRemainColumn3(String remainColumn3) {
		this.remainColumn3 = remainColumn3;
	}

	public String getRemainColumn4() {
		return remainColumn4;
	}

	public void setRemainColumn4(String remainColumn4) {
		this.remainColumn4 = remainColumn4;
	}

	public String getRemainColumn5() {
		return remainColumn5;
	}

	public void setRemainColumn5(String remainColumn5) {
		this.remainColumn5 = remainColumn5;
	}

}
