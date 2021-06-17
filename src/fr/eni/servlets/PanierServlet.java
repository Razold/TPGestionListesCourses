package fr.eni.servlets;

import fr.eni.bll.ListeCourseManager;
import fr.eni.bo.Article;
import fr.eni.bo.ListeCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {

    private ListeCourseManager listeCourseManager;
    private boolean premierPassage;

    @Override
    public void init() throws ServletException {
        this.listeCourseManager= new ListeCourseManager();
        this.premierPassage=true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*if(premierPassage == false){

        }else{
            premierPassage=false;
        }*/
        int idListeCourante = 0;
        try {
            idListeCourante = Integer.parseInt(req.getParameter("chaqueListe.id"));
        }catch (NumberFormatException nbe){
            nbe.printStackTrace();
        }
    ListeCourse listeCourseCourante = new ListeCourse();
        try {
            listeCourseCourante = listeCourseManager.afficherLaListeDeCourse(idListeCourante);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try{
            List<Article> listeArticlesAAfficher=new ArrayList<Article>();
            //afficher les articles de la liste listeCourse ou à partir de nomListeCourse
            listeArticlesAAfficher=listeCourseManager.afficherLesArticlesDuneListe(listeCourseCourante);
            req.setAttribute("listeCourseCourante",listeCourseCourante);
            req.setAttribute("listeArticlesCourants",listeArticlesAAfficher);

        }catch (Exception e){
            e.printStackTrace();
        }

        //ListeCourse listeCourante = (ListeCourse)
       // List<Article> listeArticlesCourants = (List<Article>) request.getAttribute("listeArticlesCourants");
        req.getRequestDispatcher("WEB-INF/panier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //l'id du panier est 3;


        try {
            req.setAttribute("listeCourseCourante",listeCourseManager.afficherLaListeDeCourse(3));
            req.setAttribute("listeArticlesCourants",  listeCourseManager.afficherLesArticlesDuneListe(listeCourseManager.afficherLaListeDeCourse(3)));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("WEB-INF/panier.jsp").forward(req,resp);
    }


}
