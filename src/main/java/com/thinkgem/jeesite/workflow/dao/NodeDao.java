package com.thinkgem.jeesite.workflow.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.workflow.common.definition.Node;

@Component("nodeDao")
public interface NodeDao extends JpaRepository<Node,String>,JpaSpecificationExecutor<Node>{

	public List<Node> findByFlowid(String flowid);
	
}
