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
			<th>连接</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${menus}" var="menu">
			<tr>
				<td>${menu.id}</td>
				<td>${menu.name}</td>
				<td>${menu.url}</td>
				<td><a href="javascript:void(0)" onclick="updateMenu(this)" class="label label-info">修改</a>
					<c:if test="${menu.children.size() lt 1}">
						<a href="${ctx}/menu/delete/${menu.id}" class="label label-warning">删除</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<div id="new" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${ctx}/menu/new" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">添加菜单</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">编号</label>
							<div class="col-sm-10">
								<input name="id" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input name="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">连接</label>
							<div class="col-sm-10">
								<input name="url" class="form-control" />
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
		function updateMenu(el) {
			$td = $(el).parents('tr').children('td');
			document.update.id.value = $td[0].innerHTML;
			document.update.name.value = $td[1].innerHTML;
			document.update.url.value = $td[2].innerHTML;
			document.update.action = '${ctx}/menu/' + $td[0].innerHTML;
			$('#update').modal('show')
		}
	</script>
	<div id="update" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form name="update" action="${ctx}/menu" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">修改菜单</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">编号</label>
							<div class="col-sm-10">
								<input name="id" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input name="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">连接</label>
							<div class="col-sm-10">
								<input name="url" class="form-control" />
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