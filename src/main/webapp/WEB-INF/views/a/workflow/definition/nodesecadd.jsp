<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jaken.lemoncatflow.definition.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
	<script type="text/javascript" src="/lemoncatflow/resources/common.js"></script>
	<script type="text/javascript" src="/lemoncatflow/resources/jquery-1.8.3.js"></script>
	
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
	</style>
	<script type="text/javascript">
		var selectedsec = new Array();
		$(function(){
			$('[name="allselect"] li:not(.secondli)').click(function(){
				$(this).siblings().css('border','none').removeClass('selected');
				$(this).css('border','solid 1px blue').addClass('selected');
				return false;
			});
			$('#secaddbtn').click(function(){
				if($('#nodesall').find('li.selected').length==0){
					alert('没有选择节点');
					return false;
				}
				var jnodeselected = $('#nodesall').find('li.selected');
				if($('#secall').contains(jnodeselected.text())){
					alert('不可重复添加');
					return false;
				}
				selectedsec.push(jnodeselected.attr('data-id'));
				return false;
			});
			$('#nodesecaddbtn').click(function(){
				if(selectedsec.length == 0){
					alert('没有选择任何步骤');
					return false;
				}
				for(var i = 0;i<selectedsec.length;i++){
					$('#submitform').append('<input type="hidden" name="nodesec" value="'+selectedsec[i]+'"');
				}
				$('#submitform').submit();
			});
		})
	</script>

  </head>
  
  <body style="margin-left:50px;margin-top:50px;">
  
	<%
  		if("1".equals((String)request.getAttribute("closetag"))){
  			%>
  				<script type="text/javascript">
  					window.opener.flushPage();
  					window.close();
  				</script>
  			<%
  		}
  	 %>
	<form name="submitform" id="submitform" action="/lemoncatflow/nodesec/add">
	<input type="hidden" id="flowid" name="flowid" value="<%=request.getAttribute("flowid")%>"/>
	<input type="hidden" id="nodeid" name="nodeid" value="<%=request.getAttribute("nodeid")%>"/>
	
	</form>
	
	<input type="button" value="添加步骤" id="nodesecaddbtn"/>
	
	
	
  	<div id="flowdiv" style="height:500px;width:1200px;text-align:center;margin-top:40px;">
  		<div id="flowleftdiv" style="float:left;height:100%;width:30%;border: solid 1px ; border-color: red">
  			<h4>当前流程</h4><hr/>
  			<ul name="allselect" id="secall">
  				<li>
  					来文登记
  				</li>
  				<li class="secondli">处室办理X</li>
  				<li class="secondli">结束X</li>
  				<li>处室审核</li>
  				<li>处室办理</li>
  			</ul>
  			
  		</div>
  		<div id="middlediv" style="float:left;height:100%;width:10%;">
  			<input type="button" value="<<" style="position:relative;top:40%;font-size:20px;" id="secaddbtn"/><br/>
  			<input type="button" value=">>" style="position:relative;top:45%;font-size:20px;"/>
  		</div>
  		<div id="flowrightdiv" style="float:left;height:100%;width:55%;border: solid 1px ; border-color: green;">
  			<h4>所有节点 </h4> <hr/>
  			<ul id="nodesall" name="allselect">
  				<%
  					List nodes = (List)request.getAttribute("nodelst");
  					for(int i=0;i<nodes.size();i++){
  						Node node = (Node)nodes.get(i);
  						%>
  							<li data-id="<%=node.getId()%>"><%=node.getName()%></li>
  						<%
  					}
  				 %>
  			</ul>
  		</div>
  		<div style="clear:both"></div>
  	</div>
  	<form id="submitform" action="/lemoncatflow/nodesec"
  </body>
</html>
