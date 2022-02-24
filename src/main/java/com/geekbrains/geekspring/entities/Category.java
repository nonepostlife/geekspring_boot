package com.geekbrains.geekspring.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "category_id", fetch = FetchType.EAGER)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category getParentId() {
        return parentId;
    }

    public void setParentId(Category parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            if (i == products.size() - 1) {
                sb.append(products.get(i).getTitle());
                break;
            }
            sb.append(products.get(i).getTitle()).append(", ");
        }
        return sb.toString();
    }
}