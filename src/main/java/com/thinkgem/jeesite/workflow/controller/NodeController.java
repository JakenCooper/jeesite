package com.thinkgem.jeesite.workflow.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;
import com.thinkgem.jeesite.workflow.common.definition.NodeSec;
import com.thinkgem.jeesite.workflow.controller.model.NodesecModel;
import com.thinkgem.jeesite.workflow.controller.model.ReqResult;
import com.thinkgem.jeesite.workflow.service.FlowDefinitionService;
import com.thinkgem.jeesite.workflow.temp.LemonCache;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@Controller
@RequestMapping("${adminPath}")
public class NodeController {

	@Autowired
	private FlowDefinitionService defService;
	
	
	@RequestMapping("/node/add")
	@ResponseBody
	public ReqResult nodeadd(Node node){
		Flow flow = LemonCache.getFlowmap().get(node.getFlowid());
		if(StringUtils.isBlank(node.getId())){
			node.setId(CommonUtils.genuuid());
		}
		defService.addNode(flow, node);
		return ReqResult.genSuccMessage("success!");
	}
	
	@RequestMapping(value="/nodesec/add",method=RequestMethod.POST)
	@ResponseBody
	public Node nodesecadd(@RequestBody NodesecModel nodeSecModel){
		String nodeid = nodeSecModel.getNodeid();
		String flowid = nodeSecModel.getFlowid();
		Flow flow = LemonCache.getFlowmap().get(flowid);
		
		List<NodeSec> nodeseclst =new ArrayList<NodeSec>();
		List<String> nodeids = nodeSecModel.getIds();
		
		for(String secnodeid : nodeids){
			NodeSec sec = new NodeSec(CommonUtils.genuuid(), nodeid, secnodeid);
			sec.setNextNodeName(flow.getNodeById(secnodeid).getName());
			nodeseclst.add(sec);
		}
		
		Node targetnode = flow.getNodeById(nodeid);
		defService.addNodeSec(targetnode, nodeseclst);
		
		return targetnode;
	}
	
	/*private Flow currFlow;
	
	@ModelAttribute
	public Flow getCurrentFlowJson(@RequestBody NodesecModel nodeSecModel){
		Flow flow = LemonCache.getFlowmap().get(nodeSecModel.getFlowid());
		this.currFlow = flow;
		return flow;
	}
	
	@ModelAttribute
	public Flow getCurrentFlowForm(NodesecModel nodeSecModel){
		Flow flow = LemonCache.getFlowmap().get(nodeSecModel.getFlowid());
		this.currFlow = flow;
		return flow;
	}*/
	
	@RequestMapping(value="/nodesec/getnodeid",method=RequestMethod.POST)
	@ResponseBody
	public List<NodeSec> nodesecgetbynodeid(@RequestBody NodesecModel nodeSecModel){
		String flowid = nodeSecModel.getFlowid();
		Flow flow = LemonCache.getFlowmap().get(flowid);
		Node resultNode = flow.getNodeById(nodeSecModel.getNodeid());
		return resultNode.getSecs();
	}
	
}

