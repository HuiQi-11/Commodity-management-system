package com.goodbai.demo.controller;

import com.goodbai.demo.model.Item;
import com.goodbai.demo.model.ItemCategory;
import com.goodbai.demo.service.ItemCategoryService;
import com.goodbai.demo.service.ItemService;
import com.goodbai.demo.util.DateUtil;
import com.goodbai.demo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-04 16:11
 */
//库存管理
@Controller
public class StockManage {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/user/stockManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String stockManage(Item item, @PathVariable Integer pageCurrent,
                              @PathVariable Integer pageSize,
                              @PathVariable Integer pageCount,
                              Model model){
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;
        int rows = itemService.count(item);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        List<Item> itemList = itemService.lists(item);
        for (Item i : itemList) {
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryService.list(itemCategory);
        Integer minNum = item.getMinNum();
        Integer maxNum = item.getMaxNum();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("stockManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minNum" + minNum + "&maxNum" + maxNum, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);

        return "item/stockManage";

    }

}
