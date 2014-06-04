<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="alert-box">
	<c:forEach items="${alerts}" var="alert" varStatus="status">
		<c:if test="${status.first}">
			<div id="alert-content" class="alert alert-danger alert-dismissable"
				style="position: fixed; bottom: 15px; right: 15px;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong><span class="glyphicon glyphicon-warning-sign"></span></strong> <a
					href="${ctx}/alert/${alert.id}">${alert.message}</a> <span class="badge">${alerts.size()}</span>
			</div>
		</c:if>
	</c:forEach>
</div>
<script type="text/javascript">
	$('#alert-box').load('${ctx}/alert #alert-content');
	setInterval(function() {
		$('#alert-box').load('${ctx}/alert #alert-content');
	}, 6000);
</script>