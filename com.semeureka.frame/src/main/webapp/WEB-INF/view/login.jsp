<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<div id="login" class="modal fade" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${ctx}/user/login" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" disabled="disabled">&times;</button>
						<h4 class="modal-title">用户登录</h4>
					</div>
					<div class="modal-body">
						<c:if test="${!empty shiroLoginFailure}">
							<p class="text-warning col-sm-offset-2 col-sm-10">
								<fmt:message key="${shiroLoginFailure}" />
							</p>
						</c:if>
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" value="${username}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label><input type="checkbox" name="rememberMe"> 记住密码 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#login').modal('show');
	</script>
</tt:frame>