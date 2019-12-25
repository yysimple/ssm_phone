package com.jxkj.controller;

import com.jxkj.model.Result;
import com.jxkj.service.TroubleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/troubleinfo")
public class TroubleInfoController {

    @Autowired
    private TroubleInfoService troubleInfoService;

    @RequestMapping("/add.do")
    public Result addTroubleInfo(String trouble_code,
                                 String trouble_name, String trouble_remark){
        return troubleInfoService.addTroubleInfo(trouble_code, trouble_name, trouble_remark);
    }

    @RequestMapping("/show.do")
    public Result troubleList(){
        return troubleInfoService.troubleList();
    }

    @RequestMapping("/change.do")
    public Result changStatus(String id){
        return troubleInfoService.changStatus(id);
    }
}
