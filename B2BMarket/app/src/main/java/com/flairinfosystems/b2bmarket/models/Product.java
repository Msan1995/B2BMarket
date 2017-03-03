package com.flairinfosystems.b2bmarket.models;

import java.io.Serializable;

/**
 * Created by user on 28-02-2017.
 */

public class Product implements Serializable {
    public int id;
    public String text, image;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
