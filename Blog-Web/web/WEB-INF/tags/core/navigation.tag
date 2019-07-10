<%-- 
    Document   : navigation
    Created on : 29.06.2019, 14:35:40
    Author     : JAVA
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core/" %>

<fmt:setBundle basename="resources.labels"/>
<nav>
    <ul>
        <li>
            <core:link location="/">
                <jsp:attribute name="title">
                    <fmt:message key="menu.home"/>
                </jsp:attribute>
            </core:link>
        </li>
        <li>

            <core:link location="/about">
                <jsp:attribute name="title">
                    <fmt:message key="menu.about"/>
                </jsp:attribute>
            </core:link>
        </li>
        <li>
            <core:link location="/projects" title="Projects"/>
        </li>
        <li class="pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <core:link location="/sign-out" title="Sign-out"/>
                </c:when>
                <c:otherwise>
                    <core:link location="/sign-in" title="Sign-in"/>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</nav>