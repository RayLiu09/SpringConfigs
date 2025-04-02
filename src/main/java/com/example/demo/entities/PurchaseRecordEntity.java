package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "purchaseRecord")
@Table(name = "T_PURCHASE_RECORD")
public class PurchaseRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "int(12) COMMENT '主键'")
    private int id;

    @Column(name = "user_id", nullable = false, columnDefinition = "int(12) COMMENT '用户编号'")
    private int userId;

//    @Column(name = "product_id", nullable = false, columnDefinition = "int(12) COMMENT '商品编号'")
//    private int productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_purchase_record_product_id"))
    private ProductEntity product;

    @Column(name = "price", nullable = false, columnDefinition = "decimal(16,2) COMMENT '价格'")
    private double price;

    @Column(name = "quantity", nullable = false, columnDefinition = "int(12) COMMENT '数量'")
    private int quantity;

    @Column(name = "total_price", nullable = false, columnDefinition = "decimal(16,2) COMMENT '总价'")
    private double totalPrice;

    @Column(name = "purchase_date", nullable = false, columnDefinition = "datetime COMMENT '购买日期'")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date purchaseDate;

    @Column(name = "note", columnDefinition = "varchar(512) COMMENT '备注'")
    private String note;
}
