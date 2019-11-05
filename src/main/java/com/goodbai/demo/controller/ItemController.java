package com.goodbai.demo.controller;

import com.goodbai.demo.model.Item;
import com.goodbai.demo.model.ItemCategory;
import com.goodbai.demo.model.ReItem;
import com.goodbai.demo.model.ResObject;
import com.goodbai.demo.service.ItemCategoryService;
import com.goodbai.demo.service.ItemService;
import com.goodbai.demo.service.ReItemService;
import com.goodbai.demo.util.Constant;
import com.goodbai.demo.util.DateUtil;
import com.goodbai.demo.util.ExcelUtil;
import com.goodbai.demo.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:53
 */
@Controller
//商品管理表页面
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private ReItemService reItemService;

    List<Item> itemList;

    @RequestMapping("/user/itemManage_{pageCurrent}_{pageSize}_{pageCount}")
    //查询界面（商品管理）
    public String itemManage(Item item, Model model,
                             //当前页数
                             @PathVariable Integer pageCurrent,
                             //每页数量
                             @PathVariable Integer pageSize,
                             //总页数
                             @PathVariable Integer pageCount) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        //返回查询到的行数(搜索操作）
        int count = itemService.count(item);
        if (pageCount == 0)
            pageCount = count % pageSize == 0?(count / pageSize):((count / pageSize)+1);

        //设置每一页的初始值和最终值
        item.setStart((pageCurrent-1)*pageSize);
        item.setEnd(pageSize);


        //设置日期更新为字符串格式
        itemList = itemService.list(item);
        for (Item i : itemList) {
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }


        //设置商品分类开始和结束
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryService.list(itemCategory);
        //获取最高价格和最低价格
        Integer maxPrice = item.getMaxPrice();
        Integer minPrice = item.getMinPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);

        //设置动态的url
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice,pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);

        return "item/itemManage";
    }

    //导出数据到Excel
    @RequestMapping("/user/download1")
    public void postItemExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //导出excel
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("id", "商品id");
        fieldMap.put("title", "商品标题");
        fieldMap.put("sellPoint", "商品卖点");
        fieldMap.put("price", "商品价格");
        fieldMap.put("num", "库存数量");
        fieldMap.put("image", "商品图片");
        fieldMap.put("cid", "所属类目，叶子类目");
        fieldMap.put("status", "商品状态，1-正常，2-下架，3-删除");
        fieldMap.put("created", "创建时间");
        fieldMap.put("updated", "更新时间");
        String sheetName = "商品管理报表";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=ItemManage.xls");//默认Excel名称
        response.flushBuffer();
        OutputStream fos = response.getOutputStream();
        try {
            ExcelUtil.listToExcel(itemList, fieldMap, sheetName, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编辑商品功能
    String imageName = null;
    @GetMapping("/user/itemEdit")
    public String itemEditGet(Model model,Item item){
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryService.list(itemCategory);
        model.addAttribute("itemCategoryList",itemCategoryList);
        return "item/itemEdit";
    }

    @PostMapping("/user/itemEdit")
    public String itemEditPost(Model model,Item item){
        Date date = new Date();
        item.setUpdated(date);
        item.setCreated(date);
        item.setImage(imageName);
        item.setBarcode("");
        if (item.getId()!=0){
            itemService.update(item);
        }else {
            itemService.insert(item);
        }
        return "redirect:itemManage_0_0_0";
    }

    //删除商品信息
    @ResponseBody
    @PostMapping("/user/itemEditState")
    public ResObject<Object> itemEditState(Item item1) {
        Item item = itemService.findById(item1);
        ReItem reItem = new ReItem();
        reItem.setId(item.getId());
        reItem.setBarcode(item.getBarcode());
        reItem.setCid(item.getCid());
        reItem.setImage(item.getImage());
        reItem.setPrice(item.getPrice());
        reItem.setNum(item.getNum());
        reItem.setSellPoint(item.getSellPoint());
        reItem.setStatus(item.getStatus());
        reItem.setTitle(item.getTitle());
        reItem.setRecovered(new Date());
        reItemService.insert(reItem);
        itemService.delete(item1);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }


}
