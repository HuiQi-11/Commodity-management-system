package com.goodbai.demo.controller;

import com.goodbai.demo.mapper.OrderShippingMapper;
import com.goodbai.demo.model.*;
import com.goodbai.demo.service.OrderShippingService;
import com.goodbai.demo.util.Constant;
import com.goodbai.demo.util.DateUtil;

import com.goodbai.demo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 物流管理
 */
@Controller
public class ShippingController {

    @Autowired
    private OrderShippingService orderShippingService;

    @RequestMapping("/user/shippingManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String orderManage(OrderShipping orderShipping, @PathVariable Integer pageCurrent,
                              @PathVariable Integer pageSize,
                              @PathVariable Integer pageCount,
                              Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;
        int rows = orderShippingService.selectAll().size();
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        orderShipping.setStart((pageCurrent - 1) * pageSize);
        orderShipping.setEnd(pageSize);
        List<OrderShipping> orderShippingList = orderShippingService.selectAll();
        for (OrderShipping orderShipping1 : orderShippingList){
            orderShipping1.setCreatedStr(DateUtil.getDateStr(orderShipping1.getCreated()));
            orderShipping1.setUpdatedStr(DateUtil.getDateStr(orderShipping1.getUpdated()));
        }
        model.addAttribute("orderShippingList", orderShippingList);
        String pageHTML = PageUtil.getPageContent("shippingManage_{pageCurrent}_{pageSize}_{pageCount}", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("orderShipping", orderShipping);
        return "order/shippingManage";
    }

    @ResponseBody
    @PostMapping("/user/shippingEditState")
    public ResObject<Object> shippingEditState(OrderShipping orderShipping){
        orderShippingService.deleteByPrimaryKey(orderShipping.getOrderId());
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
}
