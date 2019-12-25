package com.jxkj.controller;

import com.jxkj.model.Result;
import com.jxkj.service.CarInfoService;
import com.jxkj.service.impl.CarInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/carinfo")
public class CarInfoController {

    @Autowired
    private CarInfoService carInfoService;


    @RequestMapping("/add.do")
    public Result addCarInfo(
            String user_id,
            String plate,
            String brand,
            String model,
            String color,
            String price,
            String date,
            String remark){
        return carInfoService.addCarInfo(user_id,plate,brand,model,color,price,date,remark);
    }

    @RequestMapping("/show.do")
    public Result showCarInfo(String user_id){
        return carInfoService.showCarInfo(user_id);
    }

    @RequestMapping("/showone.do")
    public Result showOneCar(String id){
        return carInfoService.showOneCar(id);
    }

    @RequestMapping("/showAll.do")
    public Result showAllCar(){
        return carInfoService.showAllCar();
    }

    @RequestMapping("/search.do")
    public Result searchCar(String type, String keywords){
        return carInfoService.searchCar(type,keywords);
    }

    @RequestMapping("/delete.do")
    public Result delCarInfo(String id){
        return carInfoService.delCarInfo(id);
    }

    @RequestMapping("/update.do")
    public Result updateCarInfo(
            String id,
            String plate,
            String brand,
            String model,
            String color,
            String price,
            String date,
            String remark){
        return carInfoService.updateCarInfo(id,plate,brand,model,color,price,date,remark);
    }
}
