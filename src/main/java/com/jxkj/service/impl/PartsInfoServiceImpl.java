package com.jxkj.service.impl;

import com.jxkj.dao.PartsInfoDao;
import com.jxkj.model.PartsInfo;
import com.jxkj.model.Result;
import com.jxkj.service.PartsInfoService;
import com.jxkj.utils.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartsInfoServiceImpl implements PartsInfoService {

    @Autowired
    private PartsInfoDao partsInfoDao;

    @Override
    public Result buyParts(String id) {
        partsInfoDao.buyParts(id);
        return ResultsUtil.success();
    }

    @Override
    public Result useParts(String id) {
        partsInfoDao.useParts(id);
        return ResultsUtil.success();
    }

    @Override
    public Result findAllParts() {
        List<PartsInfo> partsInfos = partsInfoDao.findAllParts();
        return ResultsUtil.success(partsInfos);
    }
}
