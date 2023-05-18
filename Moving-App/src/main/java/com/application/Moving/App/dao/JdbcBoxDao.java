package com.application.Moving.App.dao;

import com.application.Moving.App.model.Box;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBoxDao implements BoxDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBoxDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Box> getAll() {
        List<Box> boxes = new ArrayList<>();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Box box = mapRowToBox(results);
            boxes.add(box);
        }
        return boxes;
    }

    @Override
    public Box get(int boxId) {
        Box box = new Box();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where b.box_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, boxId);
        if (results.next()) {
            box = mapRowToBox(results);
            return box;
        }
        return null;
    }

    @Override
    public List<Box> getByCategory(int categoryId) {
        List<Box> boxes = new ArrayList<>();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where bc.category_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, categoryId);
        while (results.next()) {
            Box box = mapRowToBox(results);
            boxes.add(box);
        }
        return boxes;
    }

    @Override
    public List<Box> getByUserId(int userId) {
        List<Box> boxes = new ArrayList<>();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where b.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Box box = mapRowToBox(results);
            boxes.add(box);
        }
        return boxes;
    }

    @Override
    public List<Box> searchByLocation(String locationSearch) {
        List<Box> boxes = new ArrayList<>();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id " +
                "where b.storage_location ILIKE ?;";
        String filterString = '%' + locationSearch + '%';
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, filterString);
        while (results.next()) {
            Box box = mapRowToBox(results);
            boxes.add(box);
        }
        return boxes;
    }

    @Override
    public Box getByItemId(int itemId) {
        Box box = new Box();
        String sql = "select b.box_id, b.user_id, b.storage_location, bc.category_id " +
                "from box b " +
                "join box_category bc on b.box_id = bc.box_id " +
                "join item_box ib on b.box_id = ib.box_id " +
                "where ib.item_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
        if (results.next()) {
            box = mapRowToBox(results);
        }
        return box;
    }

    @Override
    public Box create(Box newBox) {
        String sql = "insert into box (user_id, storage_location) " +
                "values (?, ?) " +
                "returning box_id;";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, newBox.getUserId(),
                newBox.getStorageLocation());
        newBox.setBoxId(id);
        return newBox;
    }

    public int addBoxToCategory(int boxId, int categoryId) {
        String sql = "insert into box_category (box_id, category_id) values (?, ?);";
        int count = jdbcTemplate.update(sql, boxId, categoryId);
        return count;
    }

    public int removeBoxFromCategory(int boxId, int categoryId) {
        String sql = "delete from box_category where box_id = ? AND category_id = ?;";
        int count = jdbcTemplate.update(sql, boxId, categoryId);
        return count;
    }

    @Override
    public Box update(Box modifiedBox) {
        String sqlLocation = "update box set storage_location = ? " +
                "where box_id = ?;";
        jdbcTemplate.update(sqlLocation, modifiedBox.getStorageLocation(), modifiedBox.getBoxId());

        String sqlCategory = "UPDATE box_category " +
                "SET category_id = ? " +
                "WHERE box_id = ?;";
        jdbcTemplate.update(sqlCategory, modifiedBox.getCategoryId(), modifiedBox.getBoxId());
        return get(modifiedBox.getBoxId());
    }

    @Override
    public int delete(int boxId) {
        String sql = "delete from box where box_id = ?;";
        int count = jdbcTemplate.update(sql, boxId);
        return count;
    }

    private Box mapRowToBox(SqlRowSet rowSet) {
        Box box = new Box();
        box.setBoxId(rowSet.getInt("box_id"));
        box.setUserId(rowSet.getInt("user_id"));
        box.setCategoryId(rowSet.getInt("category_id"));
        box.setStorageLocation(rowSet.getString("storage_location"));
        return box;
    }
}
