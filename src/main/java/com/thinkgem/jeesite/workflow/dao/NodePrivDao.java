package com.thinkgem.jeesite.workflow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.workflow.entity.NodePriv;

@Component("nodePrivDao")
public interface NodePrivDao extends JpaRepository<NodePriv, String>,JpaSpecificationExecutor<NodePriv>{

}
