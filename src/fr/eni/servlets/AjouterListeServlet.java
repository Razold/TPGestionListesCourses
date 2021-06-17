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

@WebServlet("/ajout")
public class AjouterListeServlet extends HttpServlet {

    private ListeCourseManager listeCourseManager;
    private boolean premierPassage;

    @Override
    public void init() throws ServletException {
        this.listeCourseManager= new ListeCourseManager();
        this.premierPassage =true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/ajouterListe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(premierPassage == false){
            int idArticle = Integer.parseInt(req.getParameter("chaqueArticle.id"));
            Article articleCourant = new Article();
            try {
                articleCourant = listeCourseManager.afficherLArticleCourant(idArticle);
                listeCourseManager.supprimerArticleDuneListe(articleCourant);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }else{
            premierPassage=false;
        }

        ListeCourse listeCourse = new ListeCourse();

        String nomListeCourse=req.getParameter("nom");
        listeCourse.setNom(nomListeCourse);
        List<Article> listeArticlesAAjouter = new ArrayList<Article>();
        //faire un split() avec les virgules puis un trim() pour enlever les espaces
        try {
            listeCourseManager.ajouterListeCourse(listeCourse);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        List<Article> listeArticlesAAfficher=new ArrayList<Article>();
        //afficher les articles de la liste listeCourse ou à partir de nomListeCourse
        try {
            listeArticlesAAfficher=listeCourseManager.afficherLesArticlesDuneListe(listeCourse);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        req.setAttribute("listeArticlesDeLaListe",listeArticlesAAfficher);
        // List<Article> articles = req.getParameter("articles");
       // listeCourseManager.ajouterListeCourse(nom,articles);
        req.setAttribute("ajout", "Article ajouté");

        this.doGet(req, resp);
    }


}
