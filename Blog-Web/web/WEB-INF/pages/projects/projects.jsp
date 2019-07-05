<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<core:layout title="Projects">
    <c:if test="${not empty sessionScope.user}">
        <div class="row">
            <core:link title="Create new publication" location="/projects/create"/>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${not empty projects}">
            <c:forEach items="${projects}" var="item">
                <div class="row">
                    ${item.title}
                </div>
                <div class="row">           
                    ${item.content}
                </div>
                <div class="row pull-left">
                    ${item.created}: ${item.author.email}
                    <div class="pull-right">
                        <c:if test="${sessionScope.user == item.author.email}">
                            <core:link title="Edit" location="/projects/edit?title=${item.title}&content=${item.content}&id=${item.id}"/>
                            <core:link title="Remove" location="/projects/edit?id=${item.id}&remove=true"/>
                        </c:if>
                    </div>
                </div>
            </c:forEach>

        </c:when>
        <c:otherwise>
            <p>
                There are no projects currently under development...
            </p>
        </c:otherwise>
    </c:choose>
</core:layout>