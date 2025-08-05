package com.example.demo.services.impl;

import com.example.demo.models.ProductModel;
import com.example.demo.models.PurchaseRecordModel;
import com.example.demo.mapper.ProductRepository;
import com.example.demo.mapper.PurchaseRecordRepository;
import com.example.demo.services.PurchaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;
    /**
     * 处理购买业务
     *
     * @param userId    用户ID
     * @param productId 产品编号
     * @param quantity  购买数量
     * @return 成功或失败
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean purchase(Long userId, Long productId, int quantity) {
        // 获取产品信息
        ProductModel productModel = productRepository.findById(productId);
        // 校验产品库存
        if (productModel.getStock() < quantity) {
            return false;
        }
        // 扣减产品库存
        productRepository.reduceStock(productId, quantity);

        // 初始化购买记录
        PurchaseRecordModel purchaseRecordModel = this.initPurchaseRecordModel(userId, productModel, quantity);
        // 保存购买记录
        purchaseRecordRepository.addPurchaseRecord(purchaseRecordModel);
        return true;
    }

    private PurchaseRecordModel initPurchaseRecordModel(Long userId, ProductModel productModel, int quantity) {
        PurchaseRecordModel purchaseRecordModel = new PurchaseRecordModel();
        purchaseRecordModel.setUserId(userId);
        purchaseRecordModel.setProductId(productModel.getId());
        purchaseRecordModel.setQuantity(quantity);
        purchaseRecordModel.setTotalPrice(quantity * productModel.getPrice());
        purchaseRecordModel.setNote("购买日志，时间：" + System.currentTimeMillis());
        return purchaseRecordModel;
    }
}
