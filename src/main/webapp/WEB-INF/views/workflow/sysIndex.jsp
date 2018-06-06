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
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand "><span class="text-lg text-primary">国博政通工作流引擎</span></a>
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
							<ul class="list-group">
								<li class="list-group-item text-center">
									<!-- 利用data-target指定URL -->
									<button class="menu-item-left btn btn-md btn-primary"
										data-target="test2.html">
										<span class="glyphicon  glyphicon-plus"></span>&nbsp;添 加 流 程
									</button>
								</li>
								<a href="${ctx}/flow" target="cont" onclick="setH()"
									class="list-group-item text-center text-lg">
									<h4 class="list-group-item-heading">
										<span class="glyphicon glyphicon-list">&nbsp;</span>公文签收
									</h4>
								</a>
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
			<div class="col-lg-10">
				<iframe name="cont" width="100%" height="100%" style="border: none"></iframe>
			</div>
		</div>
	</div>
	<script>
		function setIframeHeight(iframe) {
			if (iframe) {
				var iframeWin = iframe.contentWindow
						|| iframe.contentDocument.parentWindow;
				if (iframeWin.document.body) {
					iframe.height = 1000;
				}
			}
		}
		function setH(){
			setIframeHeight($('[name="cont"]')[0]);
		}
		$(function() {
			$(".panel-heading").click(function(e) {
				/*切换折叠指示图标*/
				$(this).find("span").toggleClass("glyphicon-chevron-down");
				$(this).find("span").toggleClass("glyphicon-chevron-up");
			});
		});
	</script>
</body>
</html>
