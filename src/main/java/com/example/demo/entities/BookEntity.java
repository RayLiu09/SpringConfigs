package com.example.demo.entities;

import jakarta.persistence.*;

@Entity(name = "book")
@Table(name = "book")
public class BookEntity {
    // 标明ID，主键策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "author", nullable = false, length = 50)
    private String author;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "publish_date", nullable = false)
    private java.util.Date publishDate;
}
