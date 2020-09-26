package com.example.menu_app.model;

public class Category {

    private String CategoryName;
    private String CategoryImage, _id;

    public Category(String _id) {
        this._id = _id;
    }

    public Category(String categoryName, String categoryImage) {
        CategoryName = categoryName;
        CategoryImage = categoryImage;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
