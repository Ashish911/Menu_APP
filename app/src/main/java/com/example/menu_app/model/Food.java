package com.example.menu_app.model;

public class Food {

    private String _id;
    private String FoodName;
    private String FoodImage;
    private String Tags;
    private String Tags2;
    private String Description;
    private String Price;
    private String Categoryid;

    public Food(String _id) {
        this._id = _id;
    }

    public Food(String foodName, String foodImage, String tags, String tags2, String description, String price, String categoryid) {
        FoodName = foodName;
        FoodImage = foodImage;
        Tags = tags;
        Tags2 = tags2;
        Description = description;
        Price = price;
        Categoryid = categoryid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getTags2() {
        return Tags2;
    }

    public void setTags2(String tags2) {
        Tags2 = tags2;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(String categoryid) {
        Categoryid = categoryid;
    }
}
