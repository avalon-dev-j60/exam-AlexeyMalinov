<%@tag import="ru.avalon.java.blog.entities.*" %>
<%@tag import="java.util.*" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="core" tagdir="/WEB-INF/tags/core/" %>

<%@attribute name="comments" type="List<Comment>" required="true"%>
<c:choose>
    <c:when test="${not empty comments}">
        <div class="row gap-bottom">
            <h5 class="pull-left"><b>Comments:</b></h5>
        </div>
        <c:forEach items="${comments}" var="item">
            <div class="row gap-bottom">
                ${item.created} ${item.author.email}:
                <p class="row gap-bottom">
                    <i>${item.text}</i>
                </p>
                <c:if test="${sessionScope.user == item.author.email}">
                    <p class="row gap-bottom">
                        <core:link classes="button" location="/comment/delete?id=${item.id}" title="Delete"/>
                    </p>
                </c:if>
            </div>
            <hr/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <center>
            <b>No comments yet!</b>
        </center>
    </c:otherwise>
</c:choose>
