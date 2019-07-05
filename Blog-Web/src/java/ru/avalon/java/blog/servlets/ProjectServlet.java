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

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet{
    //private final String[] projects = {"Documentation","Some other project","Main project"};
    @EJB
    PublicationService ps;
    
    private static final String JSP = "/WEB-INF/pages/projects/projects.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Publication> projects = ps.getAllPublications();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher(JSP).forward(request, response);
    }
    
    
}
