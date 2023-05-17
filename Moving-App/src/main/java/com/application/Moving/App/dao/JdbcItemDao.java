package com.application.Moving.App.dao;

import com.application.Moving.App.model.Item;

import java.util.List;

public class JdbcItemDao implements ItemDao {
    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item get(int itemId) {
        return null;
    }

    @Override
    public List<Item> getByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<Item> getByUserId(int userId) {
        return null;
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
}
