package com.example.demo.repositories;

import com.example.demo.models.PurchaseRecordModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseRecordRepository {
    int addPurchaseRecord(PurchaseRecordModel purchaseRecordModel);
}
