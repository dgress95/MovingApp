package com.application.Moving.App.model;

import jakarta.validation.constraints.NotBlank;

public class Item {
    @NotBlank( message = "The field 'name' is required.")
    private String name;
    private int quantity;
    private String description;

    private int boxId;
    private int itemId;
    private int userId;
    private int categoryId;

    public Item(){};

    public Item(String name, int quantity, String description, int boxId, int itemId, int userId, int categoryId) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.boxId = boxId;
        this.itemId = itemId;
        this.userId = userId;
        this.categoryId = categoryId;
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

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", boxId=" + boxId +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
