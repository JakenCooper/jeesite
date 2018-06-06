package com.thinkgem.jeesite.workflow.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.workflow.common.FlowInstanceContainer;
import com.thinkgem.jeesite.workflow.common.definition.Flow;
import com.thinkgem.jeesite.workflow.common.definition.Node;
import com.thinkgem.jeesite.workflow.common.definition.NodeSec;
import com.thinkgem.jeesite.workflow.common.exception.FlowIllegalArgumentException;
import com.thinkgem.jeesite.workflow.common.exception.FlowOperArgumentException;
import com.thinkgem.jeesite.workflow.common.instance.FlowInstance;
import com.thinkgem.jeesite.workflow.common.instance.FlowRecord;
import com.thinkgem.jeesite.workflow.common.instance.Task;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@Service
public class FlowService {

	public Task getCurrentTask(String instanceId){
		FlowInstance instance = FlowInstanceContainer.getInstanceById(instanceId);
		return instance.getCurrentTask();
	}
	
	public FlowInstance startFlow(Flow flow){
		FlowInstance instance = new FlowInstance(CommonUtils.genuuid(),flow);
		instance.setCurrentTask(new Task(flow.getStartNode().getId()));
		instance.setCurrentNode(flow.getStartNode());
		FlowInstanceContainer.addInstance(instance);
		return instance;
	}
	
	public Task complete(String instanceId,Task task){
		if(StringUtils.isBlank(task.getNextNodeId()) || StringUtils.isBlank(task.getNodeId())){
			throw new FlowIllegalArgumentException("task arg blank");
		}
		FlowInstance instance = FlowInstanceContainer.getInstanceById(instanceId);
		if(instance == null){
			throw new FlowIllegalArgumentException("instanceid not matched");
		}
		if(!instance.getCurrentNode().getId().equals(task.getNodeId())){
			throw new FlowIllegalArgumentException("nodeid not matched");
		}
		Flow currentFlow = instance.getFlow();
		Node node = currentFlow.getNodeById(task.getNodeId());
		boolean nodeseccharge = false;
		// validation for nodesec
		for(NodeSec nodesec : node.getSecs()){
			if(nodesec.getNextNodeId().equals(task.getNextNodeId())){
				nodeseccharge = true;
			}
		}
		if(node.isCanFlowBack()){
			nodeseccharge = true;
		}
		if(!nodeseccharge){
			throw new FlowOperArgumentException(task.getNextNodeId());
		}
		Node nextNode = currentFlow.getNodeById(task.getNodeId());
		instance.setCurrentNode(nextNode);
		instance.setCurrentTask(new Task(nextNode.getId()));
		FlowRecord record = new FlowRecord();
		BeanUtils.copyProperties(task, record);
		instance.getFlowRecords().add(record);
		FlowInstanceContainer.addTodo(task.getCurrentAssignee(), instance);
		return getCurrentTask(instanceId);
	}
	
	public Task backFlow(String instanceId,String currentAssignee){
		FlowInstance instance = FlowInstanceContainer.getInstanceById(instanceId);
		if(instance.getFlowRecords().size() <= 1){
			throw new FlowIllegalArgumentException("back flow record blank");
		}
		List<FlowRecord> records = instance.getFlowRecords();
		FlowRecord latestRecord = records.get(records.size()-1);
		Task currentTask = instance.getCurrentTask();
		currentTask.setAssignee(latestRecord.getCurrentAssignee());
		currentTask.setCurrentAssignee(currentAssignee);
		currentTask.setNextNodeId(latestRecord.getNodeId());
		return complete(instanceId,currentTask);
	} 
	
	public void finishFlow(String instanceId){
		//TODO before finish
		FlowInstanceContainer.removeInstance(instanceId);
	}
	
	public Task completeDirect(String instanceId,Task task){
		FlowInstance instance = FlowInstanceContainer.getInstanceById(instanceId);
		Node nextNode = instance.getFlow().getNodeById(task.getNodeId());
		if(nextNode.getSecs().size()>1){
			throw new FlowIllegalArgumentException("multi next steps,can not complete direct");
		}
		task.setAssignee(task.getCurrentAssignee());
		task.setNextNodeId(nextNode.getId());
		return complete(instanceId,task);
	}
}
