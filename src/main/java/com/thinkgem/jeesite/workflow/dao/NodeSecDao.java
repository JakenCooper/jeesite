package com.thinkgem.jeesite.workflow.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.workflow.common.definition.NodeSec;

@Component("nodeSecDao")
public interface NodeSecDao extends JpaRepository<NodeSec, String>,JpaSpecificationExecutor<NodeSec>{
	
	public List<NodeSec> findByNodeId(String nodeId);
	
	public List<NodeSec> findByNodeIdIn(List<String> nodeids);
	
	@Modifying
	@Query("delete from t_workflow_nodesec where node_id=:nodeId")
	public void deleteByNodeId(@Param("nodeId") String nodeId);
}
