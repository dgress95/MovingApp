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
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id;";
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
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where i.item_id = ?;";
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
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where bc.category_id = ?;";
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
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where i.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getByBoxId(int boxId) {
        List<Item> items = new ArrayList<>();
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where ib.box_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, boxId);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> searchByLocation(String locationName) {
        List<Item> items = new ArrayList<>();
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where b.storage_location ILIKE ?" +
                "order by i.item_id;";
        String filterString = '%' + locationName + '%';
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, filterString);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> searchByName(String itemName) {
        List<Item> items = new ArrayList<>();
        String sql = "select i.item_id, i.user_id, bc.category_id, i.name, i.quantity, b.storage_location, i.description " +
                "from item i " +
                "join item_box ib on i.item_id = ib.item_id " +
                "join box b on ib.box_id = b.box_id " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where i.name ILIKE ?" +
                "order by i.item_id;";
        String filterString = '%' + itemName + '%';
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, filterString);
        while (results.next()) {
            Item item = mapRowToItem(results);
            items.add(item);
        }
        return items;
    }

    @Override
    public Item create(Item newItem) {
        String sql = "insert into item (user_id, name, quantity, description) " +
                "values (?, ?, ?, ?) " +
                "returning item_id;";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, newItem.getUserId(), newItem.getName(),
                newItem.getQuantity(), newItem.getDescription());
        newItem.setItemId(id);
        return newItem;
    }

    @Override
    public Item update(Item modifiedItem) {
        String sql = "update item set user_id = ?, name = ?, quantity = ?, description = ? " +
                "where item_id = ?;";
        jdbcTemplate.update(sql, modifiedItem.getUserId(), modifiedItem.getName(), modifiedItem.getQuantity(),
                modifiedItem.getDescription(), modifiedItem.getItemId());
        return get(modifiedItem.getItemId());
    }

    @Override
    public void delete(int itemId) {
        String sqlOne = "delete from item_box where item_id = ?;";
        jdbcTemplate.update(sqlOne, itemId);

        String sqlTwo = "delete from item where item_id = ?;";
        jdbcTemplate.update(sqlTwo, itemId);
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