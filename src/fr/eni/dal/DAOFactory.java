package fr.eni.dal;

public class DAOFactory {
    public static ListeCourseDAO getListeCourseDAO() {
        return (ListeCourseDAO) new ListeCourseDAOImpl();
    }

}
