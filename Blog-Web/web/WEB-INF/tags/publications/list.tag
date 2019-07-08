<%@tag import="java.util.*" %>
<%@tag import="ru.avalon.java.blog.entities.*" %>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core/" %>

<%@attribute name="publications" type="List<Publication>" required="true" %>
<c:choose>
    <c:when test="${empty publications}">
        <center>
            <h2>No publications yet!</h2>
        </center>
    </c:when>
    <c:otherwise>
        <c:forEach items="${publications}" var="item">
            <p class="row gap-bottom">
                <h3>
                    <core:link location="/publication?id=${item.id}" title="${item.title}"/>
                </h3>
                    <c:if test="${not empty item.content}">
                        <p>
                            <c:choose>
                            <c:when test="${item.content.length() > 128}">
                                ${item.content.substring(0, 128)}...
                            </c:when>
                            <c:otherwise>
                                ${item.content}
                            </c:otherwise>
                            </c:choose>
                        </p>
                    </c:if>
                <p>
                    
                </p>
            </p>
        </c:forEach>
    </c:otherwise>
</c:choose>