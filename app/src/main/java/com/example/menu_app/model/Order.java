package com.example.menu_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("_id")
    @Expose
    private String id;

    private String FoodName;
    private String FoodImage;
    private String Price;
    private String Quantity;
    private String FullName;
    private String TableName;
    private boolean isConfirmed;
    private boolean isCompleted;

    public Order(String foodName, String foodImage, String price, String quantity, String fullName, String tableName, boolean isConfirmed, boolean isCompleted) {
        FoodName = foodName;
        FoodImage = foodImage;
        Price = price;
        Quantity = quantity;
        FullName = fullName;
        TableName = tableName;
        this.isConfirmed = isConfirmed;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(String foodImage) {
        FoodImage = foodImage;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
