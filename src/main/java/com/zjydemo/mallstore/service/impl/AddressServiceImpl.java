package com.zjydemo.mallstore.service.impl;

import com.zjydemo.mallstore.entity.Address;
import com.zjydemo.mallstore.mapper.AddressMapper;
import com.zjydemo.mallstore.service.IAddressService;
import com.zjydemo.mallstore.service.IDistrictService;
import com.zjydemo.mallstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

@Service
public class AddressServiceImpl implements IAddressService {

    /**
     * 服务层的实现，要实现服务层的接口，并且成员变量加载对应的DAO层接口。
     */
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }

    @Override
    public void addAddress(Address address, String name, Integer uid) {
        Integer count = addressMapper.count(uid);
        if(count > maxCount){
            throw new AddressNumbersOutOfException("地址数量达到上限");
        }

        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        //补全日志
        address.setUid(uid);

        address.setIsDefault(count == 0 ? 1 : 0);
        address.setCreatedUser(name);
        address.setCreatedTime(new Date());
        address.setModifiedUser(name);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if(rows != 1){
            throw new InsertException("添加地址失败");
        }
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        List<Address> res = addressMapper.findByUid(uid);
        for (Address address : res) {
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return  res;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw new AddressNotFoundException("没有这个地址");
        }
        if (!uid.equals(address.getUid())){
            throw new AccessDeniedException("非法访问");
        }
        addressMapper.clearDefault(uid);
        Integer rows = addressMapper.setDefault(aid, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新数据异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw new AddressNotFoundException("地址不存在");
        }
        if (!uid.equals(address.getUid())){
            throw new AccessDeniedException("非法访问");
        }


        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1){
            throw new DeleteException("删除地址出错");
        }

        if(address.getIsDefault() == 0){
            return;
        }

        Integer count = addressMapper.count(uid);
        if(count == 0){
            return;
        }

        Address lastModified = addressMapper.findLastModified(uid);
        Integer res = addressMapper.setDefault(lastModified.getAid(),
                username, new Date());
        if(res != 1){
            throw new UpdateException("更新地址出错");
        }

    }

}
