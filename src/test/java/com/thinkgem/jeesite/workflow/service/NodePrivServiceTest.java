package com.thinkgem.jeesite.workflow.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinkgem.jeesite.workflow.config.WorkflowConfig;
import com.thinkgem.jeesite.workflow.entity.NodePriv;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WorkflowConfig.class)
public class NodePrivServiceTest {

	@Autowired
	private NodePrivService service;
	
	@Autowired
	private MetadataService mds;
	
//	@Test
	public void findall(){
		List<NodePriv> nps = service.getAllNodePriv();
		for(NodePriv np : nps){
			System.out.println(np.getId());
		}
	}
	
//	@Test
	public void addone(){
		NodePriv np = new NodePriv();
		np.setId(CommonUtils.genuuid());
		service.addOne(np);
	}
	
	@Test
	public void metadata(){
		mds.getMetadataByTableName("","oa_secret_send");
	}
}
