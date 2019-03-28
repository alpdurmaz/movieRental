
package com.alpdurmaz.database.db;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.customer.CustomerRepository;
import com.alpdurmaz.database.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCustomerRepository implements CustomerRepository {
    private com.alpdurmaz.database.DbConnection dbConnection;

    public DbCustomerRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Customer getCustomerByName(String name) {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.createConnection();
            preparedStatement = dbConnection.connection.prepareStatement("SELECT C_ID, C_NAME FROM CUSTOMERS where c_name=?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            Customer customer = extractCustomer(resultSet);

            return customer;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbConnection.closeConnection();
            dbConnection.closePreparedStatement(preparedStatement);
            dbConnection.closeResultSet(resultSet);
        }

        return null;
    }

    private Customer extractCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        while (resultSet.next()) {
            customer.setCustomerID(resultSet.getInt("C_ID"));
            customer.setName(resultSet.getString("C_NAME"));
        }

        return customer;
    }
}
