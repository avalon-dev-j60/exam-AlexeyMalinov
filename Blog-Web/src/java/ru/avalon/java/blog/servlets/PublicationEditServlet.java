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
import ru.avalon.java.blog.exceptions.ValidationException;
import ru.avalon.java.blog.services.PublicationService;
import ru.avalon.java.blog.services.AuthService;
import ru.avalon.java.blog.entities.User;
import static ru.avalon.java.blog.helpers.RedirectHelper.*;
import static ru.avalon.java.blog.helpers.Validation.*;

@WebServlet("/publication/edit")
public class PublicationEditServlet extends HttpServlet {

    private static final String JSP = "/WEB-INF/pages/publications/edit.jsp";

    @EJB
    PublicationService ps;

    @Inject
    AuthService as;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Publication publication = getPublication(id);
            request.setAttribute("publication", publication);
            request.getRequestDispatcher(JSP).forward(request, response);
        } catch (NumberFormatException | ValidationException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String id = request.getParameter("id");
            require(title, "Title is required!");
            require(content, "Content is required!");
            User user = as.getUser();
            requireNonNull(user, "You should be authorized to edit publication");
            Publication publication = getPublication(id);
            publication.setTitle(title);
            publication.setContent(content);
            ps.update(publication);
            LocalRedirect(request, response, "/");
        } catch (ValidationException e) {
            request.setAttribute("exception", e);
            doGet(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Publication getPublication(String id) throws ValidationException, NumberFormatException {
        long lid = Long.parseLong(id);
        Publication publication = ps.find(lid);
        requireNonNull(publication, "");
        return publication;
    }

}
