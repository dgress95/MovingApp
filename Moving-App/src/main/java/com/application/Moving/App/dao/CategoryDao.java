package com.application.Moving.App.dao;

import com.application.Moving.App.model.Category;

import java.util.List;

public interface CategoryDao {

    /**
     * Get all categories from the datastore ordered alphabetically by name.
     *
     * @return List of all Category objects.
     */
    List<Category> getAll();

    /**
     * Get a Category from the datastore with the specified id.
     * If the id is not found, return null.
     *
     * @param categoryId The id of the category to return.
     * @return The matching Category object, or null if the categoryId is not found.
     */
    Category get(int categoryId);

    /**
     * Adds a new category to the datastore.
     *
     * @param newCategory the Category object to add.
     * @return The added Category object with its new id and any default values filled in.
     */
    Category create(Category newCategory);

    /**
     * Update a category in the datastore.
     *
     * @param modifiedCategory The Category object to update.
     */
    Category update(Category modifiedCategory);

    /**
     * Removes a category from the datastore.
     *
     * @param categoryId The id of the Category to remove. If the id is not found, no error will occur.
     */
    void delete(int categoryId);


}
