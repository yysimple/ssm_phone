package com.jxkj.service.impl;

import com.jxkj.dao.VisitorDao;
import com.jxkj.model.Result;
import com.jxkj.model.Visitor;
import com.jxkj.service.VisitorService;
import com.jxkj.utils.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorDao visitorDao;

    @Override
    public Result visitorList() {
        List<Visitor> visitors = visitorDao.visitorList();
        return ResultsUtil.success(visitors);
    }
}
