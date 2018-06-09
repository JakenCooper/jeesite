drop table if exists t_workflow_flow;
create table t_workflow_flow(
	id varchar(36) primary key comment '主键',
	name varchar(100),
	buss_table varchar(100)
);
drop table if exists t_workflow_node;
create table t_workflow_node(
	id varchar(36) primary key comment '主键',
	name varchar(100),
	node_desc varchar(100),
	flow_id varchar(36),
	can_get_back char(1),
	ready_to_begin char(1),
	ready_to_finish char(1),
	can_flow_back char(1)
);
drop table if exists t_workflow_nodesec;
create table t_workflow_nodesec(
	id varchar(36) primary key comment '主键',
	node_id varchar(36),
	next_node_id varchar(36),
	next_node_name varchar(100)
);
drop table if exists t_node_priv;
create table t_node_priv(
	id varchar(36) primary key comment '主键',
	node_id varchar(36),
	bus_table varchar(100),
	bus_column_name varchar(100),
	bus_model_name varchar(100),
	oper_can_read char(1),
	oper_can_write char(1),
	oper_can_readwrite char(1),
	oper_type int,
	flow_id varchar(36),
	remain_column1 varchar(100),
	remain_column2 varchar(100),
	remain_column3 varchar(100),
	remain_column4 varchar(100),
	remain_column5 varchar(100)
);