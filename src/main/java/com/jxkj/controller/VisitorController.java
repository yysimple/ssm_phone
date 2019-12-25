package com.jxkj.controller;

import com.jxkj.model.Result;
import com.jxkj.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @RequestMapping("/showAll.do")
    public Result visitorList(){
        return visitorService.visitorList();
    }
}
