package com.thinkgem.jeesite.workflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.controller.model.FlowWebJsonException;
import com.thinkgem.jeesite.workflow.controller.model.ReqResult;
import com.thinkgem.jeesite.workflow.service.FlowDefinitionService;
import com.thinkgem.jeesite.workflow.service.MetadataService;

@Controller
@RequestMapping("${adminPath}")
public class FlowController {

	@Autowired
	private FlowDefinitionService defService;
	
	@Autowired
	private MetadataService metadataService;
	
	@RequestMapping("/flow/all")
	@ResponseBody
	public List<Flow> flowall(){
		return defService.getAllFlowSimple();
	}
	
	@ExceptionHandler
	public ResponseEntity<ReqResult> handleException(FlowWebJsonException jsonException){
		return new ResponseEntity<ReqResult>(jsonException.getReqResult(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/flow/add",method=RequestMethod.POST)
	public String flowadd(Flow flow,Model model){
		defService.createFlow(flow);
		searchAndFindBussColumns(flow);
//		LemonCache.getFlows().add(flow);
//		LemonCache.getFlowmap().put(flow.getId(), flow);
//		model.addAttribute("allflow", LemonCache.getFlows());
		model.addAttribute("allflow", defService.getAllFlowSimple());
		return "a/workflow/sysIndex";
	}
	
	@RequestMapping(value="workflow/definition/flowIndex")
	@ModelAttribute(value="flow")
	public Flow getflow(@RequestParam("flowid") String flowid){
		Flow flow = defService.getFlowById(flowid);
		searchAndFindBussColumns(flow);
		return flow;
	}
	
	private void searchAndFindBussColumns(Flow flow){
		if(UserUtils.getSession().getAttribute("flow_"+flow.getBussTable()+"_busscolumns") == null){
			flow.setColumns(metadataService.getAllColumnsByTableName(flow.getBussTable()));
			UserUtils.getSession().setAttribute("flow_"+flow.getBussTable()+"_busscolumns", flow.getColumns());
		}
	}
	
}
