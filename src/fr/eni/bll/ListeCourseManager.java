package fr.eni.bll;

import fr.eni.bo.Article;
import fr.eni.bo.ListeCourse;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.ListeCourseDAO;
import fr.eni.servlets.BusinessException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ListeCourseManager {

   /* void insertItem(Article article);
    void insertList(ListeCourse listeCourse);
    List<ListeCourse> selectAllLists();
    List<Article> selectAllItems();//select by id_List
    void delete(ListeCourse listeCourse);
    void deleteItem(Article article);
    void deleteAllItems();*/
    public void ajouterArticle(Article article) throws BusinessException {
        ListeCourseDAO lcDAO =DAOFactory.getListeCourseDAO();
        lcDAO.insertItem(article);
    }

    public void ajouterListeCourse(ListeCourse listeCourse) throws BusinessException{
        //La liste est créée en base à partir de l'ajout du premier article donc à partir du premier clique sur le bouton +
        ListeCourseDAO lcDAO =DAOFactory.getListeCourseDAO();
        lcDAO.insertList(listeCourse);
    }

    public List<ListeCourse> afficherLesListesDeCourse() throws BusinessException{
        //afficher tous les nom se trouvant dans La table LISTES de la BDD GestionCourses
        List<ListeCourse> ListeDeListesDeCourses= new ArrayList<>();
        ListeCourseDAO lcDAO =DAOFactory.getListeCourseDAO();
       // return (lcDAO.selectAllLists());
        ListeDeListesDeCourses= lcDAO.selectAllLists();
        return ListeDeListesDeCourses;
    }

    public List<Article> afficherLesArticlesDuneListe(ListeCourse listeCourse) throws BusinessException{
        //Faire une jointure entre les tables LISTES et ARTICLES où LISTES.id = ARTICLES.id_liste
        List<Article> ListeDesArticlesDUneListe = new ArrayList<>();
        ListeCourseDAO lcDAO =DAOFactory.getListeCourseDAO();
        ListeDesArticlesDUneListe=lcDAO.selectAllItems(listeCourse);
        return ListeDesArticlesDUneListe;
    }
    public ListeCourse afficherLaListeDeCourse(int id) throws BusinessException{
        ListeCourse listeCourse = new ListeCourse();
        ListeCourseDAO lcDAO =DAOFactory.getListeCourseDAO();
        listeCourse=lcDAO.selectByIdListeCourse(id);
        return listeCourse;
    }
    public Article afficherLArticleCourant(int idArticle)  throws BusinessException{
        Article articleCourant = new Article();
        ListeCourseDAO lcDAO = DAOFactory.getListeCourseDAO();
        lcDAO.selectByIdItem(idArticle);
        return articleCourant;
    }
    //Afficher un article d'un liste de course à partir de son id

    public void supprimerListeCourse(ListeCourse listeCourse) throws BusinessException{
        ListeCourseDAO lcDAO = DAOFactory.getListeCourseDAO();
        lcDAO.deleteList(listeCourse);
    }

    public void supprimerArticleDuneListe(Article article) throws BusinessException{
        ListeCourseDAO lcDAO = DAOFactory.getListeCourseDAO();
        lcDAO.deleteItem(article);
    }

    public void supprimerTousLesArticlesDuneListe(ListeCourse listeCourse) throws BusinessException{
        ListeCourseDAO lcDAO = DAOFactory.getListeCourseDAO();
        lcDAO.deleteAllItems(listeCourse);
    }


}
