package com.jxkj.service.impl;

import com.jxkj.dao.OrderInfoDao;
import com.jxkj.model.OrderInfo;
import com.jxkj.model.Result;
import com.jxkj.service.OrderInfoService;
import com.jxkj.utils.DateUtil;
import com.jxkj.utils.IDUtil;
import com.jxkj.utils.ResultsUtil;
import com.jxkj.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public Result addOrderInfo(String user_id, String user_name, String plate,
                               String trouble_code, String trouble_name,
                               String contact, String contact_way, String remark) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(IDUtil.createId());
        orderInfo.setUser_id(user_id);
        orderInfo.setUser_name(user_name);
        orderInfo.setPlate(plate);
        orderInfo.setTrouble_code(trouble_code);
        orderInfo.setTrouble_name(trouble_name);
        orderInfo.setContact(contact);
        orderInfo.setContact_way(contact_way);
        orderInfo.setRemark(remark);
        orderInfo.setCreart_time(DateUtil.getTime());
        orderInfo.setLong_time(System.currentTimeMillis());
        orderInfo.setStatus(0);
        orderInfoDao.addOrderInfo(orderInfo);
        return ResultsUtil.success();
    }

    @Override
    public Result findAllOrder() {
        List<OrderInfo> orderInfos = orderInfoDao.findAllOrder();
        return ResultsUtil.success(orderInfos);
    }

    @Override
    public Result delOrderInfo(String id) {
        orderInfoDao.delOrderInfo(id);
        return ResultsUtil.success();
    }

    @Override
    public Result changStatus(String id) {
        orderInfoDao.changStatus(id,1);
        return ResultsUtil.success();
    }

    @Override
    public Result findOrder(String user_id) {
        List<OrderInfo> orderInfos = orderInfoDao.findOrder(user_id);
        return ResultsUtil.success(orderInfos);
    }

    @Override
    public Result searchOrderInfo(String type, String keywords) {
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isEmpty(type) && StringUtil.isEmpty(keywords)){
            return ResultsUtil.error(1,"请输入");
        }else if(StringUtil.isEmpty(type) && !StringUtil.isEmpty(keywords)){
            return ResultsUtil.error(1,"请选择分类");
        }else if(type.equals("1")){
            map.put("plate", "%"+keywords+"%");
        }else if(type.equals("2")){
            map.put("trouble_name", "%"+keywords+"%");
        }else if(type.equals("3")){
            map.put("user_name", "%"+keywords+"%");
        }else if(type.equals("4")){
            map.put("contact", "%"+keywords+"%");
        }
        List<OrderInfo> orderInfos = orderInfoDao.searchOrderInfo(map);
        return ResultsUtil.success(orderInfos);
    }

    @Override
    public Result findDealOrder() {
        List<OrderInfo> orderInfos=orderInfoDao.findByStatus(1);
        return ResultsUtil.success(orderInfos);
    }

    @Override
    public Result findUndealOrder() {
        List<OrderInfo> orderInfos=orderInfoDao.findByStatus(0);
        return ResultsUtil.success(orderInfos);
    }

    @Override
    public Result findBySort() {
        List<OrderInfo> orderInfos = orderInfoDao.findBySort();
        return ResultsUtil.success(orderInfos);
    }
}
