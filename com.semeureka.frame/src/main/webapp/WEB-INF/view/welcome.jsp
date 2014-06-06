<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<a href="#" class="label label-default">1</a>
	<a href="#" class="label label-info">12</a>
	<a href="#" class="label label-warning"><span class="glyphicon glyphicon-search"></span>&nbsp;123</a>
	<button type="button" id="fat-btn" data-loading-text="正在加载..." class="btn btn-primary" onclick="$(this).button('loading')">
		Loading state</button>
</tt:frame>
