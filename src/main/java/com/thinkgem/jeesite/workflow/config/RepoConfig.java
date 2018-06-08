package com.thinkgem.jeesite.workflow.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages="com.thinkgem.jeesite.workflow.dao",entityManagerFactoryRef="entityManagerFactory")
public class RepoConfig {

	
	@Bean(name="entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource,JpaVendorAdapter vendorAdapter){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		String[] entityPackages = {"com.thinkgem.jeesite.workflow.common.definition"
				,"com.thinkgem.jeesite.workflow.entity"};
		entityManagerFactory.setPackagesToScan(entityPackages);
		return entityManagerFactory;
	}
	
	@Bean(name="vendorAdapter")
	public HibernateJpaVendorAdapter getHibernateJpaVendorAdaptor(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return vendorAdapter;
	}
}
