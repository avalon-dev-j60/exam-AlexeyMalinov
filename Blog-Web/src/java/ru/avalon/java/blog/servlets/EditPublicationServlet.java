package ru.avalon.java.blog.servlets;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.avalon.java.blog.exceptions.ValidationException;
import ru.avalon.java.blog.helpers.RedirectHelper;
import ru.avalon.java.blog.services.AuthService;
import ru.avalon.java.blog.services.ProjectService;

@WebServlet("/projects/edit")
public class EditPublicationServlet extends HttpServlet {

    @Inject
    ProjectService projectService;

    @Inject
    AuthService authService;

    private static final String JSP = "/WEB-INF/pages/projects/edit.jsp";
    private static final String PROJECTS_JSP = "/WEB-INF/pages/projects/projects.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authService.isSignedIn()) {
            request.getRequestDispatcher(PROJECTS_JSP).forward(request, response);
        } else if (!request.getParameter("remove").equals("true")) {
            request.getRequestDispatcher(JSP).forward(request, response);
        } else if (request.getParameter("remove").equals("true")) {
            String id = request.getParameter("id");
            try {
                projectService.remove(id);
            } catch (ValidationException ignor) {}
            request.getRequestDispatcher(PROJECTS_JSP).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authService.isSignedIn()) {
            request.getRequestDispatcher(PROJECTS_JSP).forward(request, response);
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String id = request.getParameter("id");
        try {
            projectService.update(title, content, id);
            RedirectHelper.localRedirect(request, response, "/projects");
        } catch (ValidationException e) {
            request.setAttribute("exception", e);
            doGet(request, response);
        }
    }
}
