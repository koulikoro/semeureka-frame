<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form id="query" action="${ctx}/alert" class="form-inline pull-right">
		<button type="submit" class="btn btn-default btn-sm">查询</button>
	</form>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th class="col-xs-1">序号</th>
			<th>日志</th>
			<th>关注时间</th>
		</tr>
		<c:forEach items="${alerts.content}" var="alert" varStatus="status">
			<tr>
				<td>${alerts.number * alerts.size + status.count}</td>
				<td>${alert.message}</td>
				<td><c:if test="${empty alert.updateTime}">
						<label class="label label-info">未关注</label>
					</c:if> <c:if test="${!empty alert.updateTime}">
						<fmt:formatDate value="${alert.updateTime}" pattern="yyyy-MM-dd HH-mm-ss" />
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<tt:page page="${alerts}" form="#query" />
</tt:frame>