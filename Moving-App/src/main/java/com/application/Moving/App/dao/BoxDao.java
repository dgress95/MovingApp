package com.application.Moving.App.dao;

import com.application.Moving.App.model.Box;

import java.util.List;

public interface BoxDao {

    /**
     * Get all boxes from the datastore ordered alphabetically by name.
     *
     * @return List of all Box objects.
     */
    List<Box> getAll();

    /**
     * Get a box from the datastore with the specified id.
     * If the id is not found, return null.
     *
     * @param boxId The id of the box to return.
     * @return The matching Box object, or null if the boxId is not found.
     */
    Box get(int boxId);

    /**
     * Get all boxes from the datastore for a specified category.
     * Boxes are ordered alphabetically by title.
     *
     * @param categoryId the id of the category
     * @return List of all the Box objects associated to the category, or an empty list if none are found.
     */
    List<Box> getByCategory(int categoryId);

    /**
     * Get all boxes from the datastore for a specified user.
     * Boxes are ordered alphabetically by title.
     *
     * @param userId the id of the user
     * @return List of all the Box objects associated to the user, or an empty list if none are found.
     */
    List<Box> getByUserId(int userId);

    /**
     * Get all boxes from the datastore for a specified location.
     *
     * @param locationSearch the name of the storage location
     * @return List of all the Box objects associated to the location, or an empty list if none are found.
     */
    List<Box> searchByLocation(String locationSearch);

    /**
     * Get the box from the datastore associated with the specified item.
     *
     * @param itemId the id of the item
     * @return The matching Box object, or null if the itemId is not found.
     */
    Box getByItemId(int itemId);

    /**
     * Adds a new box to the datastore.
     *
     * @param newBox the Box object to add.
     * @return The added Box object with its new id and any default values filled in.
     */
    Box create(Box newBox, int categoryId);

    /**
     * Update a box in the datastore.
     * Only the storage location and category may be updated. Other values are ignored.
     *
     * @param modifiedBox The box object to update.
     */
    Box update(Box modifiedBox);

    /**
     * Removes a box from the datastore.
     *
     * @param boxId The id of the Box to remove. If the id is not found, no error will occur.
     * @return count of boxes removed
     */
    int delete(int boxId);





}
