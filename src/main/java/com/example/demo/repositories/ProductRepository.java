package com.example.demo.repositories;

import com.example.demo.models.ProductModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository {
    ProductModel findAll();
    ProductModel findById(Long id);

    void reduceStock(@Param("id") Long id, @Param("quantity") int quantity);
}
