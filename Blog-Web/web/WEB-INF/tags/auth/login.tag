<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="core" tagdir="/WEB-INF/tags/core/"%>
<form action="${pageContext.servletContext.contextPath}/sign-in" method="post">
    
    <core:error/>
    <p class="row gap-bottom">
        <input type="email"
               name="email"
               value="${param.email}"
               placeholder="Email address"
               required>
        
    </p>
    <p class="row gap-bottom">
        <input type="password"
               name="password"
               placeholder="Password"
               required>
    </p>
    
    <p class="row gap-bottom">
        <a href="${pageContext.servletContext.contextPath}/register">
            Registration
        </a>
            <button class="pull-right">
                Sign on
            </button>
    </p>
</form>