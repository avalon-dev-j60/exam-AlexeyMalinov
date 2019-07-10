<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="publication" type="ru.avalon.java.blog.entities.Publication"%>
<%@attribute name="action" required="true" %>
<core:error/>
<form action="${pageContext.servletContext.contextPath}${action}" method="post">
    <c:if test="${not empty publication.id}">
        <input type="hidden" name="id" value="${publication.id}">
    </c:if>
    <div class="row gap-bottom">
        <input type="text" name="title" placeholder="Title" <c:if test="${not empty publication}"> value="${publication.title}"
               </c:if>
               required>
    </div>
    <div class="row gap-bottom">
        <textarea name="content" placeholder="Content" required><c:if test="${not empty publication}">${publication.content}</c:if></textarea>
    </div>
    <div class="row gap-bottom">
        <button class="pull-right">
            Save
        </button>
    </div>
</form>
