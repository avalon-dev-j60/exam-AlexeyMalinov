package ru.avalon.java.blog.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import ru.avalon.java.blog.entities.*;
import ru.avalon.java.blog.services.*;
import ru.avalon.java.blog.exceptions.ValidationException;
import static ru.avalon.java.blog.helpers.RedirectHelper.*;
import static ru.avalon.java.blog.helpers.Validation.*;

@WebServlet("/comment/create")
public class CommentCreateServlet extends HttpServlet {

    private static final String JSP = "/WEB-INF/pages/comments/create.jsp";

    @EJB
    PublicationService ps;

    @EJB
    CommentService cs;

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
            String id = request.getParameter("id");
            String text = request.getParameter("text");
            require(text, "Text is required");
            Publication publication = getPublication(id);
            User user = as.getUser();
            requireNonNull(user, "You should be authorized to edit publication");
            Comment comment = new Comment(text, publication, user);
            cs.create(comment);
            localRedirect(request, response, "/publication?id="+id);
        } catch (ValidationException e) {
            request.setAttribute("exception", e);
            doGet(request, response);
        }

    }

    private Publication getPublication(String id) throws ValidationException, NumberFormatException {
        long lid = Long.parseLong(id);
        Publication publication = ps.find(lid);
        requireNonNull(publication, "");
        return publication;
    }

}
