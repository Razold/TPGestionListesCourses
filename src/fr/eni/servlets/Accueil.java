package fr.eni.servlets;

import fr.eni.bll.ListeCourseManager;
import fr.eni.bo.Article;
import fr.eni.bo.ListeCourse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/accueil")
public class Accueil extends HttpServlet {

    private ListeCourseManager listeCourseManager;
    private boolean premierPassage;

    @Override
    public void init() throws ServletException {
        this.listeCourseManager= new ListeCourseManager();
        this.premierPassage =true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idListeCourante=0;
        //Je vérifie si une liste n'a pas été demandée à la suppression avant d'afficher l'ensemble des listes
        if(premierPassage == false){
            try{
                idListeCourante = Integer.parseInt(request.getParameter("chaqueListe.id"));
            }catch (NumberFormatException nbe){
                nbe.printStackTrace();
            }

            ListeCourse listeCourseCourante = new ListeCourse();
            try {
                listeCourseCourante = listeCourseManager.afficherLaListeDeCourse(idListeCourante);
                listeCourseManager.supprimerListeCourse(listeCourseCourante);
            } catch (BusinessException e) {
                e.printStackTrace();
            }

        }else{
            premierPassage = false;
        }

        //List<ListeCourse> listeDeListesDeCourses = (List<ListeCourse>) request.getAttribute("listeDeListesDeCourses");
        List<ListeCourse> listeDeListeDeCourses = new ArrayList<>();

        try {
            for (ListeCourse lc: listeCourseManager.afficherLesListesDeCourse()) {
                listeDeListeDeCourses.add(lc);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        request.setAttribute("listeDeListesDeCourses",listeDeListeDeCourses);
        request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request,response);

    }


}
