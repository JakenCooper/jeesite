package com.thinkgem.jeesite.workflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;
import com.thinkgem.jeesite.workflow.common.definition.NodeSec;
import com.thinkgem.jeesite.workflow.common.exception.FlowIllegalArgumentException;
import com.thinkgem.jeesite.workflow.dao.FlowDao;
import com.thinkgem.jeesite.workflow.dao.NodeDao;
import com.thinkgem.jeesite.workflow.dao.NodeSecDao;
import com.thinkgem.jeesite.workflow.entity.NodePriv;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@Service
@Transactional("jpaTx")
public class FlowDefinitionService {

	@Autowired
	private FlowDao flowDao;
	
	@Autowired
	private NodeDao nodeDao;
	
	@Autowired
	private NodeSecDao nodeSecDao;
	
	public Flow createFlow(Flow flow){
		if(StringUtils.isBlank(flow.getId())){
			flow.setId(CommonUtils.genuuid());
		}
		flowDao.save(flow);
		return flow;
	}
	
	private void expandFlow(Flow flow,List<Node> nodes){
		if(flow == null || nodes == null || nodes.size() == 0){
			return ;
		}
		for(Node node:nodes){
			if(node.isReadyToBegin()){
				flow.setStartNode(node);
			}
			flow.getNodemap().put(node.getId(),node);
		}
		flow.setNodes(nodes);
	}
	
	public Flow getFlowById(String id){
		Flow flow = getFlowByIdSimple(id);
		List<Node> nodes = nodeDao.findByFlowid(flow.getId());
		if(nodes == null || nodes.size() == 0){
			return flow;
		}
		List<String> nodeids = new ArrayList<String>();
		for(Node node : nodes){
			nodeids.add(node.getId());
		}
		List<NodeSec> nodesecs = nodeSecDao.findByNodeIdIn(nodeids);
		if(nodesecs == null || nodesecs.size() == 0){
			expandFlow(flow,nodes);
			return flow;
		}
		Map<String,List<NodeSec>> nodesecmap = new HashMap<String,List<NodeSec>>();
		for(NodeSec nodesec : nodesecs){
			if(nodesecmap.get(nodesec.getNodeId()) == null){
				List<NodeSec> secs = new ArrayList<NodeSec>();
				secs.add(nodesec);
				nodesecmap.put(nodesec.getNodeId(),secs);
			}else{
				nodesecmap.get(nodesec.getNodeId()).add(nodesec);
			}
		}
		for(Node node:nodes){
			node.setSecs(nodesecmap.get(node.getId()));
		}
		expandFlow(flow,nodes);
		return flowDao.findOne(id);
	}
	
	public Flow getFlowByIdSimple(String id){
		return flowDao.findOne(id);
	}
	//只是获取flow的基本属性
	public List<Flow> getAllFlowSimple(){
		return flowDao.findAll();
	}
	
	public Node getNodeByIdSimple(String id){
		return nodeDao.findOne(id);
	}
	
	public void addNode(Flow flow,Node node){
		List<Node> curnodes = nodeDao.findByFlowid(flow.getId());
		if(curnodes != null && curnodes.size() > 0){
			for(Node curnode : curnodes){
				if(curnode.isReadyToBegin() && node.isReadyToBegin()){
					throw new FlowIllegalArgumentException("already has start node");
				}
			}
		}
		if(StringUtils.isBlank(node.getId())){
			node.setId(CommonUtils.genuuid());
		}
		nodeDao.save(node);
	}
	
	public void addNodeSec(Node node,List<NodeSec> secs){
		nodeSecDao.deleteByNodeId(node.getId());
		nodeSecDao.save(secs);
	}
	
	public List<NodeSec> getNodeSecsByNodeId(String nodeId){
		return nodeSecDao.findByNodeId(nodeId);
	}
	
	/**
	 * 通过column生成NodePriv方便后续传值
	 * */
	public List<NodePriv> genNodePrivByColumns(String flowId,String tableName,List<String> columns){
		List<NodePriv> nps = new ArrayList<NodePriv>();
		for(String column : columns){
			NodePriv np = new NodePriv();
			np.setId(CommonUtils.genuuid());
			np.setFlowId(flowId);
			np.setBusTable(tableName);
			np.setBusColumnName(column);
			nps.add(np);
		}
		return nps;
	}
}
