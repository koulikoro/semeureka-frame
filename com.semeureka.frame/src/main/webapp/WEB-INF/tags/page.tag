<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"%>
<%@ attribute name="form" required="true" type="java.lang.String"%>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%>
<ul class="pagination pagination-sm pull-right">
	<c:set var="begin"
		value="${page.totalPages - 1 - page.number lt 2 ? page.totalPages - 1 - 4 : page.number - 2}" />
	<c:set var="end" value="${page.number lt 2 ? 4 : page.number + 2}" />
	<li ${page.firstPage ? 'class="disabled"' : ''}><a href="javascript:page(0)"><span
			class="glyphicon glyphicon-step-backward"></span></a></li>
	<c:forEach begin="${begin lt 0 ? 1 : begin + 1}"
		end="${end + 1 gt page.totalPages ? page.totalPages : end + 1}" var="number">
		<li ${number - 1 eq page.number ? 'class="active"' : ''}><a
			href="javascript:page(${number - 1})">${number}</a></li>
	</c:forEach>
	<li ${page.lastPage ? 'class="disabled"' : ''}><a
		href="javascript:page(${page.totalPages > 0 ? page.totalPages - 1 : 0})"><span
			class="glyphicon glyphicon-step-forward"></span></a></li>
</ul>
<script type="text/javascript">
	function page(number) {
		var $form = $('${form}');
		$('<input/>').attr('type', 'hidden').attr('name', 'page').val(number).appendTo($form);
		$('<input/>').attr('type', 'hidden').attr('name', 'size').val(${page.size}).appendTo($form);
		$form.submit();
	}
</script>