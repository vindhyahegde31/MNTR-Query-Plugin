package com.appiancorp.amp.mnrt.utils;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;

public class DButils
{
  /*  ---------While connecting to amp use this------
  private static final String AMP_DEV_DATASOURCE = "jdbc/ampdev";
    private static final String AMP_DATASOURCE = "jdbc/amp";
    public static Connection getAmpConnection(Context context) throws SQLException, NamingException {
        try {
            DataSource dataSource = (DataSource)context.lookup(AMP_DEV_DATASOURCE);
            return dataSource.getConnection();
        } catch (NamingException | SQLException var2) {
            System.out.println("Error in getting mysql connection:");
            throw var2;
        }
    }*/

    public static Connection getTestingSiteConnection(Context context) throws SQLException, NamingException {
        try {
            DataSource dataSource = (DataSource)context.lookup("jdbc/appianprimarydb");
            System.out.println("connected");
            return dataSource.getConnection();
        } catch (NamingException | SQLException var2) {
            System.out.println("Error in getting mysql connection:");
            throw var2;
        }
    }


}
