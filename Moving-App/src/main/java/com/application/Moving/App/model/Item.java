package com.application.Moving.App.model;

import jakarta.validation.constraints.NotBlank;

public class Item {
    @NotBlank( message = "The field 'name' is required.")
    private String name;
    private int quantity;
    private String description;
    private int itemId;
    @NotBlank ( message = "Cart Items must include a userId.")
    private int userId;

    private int categoryId;

    private String storageLocation;

    public Item(){};

    public Item(int itemId, int userId, int categoryId, String name, int quantity, String storageLocation, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.itemId = itemId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.storageLocation = storageLocation;
    }

    public Item(int itemId, int userId, String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.itemId = itemId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", itemId=" + itemId +
                ", userId=" + userId +
                '}';
    }
}
