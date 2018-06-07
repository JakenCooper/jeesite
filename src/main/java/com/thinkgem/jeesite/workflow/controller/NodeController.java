package com.thinkgem.jeesite.workflow.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;
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
	
}

