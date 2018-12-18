package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.controller.vo.ProvinceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
    List<ProvinceVO> query();
}
