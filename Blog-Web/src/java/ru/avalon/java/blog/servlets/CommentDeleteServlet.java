package ru.avalon.java.blog.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.avalon.java.blog.entities.Comment;
import ru.avalon.java.blog.exceptions.ValidationException;
import ru.avalon.java.blog.services.AuthService;
import ru.avalon.java.blog.services.CommentService;
import static ru.avalon.java.blog.helpers.RedirectHelper.*;
import static ru.avalon.java.blog.helpers.Validation.*;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

    @EJB
    CommentService cs;

    @Inject
    AuthService as;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (as.isSignedIn()) {
            String id = request.getParameter("id");
            try {
                long lid = Long.parseLong(id);
                Comment comment = cs.find(lid);
                requireNonNull(comment, "");
                cs.delete(comment);
            } catch (NumberFormatException | ValidationException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        redirectBack(request, response);
    }

}
