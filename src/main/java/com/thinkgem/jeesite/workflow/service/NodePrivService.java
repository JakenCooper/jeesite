package com.thinkgem.jeesite.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.workflow.dao.NodePrivDao;
import com.thinkgem.jeesite.workflow.entity.NodePriv;

@Service
public class NodePrivService {

	@Autowired
	private NodePrivDao nodePrivDao;
	
	@Transactional("jpaTx")
	public List<NodePriv> getAllNodePriv(){
		return nodePrivDao.findAll();
	}
	
	@Transactional("jpaTx")
	public void addOne(NodePriv np){
		nodePrivDao.save(np);
	}
}
