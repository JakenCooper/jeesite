<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align: center;font-size:20px;">
  
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
  	<form method="post" action="/lemoncatflow/node/add">
  	<input type="hidden" name="flowid" value="<%=request.getAttribute("flowid") %>"/>
  	<table style="width:95%;height:100%;">
  		<tr>
  			<td>
  				节点名称
  			</td>
  			<td>
  				<input type="text" name="name"/>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				节点描述
  			</td>
  			<td>
  				<input type="text" name="desc"/>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				节点设置
  			</td>
  			<td>
  				<input type="checkbox" name="readyToBegin"/> 可以开始-开始节点标志<br/><br/>
  				<input type="checkbox" name="readyToFinish"/> 可以结束-结束节点标志<br/><br/>
  				<input type="checkbox" name="canFlowBack"/> 可以回退-退回上一个步骤<br/><br/>
  				<input type="checkbox" name="canGetBack"/> 可以收回-收回未处理的步骤<br/><br/>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				&nbsp;
  			</td>
  			<td>
  				<input type="submit" name="提  交" value ="提  交"/>
  			</td>
  		</tr>
  	</table>
  	</form>
  </body>
</html>
