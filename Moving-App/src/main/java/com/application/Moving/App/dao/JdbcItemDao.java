package com.application.Moving.App.dao;

import com.application.Moving.App.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcItemDao implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcItemDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String sql = "select item_id, user_id, category_id, name, quantity, storage_location, description " +
                "from item;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public Item get(int itemId) {
        Item item = null;
        String sql = "select item_id, user_id, category_id, name, quantity, storage_location, description " +
                "from item " +
                "where item_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
        if (results.next()) {
            item = mapRowToItem(results);
            return item;
        }
        return null;
    }

    @Override
    public List<Item> getByCategory(int categoryId) {
        List<Item> items = new ArrayList<>();
        String sql = "select item_id, user_id, category_id, name, quantity, storage_location, description " +
                "from item " +
                "where category_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, categoryId);
        if (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getByUserId(int userId) {
        List<Item> items = new ArrayList<>();
        String sql = "select item_id, user_id, category_id, name, quantity, storage_location, description " +
                "from item " +
                "where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getByBoxId(int boxId) {
        return null;
    }

    public List<Item> searchByLocation(String locationName) {
        return null;
    }

    @Override
    public Item create(Item newItem) {
        return null;
    }

    @Override
    public Item update(Item modifiedItem) {
        return null;
    }

    @Override
    public int delete(int itemId) {
        return 0;
    }


    private Item mapRowToItem(SqlRowSet rowSet) {
        Item item = new Item();
        item.setItemId(rowSet.getInt("item_id"));
        item.setUserId(rowSet.getInt("user_id"));
        item.setCategoryId(rowSet.getInt("category_id"));
        item.setName(rowSet.getString("name"));
        item.setQuantity(rowSet.getInt("quantity"));
        item.setStorageLocation(rowSet.getString("storage_location"));
        item.setDescription(rowSet.getString("description"));
        return item;
    }

}