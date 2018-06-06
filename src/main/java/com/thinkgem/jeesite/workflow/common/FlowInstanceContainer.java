package com.thinkgem.jeesite.workflow.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thinkgem.jeesite.workflow.common.instance.FlowInstance;
import com.thinkgem.jeesite.workflow.common.instance.Task;

public class FlowInstanceContainer {

	private static final Set<FlowInstance> instanceHolder;
	

	private static final Map<String, Set<FlowInstance>> todoHolder;
	
	static{
		instanceHolder = new HashSet<FlowInstance>();
		todoHolder = new HashMap<String,Set<FlowInstance>>();
	}
	
	public static FlowInstance getInstanceById(String id){
		for(FlowInstance instance : instanceHolder){
			if(instance.getId().contentEquals(id)){
				return instance;
			}
		}
		return null;
	}
	
	public static void addInstance(FlowInstance instance){
		instanceHolder.add(instance);
	}
	
	public static void removeInstance(String instanceId){
		FlowInstance instance = getInstanceById(instanceId);
		if(instance != null){
			instanceHolder.remove(instance);
		}
	}
	
	public static void addTodo(String currentAssignee,FlowInstance instance){
		Set<FlowInstance> instances = null;
		if(todoHolder.get(currentAssignee) == null){
			instances = new HashSet<FlowInstance>();
			todoHolder.put(currentAssignee, instances);
		}
		instances = todoHolder.get(currentAssignee);
		instances.add(instance);
	}
	
	public static List<Task> getAllTodo(String currentAssignee){
		List<Task> tasklst = new ArrayList<Task>();
		if(todoHolder.get(currentAssignee) == null){
			return tasklst;
		}
		for(FlowInstance instance : todoHolder.get(currentAssignee)){
			tasklst.add(instance.getCurrentTask());
		}
		return tasklst;
	}
}
