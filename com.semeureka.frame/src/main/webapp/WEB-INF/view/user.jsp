<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form class="form-inline pull-right form-group">
		<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#new">添加</button>
	</form>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>#</th>
			<th>名称</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td><c:forEach items="${user.roles}" var="role">
						<label class="label label-default">${role.name}</label>
					</c:forEach></td>
				<td><a href="javascript:void(0)" onclick="updateUser(this)" class="label label-info">修改</a>
					<c:if test="${user.username ne 'admin'}">
						<a href="${ctx}/user/delete/${user.id}" class="label label-warning">删除</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<div id="new" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${ctx}/user/new" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">添加用户</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="text" name="password" class="form-control" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function updateUser(el) {
			$td = $(el).parents('tr').children('td');
			document.update.id.value = $td[0].innerHTML;
			document.update.username.value = $td[1].innerHTML;
			document.update.password.value = $td[2].innerHTML;
			document.update.action = '${ctx}/user/' + $td[0].innerHTML;
			$('#update').modal('show')
		}
	</script>
	<div id="update" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form name="update" action="${ctx}/user" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">修改用户</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">编号</label>
							<div class="col-sm-10">
								<input type="text" name="id" class="form-control" readonly="readonly" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</tt:frame>