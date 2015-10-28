<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<c:if test="${not empty message}">
			<div class="alert alert-${messageType }">
				${message}
			</div>
		</c:if>