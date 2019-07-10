<%-- 
    Document   : register
    Created on : 29.06.2019, 15:14:11
    Author     : JAVA
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core/" %>
<form action="${pageContext.servletContext.contextPath}/register" method="post">

    <core:error/>
    
    <p class="row gap-bottom">
        <input type="email" name="email" value="${param.email}" placeholder="E-mail" required>
    </p>
    <p class="row gap-bottom">
        <input type="password" name="password" placeholder="Password" required>
    </p>
    <p class="row gap-bottom">
        <input type="password" name="confirmation" placeholder="Password confirmation" required>
    </p>
    <p class="row">
        <button class="pull-right">
            Register
        </button>
    </p>
</form>