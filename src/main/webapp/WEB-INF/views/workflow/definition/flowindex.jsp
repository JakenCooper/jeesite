<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

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
	
	
	<style type="text/css">
		li{
			list-style: none;
			margin-top:20px;
			letter-spacing: 10px;
			font-size:20px;
			font-weight: 400;
			width:100%;
			margin-left:-20px;
			padding-top:10px;
			padding-bottom:10px;
			cursor: pointer;
		}
		.secondli{
			margin-left:30px;
			margin-top:3px;
			padding:0px;
			width:70%;
			text-align:right;
			border:solid 1px yellow;
		}
		tbody{
			font-size:30px;
			margin-top:30px;
		}
		tbody tr{
			cursor:pointer;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('#flowaddbtn').click(function(){
				var argname = prompt('输入流程名:');
				$.ajax({
					url:'/lemoncatflow/flow/add',
					type:'post',
					dataType:'json',
					data:{name:argname},
					success:function(data){
						$('#flowid').val(data.id);
						$('#flowtitle').text(data.name);
					},
					error:function(){
						alert('error');
					}
				});
			});
			$('#nodeaddbtn').click(function(){
				var addwin = window.open('http://localhost:8080/lemoncatflow/node/view/add?flowid='+$('#flowid').val(),'nodeadd'
				,'width=800,height=400,left=500,top=200,location=no');
				addwin.onbeforeunload =function(){
					//alert(1);
					//flushPage();
				};
				return false;
			});
			flushPage();
		})
		function flushPage(){
			$.ajax({
				url:'/lemoncatflow/flushlatest',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.name!='test'){
						$('#flowid').val(data.id);
						if(data.name == null || data.name == 'null' || data.name==''){
							$('#flowtitle').text('当前流程');
						}else{
							$('#flowtitle').text(data.name);
						}
						var nodes = data.nodes;
						for(var i = 0;i<nodes.length;i++ ){
							$('#tbody').append('<tr onclick="nodedetail(\''+nodes[i].id+'\')"><td>'+nodes[i].name+'</td><td>'+nodes[i].desc+'</td></tr>');
						}
						$('tbody tr').mouseover(function(){
							$(this).css('backgroundColor','#888');
						}).mouseout(function(){
							$(this).css('backgroundColor','white');
						});
						rendertable();
					}
				},
				error:function(){
					alert('error');
				}
			});
		}
		function nodedetail(){
			var nodeid = arguments[0];
			var addwin = window.open('http://localhost:8080/lemoncatflow/nodesec/view/add?nodeid='+nodeid+'&flowid='+$('#flowid').val(),'nodeadd'
				,'width=1800,height=800,left=50,top=20,location=no');
		}
		function rendertable(){
			$('tbody tr').hover(function(){
				$(this).css('border','solid 1px blue');
			});
		}
	</script>

  </head>
  
  <body style="margin-left:50px;margin-top:50px;">
  
  	<h1>21321321</h1>
  	
  	<%
  		if(request.getAttribute("flushtag")!=null){
  			%>
  				<script type="text/javascript">
  					flushPage();
  				</script>
  			<%
  		}
  	 %>
  
  	<input type="button" value="添加流程" id="flowaddbtn"/>
  	<input type="button" value="添加节点" id="nodeaddbtn"/> 

	<input type="hidden" id="flowid" name="flowid"/>
  	<div id="flowdiv" style="height:500px;width:1200px;text-align:center;margin-top:40px;">
  		<div id="flowleftdiv" style="float:left;height:100%;width:80%">
  			<h4 id="flowtitle">当前流程</h4><hr/>
  			<table style="width:100%;text-align: center;margin-top:30px;">
  				<thead>
  					<tr>
  						<td>节点名称</td>
  						<td>节点描述</td>
  					</tr>
  				</thead>
  				<tbody id="tbody">
  					
  				</tbody>
  			</table>
  			
  		</div>
  		<div style="clear:both"></div>
  	</div>
  </body>
</html>
