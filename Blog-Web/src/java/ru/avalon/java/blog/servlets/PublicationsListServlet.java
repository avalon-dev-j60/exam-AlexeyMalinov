package ru.avalon.java.blog.servlets;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.avalon.java.blog.entities.Publication;
import ru.avalon.java.blog.services.PublicationService;


@WebServlet("/publications")
public class PublicationsListServlet extends HttpServlet{
    
    private static final String JSP = "/WEB-INF/pages/publications/list.jsp";
    
    @EJB
    PublicationService publicationService; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Publication> publications = publicationService.list();
        request.setAttribute("publications", publications);
        request.getRequestDispatcher(JSP).forward(request, response);
    }
    
    
}
