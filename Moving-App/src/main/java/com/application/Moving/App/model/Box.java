package com.application.Moving.App.model;

public class Box {

    private int boxId;
    private String storageLocation;

    private int categoryId;
    private int userId;

    public Box() {};

    public Box(int boxId, String storageLocation, int categoryId, int userId) {
        this.boxId = boxId;
        this.storageLocation = storageLocation;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxId=" + boxId +
                ", storageLocation='" + storageLocation + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }
}
