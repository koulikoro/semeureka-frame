<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<div class="jumbotron">
		<h2>
			<span class="text-warning"><span class="glyphicon glyphicon-warning-sign"></span> <c:choose>
					<c:when test="${code eq 400}">无效的请求，系统无法处理提交的数据，异常代码：${code}。</c:when>
					<c:when test="${code eq 401}">您没有足够的权限执行此操作，异常代码：${code}。</c:when>
					<c:when test="${code eq 404}">您请求的资源不存在，异常代码：${code}。</c:when>
					<c:when test="${code eq 500}">系统在处理您的请求时发生异常，异常代码：${code}。</c:when>
					<c:otherwise>未知异常 ${code}</c:otherwise>
				</c:choose></span>
		</h2>
		<p class="lead">${!empty message ? message : exception.message}</p>
		<p>
			<a class="btn btn-lg btn-warning" href="javascript:history.back()">返回</a>
		</p>
	</div>
</tt:frame>