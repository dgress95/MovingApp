package com.application.Moving.App.dao;

import com.application.Moving.App.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCategoryDao implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCategoryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "select category_id, name from category;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            categories.add(mapRowToCategory(results));
        }
        return categories;
    }

    @Override
    public Category get(int categoryId) {
        Category category = null;
        String sql = "select category_id, name from category " +
                "where category_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, categoryId);
        if (result.next()) {
            category = mapRowToCategory(result);
            return category;
        } else {
            return null;
        }
    }

    @Override
    public Category create(Category newCategory) {
        String sql = "INSERT INTO category (name) " +
                "VALUES (?) " +
                "RETURNING category_id;";
        int categoryId = jdbcTemplate.queryForObject(sql, Integer.class, newCategory.getName());
        newCategory.setCategoryId(categoryId);
        return newCategory;
    }

    @Override
    public Category update(Category modifiedCategory) {
        String sql = "UPDATE category SET name=? " +
                "WHERE category_id = ?;";
        jdbcTemplate.update(sql, modifiedCategory.getName(), modifiedCategory.getCategoryId());
        return get(modifiedCategory.getCategoryId());
    }

    @Override
    public void delete(int categoryId) {
        String deleteCategory = "DELETE from category " +
                "WHERE category_id = ?;";
        jdbcTemplate.update(deleteCategory, categoryId);
    }

    private Category mapRowToCategory(SqlRowSet rowSet) {
        Category category = new Category();
        category.setCategoryId(rowSet.getInt("category_id"));
        category.setName(rowSet.getString("name"));
        return category;
    }
}
