package com.viriminfotech.database;

import com.viriminfotech.utilities.Constants;

import java.sql.*;
import java.util.HashMap;

public class DatabaseHelper {

    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public DatabaseHelper() {
        try {
            Class.forName(Constants.JDBC_CLASS);
            connection = DriverManager.getConnection(Constants.V_LOGGER_DATABASE);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfTableExists(String tableName) {
        try {
            if (connection != null) {
                statement = connection.createStatement();
                ResultSet resultSet =  statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='COMPANY';");
                boolean result = resultSet.next();
                resultSet.close();
                statement.close();
                connection.close();
                return result;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    public int createTableAndInsertUsernameAndPassword(String username, String password) {
        try {
            if (connection != null) {
                statement = connection.createStatement();
                String sql = "CREATE TABLE USERDATA " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " username       TEXT    NOT NULL, " +
                        " password       TEXT     NOT NULL)";
                statement.executeUpdate(sql);

                String st = "INSERT INTO USERDATA (ID,username,password) " +
                        "VALUES (1, " +
                        "\'"+ username + "\'"+ "," +
                        "\'"+ password + "\'"+
                        ");";
                int result = statement.executeUpdate(st);
                statement.close();
                connection.close();
                return result;
            }
            return 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    public int updateUsernameAndPassword(String username, String password) {
        try {
            if (connection != null) {
                statement = connection.createStatement();
                String st = "UPDATE USERDATA SET" +
                        "\'username\'"+ "=" + username +
                        "\'password\'"+ "=" + password +
                        "WHERE id = 1"  +
                        ");";
                int result = statement.executeUpdate(st);
                statement.close();
                connection.close();
                return result;
            }
            return 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    public HashMap<String, String> getUsernameAndPassword() {
        HashMap<String, String> map = new HashMap<>();
        try {
            if (connection != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery( "SELECT * FROM USERDATA;" );
                while (resultSet.next()) {
                    String name = resultSet.getString("usernmae");
                    String  password = resultSet.getString("password");
                    map.put("username", name);
                    map.put("password", password);
                }
                statement.close();
                resultSet.close();
                return map;
            }
            return map;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void closeDBConnection() {
        try {
            if (connection != null) {
                this.connection.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("DROP TABLE USERDATA;");
            statement.close();
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
