package com.goodbai.demo.controller;

import com.goodbai.demo.model.Item;
import com.goodbai.demo.model.ReItem;
import com.goodbai.demo.model.ResObject;
import com.goodbai.demo.service.ItemService;
import com.goodbai.demo.service.ReItemService;
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

import java.util.Date;
import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:54
 */
@Controller
public class ReItemController {
    @Autowired
    private ReItemService reItemService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/user/recoverManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemManage(ReItem reItem, @PathVariable Integer pageCurrent,
                             @PathVariable Integer pageSize,
                             @PathVariable Integer pageCount,
                             Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;
        int rows = reItemService.selectAll().size();
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        reItem.setStart((pageCurrent - 1) * pageSize);
        reItem.setEnd(pageSize);
        List<ReItem> reItemList = reItemService.selectAll();
        for (ReItem r : reItemList) {
            r.setRecoveredStr(DateUtil.getDateStr(r.getRecovered()));
        }
        model.addAttribute("reItemList", reItemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("ReItem", reItem);
        return "item/recoverManage";
    }

    //编辑
    @ResponseBody
    @PostMapping("/user/reItemEditState")
    public ResObject<Object> reItemEditState(ReItem reItem) {
        ReItem reItem1 = reItemService.selectByPrimaryKey(reItem.getId());
        Item item = new Item();
        item.setId(reItem1.getId());
        item.setBarcode(reItem1.getBarcode());
        item.setCid(reItem1.getCid());
        item.setImage(reItem1.getImage());
        item.setPrice(reItem1.getPrice());
        item.setNum(reItem1.getNum());
        item.setSellPoint(reItem1.getSellPoint());
        item.setStatus(reItem1.getStatus());
        item.setTitle(reItem1.getTitle());
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemService.insert(item);
        reItemService.deleteByPrimaryKey(reItem.getId());
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }

    //删除
    @ResponseBody
    @PostMapping("/user/deleteItemEditState")
    public ResObject<Object> deleteItemEditState(ReItem reItem) {
        reItemService.deleteByPrimaryKey(reItem.getId());
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
}
