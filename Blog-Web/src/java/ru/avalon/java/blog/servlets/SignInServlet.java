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

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private static final String JSP = "/WEB-INF/pages/auth/login.jsp";

    @Inject AuthService authService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP).forward(request, response);
        if (authService.isSignedIn()){
            RedirectHelper.redirectBack(request, response);
        } else {
            request. getRequestDispatcher(JSP).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        try{
            authService.signIn(email, password);
            RedirectHelper.localRedirect(request, response, "/");
        } catch (ValidationException e ){
            request.setAttribute("exception", e);
            doGet(request, response);
        }
    }
}
