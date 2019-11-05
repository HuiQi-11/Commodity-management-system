package com.goodbai.demo.controller;

import com.goodbai.demo.model.ItemCategory;
import com.goodbai.demo.model.ResObject;
import com.goodbai.demo.service.ItemCategoryService;
import com.goodbai.demo.util.Constant;
import com.goodbai.demo.util.DateUtil;
import com.goodbai.demo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:54
 */
//商品分类
//查询功能
@Controller
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/user/itemCategoryManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemCategoryManage(ItemCategory itemCategory,
                                     @PathVariable Integer pageCurrent,
                                     @PathVariable Integer pageSize,
                                     @PathVariable Integer pageCount,
                                     Model model) {
        //判断
        if (pageSize == 0) pageSize = 20;
        if (pageCurrent == 0) pageCurrent = 1;
        int rows = itemCategoryService.count(itemCategory);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        itemCategory.setStart((pageCurrent - 1) * pageSize);
        itemCategory.setEnd(pageSize);
        List<ItemCategory> list = itemCategoryService.list(itemCategory);
        for (ItemCategory i : list) {
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
        }
        model.addAttribute("list", list);
        String pageHTML = PageUtil.getPageContent("itemCategoryManage_{pageCurrent}_{pageSize}_{pageCount}?name=" + itemCategory.getName(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("itemCategory", itemCategory);
        return "item/itemCategoryManage";
    }

    //商品编辑
    @GetMapping("/user/itemCategoryEdit")
    public String itemCategoryEditGet(Model model, ItemCategory itemCategory) {
        if (itemCategory.getId() != 0) {
            ItemCategory itemCategory0 = itemCategoryService.findById(itemCategory);
            model.addAttribute("itemCategory", itemCategory0);
        }
        return "item/itemCategoryEdit";
    }

    @PostMapping("/user/itemCategoryEdit")
    public String newsCategoryEditPost(Model model, ItemCategory itemCategory, @RequestParam MultipartFile[] imageFile, HttpSession httpSession) {
        //根据时间和随机数生成id
        Date date = new Date();
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (999 - 100 + 1)) + 10;// 获取3位随机数
        itemCategory.setCreated(date);
        itemCategory.setUpdated(date);
        List<ItemCategory> list = itemCategoryService.list1();
        String name = itemCategory.getName();
        for (ItemCategory i : list) {
            if (i.getName().equals(name))
                return "redirect:itemCategoryManage_0_0_0";
        }
        if (itemCategory.getId() != 0) {
            itemCategoryService.update(itemCategory);
        } else {
            itemCategory.setId(rannum);
            itemCategoryService.insert(itemCategory);
        }
        return "redirect:itemCategoryManage_0_0_0";
    }

    //删除
    @ResponseBody
    @PostMapping("/user/itemCategoryEditState")
    public ResObject<Object> itemCategoryEditState(ItemCategory itemCategory) {
        itemCategoryService.delete(itemCategory);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
}
