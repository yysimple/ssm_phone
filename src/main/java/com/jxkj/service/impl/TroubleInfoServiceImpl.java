package com.jxkj.service.impl;

import com.jxkj.dao.TroubleInfoDao;
import com.jxkj.model.Result;
import com.jxkj.model.TroubleInfo;
import com.jxkj.service.TroubleInfoService;
import com.jxkj.utils.IDUtil;
import com.jxkj.utils.ResultsUtil;
import com.jxkj.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  TroubleInfoServiceImpl implements TroubleInfoService {

    @Autowired
    private TroubleInfoDao troubleInfoDao;

    @Override
    public Result addTroubleInfo(String trouble_code, String trouble_name, String trouble_remark) {
        if(StringUtil.isEmpty(trouble_code)){
            return ResultsUtil.error(1,"请输入正确的错误码");
        }else if(StringUtil.isEmpty(trouble_name)){
            return ResultsUtil.error(1,"请输入正确的错误名");
        }else {
            TroubleInfo troubleInfo = new TroubleInfo();
            troubleInfo.setId(IDUtil.createId());
            troubleInfo.setTrouble_code(trouble_code);
            troubleInfo.setTrouble_name(trouble_name);
            troubleInfo.setTrouble_remark(trouble_remark);
            troubleInfo.setStatus(0);
            troubleInfoDao.addTroubleInfo(troubleInfo);
            return ResultsUtil.success();
        }
    }

    @Override
    public Result troubleList() {
        List<TroubleInfo> troubleInfos = troubleInfoDao.troubleList();
        return ResultsUtil.success(troubleInfos);
    }

    @Override
    public Result changStatus(String id) {
        TroubleInfo troubleInfo = troubleInfoDao.findTrouble(id);
        System.out.println(troubleInfo);
        if (troubleInfo.getStatus() == 0){
            troubleInfoDao.changStatus(id,1);
        }else {
            troubleInfoDao.changStatus(id,0);
        }

        return ResultsUtil.success();
    }
}
