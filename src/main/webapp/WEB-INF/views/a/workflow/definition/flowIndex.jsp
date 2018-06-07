<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="container">
	<button class="btn btn-md btn-primary" data-toggle="modal" data-target="#node_add_modal">
		<span class="glyphicon  glyphicon-plus"></span>&nbsp;添加节点
	</button>
	<br/><br/>
	<table class="table">
		<caption>---所有节点---</caption>
		<thead>
			<tr>
				<td>节点名称</td>
				<td>节点描述</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty flow}">
				<c:if test="${!empty flow.nodes}">
					<c:forEach items="${flow.nodes}" var="node">
						<tr onclick="nodedetail('${node.id}')">
							<td>${node.name}</td>
							<td>${node.desc}</td>
						</tr>
					</c:forEach>
				</c:if>
			</c:if>
		</tbody>
	</table>
	
	
	<div class="modal fade" id="node_add_modal" tabindex="-1" role="dialog" aria-labelledby="node_add_model_label" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="node_add_model_label">添加节点</h4>
	            </div>
	            <div class="modal-body">
	            	
					<form class="form-horizontal" id="nodesubmitform" role="form" action="${ctx}/node/add" method="post">
					  <input type="hidden" name="flowid" value="${flow.id}"/>
					  <div class="form-group">
					    <label for="name" class="col-sm-2 control-label">节点名称</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="name" placeholder="请输入节点名称">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="desc" class="col-sm-2 control-label">节点描述</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="desc" placeholder="请输入节点描述">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="desc" class="col-sm-2 control-label">其他选项</label>
					    <div class="col-sm-10">
					    	<div class="checkbox">
					    <label>
					      <input type="checkbox" name="readyToBegin">可以开始-开始节点标志
					    </label>
					    <br/><br/>
					    <label>
					      <input type="checkbox" name="readyToFinish">可以结束-结束节点标志
					    </label>
					    <br/><br/>
					    <label>
					      <input type="checkbox" name="canFlowBack">可以回退-退回上一个步骤
					    </label>
					    <br/><br/>
					    <label>
					      <input type="checkbox" name="canGetBack">可以收回-收回未处理的步骤
					    </label>
					  </div>
					    </div>
					  </div>
					</form>
	            	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" id="node_close_btn" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="node_add_submit()">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	
	<div class="modal fade" id="nodesec_manager_modal" tabindex="-1" role="dialog" aria-labelledby="nodesec_manager_label" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="nodesec_manager_label">节点管理</h4>
	            </div>
	            <div class="modal-body">
	            	
					<ul class="nav nav-pills">
					  <li class="active"><a onclick="/jeesite/a/node/manage?nodeid">下一步设置</a></li>
					  <li><a onclick="nodesec_manager_route('datapriv')">数据权限设置</a></li>
					  <li><a onclick="nodesec_manager_route('rolepriv')">角色权限设置</a></li>
					</ul>
	            </div>
	            <!-- 
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" id="node_close_btn" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="node_add_submit()">提交更改</button>
	            </div> -->
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<script type="text/javascript">
	
		var flow_node_curr_id = '';
		function node_add_submit(){
			var datastr = $('#nodesubmitform').serialize();
			var node_add_url = rootpath+'/node/add';
			$.ajax({
				url:node_add_url,
				data:datastr,
				type:'post',
				success:function(){
					$('#node_close_btn').click();
					flushright('node');
				}
			});
		}
		
		function nodedetail(){
			flow_node_curr_id = arguments[0];
			$('#nodesec_manager_modal').find('li:eq(0)').find('a').attr('href',rootpath+'/node/manage/nextstep?nodeid='+flow_node_curr_id);
			$('#nodesec_manager_modal').find('li:eq(1)').find('a').attr('href',rootpath+'/node/manage/datapriv/?nodeid='+flow_node_curr_id);
			$('#nodesec_manager_modal').find('li:eq(2)').find('a').attr('href',rootpath+'/node/manage/rolepriv/?nodeid='+flow_node_curr_id);
			$('#nodesec_manager_modal').modal({backdrop:false});
		}
		
		function nodesec_manager_route(){
			if(arguments[0] == 'next'){
				
			}
		}
		
	</script>
</div>