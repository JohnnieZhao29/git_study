package com.zjydemo.mallstore.service.impl;

import com.zjydemo.mallstore.entity.District;
import com.zjydemo.mallstore.mapper.DistrictMapper;
import com.zjydemo.mallstore.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getDistrict(String parent) {
        List<District> list =  districtMapper.findDistrict(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
