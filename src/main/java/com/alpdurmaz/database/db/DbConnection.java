
package com.alpdurmaz.database;

import java.sql.*;

public class DbConnection{
    public Connection connection = null;

    public void createConnection(){
        try {
            connection = DriverManager.getConnection(createConnectionUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public String createConnectionUrl() {
        String host = "localhost";
        String dbName = "VIDEO_STORE";
        String user = "alpdurmaz";
        String password = "12345";
        return "jdbc:mysql://" + host + "/" + dbName + "?" + "user=" + user + "&password=" + password + "&useSSL=false&allowPublicKeyRetrieval=true";
    }
}
