<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form:form method="post" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">页面风格</label>
			<div class="col-sm-10">
				<c:set var="themes" value="${\";.amelia;.lumen;.slate;.cerulean;.united\".split(';')}" />
				<select name="theme" class="form-control">
					<c:forEach items="${themes}" var="th">
						<option value="${th}" ${th eq cookie.theme.value ? 'selected' : '' }>${th.toUpperCase()}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">确定</button>
			</div>
		</div>
	</form:form>
</tt:frame>