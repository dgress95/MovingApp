package com.application.Moving.App.dao;

import com.application.Moving.App.model.Box;
import com.application.Moving.App.model.Item;

import java.util.List;

public interface ItemDao {
    /**
     * Get all items from the datastore ordered alphabetically by name.
     *
     * @return List of all Item objects.
     */
    List<Item> getAll();

    /**
     * Get an item from the datastore with the specified id.
     * If the id is not found, return null.
     *
     * @param itemId The id of the item to return.
     * @return The matching Item object, or null if the itemId is not found.
     */
    Item get(int itemId);

    /**
     * Get all items from the datastore for a specified category.
     * Items are ordered alphabetically by title.
     *
     * @param categoryId the id of the category
     * @return List of all the Item objects associated to the category, or an empty list if none are found.
     */
    List<Item> getByCategory(int categoryId);

    /**
     * Get all items from the datastore for a specified user.
     * Items are ordered alphabetically by title.
     *
     * @param userId the id of the user
     * @return List of all the Item objects associated to the user, or an empty list if none are found.
     */
    List<Item> getByUserId(int userId);

    /**
     * Get all items from the datastore for a specified box.
     * Items are ordered alphabetically by title.
     *
     * @param boxId the name of the box associated with the item
     * @return List of all the Item objects associated to the box, or an empty list if none are found.
     */
    List<Item> getByBoxId(int boxId);

    /**
     * Get all items from the datastore by search term.
     * Items are ordered alphabetically by title.
     *
     * @param location the location search term
     * @return List of all the Item objects associated to the search term, or an empty list if none are found.
     */
    List<Item> searchByLocation(String location);

    /**
     * Adds a new item to the datastore.
     *
     * @param newItem the Item object to add.
     * @return The added Item object with its new id and any default values filled in.
     */
    Item create(Item newItem);

    /**
     * Update an item in the datastore.
     *
     * @param modifiedItem The Item object to update.
     */
    Item update(Item modifiedItem);

    /**
     * Removes an item from the datastore.
     *
     * @param itemId The id of the Item to remove. If the id is not found, no error will occur.
     * @return count of items removed
     */
    int delete(int itemId);


}
