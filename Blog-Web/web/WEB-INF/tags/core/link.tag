<%-- 
    Document   : link
    Created on : 29.06.2019, 14:36:00
    Author     : JAVA
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="location" required="true" %>
<%@attribute name="title" required="true" %>
<a href="${pageContext.servletContext.contextPath}${location}">
    ${title}
</a>