package com.jxkj.controller;

import com.jxkj.model.Result;
import com.jxkj.service.PartsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partsinfo")
public class PartsInfoController {

    @Autowired
    private PartsInfoService partsInfoService;

    @RequestMapping("/buy.do")
    public Result buyParts(String id){
        return partsInfoService.buyParts(id);
    }

    @RequestMapping("/use.do")
    public Result useParts(String id){
        return partsInfoService.useParts(id);
    }

    @RequestMapping("/show.do")
    public Result findAllParts(){
        return partsInfoService.findAllParts();
    }
}
