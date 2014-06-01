<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx}/resources/css/bootstrap${cookie.theme.value}.css" rel="stylesheet">
<link href="${ctx}/resources/css/custom.css" rel="stylesheet">
<script src="${ctx}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/jquery.validate.js"></script>
<script src="${ctx}/resources/js/custom.js"></script>
<!--[if lt IE 9]>
	<script src="${ctx}/resources/js/html5shiv.js"></script>
	<script src="${ctx}/resources/js/respond.min.js"></script>
<![endif]-->
<title>${navs[0].name}</title>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top fullscreen">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
				</button>
				<a class="navbar-brand navbar-brand-zh" href="${ctx}${navs[0].defaultUrl}">${navs[0].name}</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<c:forEach items="${navs[0].children}" var="child">
						<li class="${child eq navs[1] ? 'active' : '' }"><a href="${ctx}${child.defaultUrl}">${child.name}</a></li>
					</c:forEach>
				</ul>
				<ul class="nav navbar-nav navbar-right" style="margin-right: -15px;">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
							class="glyphicon glyphicon-user"></span>&nbsp;用户&nbsp;<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">修改个人密码</a></li>
						</ul></li>
					<li><a href="${ctx}/user/logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;注销</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="navbar" class="fullscreen" style="padding-bottom: 70px;"></div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 fullscreen">
				<div id="menus" class="panel-group">
					<c:forEach items="${navs[1].children}" var="child">
						<div class="panel panel-default">
							<div class="panel-heading" data-toggle="collapse" data-target="#menu-${child.id}">${child.name}</div>
							<ul id="menu-${child.id}" class="list-group panel-collapse collapse in">
								<c:forEach items="${child.children}" var="child">
									<li class="list-group-item"><a href="${ctx}${child.defaultUrl}">${child.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="body" class="col-sm-9 col-md-10">
				<a class="breadcrumb pull-right" href="javascript:fullscreen()"><span
					class="glyphicon glyphicon-retweet fullscreen" style="display: none;"></span><span
					class="glyphicon glyphicon-fullscreen fullscreen"></span></a>
				<ol class="breadcrumb" ondblclick="fullscreen()">
					<c:forEach items="${navs}" var="nav" varStatus="status">
						<c:if test="${!status.first && !empty nav}">
							<li><a href="${ctx}${nav.defaultUrl}">${nav.name}</a></li>
						</c:if>
					</c:forEach>
				</ol>
				<jsp:doBody />
			</div>
		</div>
		<h5 class="page-header text-right">
			<a href="#navbar" class="text-muted location"><span class="glyphicon glyphicon-open"></span></a>
		</h5>
	</div>
	<p class="text-center">
		<fmt:message key="copyright" />
	</p>
	<script type="text/javascript">
		function fullscreen() {
			$('.fullscreen').toggle();
			$('#body').toggleClass('col-sm-9 col-md-10').toggleClass('col-sm-12 col-md-12');
			document.cookie = 'fullscreen=' + $('#navbar').is(':hidden') + ";path=${ctx};";
		}
		<c:if test="${cookie.fullscreen.value}">fullscreen();</c:if>
		$('body').on('hidden.bs.modal', '.modal', function() {
			$(this).removeData('bs.modal');
		});
	</script>
</body>
</html>