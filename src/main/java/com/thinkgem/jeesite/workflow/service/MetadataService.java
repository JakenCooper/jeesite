package com.thinkgem.jeesite.workflow.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.workflow.common.exception.FlowIllegalArgumentException;
import com.thinkgem.jeesite.workflow.entity.NodePriv;
import com.thinkgem.jeesite.workflow.utils.CommonUtils;

@Service
@Transactional("bussTx")
public class MetadataService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<NodePriv> getMetadataByTableName(String flowid,String tableName) {
		Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		List<NodePriv> nps = new ArrayList<NodePriv>();
		try {
			DatabaseMetaData dmd = connection.getMetaData();
			ResultSet resultSet = dmd.getTables(null, null, null, new String[] { "TABLE" });
			while (resultSet.next()) {
				String table = resultSet.getString("TABLE_NAME");
				if(tableName.equals(table)){
					String fetchsql = "select * from "+tableName+" limit 0,1";
					ResultSet rs = connection.prepareStatement(fetchsql).executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					for(int index=1;index<=columnCount;index++){
//						System.out.println(rsmd.getColumnName(index));
						NodePriv np = new NodePriv();
						np.setId(CommonUtils.genuuid());
						np.setBusTable(tableName);
						np.setFlowId(flowid);
						np.setBusColumnName(rsmd.getColumnName(index));
						nps.add(np);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FlowIllegalArgumentException("can not get metadata from connection");
		}
		return nps;
	}
	
	public List<String> getAllColumnsByTableName(String tableName) {
		Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		List<String> columns = new ArrayList<String>();
		try {
			DatabaseMetaData dmd = connection.getMetaData();
			ResultSet resultSet = dmd.getTables(null, null, null, new String[] { "TABLE" });
			while (resultSet.next()) {
				String table = resultSet.getString("TABLE_NAME");
				if(tableName.equals(table)){
					String fetchsql = "select * from "+tableName+" limit 0,1";
					ResultSet rs = connection.prepareStatement(fetchsql).executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					for(int index=1;index<=columnCount;index++){
						String columnName = rsmd.getColumnName(index);
						if(!columnName.toLowerCase().equals("id")){
							columns.add(columnName);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FlowIllegalArgumentException("can not get metadata from connection");
		}
		return columns;
	}
}
