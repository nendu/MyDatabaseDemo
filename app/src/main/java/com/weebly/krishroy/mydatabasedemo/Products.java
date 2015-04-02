package com.weebly.krishroy.mydatabasedemo;


public class Products {
    private int _id;
    private String productName;

    public Products(){

    }

    public Products(String productName) {
        this.productName = productName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String productName) {
        this.productName = productName;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return productName;
    }
}
