package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Generated;

@Entity(name = "product")
@Table(name = "T_PRODUCT")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "int(12)")
    @Comment("商品编号")
    private int id;

    @Column(name = "product_name", nullable = false, columnDefinition = "varchar(60)")
    @Comment("商品名称")
    private String productName;

    @Column(name = "stock", nullable = false, columnDefinition = "int(12)")
    @Comment("库存")
    private int stock;

    @Column(name = "price", nullable = false, columnDefinition = "decimal(16,2)")
    @Comment("价格")
    private double price;

    @Column(name = "version", nullable = false, columnDefinition = "int(10)")
    @Comment("版本号")
    @Generated
    @ColumnDefault("0")
    private int version;

    @Column(name = "note", nullable = true, columnDefinition = "varchar(256)")
    @Comment("备注")
    private String note;
}
