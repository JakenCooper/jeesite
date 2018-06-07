<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>国博政通工作流引擎</title>
<link rel="stylesheet" href="${ctxStatic}/bootstrap3/css/bootstrap.css">
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap3/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctxStatic}/workflow/common.js"></script>
</head>

<body>
	
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand ">
				<span class="text-lg text-primary" style="font-size:20px">国博政通工作流引擎 
				<small>v1.0.0 SNAPSHOT</small></span>
				</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="navbar-text">欢迎你，${fns:getUser().loginName}
					（${fns:getUser().name }）</li>
				<li><a href="${ctx}/logoff"><span
						class="glyphicon glyphicon-log-out"></span> 退出</a></li>
			</ul>
		</div>
	</nav>

	<div style="margin-left: 18px; width: 90%">
		<div class="row">
			<div class="col-lg-2">
				<div class="panel-group table-responsive" role="tablist">
					<div class="panel panel-primary leftMenu">
						<!-- 利用data-target指定要折叠的分组列表 -->
						<div class="panel-heading" id="collapseListGroupHeading1"
							data-toggle="collapse" data-target="#collapseListGroup1"
							role="tab">
							<h3 class="panel-title text-left">
								流程管理 <span class="glyphicon glyphicon-chevron-up right"></span>
							</h3>
						</div>
						<!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
						<div id="collapseListGroup1" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="collapseListGroupHeading1">
							<ul class="list-group" id="flow_list">
								<li class="list-group-item text-center">
									<!-- 利用data-target指定URL -->
									<button class="menu-item-left btn btn-md btn-primary" data-toggle="modal" data-target="#flow_add_modal">
										<span class="glyphicon  glyphicon-plus"></span>&nbsp;添 加 流 程
									</button>
								</li>
								<!--  
								<a data-href="${ctx}/flow" data-target="index_right_content" 
									data-hrefarg="" onclick="jump()"
									class="list-group-item text-center text-lg">
									<h4 class="list-group-item-heading">
										<span class="glyphicon glyphicon-list">&nbsp;</span>公文签收
									</h4>
								</a>-->
								
								<!-- 
								<c:if test="${!empty allflow}">
									<c:forEach var="flow" items="${allflow}">
										<a data-href="${ctx}/workflow/definition/flowIndex" data-target="index_right_content" 
											onclick="flowdetail('${flow.id}')"
											class="list-group-item text-center text-lg">
											<h4 class="list-group-item-heading">
												<span class="glyphicon glyphicon-list">&nbsp;</span>${flow.name}
											</h4>
										</a>
									</c:forEach>
								</c:if>
								 -->
								 
							</ul>
						</div>
					</div>
					<!--panel end-->
					<div class="panel panel-primary leftMenu">
						<div class="panel-heading" id="collapseListGroupHeading2"
							data-toggle="collapse" data-target="#collapseListGroup2"
							role="tab">
							<h4 class="panel-title">
								分组2 <span class="glyphicon glyphicon-chevron-down right"></span>
							</h4>
						</div>
						<div id="collapseListGroup2" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListGroupHeading2">
							<ul class="list-group">
								<li class="list-group-item">
									<button class="menu-item-left">
										<span class="glyphicon glyphicon-triangle-right"></span>分组项2-1
									</button>
								</li>
								<li class="list-group-item">
									<button class="menu-item-left">
										<span class="glyphicon glyphicon-triangle-right"></span>分组项2-2
									</button>
								</li>
								<li class="list-group-item">
									<button class="menu-item-left">
										<span class="glyphicon glyphicon-triangle-right"></span>分组项2-3
									</button>
								</li>
								<li class="list-group-item">
									<button class="menu-item-left">
										<span class="glyphicon glyphicon-triangle-right"></span>分组项2-4
									</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-10" id="index_right_content">
				<!-- <iframe name="cont" width="100%" height="100%" style="border: none"></iframe> -->
			</div>
			
			
			
			
			<div class="modal fade" id="flow_add_modal" tabindex="-1" role="dialog" aria-labelledby="flow_add_modal_label" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="flow_add_modal_label">添加流程</h4>
			            </div>
			            <div class="modal-body">
			            	
							<form class="form-horizontal" role="form" action="${ctx}/flow/add" method="post">
							  <div class="form-group">
							    <label for="firstname" class="col-sm-2 control-label">名称</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" name="name" placeholder="请输入流程名称">
							    </div>
							  </div>
							</form>
			            	
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" class="btn btn-primary" onclick="submitFlow()">提交更改</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			</div>
			
			
		</div>
	</div>
	<script>
		var node_right_content_href='';
		
		var rootpath = '${ctx}';
		$(function() {
			$(".panel-heading").click(function(e) {
				/*切换折叠指示图标*/
				$(this).find("span").toggleClass("glyphicon-chevron-down");
				$(this).find("span").toggleClass("glyphicon-chevron-up");
			});
			$.ajax({
				url:'${ctx}/flow/all',
				type:'get',
				dataType:'json',
				success:function(data){
					if(data.code && data.code == 0){
						alert(data.msg);
						return false;
					}
					for(var i=0;i<data.length;i++){
						var flow = data[i];
						$('#flow_list').append('<a data-href="${ctx}/workflow/definition/flowIndex" data-target="index_right_content" '+
									'data-hrefarg="'+flow.id+'" onclick="flowdetail(\''+flow.id+'\')" '+
									'class="list-group-item text-center text-lg"> ' +
									'<h4 class="list-group-item-heading"> '+
										'<span class="glyphicon glyphicon-list">&nbsp;</span>'+flow.name+
									'</h4></a>');
					}
				},
				error:function(data){
					alert(data);
				}
			});
		});
		
		function submitFlow(){
			$('form').submit();
		}
		
		function flowdetail(){
			var datahref = '${ctx}/workflow/definition/flowIndex?flowid='+arguments[0];
			node_right_content_href = datahref;
			$.get(datahref,function(result){
				$('#index_right_content').html(result);
			});
		}
		
		
		function flushright(){
			if(arguments[0] == 'node'){
				$.get(node_right_content_href,function(result){
					$('#index_right_content').html(result);
				});
			}
		}
		
	</script>
</body>
</html>
