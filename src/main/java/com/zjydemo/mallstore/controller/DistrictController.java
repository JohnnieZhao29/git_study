package com.zjydemo.mallstore.controller;

import com.zjydemo.mallstore.entity.District;
import com.zjydemo.mallstore.service.IDistrictService;
import com.zjydemo.mallstore.service.impl.DistrictServiceImpl;
import com.zjydemo.mallstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getDistrict(String parent){
        List<District> district = districtService.getDistrict(parent);
        return new JsonResult<>(OK, district);
    }

}
