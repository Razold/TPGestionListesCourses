package fr.eni.dal;

import fr.eni.bo.Article;
import fr.eni.bo.ListeCourse;
import fr.eni.servlets.BusinessException;

import java.util.List;

public interface ListeCourseDAO {
    void insertItem(Article article) throws BusinessException;
    void insertList(ListeCourse listeCourse) throws BusinessException;
    List<ListeCourse> selectAllLists() throws BusinessException;
    List<Article> selectAllItems(ListeCourse listeCourse) throws BusinessException;
    ListeCourse selectByIdListeCourse(int id) throws BusinessException;
    void deleteList(ListeCourse listeCourse) throws BusinessException;
    void deleteItem(Article article) throws BusinessException;
    void deleteAllItems(ListeCourse listeCourse) throws BusinessException;
    Article selectByIdItem(int idArticle) throws BusinessException;


    //Fonctions nécessaires:
    /*
    Avoir tous les articles d'une liste de course
    Avoir toutes les listes de course
    Supprimer un article d'une liste de course
    Supprimer une liste de Course
    Ajouter des articles dans son panier à partir d'une liste prédéfinie
    //A chaque fois que l'on coche un article celui-ci est ajouté à la liste des articles du panier
    Réinitialiser le panier
     //Obtenir un panier vide en supprimant tout les articles
     //Le panier est une liste de course non prédéfinies
     */
}
