package com.example.demo.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class BookModel {

    @NotNull
    private int id;
    private String name;
    private String author;
    private double price;
    private Date publishDate;
}
