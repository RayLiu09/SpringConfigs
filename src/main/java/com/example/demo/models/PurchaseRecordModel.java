package com.example.demo.models;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Data
@Alias("purchaseRecord")
public class PurchaseRecordModel implements Serializable {
    private static final long serialVersionUID = -360816189433370174L;
    private Long id;
    private Long userId;
    private Long productId;
    private double price;
    private int quantity;
    private double totalPrice;
    private String note;
    private int version;
    private Date purchaseDate;
}
