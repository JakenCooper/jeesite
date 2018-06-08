<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<ul class="nav nav-tabs" id="nodesec_tab_compo">
					  <li class="active"><a href="#nodesec_nextstep" data-toggle="tab">下一步设置</a></li>
					  <li><a href="#nodesec_datapriv" data-toggle="tab">数据权限设置</a></li>
					  <li><a href="#nodesec_rolepriv" data-toggle="tab">角色权限设置</a></li>
					</ul>
					<!-- ##################################################################### -->
					<!--nodesec begin -->
					<div id="nodesec_tab_content" class="tab-content tab-lg">
					    <div class="tab-pane fade in active" id="nodesec_nextstep">
					    	<div class="container"><br/><br/>
					    		<div class="row">
									<div class="col-sm-2">
										<form class="form-horizontal" role="form" action="" method="post" id="nodesc_left_form">
											<ul class="list-group" id="nodesec_left_form_ul">
											<c:if test="${!empty flow.nodes}">
												<c:forEach items="${flow.nodes}" var="node">
													<c:forEach items="${node.secs}" var="sec">
														<li class="list-group-item text-center">
															<label>
														      <input type="checkbox" value="${sec.nextNodeId}">&nbsp;&nbsp;${sec.nextNodeName}
														    </label>
														</li>
													</c:forEach>
												</c:forEach>
											</c:if>
											</ul>
										</form>
									</div>
									
									<div class="col-sm-1">
										<button class="btn btn-success btn-md" onclick="nodesc_exchange(1)"><<</button>
										<button class="btn btn-danger btn-md" style="margin-top:10px"  >>></button><br/>
									</div>
									<script type="text/javascript">
										function nodesc_exchange(){
											$('#nodesc_right_form').find(':checked').each(function(index,ele){
												$('#nodesec_left_form_ul').append('<li class="list-group-item text-center"><label><input type="checkbox" value="'+$(ele).val()+'"/>   '
													+$(ele).attr('data-name')+'</label></li>');
												//+'<input type="checkbox" value="'+$(ele).val()+'"/>&nbsp;&nbsp;'+$(ele).attr('data-name')
												//+'<input type="text" name="aaa"/>'
												//+'</label></li>');
												//$('#nodesec_left_form_ul').append('');
											});
										}
									</script>
									
									<div class="col-sm-2">
										
										<form class="form-horizontal" role="form" action="" method="post" id="nodesc_right_form">
										<ul class="list-group">
											<c:if test="${!empty flow}">
												<c:if test="${!empty flow.nodes}">
													<c:forEach items="${flow.nodes}" var="node">
														<li class="list-group-item text-center">
															<label>
														      <input type="checkbox" value="${node.id}" data-name="${node.name}">&nbsp;&nbsp;${node.name}</input>
														    </label>
														</li>
													</c:forEach>
												</c:if>
											</c:if>
										</ul>
										</form>
									</div>
								</div>
					    	</div>
					    </div>
					    <div class="tab-pane fade" id="nodesec_datapriv">
					    	<h3>用户权限设置</h3>
					    </div>
					    <div class="tab-pane fade" id="nodesec_rolepriv">
					    	<h3>角色权限设置</h3>
					    </div>
					</div>
					<!-- ##################################################################### -->
					<!--nodesec end -->
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" id="nodedesc_close_btn" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="nodedesc_add_submit()">提交更改</button>
	            </div>
	            <script type="text/javascript">
	            	function nodedesc_add_submit(){
	            		var nodeids = new Array();
	            		$('#nodesec_left_form_ul').find('input').each(function(index,ele){
	            			nodeids.push($(ele).val());
	            		});
	            		if(nodeids.length == 0){
	            			alert('没有选择任何节点！');
	            			return false;
	            		}
	            		var subdata = {
	            			ids : nodeids,
	            			nodeid : flow_node_curr_id,
	            			flowid : '${flow.id}'
	            		};
	            		var subdatastr = JSON.stringify(subdata);
	            		var suburl = rootpath + '/nodesec/add';
	            		var tag = false;
	            		$.ajax({
	            			url:suburl,
							type:'post',
							data:subdatastr,
							dataType:'json',
							contentType:'application/json; charset=UTF-8',
							success:function(){
								$('#nodesec_manager_modal').modal('hide');
								tag=true;
							}
	            		});
	            		$('#nodesec_manager_modal').on('hidden.bs.modal', function(){
							if(tag){
								flushright('node');
							}
						})
	            	}
	            </script>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	
	
	
	
	
	<script type="text/javascript">
	
		var flow_node_curr_id = '';
		
		function node_add_submit(){
			var datastr = $('#nodesubmitform').serialize();
			var node_add_url = rootpath+'/node/add';
			var node_add_tag = false;
			$.ajax({
				url:node_add_url,
				data:datastr,
				type:'post',
				success:function(){
					$('#node_add_modal').modal('hide');
					node_add_tag = true;
				}
			});
			$('#node_add_modal').on('hidden.bs.modal', function(){
				if(node_add_tag){
					flushright('node');
				}
			})
		}
		
		
		function nodedetail(){
			flow_node_curr_id = arguments[0];
			
			
			var subdata = {
     			nodeid : flow_node_curr_id,
     			flowid : '${flow.id}'
     		};
      		var subdatastr = JSON.stringify(subdata);
      		var suburl = rootpath + '/nodesec/getnodeid';
      		$.ajax({
      			url:suburl,
				type:'post',
				data:subdatastr,
				dataType:'json',
				async:false,
				contentType:'application/json; charset=UTF-8',
				success:function(nss){
					$('#nodesec_left_form_ul').find('li').remove();
						for(var i=0;i<nss.length;i++){
							$('#nodesec_left_form_ul').append('<li class="list-group-item text-center"><label><input type="checkbox" value="'+nss[i].id+'"/>   '
														+nss[i].nextNodeName+'</label></li>');
						}
					}
				});
			
			//$('#nodesec_tab_compo').find('li:eq(0)').find('a').attr('href',rootpath+'/workflow/definition/nodesecadd?nodeid='
			//	+flow_node_curr_id+'&flowid=${flow.id}');
			/* $('#nodesec_tab_compo').find('li:eq(1)').find('a').attr('href',rootpath+'/node/manage/datapriv/?nodeid='+flow_node_curr_id);
			$('#nodesec_tab_compo').find('li:eq(2)').find('a').attr('href',rootpath+'/node/manage/rolepriv/?nodeid='+flow_node_curr_id);
			$('#nodesec_tab_compo a:').attr('href',rootpath+'/workflow/definition/nodesecadd?nodeid='+flow_node_curr_id+'&flowid=${flow.id}'); */
			$('#nodesec_manager_modal').modal({backdrop:false});
		}
		
		
		
	</script>
</div>