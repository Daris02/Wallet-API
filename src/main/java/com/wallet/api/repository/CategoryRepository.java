package com.wallet.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wallet.api.model.Category;

@Repository
public class CategoryRepository extends AutoCrud<Category, Integer> {

    @Override
    protected String getTableName() {
        return "category";
    }
    
    @Override
    protected Category mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Category(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> saveAll(List<Category> toSave) {
        List<Category> saveAll = new ArrayList<>();
        for (Category category : toSave) {
            save(category);
            saveAll.add(category);
        }
        return saveAll;
    }
}
