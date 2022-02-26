package com.geekbrains.geekspring.entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        StringBuilder sb = new StringBuilder();
        if (category_id != null) {
            Category parent = category_id.getParentId();
            Category sub = category_id;
            while (parent != null) {
                sb.append(sub.getTitle()).append(" <-");
                sub = parent;
                parent = parent.getParentId();
            }
            sb.append(" ").append(sub.getTitle());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Product [" + id + ", " + title + ", " + price + " руб., " + getCategory() + "]";
    }
}