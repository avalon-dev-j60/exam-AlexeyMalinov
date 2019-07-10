<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<%@taglib prefix="comments" tagdir="/WEB-INF/tags/comments" %>
<core:layout title="Comment">
    <comments:create publication="${publication}"/>
</core:layout>