package com.application.Moving.App.dao;

import com.application.Moving.App.model.Box;

import java.util.List;

public interface BoxDao {
    List<Box> getAll();
    Box get(int boxId);

    List<Box> searchByCategory(int categoryId);
    List<Box> searchByUserId(int userId);
    List<Box> searchByLocation(String storageLocation);

    Box getByItemId(int itemId);

    Box create(Box newBox);

    Box update(Box box);

    void delete(int boxId);





}
