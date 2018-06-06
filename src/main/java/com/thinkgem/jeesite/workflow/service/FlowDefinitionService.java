package com.thinkgem.jeesite.workflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;
import com.thinkgem.jeesite.workflow.common.definition.NodeSec;
import com.thinkgem.jeesite.workflow.common.exception.FlowIllegalArgumentException;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@Service
public class FlowDefinitionService {

	public Flow createFlow(String name){
		Flow flow = new Flow(CommonUtils.genuuid());
		flow.setName(name);
		return flow;
	}
	
	public void addNode(Flow flow,Node node){
		flow.getNodes().add(node);
		if(node.isReadyToBegin()){
			if(flow.getStartNode() != null){
				throw new FlowIllegalArgumentException("already has start node");
			}
			flow.setStartNode(node);
		}
		flow.getNodemap().put(node.getId(), node);
	}

	public void addNodeSec(Node node,NodeSec sec){
		node.getSecs().add(sec);
	}
	
	public void addNodeSec(Node node,List<NodeSec> secs){
		node.getSecs().addAll(secs);
	}
}
