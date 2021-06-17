package fr.eni.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    private static DataSource dataSource;

    static {
        try {
            // Je dois lire le fichier context.xml
            Context context = new InitialContext();
            ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/bdd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // A partir des informations récupérées, j'ouvre une connexion
        return ConnectionProvider.dataSource.getConnection();
    }
}
