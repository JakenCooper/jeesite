package com.thinkgem.jeesite.workflow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.workflow.common.definition.Flow;

@Component("flowDao")
public interface FlowDao extends JpaRepository<Flow, String>,JpaSpecificationExecutor<Flow>{

}
