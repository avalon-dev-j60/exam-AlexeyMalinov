package ru.avalon.java.blog.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.avalon.java.blog.entities.Publication;
import ru.avalon.java.blog.entities.User;
import ru.avalon.java.blog.exceptions.ValidationException;
import ru.avalon.java.blog.services.PublicationService;
import static ru.avalon.java.blog.helpers.RedirectHelper.*;
import static ru.avalon.java.blog.helpers.Validation.*;
import ru.avalon.java.blog.services.AuthService;

@WebServlet("/publication/create")
public class PublicationCreateServlet extends HttpServlet {

    @EJB
    PublicationService publicationService;

    @Inject
    AuthService authService;

    public static final String JSP = "/WEB-INF/pages/publications/create.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            require(title, "Title is required!");
            require(content, "Content is required!");
            User user = authService.getUser();
            requireNonNull(user, "You should be authorized to create publication");
            Publication publication = new Publication(title, content, user);
            publicationService.create(publication);
        } catch (ValidationException e) {
            request.setAttribute("exception", e);
            doGet(request, response);
        }
    }

}
