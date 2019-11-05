package com.goodbai.demo.model;

import lombok.Data;

@Data
public class OrderItem extends BaseObject{
	//tb_order_item 下单的表
    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;
}