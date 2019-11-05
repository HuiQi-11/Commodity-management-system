package com.goodbai.demo.model;

import lombok.Data;

@Data
public class Delivery {
	//tb_kuadidi 快递类

    private Integer id;

    private String deliveryName;

    private String deliveryCode;

    private String deliverySort;

    private String expressNo;

}