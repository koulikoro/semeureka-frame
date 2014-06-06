<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="alert-box">
	<c:forEach items="${lasts.content}" var="alert" varStatus="status">
		<div id="alert-content" class="alert alert-${alert.type} alert-dismissable"
			style="position: fixed; bottom: 0px; right: -35px; z-index: 2147483647">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span class="glyphicon glyphicon-warning-sign"></span></strong>
			<a href="${ctx}/alert/${alert.id}">${alert.message}</a> <span class="badge">${lasts.totalElements}</span>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">
	$('#alert-box').load('${ctx}/alert/last #alert-content');
	setInterval(function() {
		$('#alert-box').load('${ctx}/alert/last #alert-content');
	}, 6000);
</script>