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
import static ru.avalon.java.blog.helpers.RedirectHelper.*;
import static ru.avalon.java.blog.helpers.Validation.*;
import ru.avalon.java.blog.services.AuthService;
import ru.avalon.java.blog.services.PublicationService;

@WebServlet("/publication/delete")
public class PublicationDeleteServlet extends HttpServlet {
    
    
    
    @EJB
    PublicationService ps;

    @Inject
    AuthService as;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(as.isSignedIn()){
            String id = request.getParameter("id");
            try {
                long lid = Long.parseLong(id);
                Publication publication = ps.find(lid);
                requireNonNull(publication, "");
                ps.delete(publication);
                localRedirect(request, response, "/");
            } catch (NumberFormatException | ValidationException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            localRedirect(request, response, "/");
        }
    }

}
