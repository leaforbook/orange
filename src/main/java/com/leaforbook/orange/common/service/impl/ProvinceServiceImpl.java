package com.leaforbook.orange.common.service.impl;

import com.leaforbook.orange.common.controller.vo.ProvinceVO;
import com.leaforbook.orange.common.dao.mapper.CommonProvinceMapper;
import com.leaforbook.orange.common.dao.model.CommonProvince;
import com.leaforbook.orange.common.dao.model.CommonProvinceExample;
import com.leaforbook.orange.common.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private CommonProvinceMapper provinceMapper;

    @Override
    public List<ProvinceVO> query() {
        CommonProvinceExample example = new CommonProvinceExample();
        example.createCriteria();
        List<CommonProvince> list = provinceMapper.selectByExample(example);
        if(list!=null&&list.size()>0) {
            List<ProvinceVO> result = new ArrayList<>();
            for(CommonProvince commonProvince:list) {
                ProvinceVO vo = new ProvinceVO();
                vo.setProvinceId(commonProvince.getProvinceId());
                vo.setProvinceName(commonProvince.getProvinceName());
                vo.setLabel(commonProvince.getProvinceName());
                result.add(vo);
            }
            return result;
        }

        return null;
    }
}
