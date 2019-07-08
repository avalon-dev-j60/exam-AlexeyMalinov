<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<%@taglib prefix="publications" tagdir="/WEB-INF/tags/publications" %>

<core:layout title="${publication.title}">
    <publications:view publication="${publication}"/>
    <c:if test="${publication.author.email == sessionScope.user}">
        <div class="row pull-left">
            <core:link classes="button" location="/publication/edit?id=${publication.id}" title="Edit"/>
            <core:link classes="button" location="/publication/delete?id=${publication.id}" title="Delete"/>
        </div>
    </c:if>
</core:layout>

