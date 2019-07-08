<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core" %>
<%@taglib prefix="publications" tagdir="/WEB-INF/tags/publications" %>
<core:layout title="Create publication">
    <h2>Publication edit</h2>
    <publications:edit publication="${publication}" action="/publication/edit"/>
</core:layout>