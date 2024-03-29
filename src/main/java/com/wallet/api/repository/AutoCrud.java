package com.wallet.api.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.api.config.ConnectionDB;

public abstract class AutoCrud<T, ID> implements Crud<T, ID>{

    protected abstract String getTableName();
    
    protected abstract T mapResultSetToEntity(ResultSet resultSet);
    
    @Override
    public T getById(ID id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UUID _id;
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";

        try {
            connection = ConnectionDB.createConnection();
            preparedStatement = connection.prepareStatement(query);
            
            if (id.getClass().equals(query.getClass())) {
                _id = UUID.fromString((String) id);
                preparedStatement.setObject(1, _id);
            } else {
                preparedStatement.setObject(1, id);
            }
            
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + getTableName();
        List<T> listAll = new ArrayList<>();

        try {
            connection = ConnectionDB.createConnection();
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listAll.add(mapResultSetToEntity(resultSet));
            }
            return listAll;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<T> saveAll(List<T> toSave) {
        return null;
    }

    @Override
    public T save(T toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();

            Class<?> toSaveClass = toSave.getClass();

            Field[] fields = toSaveClass.getDeclaredFields();
    
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + getTableName() + " (");
                for (Field field : fields) {
                    if (field.getName().equals("balance") || field.getType().equals(List.class)) {
                        continue;
                    } else if (field.getName() == "currency") {
                        queryBuilder.append("currencyid").append(", ");
                    } else {
                        queryBuilder.append(field.getName()).append(", ");
                    }
                }
                queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
                queryBuilder.append(") VALUES ( ");
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(toSave);
                    if (field.getName().equals("balance") || field.getType().equals(List.class)) {
                        continue;
                    } else if (field.getType().equals(Integer.class) || field.getType().equals(Double.class)) {
                        queryBuilder.append(value + ", ");
                    }else {
                        queryBuilder.append("'" + value + "', ");
                    }
                }
                queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
                queryBuilder.append(")");
    
                String insertQuery = queryBuilder.toString();

            statement.executeUpdate(insertQuery);
            return toSave;

        } catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public void removeById(ID id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UUID _id;
        String deleteQuery = "DELETE FROM " + getTableName() + " WHERE id = ?";

        try {
            connection = ConnectionDB.createConnection();
            preparedStatement = connection.prepareStatement(deleteQuery);
            
            if (id.getClass().equals(deleteQuery.getClass())) {
                _id = UUID.fromString((String) id);
                preparedStatement.setObject(1, _id);
            } else {
                preparedStatement.setObject(1, id);
            }
            
            preparedStatement.executeUpdate();
            System.out.println("Finish delete with success");
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
