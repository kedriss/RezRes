<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty warning}">
			<div class="alert alert-warning">
				<strong>Warning:</strong> ${warning}
			</div>
</c:if>