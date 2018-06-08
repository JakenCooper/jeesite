package com.thinkgem.jeesite.workflow.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath*:spring-context.xml")
@ComponentScan(basePackages={"com.thinkgem.jeesite.workflow.service","com.thinkgem.jeesite.workflow.dao"})
@Import(RepoConfig.class)
@EnableTransactionManagement(proxyTargetClass=true)
public class WorkflowConfig {

	@Bean(name="jpaTransactionManager")
	@Qualifier("jpaTx")
	public JpaTransactionManager getJpaTransactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(entityManagerFactory);
		return jtm;
	}
}
