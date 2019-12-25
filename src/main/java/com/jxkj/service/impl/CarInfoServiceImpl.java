package com.jxkj.service.impl;

import com.jxkj.dao.CarInfoDao;
import com.jxkj.dao.UserDao;
import com.jxkj.model.CarInfo;
import com.jxkj.model.Result;
import com.jxkj.model.User;
import com.jxkj.service.CarInfoService;
import com.jxkj.utils.IDUtil;
import com.jxkj.utils.ResultsUtil;
import com.jxkj.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private CarInfoDao carInfoDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Result addCarInfo(String user_id, String plate,
                             String brand, String model, String color,
                             String price, String date, String remark) {
        if(StringUtil.isEmpty(plate)){
            return ResultsUtil.error(1,"请输入车牌号");
        }

        CarInfo carInfo = new CarInfo();
        carInfo.setId(IDUtil.createId());
        User user = userDao.findUserById(user_id);
        carInfo.setUser_id(user_id);
        carInfo.setUser_name(user.getName());
        carInfo.setPlate(plate);
        carInfo.setBrand(brand);
        carInfo.setModel(model);
        carInfo.setColor(color);
        carInfo.setPrice(price);
        carInfo.setDate(date);
        carInfo.setRemark(remark);

        carInfoDao.addCarInfo(carInfo);
        return ResultsUtil.success();
    }

    @Override
    public Result showCarInfo(String user_id) {
        List<CarInfo> carInfos = carInfoDao.showCarInfo(user_id);
        return ResultsUtil.success(carInfos);
    }

    @Override
    public Result showOneCar(String id) {
        CarInfo carInfo = carInfoDao.showOneCar(id);
        return ResultsUtil.success(carInfo);
    }

    @Override
    public Result showAllCar() {
        List<CarInfo> carInfos = carInfoDao.showAllCar();
        return ResultsUtil.success(carInfos);
    }

    @Override
    public Result searchCar(String type, String keywords) {
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(type)){
            return ResultsUtil.error(1,"请选择分类");
        }else if(StringUtil.isEmpty(keywords)){
            return ResultsUtil.error(1,"请输入关键字");
        }else if(type.equals("1")){
            map.put("plate","%" + keywords + "%");
        }else if(type.equals("2")){
            map.put("brand","%" + keywords + "%");
        }else if(type.equals("3")){
            map.put("user_name","%" + keywords + "%");
        }

        List<CarInfo> carInfos = carInfoDao.searchCarInfo(map);
        return ResultsUtil.success(carInfos);
    }

    @Override
    public Result delCarInfo(String id) {
        carInfoDao.delCarInfo(id);
        return ResultsUtil.success();
    }

    @Override
    public Result updateCarInfo(String id, String plate, String brand,
                                String model, String color, String price, String date, String remark) {
        if (StringUtil.isEmpty(plate)){
            return ResultsUtil.error(1,"车牌号不能为空");
        }
        CarInfo ci=new CarInfo();
        ci.setId(id);
        ci.setPlate(plate);
        ci.setBrand(brand);
        ci.setDate(date);
        ci.setColor(color);
        ci.setModel(model);
        ci.setPrice(price);
        ci.setRemark(remark);
        carInfoDao.updateCarInfo(ci);
        return ResultsUtil.success();
    }
}
