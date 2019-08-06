package controller;

import dao.IUtilisateurDao;
import model.Role;
import model.Utilisateur;
import utilitaire.Upload;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2Mo pour un fichier
        maxFileSize = 1024 * 1024 * 10, //10Mo l'ensemble des fichiers plusieurs en mm tps
        maxRequestSize = 1024 * 1024 * 50) //tous les fichiers téléchargés
public class UserServlet extends HttpServlet {

    @EJB
    private IUtilisateurDao userEJB;

    private static final String chemin = "D://cours//M1//examteste//src//main//webapp//images/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        Part p = null;
        String filePath = "default.png";
        if (request.getPart("photo") != null) {
            p = request.getPart("photo");
            filePath = chemin + p.getSubmittedFileName();
            InputStream stream = p.getInputStream();
            Upload.saveFile(stream, filePath);
        }

        Utilisateur user = new Utilisateur();
        user.setNom(request.getParameter("nom"));
        user.setLogin(request.getParameter("username"));
        Role role = userEJB.getRoleById(Integer.parseInt(request.getParameter("role")));
        user.setIdrole(role);
        user.setPassword("passer");
        if (p != null && p.getSubmittedFileName() != null)
            user.setPhoto(p.getSubmittedFileName());
        else user.setPhoto(null);
        userEJB.create(user);
        request.setAttribute("message", "Utilisateur enrégistré");

    } catch(Exception e) {

        request.setAttribute("message", e.getMessage());
        e.printStackTrace();
    }
    List<Utilisateur> users = userEJB.all();
                request.setAttribute("users", users);
    getServletContext().getRequestDispatcher("/WEB-INF/createuser.jsp").
    forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userEJB.all());
        request.setAttribute("roles", userEJB.allRoles());
        getServletContext().getRequestDispatcher("/WEB-INF/createuser.jsp").
                forward(request, response);
    }
}
