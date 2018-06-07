package com.thinkgem.jeesite.workflow.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.controller.model.FlowWebJsonException;
import com.thinkgem.jeesite.workflow.controller.model.ReqResult;
import com.thinkgem.jeesite.workflow.service.FlowDefinitionService;
import com.thinkgem.jeesite.workflow.temp.LemonCache;

@Controller
@RequestMapping("${adminPath}")
public class FlowController {

	@Autowired
	private FlowDefinitionService defService;
	
	@RequestMapping("/flow/all")
	@ResponseBody
	public List<Flow> flowall(){
		return LemonCache.getFlows();
	}
	
	@ExceptionHandler
	public ResponseEntity<ReqResult> handleException(FlowWebJsonException jsonException){
		return new ResponseEntity<ReqResult>(jsonException.getReqResult(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/flow/add",method=RequestMethod.POST)
	public String flowadd(HttpServletRequest request,Model model){
		String name = request.getParameter("name");
		Flow flow = defService.createFlow(name);
		LemonCache.getFlows().add(flow);
		LemonCache.getFlowmap().put(flow.getId(), flow);
		model.addAttribute("allflow", LemonCache.getFlows());
		return "a/workflow/sysIndex";
	}
	
	@RequestMapping(value="workflow/definition/flowIndex")
	@ModelAttribute(value="flow")
	public Flow getflow(@RequestParam("flowid") String flowid){
		return LemonCache.getFlowmap().get(flowid);
	}
	
}
