package com.zjydemo.mallstore.service;

import com.zjydemo.mallstore.entity.District;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

public interface IDistrictService {

    /**
     * 根据父区域代号得到该区域下的所有属区，供用户选择
     * @param parent
     * @return
     */
    public List<District> getDistrict(String parent);

    public String getNameByCode(String code);


}
