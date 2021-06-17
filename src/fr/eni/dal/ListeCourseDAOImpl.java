package fr.eni.dal;

import fr.eni.bo.Article;
import fr.eni.bo.ListeCourse;
import fr.eni.servlets.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListeCourseDAOImpl implements ListeCourseDAO {

    @Override
    public void insertItem(Article article)  throws BusinessException{
        //Ajouter des articles dans son panier à partir d'une liste prédéfinie
        //A chaque fois que l'on coche un article celui-ci est ajouté à la liste des articles du panier
        //Pour ajouter un article dans une liste on modifie l'id_liste

        String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom,id_liste) VALUES(?,?)";
        try (
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement psmt = connection.prepareStatement(INSERT_ARTICLE,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            // J'insere l'article en base de données
            psmt.setString(2, article.getNom());
            psmt.setInt(3, article.getId_article());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                article.setId(rs.getInt(1));
            }

        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void insertList(ListeCourse listeCourse)  throws BusinessException{
        String INSERT_LISTE_COURSE = "INSERT INTO LISTES(nom) VALUES(?);";
        String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom,id_liste) VALUES(?,?)";
        //Verifier si la liste existe déjà dans la table si c'est le cas j'ajoute les articles uniquement s'ils ne sont pas déjà présents dans la dite base
        try (
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement psmt = connection.prepareStatement(INSERT_LISTE_COURSE,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement psmt2 = connection.prepareStatement(INSERT_ARTICLE,
                        PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            // J'insere la liste de course en base de données
            psmt.setString(1, listeCourse.getNom());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                listeCourse.setId(rs.getInt(1));
            }
            // J'insère les articles en base de données
            for (Article article : listeCourse.getArticles()) {
                psmt2.setString(1, article.getNom());
                psmt2.setInt(2, listeCourse.getId());
                psmt2.executeUpdate();
                ResultSet rs2 = psmt.getGeneratedKeys();
                if (rs.next()) {
                    article.setId(rs2.getInt(1));
                }
            }
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }


    @Override
    public List<ListeCourse> selectAllLists() throws BusinessException {
        List<ListeCourse> ListeDeListesDeCourses = new ArrayList<>();

        String SELECT_ALL = "SELECT l.id, l.nom,a.id,a.nom,a.id_liste " +
                "FROM LISTES l " +
                "INNER JOIN ARTICLES a WHERE l.id=a.id_liste ORDER BY l.nom ASC;";

        try (Connection connection = ConnectionProvider.getConnection();
        PreparedStatement psmt = connection.prepareStatement(SELECT_ALL))
        {
                ResultSet rs = psmt.executeQuery();
                ListeCourse listeCourseEncours = new ListeCourse();

                while (rs.next()) {
                    if (rs.getInt(1) != listeCourseEncours.getId()) {
                        listeCourseEncours  = new ListeCourse();
                        listeCourseEncours.setId(rs.getInt(1));
                        listeCourseEncours.setNom(rs.getString(2));

                        ListeDeListesDeCourses.add(listeCourseEncours);
                    }
                    Article article = new Article(
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getInt(5));
                    listeCourseEncours.getArticles().add(article);
                }
                rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
        System.out.println(ListeDeListesDeCourses);
        return ListeDeListesDeCourses;
    }

    @Override
    public List<Article> selectAllItems(ListeCourse listeCourse)  throws BusinessException{
        List<Article> listeArticles = new ArrayList<>();
        String SELECT_ALL_ITEMS = "SELECT * FROM ARTICLES " +
                "WHERE id_liste =?" +
                " ORDER BY nom ASC;";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement psmt = connection.prepareStatement(SELECT_ALL_ITEMS)) {
            psmt.setInt(1,listeCourse.getId());
            ResultSet rs = psmt.executeQuery();

            Article articleEnCours = new Article();
            while (rs.next()) {
                if (rs.getInt(1) != articleEnCours.getId()) {
                    articleEnCours.setId(rs.getInt(1));
                    articleEnCours.setNom(rs.getString(2));
                    articleEnCours.setId_article(rs.getInt(3));
                    listeArticles.add(articleEnCours);
                }
            }
            rs.close();
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
        return listeArticles;
    }

    @Override
    public ListeCourse selectByIdListeCourse(int id)  throws BusinessException{
        ListeCourse listeCourseCourante = new ListeCourse();
        String SELECT_BY_ID_LISTE_DE_COURSE = "SELECT * FROM LISTES WHERE id=?";

        try(     Connection connection = ConnectionProvider.getConnection();
                 PreparedStatement psmt = connection.prepareStatement(SELECT_BY_ID_LISTE_DE_COURSE))
        {

            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) != id) {
                    listeCourseCourante.setId(rs.getInt(1));
                    listeCourseCourante.setNom(rs.getString(2));
                }
            }
            rs.close();
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
        return listeCourseCourante;
    }
    @Override
    public Article selectByIdItem(int idArticle)  throws BusinessException{
        Article articleCourant = new Article();
        String SELECT_BY_ID_ARTICLES= "SELECT * FROM ARTICLES WHERE id=?";

        try(     Connection connection = ConnectionProvider.getConnection();
                 PreparedStatement psmt = connection.prepareStatement(SELECT_BY_ID_ARTICLES))
        {

            psmt.setInt(1,idArticle);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) != idArticle) {
                    articleCourant.setId(rs.getInt(1));
                    articleCourant.setNom(rs.getString(2));
                    articleCourant.setId_article(rs.getInt(3));
                }
            }
            rs.close();
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
        return articleCourant;
    }
    @Override
    public void deleteList(ListeCourse listeCourse)  throws BusinessException{
        String SQL_DELETE_LIST = "DELETE FROM LISTES where id=?";

        try (
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_LIST)) {

            // l'intégrité référentielle s'occupe d'invalider la suppression
            // si l'article est référencé dans une ligne de commande
            psmt.setInt(1, listeCourse.getId());
            int nbRows = psmt.executeUpdate();
            if (nbRows != 1) {
                System.err.println("Delete ListeCourse failed - Liste de course inconnue ");

            }
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void deleteItem(Article article)  throws BusinessException{
        String SQL_DELETE_ITEM = "DELETE FROM Article where id=?";

        try (
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_ITEM)) {

            // l'intégrité référentielle s'occupe d'invalider la suppression
            // si l'article est référencé dans une ligne de commande
            psmt.setInt(1, article.getId());
            int nbRows = psmt.executeUpdate();
            if (nbRows != 1) {
                System.err.println("Delete item failed - Article inconnu");

            }
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }

    @Override
    public void deleteAllItems(ListeCourse listeCourse)  throws BusinessException{
        String SQL_DELETE_ALL_ITEMS = "DELETE FROM Article where id_article=?";
        try (
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_ALL_ITEMS)) {

            // l'intégrité référentielle s'occupe d'invalider la suppression
            // si l'article est référencé dans une ligne de commande
            psmt.setInt(1, listeCourse.getId());
            int nbRows = psmt.executeUpdate();
            if (nbRows != 1) {
                System.err.println("Delete item failed - Article inconnu");

            }
        } catch (Exception e) {
              e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
            throw businessException;
        }
    }


}


