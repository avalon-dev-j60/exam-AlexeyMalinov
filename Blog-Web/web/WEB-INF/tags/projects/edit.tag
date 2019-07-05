<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core/" %>
<%@attribute name="location" %>
<form action="${pageContext.servletContext.contextPath}${location}" method="post">
    <core:error/>
    <p class="row gap-bottom">
        <input type="text" name="title" value="${param.title}" placeholder="Title" required>
    </p>
    <p class="row gap-bottom">
        <input type="text" name="content" value="${param.content}" placeholder="Content" required>
    </p>
    <div>
        Autor: ${sessionScope.user}
    </div>
    <c:if test="${not empty sessionScope.user}">
        <p class="row">
            <button class="pull-right">
                Send
            </button>
        </p>
    </c:if>
</form>
<form action="${pageContext.servletContext.contextPath}/projects">
    <button class="pull-right">
        Cancel
    </button>
</form>