package ru.avalon.java.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet{
    private final String[] projects = {"Documentation","Some other project","Main project"};
    
    private static final String JSP = "/WEB-INF/pages/projects.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projects", projects);
        request.getRequestDispatcher(JSP).forward(request, response);
    }
    
    
}
