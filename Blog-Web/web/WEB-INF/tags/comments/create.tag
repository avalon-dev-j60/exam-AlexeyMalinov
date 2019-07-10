<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="publication" type="ru.avalon.java.blog.entities.Publication" required="true"%>
<form action="${pageContext.servletContext.contextPath}/comment/create" method="post">
    <input type="hidden" name="id" value="${publication.id}">
    <div class="row gap-bottom">
        ${publication.title}
    </div>
    <div class="row gap-bottom">
        ${publication.content}
    </div>
    <div class="row gap-bottom">
        <textarea name="text" placeholder="text" required></textarea>
    </div>
    <div class="row gap-bottom">
        <button class="pull-right">
            Save
        </button>
    </div>
</form>