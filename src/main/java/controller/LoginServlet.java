package controller;

import dao.IUtilisateurDao;
import model.Utilisateur;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private IUtilisateurDao iUtilisateurDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Utilisateur utilisateur = iUtilisateurDao.getUserByUsernameAndPassword(login, password);
        if (utilisateur != null) {
            List<Utilisateur> users = iUtilisateurDao.all();
            request.setAttribute("users", users);
            request.setAttribute("roles", iUtilisateurDao.allRoles());
            request.getRequestDispatcher("/WEB-INF/createuser.jsp").forward(request,response);
        } else {
            request.setAttribute("message", "login ou mot de passe incorrect");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
