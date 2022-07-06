package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.District;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

public interface DistrictMapper {

    List<District> findDistrict(String parent);

    String findNameByCode(String code);
}
