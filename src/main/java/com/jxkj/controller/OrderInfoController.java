package com.jxkj.controller;

import com.jxkj.model.OrderInfo;
import com.jxkj.model.Result;
import com.jxkj.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderinfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping("/add.do")
    public Result addOrderInfo(String user_id, String user_name,
                               String plate, String trouble_code, String trouble_name,
                               String contact, String contact_way,
                               String remark){
        return orderInfoService.addOrderInfo(user_id, user_name, plate, trouble_code,trouble_name, contact, contact_way, remark);

    }

    @RequestMapping("/showAll.do")
    public Result findAllOrder(){
        return orderInfoService.findAllOrder();
    }

    @RequestMapping("/delete.do")
    public Result delOrderInfo(String id){
        return orderInfoService.delOrderInfo(id);
    }

    @RequestMapping("/change.do")
    public Result changStatus(String id){
        return orderInfoService.changStatus(id);
    }

    @RequestMapping("/show.do")
    public Result findOrder(String user_id){
        return orderInfoService.findOrder(user_id);
    }

    @RequestMapping("/searchorder.do")
    public Result searchOrderInfo(String type, String keywords){
        return orderInfoService.searchOrderInfo(type,keywords);
    }

    @RequestMapping("/deal.do")
    public Result findDealOrder(){
        return orderInfoService.findDealOrder();
    }

    @RequestMapping("/undeal.do")
    public Result findUndealOrder(){
        return orderInfoService.findUndealOrder();
    }

    @RequestMapping("/sort.do")
    public Result findBySort(){
        return orderInfoService.findBySort();
    }

}
