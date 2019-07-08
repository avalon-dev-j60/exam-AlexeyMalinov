<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<%@taglib prefix="publications" tagdir="/WEB-INF/tags/publications" %>

<core:layout title="${publication.title}">
    <publication:view publications="${publications}"/>
</core:layout>
