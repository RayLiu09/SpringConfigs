package com.example.demo.repositories;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entities.ProductEntity;
import com.example.demo.models.ProductModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository extends BaseMapper<ProductEntity> {
//    @Select("SELECT * FROM product")
    ProductModel findAll();
    ProductModel findById(Long id);

    void reduceStock(@Param("id") Long id, @Param("quantity") int quantity);
}
