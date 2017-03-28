package com.hill.customview.glidedemo;

/**
 * Created by hill on 17/3/28.
 */

public class Product {
    private String imgUrl; // 图片地址
    private String name; // 菜名
    private String price; // 菜价

    public Product(String imgUrl, String name, String price) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
